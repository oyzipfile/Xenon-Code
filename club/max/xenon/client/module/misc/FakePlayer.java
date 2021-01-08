// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.misc;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.module.Module;

public class FakePlayer extends Module
{
    public FakePlayer() {
        super("Fake Player", "Creates a fake player for configging", "fakeplayer", Category.Misc);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        final EntityOtherPlayerMP fakePlayer = new EntityOtherPlayerMP((World)this.mc.world, new GameProfile(UUID.fromString("2da1acb3-1a8c-471f-a877-43f13cf37e6a"), "0IMAX"));
        fakePlayer.copyLocationAndAnglesFrom((Entity)this.mc.player);
        fakePlayer.rotationYawHead = this.mc.player.rotationYawHead;
        this.mc.world.addEntityToWorld(-100, (Entity)fakePlayer);
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.world.removeEntityFromWorld(-100);
    }
}
