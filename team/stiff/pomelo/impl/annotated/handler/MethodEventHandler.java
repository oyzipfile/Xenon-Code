// 
// Decompiled by Procyon v0.5.36
// 

package team.stiff.pomelo.impl.annotated.handler;

import team.stiff.pomelo.handler.ListenerPriority;
import java.util.Iterator;
import java.lang.reflect.InvocationTargetException;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;
import team.stiff.pomelo.filter.EventFilter;
import java.util.Set;
import java.lang.reflect.Method;
import team.stiff.pomelo.handler.EventHandler;

public final class MethodEventHandler implements EventHandler
{
    private final Object listenerParent;
    private final Method method;
    private final Set<EventFilter> eventFilters;
    private final Listener listenerAnnotation;
    
    public MethodEventHandler(final Object listenerParent, final Method method, final Set<EventFilter> eventFilters) {
        this.listenerParent = listenerParent;
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        this.method = method;
        this.eventFilters = eventFilters;
        this.listenerAnnotation = method.getAnnotation(Listener.class);
    }
    
    @Override
    public <E> void handle(final E event) {
        for (final EventFilter filter : this.eventFilters) {
            if (!filter.test(this, event)) {
                return;
            }
        }
        try {
            this.method.invoke(this.listenerParent, event);
        }
        catch (IllegalAccessException | InvocationTargetException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException exception = ex;
            exception.printStackTrace();
        }
    }
    
    @Override
    public Object getListener() {
        return this.method;
    }
    
    @Override
    public ListenerPriority getPriority() {
        return this.listenerAnnotation.priority();
    }
    
    @Override
    public Iterable<EventFilter> getFilters() {
        return this.eventFilters;
    }
    
    @Override
    public int compareTo(final EventHandler eventHandler) {
        return Integer.compare(eventHandler.getPriority().getPriorityLevel(), this.getPriority().getPriorityLevel());
    }
}
