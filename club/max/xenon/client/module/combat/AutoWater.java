// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.combat;

import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import club.max.xenon.api.util.player.InventoryUtils;
import net.minecraft.init.Items;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.module.Module;

public class AutoWater extends Module
{
    public AutoWater() {
        super("Auto Water", "Automatically places a water bucket at your feet", "Autowater", Category.Combat);
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        InventoryUtils.switchToSlot(Items.WATER_BUCKET);
        if (this.mc.player.inventory.getCurrentItem().getItem() == Items.WATER_BUCKET) {
            final float oldYaw = this.mc.player.rotationPitch;
            this.mc.player.rotationPitch = 0.0f;
            this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(0.0f, 0.0f, true));
            this.mc.player.swingArm(EnumHand.MAIN_HAND);
            this.mc.playerController.processRightClick((EntityPlayer)this.mc.player, (World)this.mc.world, EnumHand.MAIN_HAND);
            this.mc.player.rotationPitch = oldYaw;
        }
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        InventoryUtils.switchToSlot(Items.BUCKET);
        if (this.mc.player.inventory.getCurrentItem().getItem() == Items.BUCKET) {
            final float oldYaw = this.mc.player.rotationPitch;
            this.mc.player.rotationPitch = 0.0f;
            this.mc.playerController.processRightClick((EntityPlayer)this.mc.player, (World)this.mc.world, EnumHand.MAIN_HAND);
            this.mc.player.swingArm(EnumHand.MAIN_HAND);
            this.mc.player.rotationPitch = oldYaw;
        }
    }
}
