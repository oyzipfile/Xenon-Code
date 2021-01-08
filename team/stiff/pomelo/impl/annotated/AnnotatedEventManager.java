// 
// Decompiled by Procyon v0.5.36
// 

package team.stiff.pomelo.impl.annotated;

import team.stiff.pomelo.handler.EventHandler;
import java.util.Set;
import team.stiff.pomelo.impl.annotated.dispatch.MethodEventDispatcher;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import team.stiff.pomelo.impl.annotated.handler.scan.MethodHandlerScanner;
import team.stiff.pomelo.dispatch.EventDispatcher;
import java.util.Map;
import team.stiff.pomelo.handler.scan.EventHandlerScanner;
import team.stiff.pomelo.EventManager;

public final class AnnotatedEventManager implements EventManager
{
    private final EventHandlerScanner eventHandlerScanner;
    private final Map<Object, EventDispatcher> listenerDispatchers;
    
    public AnnotatedEventManager() {
        this.eventHandlerScanner = new MethodHandlerScanner();
        this.listenerDispatchers = new ConcurrentHashMap<Object, EventDispatcher>();
    }
    
    @Override
    public <E> E dispatchEvent(final E event) {
        for (final EventDispatcher dispatcher : this.listenerDispatchers.values()) {
            dispatcher.dispatch(event);
        }
        return event;
    }
    
    @Override
    public boolean isRegisteredListener(final Object listener) {
        return this.listenerDispatchers.containsKey(listener);
    }
    
    @Override
    public boolean addEventListener(final Object listenerContainer) {
        if (this.listenerDispatchers.containsKey(listenerContainer)) {
            return false;
        }
        final Map<Class<?>, Set<EventHandler>> eventHandlers = this.eventHandlerScanner.locate(listenerContainer);
        return !eventHandlers.isEmpty() && this.listenerDispatchers.put(listenerContainer, new MethodEventDispatcher(eventHandlers)) == null;
    }
    
    @Override
    public boolean removeEventListener(final Object listenerContainer) {
        return this.listenerDispatchers.remove(listenerContainer) != null;
    }
}
