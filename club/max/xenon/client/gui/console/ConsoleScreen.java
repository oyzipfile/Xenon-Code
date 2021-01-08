// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.gui.console;

import java.io.IOException;
import net.minecraft.client.renderer.GlStateManager;
import club.max.turok.render.font.management.TurokFontManager;
import club.max.xenon.client.module.client.ClickGuiModule;
import org.lwjgl.opengl.GL11;
import club.max.turok.render.opengl.TurokRenderGL;
import club.max.xenon.client.gui.component.frame.Frame;
import club.max.xenon.XenonClient;
import club.max.turok.util.TurokRect;
import club.max.turok.render.font.TurokFont;
import club.max.turok.hardware.mouse.TurokMouse;
import club.max.turok.util.TurokDisplay;
import net.minecraft.client.gui.GuiScreen;

public class ConsoleScreen extends GuiScreen
{
    int x;
    int y;
    int width;
    int height;
    public TurokDisplay display;
    public TurokMouse mouse;
    TurokFont rendererBig;
    int barheight;
    int spacing;
    int inputHeight;
    int outputHeight;
    public OutputFrame outFrame;
    public InputFrame inFrame;
    TurokRect baseRect;
    boolean dragging;
    int dragX;
    int dragY;
    
    public ConsoleScreen() {
        this.x = 100;
        this.y = 50;
        this.width = 100;
        this.height = 100;
        final Frame frame = XenonClient.gui.frame;
        this.rendererBig = Frame.fontRendererBig;
        this.spacing = 10;
        this.barheight = 25;
        this.inputHeight = 18;
        this.outputHeight = this.height - this.barheight - this.spacing * 2 - this.inputHeight - this.spacing;
        this.outFrame = new OutputFrame(this.x + this.spacing, this.y + this.barheight + this.spacing, this.width - this.spacing * 2, this.outputHeight);
        this.inFrame = new InputFrame(this.x + this.spacing, this.y + this.barheight + this.spacing * 2 + this.outputHeight, this.width - this.spacing * 2, this.inputHeight);
        this.baseRect = new TurokRect(this.x, this.y, this.width, this.height);
        this.dragging = false;
        this.dragX = 0;
        this.dragY = 0;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        XenonClient.gui.frame.updateColors();
        this.outFrame.updateMouse(mouseX, mouseY);
        if (this.dragging) {
            this.x = mouseX - this.dragX;
            this.y = mouseY - this.dragY;
        }
        this.width = this.mc.displayWidth / 3;
        this.height = this.mc.displayHeight / 3;
        this.outputHeight = this.height - this.barheight - this.spacing * 2 - this.inputHeight - this.spacing;
        this.outFrame.setDims(this.x + this.spacing, this.y + this.barheight + this.spacing, this.width - this.spacing * 2, this.outputHeight);
        this.inFrame.setDims(this.x + this.spacing, this.y + this.barheight + this.spacing * 2 + this.outputHeight, this.width - this.spacing * 2, this.inputHeight);
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.display = new TurokDisplay(XenonClient.mc);
        this.mouse = new TurokMouse(mouseX, mouseY);
        TurokRenderGL.init(this.display);
        TurokRenderGL.init(this.mouse);
        TurokRenderGL.autoScale();
        GL11.glDisable(3553);
        this.baseRect = new TurokRect(this.x, this.y, this.width, this.height);
        TurokRenderGL.color(XenonClient.gui.frame.color1.getRed(), XenonClient.gui.frame.color1.getGreen(), XenonClient.gui.frame.color1.getBlue(), XenonClient.gui.frame.color1.getAlpha());
        final TurokRect baseRect = this.baseRect;
        final ClickGuiModule clickGuiModule = (ClickGuiModule)XenonClient.moduleManager.getByTag("ClickGui");
        TurokRenderGL.drawRoundedRect(baseRect, ClickGuiModule.rounded.getValue());
        final TurokRect headerRect = new TurokRect(this.x, this.y, this.width, this.barheight);
        TurokRenderGL.color(XenonClient.gui.frame.color2.getRed(), XenonClient.gui.frame.color2.getGreen(), XenonClient.gui.frame.color2.getBlue(), XenonClient.gui.frame.color2.getAlpha());
        TurokRenderGL.drawSolidRect(headerRect);
        TurokRenderGL.color(255, 255, 255, 255);
        TurokRenderGL.drawOutlineRect(headerRect);
        final int centerHeaderText = (this.barheight - TurokFontManager.getStringHeight(this.rendererBig, "CONSOLE")) / 2;
        this.drawString("CONSOLE", this.x + 5, this.y + centerHeaderText, false, this.rendererBig);
        this.outFrame.render();
        this.inFrame.render();
        GL11.glDisable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GlStateManager.color(1.0f, 1.0f, 1.0f);
    }
    
    public void drawString(final String str, final int x, final int y, final boolean shadow, final TurokFont fontRenderer) {
        GL11.glEnable(3553);
        TurokFontManager.render(fontRenderer, str, x, y, false, XenonClient.gui.frame.colorText);
        GL11.glDisable(3553);
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.inFrame.onClick(mouseX, mouseY, mouseButton);
        final TurokMouse mouse = new TurokMouse(mouseX, mouseY);
        if (this.baseRect.collideWithMouse(mouse)) {
            this.dragging = true;
            this.dragX = mouseX - this.x;
            this.dragY = mouseY - this.y;
        }
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        this.inFrame.onKey(typedChar, keyCode);
    }
    
    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        super.mouseReleased(mouseX, mouseY, state);
        if (this.dragging) {
            this.dragging = false;
        }
    }
}
