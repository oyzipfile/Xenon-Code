// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import club.max.xenon.api.util.player.MotionUtils;
import club.max.xenon.api.module.impl.Category;
import java.util.ArrayList;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class LongJump extends Module
{
    Value bypass;
    Value bypassMode;
    ArrayList<String> bypassModes;
    ArrayList<String> boostModes;
    Value boostMode;
    Value boost;
    ArrayList<String> calcs;
    Value calcMode;
    float boostSpeed;
    int boostSpeedInt;
    boolean jumped;
    boolean boostable;
    
    public LongJump() {
        super("Long jump", "Makes you jump really far", "longjump", Category.Movement);
        this.bypass = this.initValue("Bypass", "Bypass for certain servers", "bypass", true);
        (this.bypassModes = new ArrayList<String>()).add("Packet 2");
        this.bypassModes.add("Packet");
        this.bypassMode = this.initValue("Bypass Mode", "The mode for bypass", "bypassMode", this.bypassModes, "Packet");
        (this.boostModes = new ArrayList<String>()).add("Only ground");
        this.boostModes.add("Always");
        this.boostModes.add("Jump Event");
        this.boostMode = this.initValue("Boost Mode", "How it boosts you", "boostmode", this.boostModes, "Only ground");
        (this.calcs = new ArrayList<String>()).add("Dissolve");
        this.calcs.add("Constant");
        this.calcMode = this.initValue("Calc Mode", "The motion calcs to use", "calcmode", this.calcs, "Constant");
        this.boost = this.initValue("Boost Speed", "The speed value to use", "boostSpeed", 37, 1, 100);
        this.boostSpeed = 0.0f;
        this.jumped = false;
        this.boostable = false;
    }
    
    @Override
    public void onUpdateConstant() {
        super.onUpdateConstant();
        if (!this.bypass.getValue()) {
            this.bypassMode.setShown(false);
        }
        else {
            this.bypassMode.setShown(true);
        }
        this.boostSpeedInt = this.boost.getValue();
        this.boostSpeed = this.boostSpeedInt / 20.0f;
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.mc.player.onGround) {
            this.jumped = false;
            this.mc.player.motionX = 0.0;
            this.mc.player.motionZ = 0.0;
        }
        final double yaw = MotionUtils.getDirection();
        if (this.boostMode.getValue().equalsIgnoreCase("always")) {
            this.setMotion(yaw, this.boostSpeed);
        }
        else if (this.boostMode.getValue().equalsIgnoreCase("onlyground")) {
            if (this.mc.player.onGround && this.mc.player.moveForward != 0.0f) {
                if (this.calcMode.getValue().equalsIgnoreCase("constant")) {
                    this.setMotion(yaw, this.boostSpeed);
                }
                else {
                    this.mc.timer.tickLength = 50.0f;
                    this.mc.player.motionX = -Math.sin(yaw) * ((float)Math.sqrt(this.mc.player.motionX * this.mc.player.motionX + this.mc.player.motionZ * this.mc.player.motionZ) * (this.mc.player.onGround ? this.boostSpeed : 1.0f));
                    this.mc.player.motionZ = Math.cos(yaw) * ((float)Math.sqrt(this.mc.player.motionX * this.mc.player.motionX + this.mc.player.motionZ * this.mc.player.motionZ) * (this.mc.player.onGround ? this.boostSpeed : 1.0f));
                }
            }
            else {
                this.mc.timer.tickLength = 50.0f;
                this.mc.player.motionX = -Math.sin(yaw) * ((float)Math.sqrt(this.mc.player.motionX * this.mc.player.motionX + this.mc.player.motionZ * this.mc.player.motionZ) * (this.mc.player.onGround ? this.boostSpeed : 1.0f));
                this.mc.player.motionZ = Math.cos(yaw) * ((float)Math.sqrt(this.mc.player.motionX * this.mc.player.motionX + this.mc.player.motionZ * this.mc.player.motionZ) * (this.mc.player.onGround ? this.boostSpeed : 1.0f));
            }
        }
        else {
            this.mc.timer.tickLength = 50.0f;
            this.mc.player.motionX = -Math.sin(yaw) * ((float)Math.sqrt(this.mc.player.motionX * this.mc.player.motionX + this.mc.player.motionZ * this.mc.player.motionZ) * (this.boostable ? this.boostSpeed : 1.0f));
            this.mc.player.motionZ = Math.cos(yaw) * ((float)Math.sqrt(this.mc.player.motionX * this.mc.player.motionX + this.mc.player.motionZ * this.mc.player.motionZ) * (this.boostable ? this.boostSpeed : 1.0f));
            this.boostable = false;
        }
        if (this.mc.player.moveForward != 0.0f) {
            if (this.mc.player.onGround) {
                this.mc.player.jump();
            }
            if (this.bypass.getValue()) {
                if (this.bypassMode.getValue().equalsIgnoreCase("packet")) {
                    this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ, true));
                    this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX + this.mc.player.motionX, this.mc.player.posY + this.mc.player.motionY, this.mc.player.posZ + this.mc.player.motionZ, true));
                }
                else {
                    this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ, true));
                    this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ, true));
                    this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX + this.mc.player.motionX, this.mc.player.posY + this.mc.player.motionY, this.mc.player.posZ + this.mc.player.motionZ, true));
                    this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX + this.mc.player.motionX, this.mc.player.posY + this.mc.player.motionY, this.mc.player.posZ + this.mc.player.motionZ, true));
                }
            }
        }
        else if (this.mc.player.moveForward == 0.0f && this.mc.player.moveStrafing == 0.0f) {
            this.mc.player.motionX = 0.0;
            this.mc.player.motionZ = 0.0;
        }
    }
    
    @Override
    public void onDisable() {
        this.mc.player.motionX = 0.0;
        this.mc.player.motionZ = 0.0;
        super.onDisable();
    }
    
    public void setMotion(final double yaw, final float sped) {
        this.mc.player.motionX = -Math.sin(yaw) * sped;
        this.mc.player.motionZ = Math.cos(yaw) * sped;
    }
    
    @SubscribeEvent
    public void onJump(final LivingEvent.LivingJumpEvent event) {
        if (this.mc.player != null && this.mc.world != null && event.getEntity() == this.mc.player && (this.mc.player.movementInput.moveForward != 0.0f || this.mc.player.movementInput.moveStrafe != 0.0f)) {
            this.jumped = true;
            this.boostable = true;
        }
    }
    
    @Override
    public String getHudData() {
        return this.boostMode.getValue();
    }
}
