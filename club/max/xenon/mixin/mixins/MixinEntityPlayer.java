// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import club.max.xenon.XenonClient;
import club.max.xenon.client.event.player.EventPlayerMove;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.MoverType;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityPlayerSP.class })
public class MixinEntityPlayer
{
    @Inject(method = { "move" }, at = { @At("HEAD") }, cancellable = true)
    public void move(final MoverType type, final double x, final double y, final double z, final CallbackInfo info) {
        final EventPlayerMove send = new EventPlayerMove(type, x, y, z);
        XenonClient.EVENT_BUS.dispatch(send);
        if (send.isCancelled()) {
            info.cancel();
        }
    }
}
