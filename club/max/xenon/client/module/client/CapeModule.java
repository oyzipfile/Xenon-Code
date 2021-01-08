// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.client;

import club.max.cape.players.CapeUtils;
import club.max.xenon.api.module.impl.Category;
import club.max.cape.cape.CapeTypes;
import net.minecraft.entity.player.EntityPlayer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import club.max.xenon.api.module.Module;

public class CapeModule extends Module
{
    ArrayList<String> names;
    ConcurrentHashMap<EntityPlayer, CapeTypes> capedPlayers;
    
    public CapeModule() {
        super("Capes", "Renders capes on users", "capes", Category.Client);
        this.names = CapeUtils.getNames();
        this.capedPlayers = new ConcurrentHashMap<EntityPlayer, CapeTypes>();
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        this.capedPlayers = CapeUtils.getCapedPlayers(this.names);
    }
}
