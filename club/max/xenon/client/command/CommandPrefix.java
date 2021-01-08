// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.command;

import club.max.xenon.api.util.chat.ChatUtil;
import club.max.xenon.XenonClient;
import club.max.xenon.api.command.management.CommandManager;
import club.max.xenon.api.command.Command;

public class CommandPrefix extends Command
{
    public CommandPrefix() {
        super("Prefix", new String[] { "prefix", "p" }, "Set new prefix to chat command.");
    }
    
    @Override
    public String setSyntax() {
        return "prefix <char>";
    }
    
    @Override
    public void onCommand(final String[] args) {
        String _char = null;
        if (args.length > 1) {
            _char = args[1];
        }
        if (args.length > 2 || _char == null) {
            this.splash();
            return;
        }
        CommandManager.getCommandPrefix().setPrefix(_char);
        XenonClient.configManager.savePrefix();
        ChatUtil.print("Chat prefix has been update to '" + CommandManager.getCommandPrefix().getPrefix() + "'.");
    }
}
