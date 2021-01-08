// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.gui.component.Components;

import club.max.xenon.XenonClient;
import club.max.xenon.client.gui.component.frame.Frame;
import club.max.xenon.api.module.Module;
import club.max.xenon.client.gui.component.Component;

public class KeybindComponent extends Component
{
    int x;
    int y;
    int width;
    int height;
    public boolean binding;
    public Module module;
    Frame parent;
    
    public KeybindComponent(final int x, final int y, final Module module, final Frame parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.width = this.parent.width - this.width / 3 - 30 - 4;
        this.height = 16;
        this.binding = module.binding;
        this.module = module;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getX() {
        return this.x;
    }
    
    @Override
    public void onClick(final int MouseX, final int MouseY, final int Button) {
        if (this.module.binding) {
            XenonClient.gui.setCancelClose(false);
            this.module.binding = false;
        }
        if (this.module.open && this.isMouseOnButton(MouseX, MouseY) && Button == 0) {
            XenonClient.gui.setCancelClose(true);
            this.module.binding = !this.module.binding;
            this.binding = this.module.binding;
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
    }
    
    @Override
    public void onKey(final char typedChar, final int keyCode) {
        if (this.binding) {
            switch (keyCode) {
                case 1: {
                    this.module.binding = false;
                    XenonClient.gui.setCancelClose(false);
                    this.binding = false;
                    break;
                }
                case 211: {
                    this.module.setKey(-1);
                    this.module.binding = false;
                    XenonClient.gui.setCancelClose(false);
                    this.binding = false;
                    break;
                }
                default: {
                    this.module.setKey(keyCode);
                    this.module.binding = false;
                    XenonClient.gui.setCancelClose(false);
                    this.binding = false;
                    break;
                }
            }
        }
        super.onKey(typedChar, keyCode);
    }
}
