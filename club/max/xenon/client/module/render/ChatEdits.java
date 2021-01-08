// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.text.TextComponentString;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Date;
import java.text.SimpleDateFormat;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class ChatEdits extends Module
{
    Value timestamps;
    
    public ChatEdits() {
        super("Chat Modifications", "Modifies the look of the chat", "chatmods", Category.Render);
        this.timestamps = this.initValue("Timestamps", "Puts a timestamp on messages", "timestamps", true);
    }
    
    @SubscribeEvent
    public void onChat(final ClientChatReceivedEvent event) {
        if (this.timestamps.getValue()) {
            final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            final Date date = new Date();
            final TextComponentString time = new TextComponentString(ChatFormatting.GRAY + "<" + formatter.format(date) + "> " + ChatFormatting.RESET);
            event.setMessage(time.appendSibling(event.getMessage()));
        }
    }
}
