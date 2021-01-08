// 
// Decompiled by Procyon v0.5.36
// 

package club.max.turok.render.image.management;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import club.max.turok.render.opengl.TurokRenderGL;
import java.awt.Color;
import club.max.turok.render.image.TurokImage;

public class TurokImageManager
{
    public static void render(final TurokImage image, final int x, final int y, final float xx, final float yy, final int w, final int h, final float ww, final float hh, final Color color) {
        TurokRenderGL.enable(3042);
        TurokRenderGL.blendFunc(770, 771);
        TurokRenderGL.enable(3553);
        TurokRenderGL.enable(2884);
        TurokRenderGL.disable(2929);
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(image.getResourceLocation());
        GL11.glTexParameteri(3553, 10242, 10497);
        GL11.glTexParameteri(3553, 10243, 10497);
        TurokRenderGL.drawTextureInterpolated((float)x, (float)y, xx, yy, (float)w, (float)h, ww, hh);
        TurokRenderGL.disable(3042);
        TurokRenderGL.disable(3553);
        TurokRenderGL.disable(2884);
        TurokRenderGL.enable(2929);
    }
    
    public static void render(final TurokImage image, final int x, final int y, final int w, final int h, final Color color) {
        render(image, x, y, (float)w, (float)h, 0, 0, 1.0f, 1.0f, color);
    }
}
