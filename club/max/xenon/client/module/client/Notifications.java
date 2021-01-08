// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.client;

import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class Notifications extends Module
{
    public Value x;
    public Value y;
    
    public Notifications() {
        super("Notifications", "The notification system for the client", "notifications", Category.Client);
        this.x = this.initValue("X", "The X coordinates for notifications", "x", 300, 0, 1000);
        this.y = this.initValue("Y", "The Y coordinates for notifications", "y", 300, 0, 1000);
    }
    
    @Override
    public void onFrameRender() {
        super.onFrameRender();
    }
}
