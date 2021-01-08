// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.gui.console;

import java.util.Iterator;
import club.max.turok.render.font.management.TurokFontManager;
import club.max.xenon.client.gui.component.frame.Frame;
import java.util.List;
import java.util.Collections;
import org.lwjgl.opengl.GL11;
import club.max.xenon.client.module.client.ClickGuiModule;
import club.max.turok.render.opengl.TurokRenderGL;
import club.max.xenon.XenonClient;
import org.lwjgl.input.Mouse;
import club.max.turok.hardware.mouse.TurokMouse;
import club.max.turok.util.TurokRect;
import java.util.ArrayList;
import club.max.xenon.client.gui.component.Component;

public class OutputFrame extends Component
{
    public int x;
    public int y;
    public int width;
    public int height;
    int scroll;
    ArrayList<String> strings;
    TurokRect outputRect;
    TurokMouse mouse;
    
    public OutputFrame(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.strings = new ArrayList<String>();
        this.scroll = 0;
        this.outputRect = new TurokRect(x, y, width, height);
        final TurokMouse mouse = new TurokMouse(0, 0);
    }
    
    @Override
    public void render() {
        super.render();
        int dWheel = 0;
        if (this.outputRect.collideWithMouse(this.mouse)) {
            dWheel = Mouse.getDWheel();
            if (dWheel > 0) {
                this.scroll -= 15;
            }
            else if (dWheel < 0) {
                this.scroll += 15;
            }
        }
        this.outputRect = new TurokRect(this.x, this.y, this.width, this.height);
        TurokRenderGL.color(XenonClient.gui.frame.color2.getRed(), XenonClient.gui.frame.color2.getGreen(), XenonClient.gui.frame.color2.getBlue(), XenonClient.gui.frame.color2.getAlpha());
        final TurokRect outputRect = this.outputRect;
        final ClickGuiModule clickGuiModule = (ClickGuiModule)XenonClient.moduleManager.getByTag("ClickGui");
        TurokRenderGL.drawRoundedRect(outputRect, ClickGuiModule.rounded.getValue());
        TurokRenderGL.color(255, 255, 255, 255);
        TurokRenderGL.drawScissor(this.x + 1, this.y + 1, this.width - 2, this.height - 2);
        GL11.glEnable(3089);
        Collections.reverse(this.strings);
        int offset = 0;
        for (final String string : this.strings) {
            XenonClient.console.drawString(string, this.x + 4, this.y + this.height - TurokFontManager.getStringHeight(Frame.fontRenderer, string) - 4 + offset + this.scroll, false, Frame.fontRenderer);
            offset -= TurokFontManager.getStringHeight(Frame.fontRenderer, string) + 2;
        }
        Collections.reverse(this.strings);
        GL11.glDisable(3089);
    }
    
    public void setDims(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void println(final String string) {
        this.scroll = 0;
        this.strings.add(string);
    }
    
    public void updateMouse(final int x, final int y) {
        this.mouse = new TurokMouse(x, y);
    }
}
