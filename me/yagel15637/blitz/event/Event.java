// 
// Decompiled by Procyon v0.5.36
// 

package me.yagel15637.blitz.event;

import me.yagel15637.blitz.modifiers.EventEra;

public abstract class Event
{
    private boolean cancelled;
    public final EventEra era;
    
    public Event(final EventEra era) {
        this.cancelled = false;
        this.era = era;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void cancel() {
        this.cancelled = true;
    }
}
