// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.movement;

import net.minecraft.client.entity.EntityPlayerSP;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class ReverseStep extends Module
{
    private Value value;
    
    public ReverseStep() {
        super("Reverse Step", "Makes you fall fast coming off of blocks", "reverseStep", Category.Movement);
        this.value = this.initValue("Force", "Apply force to fall", "Force", 0, 0, 10);
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.mc.player == null || this.mc.world == null || this.mc.player.isInWater() || this.mc.player.isInLava()) {
            return;
        }
        if (this.mc.player.onGround) {
            final EntityPlayerSP player = this.mc.player;
            player.motionY -= this.value.getValue();
        }
    }
}
