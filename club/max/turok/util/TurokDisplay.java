// 
// Decompiled by Procyon v0.5.36
// 

package club.max.turok.util;

import net.minecraft.client.Minecraft;

public class TurokDisplay
{
    private Minecraft mc;
    private int scaleFactor;
    private float partialTicks;
    
    public TurokDisplay(final Minecraft mc) {
        this.mc = mc;
        this.scaleFactor = 1;
    }
    
    public int getWidth() {
        return this.mc.displayWidth;
    }
    
    public int getHeight() {
        return this.mc.displayHeight;
    }
    
    public int getScaledWidth() {
        this.onUpdate();
        return (int)(this.mc.displayWidth / (double)this.scaleFactor);
    }
    
    public int getScaledHeight() {
        this.onUpdate();
        return (int)(this.mc.displayHeight / (double)this.scaleFactor);
    }
    
    public int getScaleFactor() {
        return this.scaleFactor;
    }
    
    public void setPartialTicks(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public float getRenderPartialTicks() {
        final float _partialTicks = this.mc.isGamePaused() ? (Minecraft.getDebugFPS() / this.mc.getTickLength()) : this.mc.getRenderPartialTicks();
        return _partialTicks;
    }
    
    protected void onUpdate() {
        final boolean isUnicode = this.mc.isUnicode();
        int minecraftScale = this.mc.gameSettings.guiScale;
        if (minecraftScale == 0) {
            minecraftScale = 1000;
        }
        while (this.scaleFactor < minecraftScale && this.getWidth() / (this.scaleFactor + 1) >= 320 && this.getHeight() / (this.scaleFactor + 1) >= 240) {
            ++this.scaleFactor;
        }
        if (isUnicode && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
            --this.scaleFactor;
        }
    }
}
