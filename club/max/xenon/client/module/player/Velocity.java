// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.player;

import me.yagel15637.blitz.dispatcher.DispatcherEntry;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import club.max.xenon.client.event.network.EventPacket;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class Velocity extends Module
{
    public final Value horizontalMultiplier;
    public final Value verticalMultiplier;
    
    public Velocity() {
        super("Velocity", "Reduce the amount of knockback you take", "Velocity", Category.Player);
        this.horizontalMultiplier = this.initValue("Horizontal", "How much horizontal knockback to take", "Horizontal", 0, 0, 100);
        this.verticalMultiplier = this.initValue("Vertical", "How much vertical knockback to take", "Vertical", 0, 0, 100);
    }
    
    @DispatcherEntry
    public void onPacketReceive(final EventPacket.Receive event) {
        if (event.getPacket() instanceof SPacketEntityVelocity) {
            final SPacketEntityVelocity packet = (SPacketEntityVelocity)event.getPacket();
            if (packet.entityID == this.mc.player.entityId) {
                final SPacketEntityVelocity sPacketEntityVelocity = packet;
                sPacketEntityVelocity.motionX /= this.horizontalMultiplier.getValue();
                final SPacketEntityVelocity sPacketEntityVelocity2 = packet;
                sPacketEntityVelocity2.motionY /= this.verticalMultiplier.getValue();
                final SPacketEntityVelocity sPacketEntityVelocity3 = packet;
                sPacketEntityVelocity3.motionZ /= this.horizontalMultiplier.getValue();
            }
        }
    }
}
