// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.movement;

import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.setting.Value;
import java.util.ArrayList;
import club.max.xenon.api.module.Module;

public class Sprint extends Module
{
    ArrayList<String> modes;
    public Value mode;
    
    public Sprint() {
        super("Sprint", "Sprints for you", "sprint", Category.Movement);
        (this.modes = new ArrayList<String>()).add("Legit");
        this.modes.add("Always");
        this.modes.add("Forward and backward");
        this.mode = this.initValue("Sprint Mode", "The mode for the sprint", "sprint mode", this.modes, "Legit");
    }
    
    @Override
    public void onUpdate() {
        if (!this.mc.player.isSprinting()) {
            if (this.mode.getValue().equalsIgnoreCase("Legit")) {
                if (this.mc.player.moveForward > 0.0f) {
                    this.mc.player.setSprinting(true);
                }
            }
            else if (this.mode.getValue().equalsIgnoreCase("Always")) {
                this.mc.player.setSprinting(true);
            }
            else if (this.mode.getValue().equalsIgnoreCase("Forward and backward") && this.mc.player.moveForward != 0.0f) {
                this.mc.player.setSprinting(true);
            }
        }
    }
}
