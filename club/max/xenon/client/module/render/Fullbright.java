// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.render;

import club.max.xenon.api.module.impl.Category;
import java.util.ArrayList;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class Fullbright extends Module
{
    public Value mode;
    public ArrayList<String> modes;
    float oldVal;
    
    public Fullbright() {
        super("Full Bright", "Makes it look like its always daytime", "fullbright", Category.Render);
        (this.modes = new ArrayList<String>()).add("Gamma");
        this.modes.add("Night Vision");
        this.mode = this.initValue("Mode", "Method to brighten the screen", "mode", this.modes, "Gamma");
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.oldVal = this.mc.gameSettings.gammaSetting;
    }
    
    @Override
    public void onUpdate() {
        if (this.mode.getValue().equalsIgnoreCase("Gamma")) {
            this.mc.gameSettings.gammaSetting = 20.0f;
        }
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.gameSettings.gammaSetting = this.oldVal;
    }
}
