// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.module.management;

import club.max.xenon.api.module.impl.Category;
import java.util.Iterator;
import java.util.Comparator;
import club.max.xenon.XenonClient;
import club.max.xenon.client.module.combat.AntiCrystal;
import club.max.xenon.client.module.combat.AutoWater;
import club.max.xenon.client.module.combat.AntiHoleFill;
import club.max.xenon.client.module.combat.AutoAnvil;
import club.max.xenon.client.module.combat.AutoCrystal;
import club.max.xenon.client.module.combat.AutoOffhand;
import club.max.xenon.client.module.misc.ChatLogger;
import club.max.xenon.client.module.misc.FakePlayer;
import club.max.xenon.client.module.misc.ChatSuffix;
import club.max.xenon.client.module.player.XCarry;
import club.max.xenon.client.module.player.Velocity;
import club.max.xenon.client.module.player.FastUtil;
import club.max.xenon.client.module.render.ChatEdits;
import club.max.xenon.client.module.render.HoleEsp;
import club.max.xenon.client.module.render.BlockHighlight;
import club.max.xenon.client.module.render.FogColor;
import club.max.xenon.client.module.render.Fullbright;
import club.max.xenon.client.module.render.Viewmodel;
import club.max.xenon.client.module.render.Zoom;
import club.max.xenon.client.module.movement.Ledges;
import club.max.xenon.client.module.movement.LongJump;
import club.max.xenon.client.module.movement.YawLock;
import club.max.xenon.client.module.movement.Step;
import club.max.xenon.client.module.movement.ReverseStep;
import club.max.xenon.client.module.movement.Sprint;
import club.max.xenon.client.module.client.NewClickGuiForTutorial;
import club.max.xenon.client.module.client.ConsoleModule;
import club.max.xenon.client.module.client.RPC;
import club.max.xenon.client.module.client.Hud;
import club.max.xenon.client.module.client.ClickGuiModule;
import club.max.xenon.client.module.client.RainbowModule;
import club.max.xenon.api.module.Module;
import java.util.ArrayList;

public class ModuleManager
{
    static ArrayList<Module> modules;
    
    public ModuleManager() {
        ModuleManager.modules = new ArrayList<Module>();
    }
    
    public void addMod(final Module mod) {
        ModuleManager.modules.add(mod);
    }
    
    public void initMods() {
        this.addMod(new RainbowModule());
        this.addMod(new ClickGuiModule());
        this.addMod(new Hud());
        this.addMod(new RPC());
        this.addMod(new ConsoleModule());
        this.addMod(new NewClickGuiForTutorial());
        this.addMod(new Sprint());
        this.addMod(new ReverseStep());
        this.addMod(new Step());
        this.addMod(new YawLock());
        this.addMod(new LongJump());
        this.addMod(new Ledges());
        this.addMod(new Zoom());
        this.addMod(new Viewmodel());
        this.addMod(new Fullbright());
        this.addMod(new FogColor());
        this.addMod(new BlockHighlight());
        this.addMod(new HoleEsp());
        this.addMod(new ChatEdits());
        this.addMod(new FastUtil());
        this.addMod(new Velocity());
        this.addMod(new XCarry());
        this.addMod(new ChatSuffix());
        this.addMod(new FakePlayer());
        this.addMod(new FastUtil());
        this.addMod(new ChatLogger());
        this.addMod(new AutoOffhand());
        this.addMod(new AutoCrystal());
        this.addMod(new AutoAnvil());
        this.addMod(new AntiHoleFill());
        this.addMod(new AutoWater());
        this.addMod(new AntiCrystal());
        ModuleManager.modules.sort(Comparator.comparing(s -> -XenonClient.mc.fontRenderer.getStringWidth(s.name)));
    }
    
    public void onPlayerTick() {
        for (final Module module : ModuleManager.modules) {
            if (module.isToggled() && XenonClient.mc.player != null) {
                module.onUpdate();
            }
            module.onUpdateConstant();
        }
    }
    
    public void onRender2D() {
        for (final Module module : ModuleManager.modules) {
            if (module.isToggled() && XenonClient.mc.player != null) {
                module.onFrameRender();
            }
        }
    }
    
    public void onRender3D() {
        for (final Module module : ModuleManager.modules) {
            if (module.isToggled() && XenonClient.mc.player != null) {
                module.onWorldRender3D();
            }
        }
    }
    
    public void onKey(final int Key) {
        for (final Module module : ModuleManager.modules) {
            if (module.getKey() == Key) {
                System.out.println(module.getName());
                System.out.println(module.toggled);
                module.toggle();
            }
        }
    }
    
    public ArrayList<Module> getToggledModules() {
        final ArrayList<Module> toggledModules = new ArrayList<Module>();
        for (final Module module : ModuleManager.modules) {
            if (module.isToggled()) {
                toggledModules.add(module);
            }
        }
        return toggledModules;
    }
    
    public ArrayList<Module> getModulesByCategory(final Category category) {
        final ArrayList<Module> categoryModules = new ArrayList<Module>();
        for (final Module module : ModuleManager.modules) {
            if (module.getCategory().equals(category)) {
                categoryModules.add(module);
            }
        }
        return categoryModules;
    }
    
    public Module get(final Class clazz) {
        for (final Module module : ModuleManager.modules) {
            if (module.getClass() == clazz) {
                return module;
            }
        }
        return null;
    }
    
    public Module getByTag(final String tag) {
        for (final Module modules : ModuleManager.modules) {
            if (modules.getTag().equalsIgnoreCase(tag)) {
                return modules;
            }
        }
        return null;
    }
    
    public static Module get(final String tag) {
        for (final Module modules : ModuleManager.modules) {
            if (modules.getTag().equalsIgnoreCase(tag)) {
                return modules;
            }
        }
        return null;
    }
    
    public boolean isModuleEnabled(final Module module) {
        for (final Module module2 : ModuleManager.modules) {
            if (module2.getTag().equalsIgnoreCase(module.getTag())) {
                return module2.toggled;
            }
        }
        return false;
    }
    
    public ArrayList<Module> getModules() {
        return ModuleManager.modules;
    }
}
