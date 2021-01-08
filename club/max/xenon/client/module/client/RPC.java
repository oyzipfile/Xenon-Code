// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.client;

import club.max.xenon.XenonClient;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.module.Module;

public class RPC extends Module
{
    public RPC() {
        super("Discord RPC", "Does the rpc thingie", "rpc", Category.Client);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        final String string = this.mc.player.getDisplayName() + " IS SOO COOL";
        XenonClient.rpc.update(string);
    }
}
