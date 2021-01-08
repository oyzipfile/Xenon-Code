// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.command;

import java.util.Iterator;
import club.max.xenon.XenonClient;
import club.max.xenon.api.util.chat.ChatUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import club.max.xenon.api.command.Command;

public class CommandHelp extends Command
{
    public CommandHelp() {
        super("Help", new String[] { "help" }, "Displays all the commands");
    }
    
    @Override
    public String setSyntax() {
        return "help";
    }
    
    @Override
    public void onCommand(final String[] args) {
        ChatUtil.print(ChatFormatting.BLUE + "Here is a list of available commands");
        ChatUtil.print("------------------------------------");
        for (final Command command : XenonClient.getCommandManager().getCommandList()) {
            ChatUtil.print(command.getName() + " - " + command.getDescription());
        }
    }
}
