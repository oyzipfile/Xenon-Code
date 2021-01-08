// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.combat;

import club.max.xenon.api.util.player.InventoryUtils;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import club.max.xenon.api.module.impl.Category;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.EntityPlayer;
import java.util.ArrayList;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class AutoAnvil extends Module
{
    public Value height;
    public Value ghostSwitch;
    ArrayList<String> breakmodes;
    Value breakmode;
    public Value distance;
    boolean placingObby;
    boolean anviling;
    int heightFrom;
    EntityPlayer target;
    public BlockPos[] horiOffsets;
    
    public AutoAnvil() {
        super("Auto Anvil", "Automatically places anvils on enemies head", "AutoAnvil", Category.Combat);
        this.height = this.initValue("Height", "The height for the tower", "height", 4, 3, 6);
        this.ghostSwitch = this.initValue("Chost Switch", "Switches server side", "ghostSw", true);
        this.breakmodes = new ArrayList<String>();
        this.distance = this.initValue("Distance", "The max distance to target at", "distance", 30, 10, 80);
        this.placingObby = false;
        this.anviling = false;
        this.horiOffsets = new BlockPos[] { new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1) };
        this.breakmodes.add("None");
        this.breakmodes.add("Button");
        this.breakmodes.add("plate");
        this.breakmode = this.initValue("Break Mode", "how it breaks the anvil", "breakmode", this.breakmodes, "button");
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.placingObby && !this.anviling) {
            this.placingObby = true;
        }
        if (this.placingObby) {
            this.switchToItem(Item.getItemFromBlock(Blocks.OBSIDIAN));
        }
        if (this.anviling) {}
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.placingObby = false;
        this.anviling = false;
        this.target = null;
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.placingObby = false;
        this.anviling = false;
        this.target = null;
    }
    
    public void switchToItem(final Item item) {
        if (this.ghostSwitch.getValue()) {
            InventoryUtils.switchToSlotGhost(item);
        }
        else {
            InventoryUtils.switchToSlot(item);
        }
    }
    
    public BlockPos getPlaceableBlock(final EntityPlayer entityPlayer) {
        for (int i = 3; i > -1; --i) {
            for (final BlockPos blockPos : this.horiOffsets) {}
        }
        return null;
    }
}
