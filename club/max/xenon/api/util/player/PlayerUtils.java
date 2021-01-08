// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.util.player;

import net.minecraft.util.math.BlockPos;
import net.minecraft.client.Minecraft;

public class PlayerUtils
{
    public static Minecraft mc;
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(PlayerUtils.mc.player.posX), Math.floor(PlayerUtils.mc.player.posY), Math.floor(PlayerUtils.mc.player.posZ));
    }
    
    static {
        PlayerUtils.mc = Minecraft.getMinecraft();
    }
}
