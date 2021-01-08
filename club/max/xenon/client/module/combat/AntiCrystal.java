// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.combat;

import net.minecraft.entity.Entity;
import java.util.Iterator;
import club.max.xenon.api.util.world.BlockUtils;
import net.minecraft.util.EnumHand;
import club.max.xenon.api.util.player.InventoryUtils;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.entity.item.EntityEnderCrystal;
import club.max.xenon.api.module.impl.Category;
import java.util.ArrayList;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class AntiCrystal extends Module
{
    Value<Integer> delay;
    Value<Integer> range;
    Value<Boolean> rotate;
    Value<String> switchMode;
    ArrayList<String> switchmodes;
    int index;
    
    public AntiCrystal() {
        super("Anti Crystal", "Blocks crystal damage with pressure plates", "anticrystal", Category.Combat);
        this.delay = (Value<Integer>)this.initValue("Delay", "The delay for placement", "delay", 2, 1, 20);
        this.range = (Value<Integer>)this.initValue("Range", "The range for placement", "range", 4, 0, 10);
        this.rotate = (Value<Boolean>)this.initValue("Rotate", "Rotates for placement", "rotate", true);
        this.index = 0;
        (this.switchmodes = new ArrayList<String>()).add("Normal");
        this.switchmodes.add("Ghost");
        this.switchmodes.add("None");
        this.switchMode = (Value<String>)this.initValue("Switch Mode", "The mode to switch to the item with", "switchMode", this.switchmodes, "Normal");
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.index > 2000) {
            this.index = 0;
        }
        for (final EntityEnderCrystal cry : this.getNonPlaced()) {
            if (this.index % this.delay.getValue() == 0) {
                if (this.switchMode.getValue().equalsIgnoreCase("Normal")) {
                    InventoryUtils.switchToSlot(Item.getItemFromBlock(Blocks.WOODEN_PRESSURE_PLATE));
                }
                else if (this.switchMode.getValue().equalsIgnoreCase("Ghost")) {
                    InventoryUtils.switchToSlotGhost(Item.getItemFromBlock(Blocks.WOODEN_PRESSURE_PLATE));
                }
                else if (this.switchMode.getValue().equalsIgnoreCase("None")) {}
                if (this.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Item.getItemFromBlock(Blocks.WOODEN_PRESSURE_PLATE)) {
                    BlockUtils.placeBlock(cry.getPosition(), this.rotate.getValue());
                    return;
                }
                continue;
            }
        }
    }
    
    public ArrayList<EntityEnderCrystal> getCrystals() {
        final ArrayList<EntityEnderCrystal> crystals = new ArrayList<EntityEnderCrystal>();
        for (final Entity ent : this.mc.world.getLoadedEntityList()) {
            if (ent instanceof EntityEnderCrystal) {
                crystals.add((EntityEnderCrystal)ent);
            }
        }
        return crystals;
    }
    
    public ArrayList<EntityEnderCrystal> getInRange() {
        final ArrayList<EntityEnderCrystal> inRange = new ArrayList<EntityEnderCrystal>();
        for (final EntityEnderCrystal crystal : this.getCrystals()) {
            if (this.mc.player.getDistance((Entity)crystal) <= this.range.getValue()) {
                inRange.add(crystal);
            }
        }
        return inRange;
    }
    
    public ArrayList<EntityEnderCrystal> getNonPlaced() {
        final ArrayList<EntityEnderCrystal> returnOutput = new ArrayList<EntityEnderCrystal>();
        for (final EntityEnderCrystal crystal : this.getInRange()) {
            if (this.mc.world.getBlockState(crystal.getPosition()).getBlock() == Blocks.WOODEN_PRESSURE_PLATE) {
                continue;
            }
            returnOutput.add(crystal);
        }
        return returnOutput;
    }
}
