// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.mixin;

import java.util.Map;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.launch.MixinBootstrap;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

public class XenonMixinLoader implements IFMLLoadingPlugin
{
    public XenonMixinLoader() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.rocan.json");
    }
    
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> map) {
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
}
