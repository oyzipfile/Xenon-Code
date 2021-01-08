// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.util.world;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import java.util.ArrayList;
import java.util.Comparator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.Minecraft;

public class TargetUtils
{
    static Minecraft mc;
    
    public static EntityPlayer getTarget(final double range) {
        final ArrayList<EntityPlayer> players = getPlayers();
        final float distance = 1000.0f;
        final EntityPlayer player = players.stream().filter(entity -> isInRange(entity, range)).min(Comparator.comparing(c -> TargetUtils.mc.player.getDistance(c))).orElse(null);
        return player;
    }
    
    public static ArrayList<EntityPlayer> getPlayers() {
        final ArrayList<EntityPlayer> players = new ArrayList<EntityPlayer>();
        if (TargetUtils.mc.player != null) {
            for (final Entity entity : TargetUtils.mc.world.getLoadedEntityList()) {
                if (entity instanceof EntityPlayer) {
                    players.add((EntityPlayer)entity);
                }
            }
        }
        return players;
    }
    
    public static boolean isInRange(final Entity entity, final double range) {
        return TargetUtils.mc.player.getDistance(entity) <= range;
    }
    
    static {
        TargetUtils.mc = Minecraft.getMinecraft();
    }
}
