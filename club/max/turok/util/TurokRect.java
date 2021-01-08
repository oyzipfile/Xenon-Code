// 
// Decompiled by Procyon v0.5.36
// 

package club.max.turok.util;

import club.max.turok.hardware.mouse.TurokMouse;

public class TurokRect
{
    public int x;
    public int y;
    public int width;
    public int height;
    public String tag;
    
    public TurokRect(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tag = "nontag";
    }
    
    public TurokRect(final String tag, final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tag = "nontag";
    }
    
    public TurokRect(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.width = 0;
        this.height = 0;
        this.tag = "nontag";
    }
    
    public TurokRect(final String tag, final int x, final int y) {
        this.x = x;
        this.y = y;
        this.width = 0;
        this.height = 0;
        this.tag = tag;
    }
    
    public void setTag(final String tag) {
        this.tag = tag;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getDistance(final TurokRect rect) {
        final int calculatedX = this.x - rect.getX();
        final int calculatedY = this.y - rect.getY();
        final int calculatedW = this.x + this.width - (rect.getX() + rect.getWidth());
        final int calculatedH = this.y + this.height - (rect.getY() + rect.getHeight());
        return TurokMath.sqrt(calculatedX * calculatedW + calculatedY * calculatedH);
    }
    
    public boolean collideWithMouse(final TurokMouse mouse) {
        return mouse.getX() >= this.x && mouse.getX() <= this.x + this.width && mouse.getY() >= this.y && mouse.getY() <= this.y + this.height;
    }
    
    public boolean collideWithRect(final TurokRect rect) {
        return this.x <= rect.getX() + rect.getWidth() && this.x + this.width >= rect.getX() && this.y <= rect.getY() + rect.getHeight() && this.y + this.height >= rect.getY();
    }
    
    public static boolean collideRectWith(final TurokRect rect, final TurokMouse mouse) {
        return rect.collideWithMouse(mouse);
    }
    
    public static boolean collideRectWith(final TurokRect rect, final TurokRect rect1) {
        return rect.collideWithRect(rect1);
    }
}
