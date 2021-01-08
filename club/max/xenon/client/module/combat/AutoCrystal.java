// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.combat;

import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class AutoCrystal extends Module
{
    Value place;
    Value placeRange;
    Value raytrace;
    Value placeDelay;
    Value chainLength;
    Value hit;
    Value weakness;
    Value hitAttempts;
    Value hitRange;
    Value hitDelay;
    boolean placing;
    boolean breaking;
    int attempt;
    int chain;
    
    public AutoCrystal() {
        super("Auto Crystal", "Automatically places and breaks crystals", "autocrystal", Category.Combat);
        this.place = this.initValue("Place", "Places crystals", "place", true);
        this.placeRange = this.initValue("Place Range", "The range at which to place crystals", "placerange", 4, 0, 6);
        this.raytrace = this.initValue("Raytrace", "Makes it easier to place through walls", "raytrace", true);
        this.placeDelay = this.initValue("Place Delay", "How fast it places", "placedelay", 0, 0, 20);
        this.chainLength = this.initValue("Chain length", "How many crystals to place in one spot before change", "chainlength", 2, 0, 10);
        this.hit = this.initValue("Break", "Breaks the crystals", "break", true);
        this.weakness = this.initValue("Anti Weakness", "Uses a sword to break crystals", "antiweakness", true);
        this.hitAttempts = this.initValue("Break Attempts", "How many times it break crystals (slows down)", "hitattempts", 1, 0, 20);
        this.hitRange = this.initValue("Break Range", "The range at which to break crystals", "breakrange", 4, 0, 6);
        this.hitDelay = this.initValue("Break Delay", "How fast it breaks", "breakdelay", 0, 0, 20);
    }
    
    public void runAura() {
    }
}
