// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.gui.component.frame;

import club.max.xenon.api.module.impl.Category;
import club.max.xenon.client.gui.component.Component;

public class CategoryButton extends Component
{
    public int x;
    public int y;
    public Category category;
    public Frame parent;
    
    public CategoryButton(final int x, final int y, final Category category, final Frame parent) {
        this.x = x;
        this.y = y;
        this.category = category;
        this.parent = parent;
    }
    
    @Override
    public void render() {
    }
    
    @Override
    public void onClick(final int MouseX, final int MouseY, final int Button) {
        if (this.isWithin(MouseX, MouseY)) {
            this.parent.focused = this.category;
        }
        super.onClick(MouseX, MouseY, Button);
    }
    
    boolean isWithin(final int x, final int y) {
        return x >= this.x && x <= this.x + this.parent.width / 6 && y >= this.y && y <= this.y + 25;
    }
}
