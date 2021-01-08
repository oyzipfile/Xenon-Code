// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.command;

import club.max.xenon.api.util.chat.ChatUtil;
import com.mojang.realmsclient.gui.ChatFormatting;

public class Command
{
    private String name;
    private String[] alias;
    private String description;
    
    public Command(final String name, final String[] alias, final String description) {
        this.name = name;
        this.alias = alias;
        this.description = description;
    }
    
    public void setAlias(final String[] alias) {
        this.alias = alias;
    }
    
    public String[] getAlias() {
        return this.alias;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void splash() {
        ChatUtil.print(ChatFormatting.RED + this.setSyntax());
    }
    
    public void splash(final String splash) {
        ChatUtil.print(splash);
    }
    
    public String setSyntax() {
        return null;
    }
    
    public void onCommand(final String[] args) {
    }
}
