// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.module;

import club.max.xenon.XenonClient;
import club.max.xenon.api.util.chat.ChatUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import club.max.xenon.api.setting.Value;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import club.max.xenon.api.module.impl.Category;

public class Module
{
    public String name;
    public String desc;
    String tag;
    public Category category;
    public int key;
    public boolean toggled;
    public Minecraft mc;
    public ArrayList<Value> valueList;
    public boolean open;
    public boolean binding;
    public Value visible;
    public Value chatNotify;
    private ICamera camera;
    
    public Module(final String name, final String desc, final String tag, final Category category) {
        this.camera = (ICamera)new Frustum();
        this.valueList = new ArrayList<Value>();
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.key = -1;
        this.toggled = false;
        this.mc = Minecraft.getMinecraft();
        this.open = false;
        this.binding = false;
        this.tag = tag;
        this.visible = this.initValue("Visible", "Visible in Array List", "visible", true);
        this.chatNotify = this.initValue("Chat Notify", "Notifies you in chat when you toggle", "chatNotify", true);
    }
    
    public void onUpdate() {
    }
    
    public void onUpdateConstant() {
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        if (this.chatNotify.getValue()) {
            ChatUtil.print("Enabled : " + ChatFormatting.GREEN + this.name);
        }
        XenonClient.EVENT_BUS.register(this);
    }
    
    public void onDisable() {
        if (this.chatNotify.getValue()) {
            ChatUtil.print("Disabled : " + ChatFormatting.RED + this.name);
        }
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        XenonClient.EVENT_BUS.unregister(this);
    }
    
    public void onFrameRender() {
    }
    
    public void onWorldRender3D() {
    }
    
    public void onStartUp() {
    }
    
    public void enable() {
        this.toggled = true;
        this.onEnable();
    }
    
    public void disable() {
        this.toggled = false;
        this.onDisable();
    }
    
    public void toggle() {
        if (this.toggled) {
            this.disable();
        }
        else {
            this.enable();
        }
    }
    
    public void setOpen(final boolean open) {
        this.open = open;
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    public void setToggled(final boolean toggled) {
        if (toggled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
    }
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public void setDesc(final String desc) {
        this.desc = desc;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(final Category category) {
        this.category = category;
    }
    
    public Value initValue(final String name, final String desc, final String tag) {
        final Value val = new Value(name, tag, desc, this);
        this.valueList.add(val);
        return val;
    }
    
    public Value initValue(final String name, final String desc, final String tag, final boolean value) {
        final Value<Boolean> val = new Value<Boolean>(name, tag, desc, this, value);
        this.valueList.add(val);
        return val;
    }
    
    public Value initValue(final String name, final String desc, final String tag, final int value, final int min, final int max) {
        final Value<Integer> val = new Value<Integer>(name, tag, desc, this, value, min, max);
        this.valueList.add(val);
        return val;
    }
    
    public Value initValue(final String name, final String desc, final String tag, final double value, final double min, final double max) {
        final Value<Double> val = new Value<Double>(name, tag, desc, this, value, min, max);
        this.valueList.add(val);
        return val;
    }
    
    public Value initValue(final String name, final String desc, final String tag, final ArrayList<String> modes, final String modeValue) {
        final Value<String> val = new Value<String>(name, tag, desc, this, modes, modeValue);
        this.valueList.add(val);
        return val;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public ArrayList<Value> getValueList() {
        return this.valueList;
    }
    
    public ICamera getCamera() {
        return this.camera;
    }
    
    public String getHudData() {
        return "";
    }
    
    public void onSave() {
    }
}
