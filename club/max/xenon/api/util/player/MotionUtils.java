// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.util.player;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.Minecraft;

public class MotionUtils
{
    private static final Minecraft mc;
    
    public static double getDirection() {
        float rotationYaw = MotionUtils.mc.player.rotationYaw;
        if (MotionUtils.mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float forward = 1.0f;
        if (MotionUtils.mc.player.moveForward < 0.0f) {
            forward = -0.5f;
        }
        else if (MotionUtils.mc.player.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (MotionUtils.mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * forward;
        }
        if (MotionUtils.mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * forward;
        }
        return Math.toRadians(rotationYaw);
    }
    
    public static double[] transformStrafeMovement(final EntityPlayerSP entity) {
        double entity_rotation_yaw = entity.rotationYaw;
        final double entity_rotation_pitch = entity.rotationPitch;
        double entity_movement_forward = entity.movementInput.moveForward;
        double entity_movement_strafe = entity.movementInput.moveStrafe;
        if (entity_movement_forward != 0.0 && entity_movement_strafe != 0.0 && entity_movement_forward != 0.0) {
            if (entity_movement_strafe > 0.0) {
                entity_rotation_yaw += ((entity_movement_forward > 0.0) ? -45 : 45);
            }
            else if (entity_movement_strafe < 0.0) {
                entity_rotation_yaw += ((entity_movement_forward > 0.0) ? 45 : -45);
            }
            entity_movement_strafe = 0.0;
            if (entity_movement_forward > 0.0) {
                entity_movement_forward = 1.0;
            }
            else if (entity_movement_forward < 0.0) {
                entity_movement_forward = -1.0;
            }
        }
        return new double[] { entity_rotation_yaw, entity_rotation_pitch, entity_movement_forward, entity_movement_strafe };
    }
    
    public void jumpHigh() {
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
