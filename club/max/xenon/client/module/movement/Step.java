// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.movement;

import java.util.Iterator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class Step extends Module
{
    public Value blocks2;
    private final double[] one_block_height;
    private final double[] two_block_height;
    private double[] selected;
    private int packets;
    boolean stepping;
    boolean step2;
    
    public Step() {
        super("Step", "Allows you to step up 1 or 2 blocks", "step", Category.Movement);
        this.one_block_height = new double[] { 0.42, 0.75 };
        this.two_block_height = new double[] { 0.4, 0.75, 0.5, 0.41, 0.83, 1.16, 1.41, 1.57, 1.58, 1.42 };
        this.selected = new double[0];
        this.stepping = false;
        this.blocks2 = this.initValue("2 Blocks", "Allows you to step up 2 blocks", "2blocks", true);
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.mc.player.collidedHorizontally && this.mc.player.onGround && (this.mc.player.moveForward != 0.0f || this.mc.player.moveStrafing != 0.0f) && this.getNormal() <= 2.02) {
            this.stepping = true;
        }
        if (this.mc.player.collidedHorizontally && this.stepping) {
            if (this.getNormal() == 2.0) {
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.42, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.78, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.63, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.51, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.9, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.21, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.45, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.43, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.setPosition(this.mc.player.posX, this.mc.player.posY + 2.0, this.mc.player.posZ);
            }
            if (this.getNormal() == 1.5) {
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.41999998688698, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.7531999805212, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.00133597911214, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.16610926093821, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.24918707874468, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.1707870772188, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.setPosition(this.mc.player.posX, this.mc.player.posY + 1.0, this.mc.player.posZ);
            }
            if (this.getNormal() == 1.0) {
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.41999998688698, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.7531999805212, this.mc.player.posZ, this.mc.player.onGround));
                this.mc.player.setPosition(this.mc.player.posX, this.mc.player.posY + 1.0, this.mc.player.posZ);
            }
        }
        if (this.stepping && this.mc.player.onGround) {
            this.stepping = false;
        }
    }
    
    public double getNormal() {
        this.mc.player.stepHeight = 0.5f;
        double maxY = -1.0;
        final AxisAlignedBB grow = this.mc.player.getEntityBoundingBox().offset(0.0, 0.05, 0.0).grow(0.05);
        if (!this.mc.world.getCollisionBoxes((Entity)this.mc.player, grow.offset(0.0, 2.0, 0.0)).isEmpty()) {
            return 100.0;
        }
        for (final AxisAlignedBB aabb : this.mc.world.getCollisionBoxes((Entity)this.mc.player, grow)) {
            if (aabb.maxY > maxY) {
                maxY = aabb.maxY;
            }
        }
        return maxY - this.mc.player.posY;
    }
}
