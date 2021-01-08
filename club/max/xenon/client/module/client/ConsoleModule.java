// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.client;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.gui.GuiScreen;
import club.max.xenon.XenonClient;
import net.minecraft.client.renderer.GlStateManager;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.module.Module;

public class ConsoleModule extends Module
{
    public ConsoleModule() {
        super("Console", "Displays a console", "console", Category.Client);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        System.out.println("Opening gui");
        GlStateManager.pushMatrix();
        this.mc.displayGuiScreen((GuiScreen)XenonClient.console);
        if (this.mc.currentScreen == XenonClient.console) {
            this.disable();
        }
        GL11.glEnable(3553);
        GL11.glEnable(3042);
        GL11.glEnable(6145);
        GlStateManager.popMatrix();
    }
}
