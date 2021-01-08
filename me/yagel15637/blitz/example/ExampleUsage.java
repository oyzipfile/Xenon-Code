// 
// Decompiled by Procyon v0.5.36
// 

package me.yagel15637.blitz.example;

import me.yagel15637.blitz.event.Event;
import me.yagel15637.blitz.modifiers.EventPriority;
import me.yagel15637.blitz.dispatcher.DispatcherEntry;
import me.yagel15637.blitz.modifiers.EventEra;
import me.yagel15637.blitz.dispatcher.EventDispatcher;

public final class ExampleUsage
{
    public static EventDispatcher dispatcher;
    
    public static void main(final String[] args) {
        final ExampleUsage instance = new ExampleUsage();
        ExampleUsage.dispatcher.register(instance);
        ExampleUsage.dispatcher.dispatch(new Event1(EventEra.PRE));
        ExampleUsage.dispatcher.dispatch(new Event2(EventEra.PRE));
        ExampleUsage.dispatcher.dispatch(new EventWithVariables(10, 20, 30, EventEra.PRE));
        ExampleUsage.dispatcher.unregister(instance);
        ExampleUsage.dispatcher.dispatch(new Event3(EventEra.PRE));
        ExampleUsage.dispatcher.register(instance);
    }
    
    @DispatcherEntry(priority = EventPriority.HIGH)
    public void onEvent1(final Event1 event) {
        System.out.print("Hello ");
        event.cancel();
    }
    
    @DispatcherEntry
    public void onEvent1Take2(final Event1 event) {
        System.out.println("This will never be printed; another method that listens to Event 1 has higher priority and will always cancel it!");
    }
    
    @DispatcherEntry(era = EventEra.POST)
    public void onEvent1Post(final Event1 event1) {
        System.out.println("This will not be displayed; there is no event 1 being called with EventEra set to POST.");
    }
    
    @DispatcherEntry
    public static void onEvent2(final Event2 event) {
        System.out.println("World!");
    }
    
    @DispatcherEntry
    public static void onEvent3(final Event3 event) {
        System.out.println("This will not be displayed; object main is unregistered!");
    }
    
    static {
        ExampleUsage.dispatcher = new EventDispatcher();
    }
    
    public static class Event1 extends Event
    {
        public Event1(final EventEra era) {
            super(era);
        }
    }
    
    public static class Event2 extends Event
    {
        public Event2(final EventEra era) {
            super(era);
        }
    }
    
    public static class Event3 extends Event
    {
        public Event3(final EventEra era) {
            super(era);
        }
    }
    
    public static class EventWithVariables extends Event
    {
        private final int int1;
        private final int int2;
        private final int int3;
        
        public EventWithVariables(final int int1, final int int2, final int int3, final EventEra era) {
            super(era);
            this.int1 = int1;
            this.int2 = int2;
            this.int3 = int3;
        }
        
        public int getInt1() {
            return this.int1;
        }
        
        public int getInt2() {
            return this.int2;
        }
        
        public int getInt3() {
            return this.int3;
        }
    }
}
