// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import club.max.xenon.api.module.management.ModuleManager;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.client.module.client.RainbowModule;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class FogColor extends Module
{
    public Value red;
    public Value green;
    public Value blue;
    public Value rainbow;
    public float floatRed;
    public float floatGreen;
    public float floatBlue;
    RainbowModule rainbowModule;
    
    public FogColor() {
        super("Fog Color", "Allows you to change the color of fog and sky", "fogColor", Category.Render);
        this.red = this.initValue("Red", "Red color of the sky", "red", 255, 0, 255);
        this.green = this.initValue("Green", "Green color of the sky", "green", 255, 0, 255);
        this.blue = this.initValue("Blue", "Blue color of the sky", "blue", 255, 0, 255);
        this.rainbow = this.initValue("Rainbow", "Rainbow for the sky", "rainbow", true);
        this.rainbowModule = (RainbowModule)ModuleManager.get("rainbow");
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.rainbow.getValue()) {
            final Value red = this.red;
            final RainbowModule rainbowModule = this.rainbowModule;
            red.setValue(RainbowModule.getColor().getRed());
            final Value green = this.green;
            final RainbowModule rainbowModule2 = this.rainbowModule;
            green.setValue(RainbowModule.getColor().getGreen());
            final Value blue = this.blue;
            final RainbowModule rainbowModule3 = this.rainbowModule;
            blue.setValue(RainbowModule.getColor().getBlue());
        }
        this.floatRed = this.red.getValue();
        this.floatGreen = this.green.getValue();
        this.floatBlue = this.blue.getValue();
    }
    
    @SubscribeEvent
    public void fogColor(final EntityViewRenderEvent.FogColors event) {
        event.setRed(this.floatRed / 270.0f);
        event.setGreen(this.floatGreen / 270.0f);
        event.setBlue(this.floatBlue / 270.0f);
    }
    
    @SubscribeEvent
    public void fogDensity(final EntityViewRenderEvent.FogDensity event) {
        event.setDensity(0.0f);
        event.setCanceled(true);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
    }
}
