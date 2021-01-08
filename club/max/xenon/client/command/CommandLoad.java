// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.command;

import club.max.xenon.api.util.chat.ChatUtil;
import club.max.xenon.XenonClient;
import club.max.xenon.api.command.Command;

public class CommandLoad extends Command
{
    public CommandLoad() {
        super("Load", new String[] { "load", "l" }, "Loads your config");
    }
    
    @Override
    public String setSyntax() {
        return "l/load";
    }
    
    @Override
    public void onCommand(final String[] args) {
        super.onCommand(args);
        XenonClient.configManager.loadModules();
        ChatUtil.print("Loaded config");
    }
}
