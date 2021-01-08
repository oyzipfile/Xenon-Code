// 
// Decompiled by Procyon v0.5.36
// 

package me.yagel15637.blitz.dispatcher;

import me.yagel15637.blitz.modifiers.EventPriority;
import me.yagel15637.blitz.modifiers.EventEra;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface DispatcherEntry {
    EventEra era() default EventEra.PRE;
    
    EventPriority priority() default EventPriority.MEDIUM;
}
