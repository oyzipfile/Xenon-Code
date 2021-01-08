// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.tutorialgui;

import net.minecraft.client.gui.Gui;
import java.awt.Color;
import java.util.Iterator;
import club.max.xenon.api.module.Module;
import club.max.xenon.XenonClient;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import club.max.xenon.api.module.impl.Category;

public class FrameForGuiVeryLongName
{
    int x;
    int y;
    int width;
    int height;
    Category category;
    Minecraft mc;
    ArrayList<ModuleButton> moduleButtons;
    
    public FrameForGuiVeryLongName(final Category category, final int x, final int y) {
        this.mc = Minecraft.getMinecraft();
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 240;
        this.category = category;
        this.moduleButtons = new ArrayList<ModuleButton>();
        int offsetY = 14;
        for (final Module module : XenonClient.moduleManager.getModulesByCategory(category)) {
            this.moduleButtons.add(new ModuleButton(module, x, y + offsetY, this));
            offsetY += 14;
        }
        this.height = offsetY;
    }
    
    public void render(final int MouseX, final int MouseY) {
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(0, 0, 0, 100).getRGB());
        this.mc.fontRenderer.drawString(this.category.toString(), this.x + 2, this.y + 2, new Color(255, 255, 255).getRGB());
        for (final ModuleButton moduleButton : this.moduleButtons) {
            moduleButton.draw(MouseX, MouseY);
        }
    }
    
    public void onClick(final int x, final int y, final int button) {
        for (final ModuleButton moduleButton : this.moduleButtons) {
            moduleButton.onClick(x, y, button);
        }
    }
}
