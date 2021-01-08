// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import club.max.xenon.XenonClient;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class Viewmodel extends Module
{
    public Value FOV;
    Value items;
    
    public Viewmodel() {
        super("Viewmodel", "The viewmodel changer", "viewmodel", Category.Render);
        this.FOV = this.initValue("FOV", "The FOV to set to", "FOV", 130, 110, 150);
        this.items = this.initValue("Items", "The kind of fov to use", "items", true);
    }
    
    @Override
    public void onUpdate() {
        if (!XenonClient.moduleManager.isModuleEnabled(new Zoom()) && this.mc.gameSettings.fovSetting != this.FOV.getValue()) {
            this.mc.gameSettings.fovSetting = this.FOV.getValue();
        }
    }
    
    @SubscribeEvent
    public void onFov(final EntityViewRenderEvent.FOVModifier event) {
        if (this.items.getValue() && !XenonClient.getModuleManager().isModuleEnabled(new Zoom())) {
            event.setFOV((float)this.FOV.getValue());
        }
    }
}
