// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.command;

import club.max.xenon.api.module.Module;
import club.max.xenon.api.util.chat.ChatUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import club.max.xenon.api.module.management.ModuleManager;
import club.max.xenon.XenonClient;
import club.max.xenon.api.command.Command;

public class CommandToggle extends Command
{
    public CommandToggle() {
        super("Toggle", new String[] { "t", "toggle" }, "Toggle modules.");
    }
    
    @Override
    public String setSyntax() {
        return "t/toggle <module>";
    }
    
    @Override
    public void onCommand(final String[] args) {
        String tag = null;
        if (args.length > 1) {
            tag = args[1];
        }
        if (args.length > 2 || tag == null) {
            this.splash();
            return;
        }
        final ModuleManager moduleManager = XenonClient.moduleManager;
        final Module module = ModuleManager.get(tag);
        if (module == null) {
            ChatUtil.print(ChatFormatting.RED + "Unknown module.");
            return;
        }
        module.toggle();
    }
}
