// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.gui;

import java.io.IOException;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import club.max.xenon.XenonClient;
import club.max.turok.render.opengl.TurokRenderGL;
import club.max.turok.hardware.mouse.TurokMouse;
import club.max.turok.util.TurokDisplay;
import club.max.xenon.client.gui.component.frame.Frame;
import net.minecraft.client.gui.GuiScreen;

public class ClickGui extends GuiScreen
{
    public Frame frame;
    int mouseX;
    int mouseY;
    public TurokDisplay display;
    public TurokMouse mouse;
    public boolean cancelClose;
    
    public ClickGui() {
        TurokRenderGL.init();
        this.frame = new Frame(150, 50);
        this.mouseX = 0;
        this.mouseY = 0;
    }
    
    public void setCancelClose(final boolean cancelClose) {
        this.cancelClose = cancelClose;
    }
    
    public boolean isCancelClose() {
        return this.cancelClose;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.display = new TurokDisplay(XenonClient.mc);
        this.mouse = new TurokMouse(mouseX, mouseY);
        TurokRenderGL.init(this.display);
        TurokRenderGL.init(this.mouse);
        TurokRenderGL.autoScale();
        GL11.glDisable(3553);
        this.frame.render();
        this.frame.MouseX = mouseX;
        this.frame.MouseY = mouseY;
        GL11.glDisable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        this.frame.onClick(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        this.frame.onRelease(mouseX, mouseY, state);
        super.mouseReleased(mouseX, mouseY, state);
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public void updateScreen() {
        super.updateScreen();
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (!this.cancelClose) {
            if (keyCode == 1) {
                this.onGuiClosed();
                XenonClient.mc.displayGuiScreen((GuiScreen)null);
            }
        }
        else {
            this.frame.onKey(typedChar, keyCode);
        }
    }
    
    public void onGuiClosed() {
        super.onGuiClosed();
        this.frame.onRelease(this.mouseX, this.mouseY, 0);
    }
}
