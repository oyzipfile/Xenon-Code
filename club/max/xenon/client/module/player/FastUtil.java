// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.player;

import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.module.Module;

public class FastUtil extends Module
{
    public FastUtil() {
        super("Fast Util", "Allows you to use your utils really fast", "fastutil", Category.Misc);
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        try {
            if (this.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE || this.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.END_CRYSTAL) {
                this.mc.rightClickDelayTimer = 0;
            }
            else {
                this.mc.rightClickDelayTimer = 6;
            }
        }
        catch (Exception ex) {}
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        try {
            this.mc.rightClickDelayTimer = 6;
        }
        catch (Exception ex) {}
    }
}
