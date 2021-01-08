// 
// Decompiled by Procyon v0.5.36
// 

package club.max.turok.hardware.mouse;

import org.lwjgl.input.Mouse;

public class TurokMouse
{
    private int scroll;
    private int x;
    private int y;
    
    public TurokMouse(final int mx, final int my) {
        this.x = mx;
        this.y = my;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getScroll() {
        return -(Mouse.getDWheel() / 10);
    }
    
    public boolean hasWheel() {
        return Mouse.hasWheel();
    }
}
