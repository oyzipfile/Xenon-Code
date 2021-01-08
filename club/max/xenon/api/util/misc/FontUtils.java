// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.util.misc;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.Minecraft;
import java.awt.Font;

public class FontUtils
{
    public static Font UBUNTU_FONT;
    
    public static Font getFontByName(final String name) {
        if (name.equalsIgnoreCase("ubuntu")) {
            return getFontFromInput("/assets/xenon/fonts/Ubuntu.ttf");
        }
        return null;
    }
    
    public static Font getFontFromInput(final String path) {
        try {
            final ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
            final Font newFont = Font.createFont(0, FontUtils.class.getResourceAsStream(path));
            if (newFont != null) {
                return newFont;
            }
        }
        catch (Exception e) {
            return null;
        }
        return null;
    }
    
    static {
        FontUtils.UBUNTU_FONT = getFontByName("ubuntu");
    }
}
