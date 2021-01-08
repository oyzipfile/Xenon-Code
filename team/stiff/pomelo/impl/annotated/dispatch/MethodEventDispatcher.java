// 
// Decompiled by Procyon v0.5.36
// 

package team.stiff.pomelo.impl.annotated.dispatch;

import java.util.Iterator;
import java.util.Collections;
import team.stiff.pomelo.handler.EventHandler;
import java.util.Set;
import java.util.Map;
import team.stiff.pomelo.dispatch.EventDispatcher;

public final class MethodEventDispatcher implements EventDispatcher
{
    private final Map<Class<?>, Set<EventHandler>> eventHandlers;
    
    public MethodEventDispatcher(final Map<Class<?>, Set<EventHandler>> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }
    
    @Override
    public <E> void dispatch(final E event) {
        for (final EventHandler eventHandler : this.eventHandlers.getOrDefault(event.getClass(), Collections.emptySet())) {
            eventHandler.handle(event);
        }
    }
}
