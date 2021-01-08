// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.client;

import club.max.turok.render.opengl.TurokRenderGL;
import club.max.turok.util.TurokRect;
import org.lwjgl.opengl.GL11;
import java.util.Iterator;
import java.util.Comparator;
import club.max.turok.render.font.management.TurokFontManager;
import java.util.ArrayList;
import com.mojang.realmsclient.gui.ChatFormatting;
import club.max.xenon.api.module.management.ModuleManager;
import club.max.xenon.XenonClient;
import java.awt.Font;
import net.minecraft.client.Minecraft;
import club.max.xenon.api.module.impl.Category;
import java.awt.Color;
import club.max.turok.render.font.TurokFont;
import net.minecraft.client.gui.ScaledResolution;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class Hud extends Module
{
    public RainbowModule rainbowModule;
    public Value r;
    public Value g;
    public Value b;
    public Value rainbow;
    public Value shadow;
    public Value cfont;
    public Value background;
    public Value bgAlpha;
    public Value WaterMark;
    public Value wattermarkX;
    public Value wattermarkY;
    public Value ArrayList;
    public Value ListX;
    public Value ListY;
    public Value FPS;
    public Value FPSX;
    public Value FPSY;
    public Value Coords;
    public Value coordsX;
    public Value coordsY;
    ScaledResolution resolution;
    public TurokFont renderer;
    Color color;
    
    public Hud() {
        super("HUD", "Renders hud componenents on screen", "hud", Category.Client);
        this.r = this.initValue("Red", "The red value for hud components", "red", 255, 0, 255);
        this.g = this.initValue("Green", "The green value for hud components", "green", 255, 0, 255);
        this.b = this.initValue("Blue", "The blue value for hud components", "blue", 255, 0, 255);
        this.rainbow = this.initValue("Rainbow", "Rainbow for hud components", "rainbow", false);
        this.shadow = this.initValue("Shadow", "Uses a shadow for font rendering", "shadow", false);
        this.cfont = this.initValue("Custom Font", "Uses a custom font fur hud components", "cfont", false);
        this.background = this.initValue("Background", "Renders a background on hud components", "background", true);
        this.bgAlpha = this.initValue("Background Alpha", "The alpha value for the background", "bgalpha", 100, 0, 255);
        this.WaterMark = this.initValue("Watermark", "Puts a watermark on the screen", "watermark", true);
        this.wattermarkX = this.initValue("Watermark X", "The X coordinates for watermark", "watermarkX", 1, 0, 1000);
        this.wattermarkY = this.initValue("Watermark Y", "The Y coordinates for watermark", "watermarkY", 1, 0, 1000);
        this.ArrayList = this.initValue("Array List", "Shows the toggled modules", "ArrayList", true);
        this.ListX = this.initValue("Array List X", "The X coordinates for ArrayList", "ArrayListX", 1, 0, 1000);
        this.ListY = this.initValue("Array List Y", "The Y coordinates for ArrayList", "ArrayListY", 1, 0, 1000);
        this.FPS = this.initValue("FPS", "Shows your frames per second", "fps", true);
        this.FPSX = this.initValue("FPS X", "The X coordinates for FPS", "fpsX", 1, 0, 1000);
        this.FPSY = this.initValue("FPS Y", "The Y coordinates for FPS", "fpsY", 1, 0, 1000);
        this.Coords = this.initValue("Coords", "Shows your Coordinates", "coords", true);
        this.coordsX = this.initValue("Coords X", "The X coordinates for Coords", "coordsX", 1, 0, 1000);
        this.coordsY = this.initValue("Coords Y", "The Y coordinates for Coords", "coordsY", 1, 0, 1000);
        this.resolution = new ScaledResolution(Minecraft.getMinecraft());
        this.renderer = new TurokFont(new Font("Arial", 0, 18), true, true);
        this.color = new Color(0, 0, 0, 0);
        final ModuleManager moduleManager = XenonClient.moduleManager;
        this.rainbowModule = (RainbowModule)ModuleManager.get("rainbow");
    }
    
    @Override
    public void onUpdateConstant() {
        super.onUpdateConstant();
        if (this.background.getValue()) {
            this.bgAlpha.shown = true;
        }
        else {
            this.bgAlpha.shown = false;
        }
        if (!this.WaterMark.getValue()) {
            this.wattermarkX.shown = false;
            this.wattermarkY.shown = false;
        }
        else {
            this.wattermarkX.shown = true;
            this.wattermarkY.shown = true;
        }
        if (!this.ArrayList.getValue()) {
            this.ListX.shown = false;
            this.ListY.shown = false;
        }
        else {
            this.ListX.shown = true;
            this.ListY.shown = true;
        }
        if (!this.FPS.getValue()) {
            this.FPSX.shown = false;
            this.FPSY.shown = false;
        }
        else {
            this.FPSX.shown = true;
            this.FPSY.shown = true;
        }
        if (!this.Coords.getValue()) {
            this.coordsX.shown = false;
            this.coordsY.shown = false;
        }
        else {
            this.coordsX.shown = true;
            this.coordsY.shown = true;
        }
        if (this.rainbow.getValue()) {
            final Value r = this.r;
            final RainbowModule rainbowModule = this.rainbowModule;
            r.setValue(RainbowModule.getColor().getRed());
            final Value g = this.g;
            final RainbowModule rainbowModule2 = this.rainbowModule;
            g.setValue(RainbowModule.getColor().getGreen());
            final Value b = this.b;
            final RainbowModule rainbowModule3 = this.rainbowModule;
            b.setValue(RainbowModule.getColor().getBlue());
        }
        this.color = ((ClickGuiModule)XenonClient.getModuleManager().getByTag("ClickGui")).getColor();
    }
    
    @Override
    public void onFrameRender() {
        super.onFrameRender();
        if (this.WaterMark.getValue()) {
            this.renderWatermark(this.wattermarkX.getValue(), this.wattermarkY.getValue());
        }
        if (this.ArrayList.getValue()) {
            this.renderArrayList(this.ListX.getValue(), this.ListY.getValue());
        }
        if (this.FPS.getValue()) {
            this.renderFPS(this.FPSX.getValue(), this.FPSY.getValue());
        }
        if (this.Coords.getValue()) {
            this.renderCoords(this.coordsX.getValue(), this.coordsY.getValue());
        }
    }
    
    public void renderWatermark(final int x, final int y) {
        final String watermark = "Welcome to " + ChatFormatting.BOLD + "XENON" + " " + "0.0.6" + " " + ChatFormatting.RESET + ChatFormatting.GRAY + this.mc.player.getName();
        this.renderString(watermark, x, y);
    }
    
    public void renderFPS(final int x, final int y) {
        final String fps = "FPS: " + Minecraft.getDebugFPS();
        this.renderString(fps, x, y);
    }
    
    public void renderArrayList(final int x, final int y) {
        final ArrayList<String> parsedModules = new ArrayList<String>();
        for (final Module module : XenonClient.moduleManager.getToggledModules()) {
            if (module.visible.getValue()) {
                String hudData;
                if (module.getHudData() == "") {
                    hudData = "";
                }
                else {
                    hudData = " " + ChatFormatting.GRAY + "{" + module.getHudData() + "}" + ChatFormatting.RESET;
                }
                parsedModules.add("" + module.name + hudData);
            }
        }
        if (this.cfont.getValue()) {
            parsedModules.sort(Comparator.comparing(s -> -TurokFontManager.getStringWidth(this.renderer, s)));
        }
        else {
            parsedModules.sort(Comparator.comparing(s -> -this.mc.fontRenderer.getStringWidth(s)));
        }
        int offset = 0;
        for (final String string : parsedModules) {
            this.renderString(string, x, y + offset);
            if (this.cfont.getValue()) {
                offset += this.getSpacing(string);
            }
            else {
                offset += this.getSpacing(string);
            }
        }
    }
    
    public void renderCoords(final int x, final int y) {
        int spacing = 0;
        this.renderString("X : " + ChatFormatting.GRAY + (int)this.mc.player.posX, x, y + spacing);
        spacing += this.getSpacing("X : " + ChatFormatting.GRAY + (int)this.mc.player.posX);
        this.renderString("Y : " + ChatFormatting.GRAY + (int)this.mc.player.posY, x, y + spacing);
        spacing += this.getSpacing("Y : " + ChatFormatting.GRAY + (int)this.mc.player.posY);
        this.renderString("Z : " + ChatFormatting.GRAY + (int)this.mc.player.posZ, x, y + spacing);
        spacing += this.getSpacing("Z : " + ChatFormatting.GRAY + (int)this.mc.player.posZ);
    }
    
    public void renderString(final String string, final int x, final int y) {
        final Color col = new Color(this.r.getValue(), this.g.getValue(), this.b.getValue());
        if (this.background.getValue()) {
            GL11.glDisable(3553);
            final Color colorBG = new Color(0, 0, 0, this.bgAlpha.getValue());
            TurokRect rect = null;
            if (this.cfont.getValue()) {
                rect = new TurokRect("rect", x - 1, y - 2, TurokFontManager.getStringWidth(this.renderer, string) + 2, this.getSpacing(string));
            }
            else {
                rect = new TurokRect("rect", x - 1, y - 2, this.mc.fontRenderer.getStringWidth(string) + 2, this.getSpacing(string));
            }
            TurokRenderGL.color(colorBG.getRed(), colorBG.getGreen(), colorBG.getBlue(), colorBG.getAlpha());
            TurokRenderGL.drawSolidRect(rect);
            TurokRenderGL.color(col.getRed(), col.getGreen(), col.getBlue(), 255);
            GL11.glEnable(3553);
        }
        if (this.shadow.getValue()) {
            if (this.cfont.getValue()) {
                TurokFontManager.render(this.renderer, string, x, y, true, col);
                GL11.glEnable(3553);
            }
            else {
                this.mc.fontRenderer.drawStringWithShadow(string, (float)x, (float)y, new Color(this.r.getValue(), this.g.getValue(), this.b.getValue()).getRGB());
            }
        }
        else if (this.cfont.getValue()) {
            TurokFontManager.render(this.renderer, string, x, y, false, col);
            GL11.glEnable(3553);
        }
        else {
            this.mc.fontRenderer.drawString(string, x, y, col.getRGB());
        }
    }
    
    public int getSpacing(final String string) {
        if (this.cfont.getValue()) {
            return TurokFontManager.getStringHeight(this.renderer, string) + 4;
        }
        return 12;
    }
}
