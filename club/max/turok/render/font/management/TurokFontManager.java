// 
// Decompiled by Procyon v0.5.36
// 

package club.max.turok.render.font.management;

import net.minecraft.client.Minecraft;
import club.max.turok.render.opengl.TurokRenderGL;
import java.awt.Color;
import club.max.turok.render.font.TurokFont;

public class TurokFontManager
{
    public static void render(final TurokFont fontRenderer, final String string, final int x, final int y, final boolean shadow, final Color color) {
        TurokRenderGL.prepareOverlay();
        TurokRenderGL.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        if (shadow) {
            if (fontRenderer.isRenderingCustomFont()) {
                fontRenderer.drawStringWithShadow(string, x, y, color.getRGB());
            }
            else {
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(string, (float)x, (float)y, color.getRGB());
            }
        }
        else if (fontRenderer.isRenderingCustomFont()) {
            fontRenderer.drawString(string, (float)x, (float)y, color.getRGB());
        }
        else {
            Minecraft.getMinecraft().fontRenderer.drawString(string, x, y, color.getRGB());
        }
        TurokRenderGL.disable(3553);
    }
    
    public static int getStringWidth(final TurokFont fontRenderer, final String string) {
        return fontRenderer.isRenderingCustomFont() ? fontRenderer.getStringWidth(string) : Minecraft.getMinecraft().fontRenderer.getStringWidth(string);
    }
    
    public static int getStringHeight(final TurokFont fontRenderer, final String string) {
        return fontRenderer.isRenderingCustomFont() ? fontRenderer.getStringHeight(string) : (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * fontRenderer.getFontSize());
    }
}
