// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.combat;

import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.EnumFacing;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import club.max.xenon.api.util.world.BlockUtils;
import club.max.xenon.api.util.player.PlayerUtils;
import net.minecraft.util.NonNullList;
import java.util.List;
import java.util.Iterator;
import net.minecraft.util.EnumHand;
import club.max.xenon.api.util.player.InventoryUtils;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.setting.Value;
import java.util.ArrayList;
import club.max.xenon.api.module.Module;

public class AntiHoleFill extends Module
{
    ArrayList<String> switchModes;
    Value switchMode;
    Value rotate;
    Value delay;
    Value range;
    int delayIndex;
    
    public AntiHoleFill() {
        super("Anti Hole Fill", "Puts buttons in holes", "AntiHoleFill", Category.Combat);
        (this.switchModes = new ArrayList<String>()).add("None");
        this.switchModes.add("Normal");
        this.switchModes.add("Ghost");
        this.switchMode = this.initValue("Switch Mode", "How it switches to the item", "switchMode", this.switchModes, "Normal");
        this.rotate = this.initValue("Rotate", "Weather it rotates for placements or not", "rotate", true);
        this.range = this.initValue("Range", "The range to place at", "range", 4, 0, 10);
        this.delay = this.initValue("Delay", "How many ticks to wait after placing", "delay", 4, 1, 20);
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        ++this.delayIndex;
        if (this.delayIndex % this.delay.getValue() == 0) {
            final Iterator<BlockPos> iterator = this.getHoles().iterator();
            if (iterator.hasNext()) {
                final BlockPos pos = iterator.next();
                if (this.switchMode.getValue().equalsIgnoreCase("Normal")) {
                    InventoryUtils.switchToSlot(Item.getItemFromBlock(Blocks.WOODEN_PRESSURE_PLATE));
                }
                else if (this.switchMode.getValue().equalsIgnoreCase("Ghost")) {
                    InventoryUtils.switchToSlotGhost(Item.getItemFromBlock(Blocks.WOODEN_PRESSURE_PLATE));
                }
                else if (this.switchMode.getValue().equalsIgnoreCase("None")) {}
                if (this.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() != Item.getItemFromBlock(Blocks.WOODEN_PRESSURE_PLATE)) {
                    return;
                }
                this.place(pos, this.rotate.getValue());
            }
        }
    }
    
    public List<BlockPos> getHoles() {
        final NonNullList positions = NonNullList.create();
        positions.addAll((Collection)BlockUtils.getSphere(PlayerUtils.getPlayerPos(), this.range.getValue(), this.range.getValue(), false, true, 0).stream().filter((Predicate<? super Object>)BlockUtils::isHole).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)positions;
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.delayIndex = 0;
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.delayIndex = 0;
    }
    
    public void place(final BlockPos pos, final boolean rotate) {
        for (final EnumFacing side : EnumFacing.values()) {
            final BlockPos neighbor = pos.offset(side);
            final EnumFacing side2 = side.getOpposite();
            if (BlockUtils.canBeClicked(neighbor)) {
                final Vec3d hitVec = new Vec3d((Vec3i)neighbor).add(new Vec3d(0.5, 0.5, 0.5)).add(new Vec3d(side2.getDirectionVec()).scale(0.5));
                if (rotate) {
                    BlockUtils.faceVectorPacketInstant(hitVec);
                }
                this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                this.mc.playerController.processRightClickBlock(this.mc.player, this.mc.world, pos, side, hitVec, EnumHand.MAIN_HAND);
                this.mc.player.swingArm(EnumHand.MAIN_HAND);
                this.mc.rightClickDelayTimer = 0;
                this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                return;
            }
        }
    }
}
