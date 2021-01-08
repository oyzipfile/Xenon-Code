// 
// Decompiled by Procyon v0.5.36
// 

package team.stiff.pomelo.impl.annotated.filter;

import java.util.HashSet;
import java.util.Collections;
import java.lang.annotation.Annotation;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;
import team.stiff.pomelo.filter.EventFilter;
import java.util.Set;
import java.lang.reflect.Method;
import team.stiff.pomelo.filter.EventFilterScanner;

public final class MethodFilterScanner implements EventFilterScanner<Method>
{
    @Override
    public Set<EventFilter> scan(final Method listener) {
        if (!listener.isAnnotationPresent(Listener.class)) {
            return (Set<EventFilter>)Collections.emptySet();
        }
        final Set<EventFilter> filters = new HashSet<EventFilter>();
        for (final Class<? extends EventFilter> filter : listener.getDeclaredAnnotation(Listener.class).filters()) {
            try {
                filters.add((EventFilter)filter.newInstance());
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return filters;
    }
}
