// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.combat;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import club.max.xenon.api.util.player.InventoryUtils;
import net.minecraft.util.EnumHand;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.setting.Value;
import java.util.ArrayList;
import club.max.xenon.api.module.Module;

public class AutoOffhand extends Module
{
    int totems;
    boolean moving;
    boolean returnI;
    ArrayList<String> modes;
    Value soft;
    Value offhandItem;
    Value totemHealth;
    Value swordGap;
    
    public AutoOffhand() {
        super("Auto Offhand", "Automatically moves an item to your offhand", "autooffhand", Category.Combat);
        (this.modes = new ArrayList<String>()).add("Totem");
        this.modes.add("Crystal");
        this.modes.add("Golden Apple");
        this.modes.add("Bed");
        this.modes.add("Shield");
        this.soft = this.initValue("Soft", "Decides whether to use other items too", "soft", false);
        this.offhandItem = this.initValue("Item", "The item to use", "item", this.modes, "Totem");
        this.totemHealth = this.initValue("Totem Health", "The health at which to switch to a totem", "totemHealth", 8, 1, 36);
        this.swordGap = this.initValue("Sword Gap", "Switches to a golden apple when swording", "swordgap", true);
    }
    
    @Override
    public void onUpdateConstant() {
        super.onUpdateConstant();
        if (this.soft.getValue()) {
            this.offhandItem.setShown(true);
            this.totemHealth.setShown(true);
            this.swordGap.setShown(true);
        }
        else {
            this.offhandItem.setShown(false);
            this.totemHealth.setShown(false);
            this.swordGap.setShown(false);
        }
    }
    
    @Override
    public void onUpdate() {
        if (this.mc.player.getHeldItem(EnumHand.OFF_HAND).getItem() != this.getItem()) {
            InventoryUtils.moveItemToOffhand(this.getItem());
        }
        super.onUpdate();
    }
    
    public Item getItem() {
        Item item = Items.TOTEM_OF_UNDYING;
        if (this.mc.player.getHealth() + this.mc.player.getAbsorptionAmount() > this.totemHealth.getValue() && this.soft.getValue()) {
            if (this.offhandItem.getValue().equalsIgnoreCase("totem")) {
                item = Items.TOTEM_OF_UNDYING;
            }
            else if (this.offhandItem.getValue().equalsIgnoreCase("Crystal")) {
                item = Items.END_CRYSTAL;
            }
            else if (this.offhandItem.getValue().equalsIgnoreCase("Golden Apple")) {
                item = Items.GOLDEN_APPLE;
            }
            else if (this.offhandItem.getValue().equalsIgnoreCase("Bed")) {
                item = Items.BED;
            }
            else if (this.offhandItem.getValue().equalsIgnoreCase("Shield")) {
                item = Items.SHIELD;
            }
            if (this.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.DIAMOND_SWORD && this.swordGap.getValue()) {
                item = Items.GOLDEN_APPLE;
            }
        }
        else {
            item = Items.TOTEM_OF_UNDYING;
        }
        return item;
    }
    
    @Override
    public String getHudData() {
        if (this.soft.getValue()) {
            return this.offhandItem.getValue();
        }
        return "";
    }
}
