// 
// Decompiled by Procyon v0.5.36
// 

package me.yagel15637.blitz.dispatcher;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Iterator;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.lang.reflect.Method;
import java.util.List;
import me.yagel15637.blitz.event.Event;
import java.util.HashMap;

public final class EventDispatcher
{
    private final HashMap<Object, HashMap<Class<? extends Event>, List<Method>>> listenerMap;
    private final HashMap<Object, HashMap<Class<? extends Event>, List<Method>>> cache;
    private boolean multiThreading;
    
    public EventDispatcher() {
        this.listenerMap = new HashMap<Object, HashMap<Class<? extends Event>, List<Method>>>();
        this.cache = new HashMap<Object, HashMap<Class<? extends Event>, List<Method>>>();
        this.multiThreading = false;
    }
    
    public boolean isMultithreading() {
        return this.multiThreading;
    }
    
    public void setMultiThreading(final boolean multiThreading) {
        this.multiThreading = multiThreading;
    }
    
    public void register(final Object object) {
        if (this.cache.containsKey(object)) {
            this.listenerMap.put(object, this.cache.get(object));
            return;
        }
        final List<Method> methods = Arrays.stream(object.getClass().getDeclaredMethods()).filter(it -> it.isAnnotationPresent(DispatcherEntry.class)).filter(it -> it.getParameterCount() == 1 && it.getParameterTypes()[0].getSuperclass().isAssignableFrom(Event.class)).collect((Collector<? super Method, ?, List<Method>>)Collectors.toList());
        final HashMap<Class<? extends Event>, List<Method>> filteredMethods = new HashMap<Class<? extends Event>, List<Method>>();
        for (final Method method : methods) {
            final Class<? extends Event> eventClass = (Class<? extends Event>)method.getParameterTypes()[0];
            final List<Method> methodList = filteredMethods.containsKey(eventClass) ? filteredMethods.get(eventClass) : new ArrayList<Method>();
            methodList.add(method);
            filteredMethods.put(eventClass, methodList.stream().sorted(Comparator.comparing(it -> it.getDeclaredAnnotation(DispatcherEntry.class).priority().ordinal())).collect((Collector<? super Object, ?, List<Method>>)Collectors.toList()));
        }
        this.listenerMap.put(object, filteredMethods);
        this.cache.put(object, filteredMethods);
    }
    
    public void unregister(final Object object) {
        this.listenerMap.remove(object);
    }
    
    private synchronized <T extends Event> void dispatch0(final T event) {
        for (final Map.Entry<Object, HashMap<Class<? extends Event>, List<Method>>> entry : this.listenerMap.entrySet()) {
            if (entry.getValue().containsKey(event.getClass())) {
                for (final Method m : entry.getValue().get(event.getClass())) {
                    this.invoke(m, entry.getKey(), event);
                    if (event.isCancelled()) {
                        return;
                    }
                }
            }
        }
    }
    
    public synchronized <T extends Event> void dispatch(final T event) {
        if (this.multiThreading) {
            new Thread(() -> this.dispatch0((Event)event)).start();
        }
        else {
            this.dispatch0((Event)event);
        }
    }
    
    private void invoke(final Method m, final Object object, final Object... args) {
        try {
            m.invoke(object, args);
        }
        catch (IllegalAccessException | InvocationTargetException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException e = ex;
            e.printStackTrace();
        }
    }
}
