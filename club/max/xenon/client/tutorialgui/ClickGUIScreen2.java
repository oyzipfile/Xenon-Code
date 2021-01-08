// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.tutorialgui;

import java.io.IOException;
import java.util.Iterator;
import club.max.xenon.api.module.impl.Category;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;

public class ClickGUIScreen2 extends GuiScreen
{
    public static ClickGUIScreen2 INSTANCE;
    ArrayList<FrameForGuiVeryLongName> frames;
    
    public ClickGUIScreen2() {
        this.frames = new ArrayList<FrameForGuiVeryLongName>();
        int offset = 0;
        for (final Category category : Category.values()) {
            this.frames.add(new FrameForGuiVeryLongName(category, 10 + offset, 20));
            offset += 110;
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (final FrameForGuiVeryLongName frame : this.frames) {
            frame.render(mouseX, mouseY);
        }
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (final FrameForGuiVeryLongName frame : this.frames) {
            frame.onClick(mouseX, mouseY, mouseButton);
        }
    }
    
    static {
        ClickGUIScreen2.INSTANCE = new ClickGUIScreen2();
    }
}
