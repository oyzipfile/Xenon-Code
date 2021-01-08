// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.client;

import club.max.xenon.api.module.impl.Category;
import java.awt.Color;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class RainbowModule extends Module
{
    static Value Sat;
    static Value Bri;
    static float floatSat;
    static float floatBri;
    static double hue;
    static Color color;
    
    public RainbowModule() {
        super("Rainbow", "The module that handles the rainbow", "rainbow", Category.Client);
        this.setToggled(true);
        RainbowModule.Sat = this.initValue("Saturation", "The saturation for the rainbow", "saturation", 255, 0, 255);
        RainbowModule.Bri = this.initValue("Brightness", "The brightness for the rainbow", "brightness", 255, 0, 255);
        RainbowModule.hue = 0.0;
        RainbowModule.color = new Color(0, 0, 0, 0);
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.enable();
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onUpdateConstant() {
        super.onUpdateConstant();
        if (!this.toggled) {
            this.toggled = true;
        }
        RainbowModule.hue += 2.0;
        RainbowModule.color = new Color(rainbow());
        if (RainbowModule.hue >= 360.0) {
            RainbowModule.hue = 0.0;
        }
    }
    
    @Override
    public void onFrameRender() {
        super.onFrameRender();
    }
    
    public static int rainbow() {
        RainbowModule.floatSat = RainbowModule.Sat.getValue();
        RainbowModule.floatBri = RainbowModule.Bri.getValue();
        return Color.getHSBColor((float)(RainbowModule.hue / 360.0), RainbowModule.floatSat / 255.0f, RainbowModule.floatBri / 255.0f).getRGB();
    }
    
    public static Color getColor() {
        return RainbowModule.color;
    }
}
