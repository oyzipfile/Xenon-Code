// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.render;

import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class Zoom extends Module
{
    Value zoomAmount;
    int oldFOV;
    
    public Zoom() {
        super("Zoom", "A Module like optifine zoom but better", "Zoom", Category.Render);
        this.zoomAmount = this.initValue("Zoom Amount", "Amount to zoom by", "zoomAmount", 10, 2, 50);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.oldFOV = (int)this.mc.gameSettings.fovSetting;
    }
    
    @Override
    public void onUpdate() {
        if (this.mc.gameSettings.fovSetting != this.zoomAmount.getValue()) {
            this.mc.gameSettings.fovSetting = this.zoomAmount.getValue();
        }
        super.onUpdate();
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.gameSettings.fovSetting = (float)this.oldFOV;
    }
}
