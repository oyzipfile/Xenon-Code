// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.command;

import club.max.xenon.api.module.Module;
import org.lwjgl.input.Keyboard;
import club.max.xenon.api.util.chat.ChatUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import club.max.xenon.api.module.management.ModuleManager;
import club.max.xenon.XenonClient;
import club.max.xenon.api.command.Command;

public class CommandBind extends Command
{
    public CommandBind() {
        super("Bind", new String[] { "bind", "b" }, "Binds a module");
    }
    
    @Override
    public String setSyntax() {
        return "bind <module> <bind>";
    }
    
    @Override
    public void onCommand(final String[] args) {
        String tag = null;
        String keyTyped = null;
        if (args.length > 2) {
            tag = args[1];
            keyTyped = args[2].toUpperCase();
        }
        if (args.length > 3 || tag == null || keyTyped == null) {
            this.splash();
            return;
        }
        XenonClient.getModuleManager();
        final Module module = ModuleManager.get(tag);
        if (module == null) {
            ChatUtil.print(ChatFormatting.RED + "Unknown module.");
            return;
        }
        module.setKey(Keyboard.getKeyIndex(keyTyped));
        ChatUtil.print("Set " + module.getName() + " bind to " + Keyboard.getKeyName(module.getKey()));
    }
}
