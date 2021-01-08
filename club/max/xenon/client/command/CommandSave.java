// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.command;

import club.max.xenon.api.util.chat.ChatUtil;
import club.max.xenon.XenonClient;
import club.max.xenon.api.command.Command;

public class CommandSave extends Command
{
    public CommandSave() {
        super("Save", new String[] { "s", "save" }, "Saves the config.");
    }
    
    @Override
    public String setSyntax() {
        return "s/save";
    }
    
    @Override
    public void onCommand(final String[] args) {
        XenonClient.configManager.saveModules();
        ChatUtil.print("Saved Config");
    }
}
