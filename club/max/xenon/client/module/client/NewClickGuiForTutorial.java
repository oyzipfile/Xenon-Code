// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.client;

import net.minecraft.client.gui.GuiScreen;
import club.max.xenon.client.tutorialgui.ClickGUIScreen2;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.module.Module;

public class NewClickGuiForTutorial extends Module
{
    public NewClickGuiForTutorial() {
        super("Edfgshijnsmdf uedrjkms fjkm", "gfudhjks", "newgui", Category.Client);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.mc.displayGuiScreen((GuiScreen)ClickGUIScreen2.INSTANCE);
    }
}
