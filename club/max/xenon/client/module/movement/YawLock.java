// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.movement;

import club.max.xenon.api.module.impl.Category;
import java.util.ArrayList;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class YawLock extends Module
{
    Value mode;
    Value yaw;
    ArrayList<String> modes;
    
    public YawLock() {
        super("Yaw Lock", "Locks your yaw so you only go in one direction", "Yawlock", Category.Movement);
        (this.modes = new ArrayList<String>()).add("Multi Yaw");
        this.modes.add("Set Yaw");
        this.mode = this.initValue("Mode", "The mode for yaw locking", "mode", this.modes, "Multi Yaw");
        (this.yaw = this.initValue("Yaw", "The yaw to use for set yaw", "yaw", 180, 0, 360)).setShown(false);
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.mode.getValue().equalsIgnoreCase("Multi Yaw")) {
            this.yaw.setShown(false);
        }
        else if (this.mode.getValue().equalsIgnoreCase("Set Yaw")) {
            this.yaw.setShown(true);
            this.mc.player.rotationYaw = this.yaw.getValue();
        }
    }
}
