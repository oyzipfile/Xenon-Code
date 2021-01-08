// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.mixin.mixins;

import io.netty.channel.ChannelHandlerContext;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import club.max.xenon.XenonClient;
import club.max.xenon.client.event.network.EventPacket;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.Packet;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { NetworkManager.class }, priority = 38631)
public class MixinNetworkManager
{
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    public void onPacketSend(final Packet<?> packet, final CallbackInfo ci) {
        final EventPacket.Send send = new EventPacket.Send(packet);
        XenonClient.EVENT_BUS.dispatch(send);
        if (send.isCancelled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "channelRead0" }, at = { @At("HEAD") }, cancellable = true)
    public void onPacketRead(final ChannelHandlerContext chc, final Packet<?> packet, final CallbackInfo ci) {
        final EventPacket.Receive send = new EventPacket.Receive(packet);
        XenonClient.EVENT_BUS.dispatch(send);
        if (send.isCancelled()) {
            ci.cancel();
        }
    }
}
