// 
// Decompiled by Procyon v0.5.36
// 

package team.stiff.pomelo.filter;

import team.stiff.pomelo.handler.EventHandler;

public interface EventFilter<E>
{
    boolean test(final EventHandler p0, final E p1);
}
