// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.player;

import me.yagel15637.blitz.dispatcher.DispatcherEntry;
import net.minecraft.network.play.client.CPacketCloseWindow;
import club.max.xenon.client.event.network.EventPacket;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.module.Module;

public class XCarry extends Module
{
    public XCarry() {
        super("X Carry", "Gives you 4 extra inventory slots", "xCarry", Category.Player);
    }
    
    @DispatcherEntry
    public void onSendPacket(final EventPacket.Send event) {
        if (event.getPacket() instanceof CPacketCloseWindow) {
            event.cancel();
        }
    }
}
