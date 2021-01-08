// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.command.management;

import java.util.Iterator;
import club.max.xenon.api.util.chat.ChatUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import club.max.xenon.client.command.CommandSet;
import club.max.xenon.client.command.CommandLoad;
import club.max.xenon.client.command.CommandSave;
import club.max.xenon.client.command.CommandBind;
import club.max.xenon.client.command.CommandToggle;
import club.max.xenon.client.command.CommandHelp;
import club.max.xenon.api.command.impl.CommandPrefix;
import club.max.xenon.api.command.Command;
import java.util.ArrayList;

public class CommandManager
{
    public static CommandManager INSTANCE;
    private ArrayList<Command> commandList;
    private CommandPrefix commandPrefix;
    
    public CommandManager() {
        CommandManager.INSTANCE = this;
        this.commandList = new ArrayList<Command>();
        this.commandPrefix = new CommandPrefix(".");
    }
    
    public void initCommands() {
        this.registry(new CommandHelp());
        this.registry(new club.max.xenon.client.command.CommandPrefix());
        this.registry(new CommandToggle());
        this.registry(new CommandBind());
        this.registry(new CommandSave());
        this.registry(new CommandLoad());
        this.registry(new CommandSet());
    }
    
    public void registry(final Command command) {
        this.commandList.add(command);
    }
    
    public void setCommandList(final ArrayList<Command> commandList) {
        this.commandList = commandList;
    }
    
    public ArrayList<Command> getCommandList() {
        return this.commandList;
    }
    
    public static CommandPrefix getCommandPrefix() {
        return CommandManager.INSTANCE.commandPrefix;
    }
    
    public void onCommand(final String[] args) {
        final Command command = get(args[0]);
        if (command == null) {
            ChatUtil.print(ChatFormatting.RED + "Unknown command.");
        }
        else {
            command.onCommand(args);
        }
    }
    
    public static Command get(final Class clazz) {
        for (final Command commands : CommandManager.INSTANCE.getCommandList()) {
            if (commands.getClass() == clazz) {
                return commands;
            }
        }
        return null;
    }
    
    public static Command get(final String _alias) {
        for (final Command commands : CommandManager.INSTANCE.getCommandList()) {
            for (final String alias : commands.getAlias()) {
                if (alias.equalsIgnoreCase(_alias)) {
                    return commands;
                }
            }
        }
        return null;
    }
    
    public String[] split(final String message) {
        return message.replaceFirst(this.commandPrefix.getPrefix(), "").split(" ");
    }
}
