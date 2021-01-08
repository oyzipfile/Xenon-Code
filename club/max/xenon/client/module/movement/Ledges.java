// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.movement;

import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.module.Module;

public class Ledges extends Module
{
    public Ledges() {
        super("Ledges", "Prevents you from falling off of blocks", "Ledges", Category.Movement);
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.START_SNEAKING));
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
    }
}
