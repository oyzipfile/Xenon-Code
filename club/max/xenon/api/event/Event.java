// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.event;

import club.max.xenon.api.event.impl.EventStage;

public class Event
{
    private EventStage stage;
    private boolean isCanceled;
    
    public Event() {
    }
    
    public Event(final EventStage stage) {
        this.stage = stage;
        this.isCanceled = false;
    }
    
    public void setStage(final EventStage stage) {
        this.stage = stage;
        this.isCanceled = false;
    }
    
    public EventStage getStage() {
        return this.stage;
    }
    
    public void setCanceled(final boolean canceled) {
        this.isCanceled = canceled;
    }
    
    public boolean isCanceled() {
        return this.isCanceled;
    }
}
