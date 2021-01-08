// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import club.max.xenon.api.util.chat.ChatUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import club.max.xenon.XenonClient;
import java.util.Date;
import java.text.SimpleDateFormat;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.util.misc.WriteFile;
import java.io.File;
import club.max.xenon.api.module.Module;

public class ChatLogger extends Module
{
    public File txt;
    File folder;
    WriteFile out;
    Value onlyCoords;
    
    public ChatLogger() {
        super("Chat Logger", "Logs chat into a file", "chatlogger", Category.Misc);
        this.onlyCoords = this.initValue("Only coords", "Only puts coords in the logs", "onlycoords", true);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
            final Date date = new Date();
            this.folder = new File(XenonClient.configManager.getMain() + File.separator + "logs");
            if (!this.folder.exists()) {
                this.folder.mkdirs();
            }
            String fileName = "";
            if (this.mc.isIntegratedServerRunning()) {
                fileName = formatter.format(date) + "Singleplayer.chatlogs";
            }
            else {
                fileName = formatter.format(date) + this.mc.getCurrentServerData().serverIP + ".chatlogs";
            }
            (this.txt = new File(this.folder + File.separator + fileName)).createNewFile();
            this.out = new WriteFile(this.txt);
        }
        catch (Exception e) {
            ChatUtil.print(ChatFormatting.RED + "" + ChatFormatting.UNDERLINE + "ERROR");
            e.printStackTrace();
        }
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        if (this.out != null) {
            try {
                this.out.close();
            }
            catch (Exception e) {
                ChatUtil.print(ChatFormatting.RED + "" + ChatFormatting.UNDERLINE + "ERROR");
                e.printStackTrace();
            }
        }
    }
    
    @SubscribeEvent
    public void onChatRecieved(final ClientChatReceivedEvent event) {
        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        final Date date = new Date();
        try {
            String message = "";
            if (!XenonClient.moduleManager.isModuleEnabled(XenonClient.moduleManager.getByTag("chatmods"))) {
                message = "<" + formatter.format(date) + "> " + event.getMessage().getUnformattedText();
            }
            else {
                message = event.getMessage().getUnformattedText();
            }
            if (this.onlyCoords.getValue()) {
                if (message.matches(".*\\d.*")) {
                    this.out.println(message);
                }
            }
            else {
                this.out.println(message);
            }
        }
        catch (Exception e) {
            ChatUtil.print(ChatFormatting.RED + "" + ChatFormatting.UNDERLINE + "ERROR");
            e.printStackTrace();
        }
    }
    
    @Override
    public void onStartUp() {
        super.onStartUp();
        this.toggled = false;
    }
}
