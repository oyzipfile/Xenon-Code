// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.event.network;

import me.yagel15637.blitz.modifiers.EventEra;
import net.minecraft.network.Packet;
import me.yagel15637.blitz.event.Event;

public class EventPacket extends Event
{
    private Packet packet;
    
    public EventPacket(final Packet packet, final EventEra era) {
        super(era);
        this.packet = packet;
    }
    
    public void setPacket(final Packet packet) {
        this.packet = packet;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public static class Send extends EventPacket
    {
        public Send(final Packet packet) {
            super(packet, EventEra.PRE);
        }
    }
    
    public static class Receive extends EventPacket
    {
        public Receive(final Packet packet) {
            super(packet, EventEra.PRE);
        }
    }
}
