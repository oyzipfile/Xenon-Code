// 
// Decompiled by Procyon v0.5.36
// 

package team.stiff.pomelo.handler.scan;

import team.stiff.pomelo.handler.EventHandler;
import java.util.Set;
import java.util.Map;

public interface EventHandlerScanner
{
    Map<Class<?>, Set<EventHandler>> locate(final Object p0);
}
