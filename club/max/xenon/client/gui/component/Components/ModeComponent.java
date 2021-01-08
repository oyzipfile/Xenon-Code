// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.gui.component.Components;

import club.max.xenon.client.gui.component.frame.Frame;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;
import club.max.xenon.client.gui.component.Component;

public class ModeComponent extends Component
{
    int x;
    int y;
    int width;
    int height;
    public boolean val;
    public Module module;
    public Value value;
    Frame parent;
    
    public ModeComponent(final int x, final int y, final Module module, final Value value, final Frame parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.width = this.parent.width - this.parent.width / 3 - 30 - 4;
        this.height = 16;
        this.module = module;
        this.value = value;
    }
    
    public int getY() {
        return this.y;
    }
    
    @Override
    public void onClick(final int MouseX, final int MouseY, final int Button) {
        super.onClick(MouseX, MouseY, Button);
        if (this.module.open && this.isMouseOnButton(MouseX, MouseY) && Button == 0) {
            this.value.cycleModes();
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
    }
}
