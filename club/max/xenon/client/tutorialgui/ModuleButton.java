// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.tutorialgui;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import club.max.xenon.api.module.Module;

public class ModuleButton
{
    int x;
    int y;
    int width;
    int height;
    Module module;
    FrameForGuiVeryLongName parent;
    Minecraft mc;
    
    public ModuleButton(final Module module, final int x, final int y, final FrameForGuiVeryLongName parent) {
        this.mc = Minecraft.getMinecraft();
        this.module = module;
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.width = parent.width;
        this.height = 14;
    }
    
    public void draw(final int MouseX, final int MouseY) {
        if (this.module.toggled) {
            this.mc.fontRenderer.drawString(this.module.getName(), this.x + 2, this.y + 2, new Color(255, 0, 0).getRGB());
        }
        else {
            this.mc.fontRenderer.drawString(this.module.getName(), this.x + 2, this.y + 2, new Color(154, 154, 154).getRGB());
        }
    }
    
    public void onClick(final int x, final int y, final int button) {
        if (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height) {
            this.module.toggle();
        }
    }
}
