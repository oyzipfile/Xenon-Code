// 
// Decompiled by Procyon v0.5.36
// 

package club.max.turok.util;

public class TurokTick
{
    private long ticks;
    
    public TurokTick() {
        this.ticks = -1L;
    }
    
    public void reset() {
        this.ticks = System.currentTimeMillis();
    }
    
    public void setTicks(final long ticks) {
        this.ticks = ticks;
    }
    
    public long getTicks() {
        return this.ticks;
    }
    
    public float getCurrentTicks() {
        return (float)(System.currentTimeMillis() - this.ticks);
    }
    
    public int getCurrentTicksCount(final double speed) {
        return (int)((System.currentTimeMillis() - this.ticks) / speed);
    }
    
    public boolean isPassedMS(final float ms) {
        return System.currentTimeMillis() - this.ticks >= ms;
    }
    
    public boolean isPassedSI(final float si) {
        return System.currentTimeMillis() - this.ticks >= si * 1000.0f;
    }
}
