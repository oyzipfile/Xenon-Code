// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.client;

import java.awt.Color;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import club.max.xenon.api.module.management.ModuleManager;
import club.max.xenon.XenonClient;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class ClickGuiModule extends Module
{
    public static Value darkMode;
    public static Value<Integer> Red;
    public static Value<Integer> Green;
    public static Value<Integer> Blue;
    public static Value<Integer> Alpha;
    public static Value<Integer> sliderRed;
    public static Value<Integer> sliderGreen;
    public static Value<Integer> sliderBlue;
    public static Value<Integer> sliderAlpha;
    public static Value<Integer> rounded;
    RainbowModule rainbowModule;
    public Value Rainbow;
    
    public ClickGuiModule() {
        super("Click GUI", "The Click GUI module", "ClickGui", Category.Client);
        this.setKey(25);
        System.out.println("set key");
        ClickGuiModule.darkMode = this.initValue("Dark Mode", "Sets the GUI to dark mode", "darkMode", false);
        ClickGuiModule.Red = (Value<Integer>)this.initValue("Red", "Red Value of highlight", "red", 0, 0, 255);
        ClickGuiModule.Green = (Value<Integer>)this.initValue("Green", "Green Value of highlight", "green", 245, 0, 255);
        ClickGuiModule.Blue = (Value<Integer>)this.initValue("Blue", "Blue Value of highlight", "blue", 142, 0, 255);
        ClickGuiModule.Alpha = (Value<Integer>)this.initValue("Alpha", "Alpha Value of Highlight", "alpha", 255, 0, 255);
        ClickGuiModule.sliderRed = (Value<Integer>)this.initValue("Slider Red", "Red Value of Slider BG", "redS", 30, 0, 255);
        ClickGuiModule.sliderGreen = (Value<Integer>)this.initValue("Slider Green", "Green Value of Slider BG", "greenS", 30, 0, 255);
        ClickGuiModule.sliderBlue = (Value<Integer>)this.initValue("Slider Blue", "Blue Value of Slider BG", "blueS", 30, 0, 255);
        ClickGuiModule.sliderAlpha = (Value<Integer>)this.initValue("Slider Alpha", "Alpha Value of Slider BG", "alphaS", 190, 0, 255);
        ClickGuiModule.rounded = (Value<Integer>)this.initValue("Rounded Amount", "The amount to round the GUI", "rounded", 2, 0, 6);
        this.Rainbow = this.initValue("Rainbow", "Turns the accents rainbow", "rainbow", true);
        final ModuleManager moduleManager = XenonClient.moduleManager;
        this.rainbowModule = (RainbowModule)ModuleManager.get("rainbow");
    }
    
    @Override
    public void onUpdateConstant() {
        super.onUpdateConstant();
        if (this.Rainbow.getValue() && this.mc.player != null) {
            final Value<Integer> red = ClickGuiModule.Red;
            final RainbowModule rainbowModule = this.rainbowModule;
            red.setValue(RainbowModule.getColor().getRed());
            final Value<Integer> green = ClickGuiModule.Green;
            final RainbowModule rainbowModule2 = this.rainbowModule;
            green.setValue(RainbowModule.getColor().getGreen());
            final Value<Integer> blue = ClickGuiModule.Blue;
            final RainbowModule rainbowModule3 = this.rainbowModule;
            blue.setValue(RainbowModule.getColor().getBlue());
        }
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        System.out.println("Opening gui");
        GlStateManager.pushMatrix();
        this.mc.displayGuiScreen((GuiScreen)XenonClient.gui);
        if (this.mc.currentScreen == XenonClient.gui) {
            this.disable();
        }
        GL11.glEnable(3553);
        GL11.glEnable(3042);
        GL11.glEnable(6145);
        GlStateManager.popMatrix();
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.mc.currentScreen != XenonClient.gui) {
            this.mc.displayGuiScreen((GuiScreen)XenonClient.gui);
        }
        else {
            this.disable();
        }
    }
    
    public Color getColor() {
        return new Color(ClickGuiModule.Red.getValue(), ClickGuiModule.Green.getValue(), ClickGuiModule.Blue.getValue());
    }
}
