// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.gui.component.frame;

import club.max.xenon.api.module.Module;
import club.max.xenon.client.gui.component.Component;

public class ModuleButton extends Component
{
    public Frame parent;
    public Module module;
    public boolean rendered;
    public int x;
    public int y;
    public int wid;
    public int height;
    public boolean open;
    
    public ModuleButton(final boolean rendered, final int x, final int y, final int wid, final int hight, final Frame parent, final Module module) {
        this.x = x;
        this.y = y;
        this.wid = wid;
        this.height = hight;
        this.rendered = rendered;
        this.parent = parent;
        this.module = module;
        this.open = this.module.open;
    }
    
    @Override
    public void onClick(final int MouseX, final int MouseY, final int Button) {
        if (this.isOnButton(MouseX, MouseY) && Button == 0) {
            this.module.toggle();
        }
        else if (this.isOnButton(MouseX, MouseY) && Button == 1) {
            this.module.setOpen(!this.module.isOpen());
            this.open = this.module.isOpen();
        }
        else if (Button == 1) {
            this.module.setOpen(false);
        }
        super.onClick(MouseX, MouseY, Button);
    }
    
    public boolean isOnButton(final int x, final int y) {
        return x >= this.x && x <= this.x + this.wid && y >= this.y && y <= this.y + this.height;
    }
}
