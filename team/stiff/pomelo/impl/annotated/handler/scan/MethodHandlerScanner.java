// 
// Decompiled by Procyon v0.5.36
// 

package team.stiff.pomelo.impl.annotated.handler.scan;

import team.stiff.pomelo.impl.annotated.handler.MethodEventHandler;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.HashMap;
import team.stiff.pomelo.handler.EventHandler;
import java.util.Set;
import java.util.Map;
import team.stiff.pomelo.impl.annotated.filter.MethodFilterScanner;
import java.lang.reflect.Method;
import team.stiff.pomelo.filter.EventFilterScanner;
import team.stiff.pomelo.handler.scan.EventHandlerScanner;

public final class MethodHandlerScanner implements EventHandlerScanner
{
    private final AnnotatedListenerPredicate annotatedListenerPredicate;
    private final EventFilterScanner<Method> filterScanner;
    
    public MethodHandlerScanner() {
        this.annotatedListenerPredicate = new AnnotatedListenerPredicate();
        this.filterScanner = new MethodFilterScanner();
    }
    
    @Override
    public Map<Class<?>, Set<EventHandler>> locate(final Object listenerContainer) {
        final Map<Class<?>, Set<EventHandler>> eventHandlers = new HashMap<Class<?>, Set<EventHandler>>();
        Stream.of(listenerContainer.getClass().getDeclaredMethods()).filter(this.annotatedListenerPredicate).forEach(method -> eventHandlers.computeIfAbsent(method.getParameterTypes()[0], obj -> new TreeSet()).add(new MethodEventHandler(listenerContainer, method, this.filterScanner.scan(method))));
        return eventHandlers;
    }
}
