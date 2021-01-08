// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.command.impl;

public class CommandPrefix
{
    String prefix;
    
    public CommandPrefix(final String _default) {
        this.prefix = _default;
    }
    
    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
}
