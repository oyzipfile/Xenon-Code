// 
// Decompiled by Procyon v0.5.36
// 

package club.max.cape;

import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

public class Cape
{
    static Minecraft mc;
    
    public void runCapes() {
        for (final Entity entity : Cape.mc.world.getLoadedEntityList()) {
            if (entity instanceof EntityPlayer) {
                final EntityPlayer entityPlayer = (EntityPlayer)entity;
            }
        }
    }
    
    static {
        Cape.mc = Minecraft.getMinecraft();
    }
}
