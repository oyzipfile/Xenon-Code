// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.client;

import club.max.xenon.api.module.impl.Category;
import java.util.ArrayList;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class Colors extends Module
{
    public static Value Color;
    public ArrayList<String> colorsChat;
    
    public Colors() {
        super("Colors", "The Clients color scheme", "colors", Category.Client);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.enable();
    }
}
