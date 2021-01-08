// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.render;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import java.awt.Color;
import club.max.xenon.api.util.render.Render3DUtil;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.util.math.RayTraceResult;
import club.max.xenon.api.module.management.ModuleManager;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.client.module.client.RainbowModule;
import java.util.ArrayList;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class BlockHighlight extends Module
{
    public Value mode;
    ArrayList<String> modes;
    public Value lineWidth;
    public Value rainbow;
    public Value red;
    public Value green;
    public Value blue;
    public Value alpha;
    RainbowModule rainbowModule;
    
    public BlockHighlight() {
        super("Block Highlight", "A better way to highlight the block you are looking at", "blockhighlight", Category.Render);
        (this.modes = new ArrayList<String>()).add("Outline");
        this.modes.add("Fill");
        this.modes.add("Both");
        this.modes.add("Top");
        this.modes.add("Gradient");
        this.mode = this.initValue("Mode", "The rendering mode", "mode", this.modes, "Both");
        this.lineWidth = this.initValue("Line Width", "The width of the outline", "lineWidth", 3, 1, 20);
        this.rainbow = this.initValue("Rainbow", "Renders the block heighlight as a rainbow", "rainbow", false);
        this.red = this.initValue("Red", "The red value for rainbow", "red", 255, 0, 255);
        this.green = this.initValue("Green", "The green value for rainbow", "green", 255, 0, 255);
        this.blue = this.initValue("Blue", "The blue value for rainbow", "blue", 255, 0, 255);
        this.alpha = this.initValue("Alpha", "The alpha value for rainbow", "alpha", 150, 0, 255);
        this.rainbowModule = (RainbowModule)ModuleManager.get("rainbow");
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.rainbow.getValue()) {
            final Value red = this.red;
            final RainbowModule rainbowModule = this.rainbowModule;
            red.setValue(RainbowModule.getColor().getRed());
            final Value green = this.green;
            final RainbowModule rainbowModule2 = this.rainbowModule;
            green.setValue(RainbowModule.getColor().getGreen());
            final Value blue = this.blue;
            final RainbowModule rainbowModule3 = this.rainbowModule;
            blue.setValue(RainbowModule.getColor().getBlue());
        }
        if (this.mode.getValue().equalsIgnoreCase("fill")) {
            this.lineWidth.setShown(false);
        }
        else {
            this.lineWidth.setShown(true);
        }
    }
    
    @Override
    public void onWorldRender3D() {
        super.onWorldRender3D();
        final RayTraceResult ray = this.mc.objectMouseOver;
        if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK) {
            final BlockPos pos = ray.getBlockPos();
            final AxisAlignedBB bb = this.mc.world.getBlockState(pos).getSelectedBoundingBox((World)this.mc.world, pos);
            if (bb != null && pos != null && this.mc.world.getBlockState(pos).getMaterial() != Material.AIR) {
                if (this.mode.getValue().equalsIgnoreCase("Fill")) {
                    Render3DUtil.render3DSolid(this.getCamera(), pos, this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue());
                }
                else if (this.mode.getValue().equalsIgnoreCase("Outline")) {
                    Render3DUtil.render3DOutline(this.getCamera(), pos, this.red.getValue(), this.green.getValue(), this.blue.getValue(), 255, this.lineWidth.getValue());
                }
                else if (this.mode.getValue().equalsIgnoreCase("both")) {
                    Render3DUtil.render3DSolid(this.getCamera(), pos, this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue());
                    Render3DUtil.render3DOutline(this.getCamera(), pos, this.red.getValue(), this.green.getValue(), this.blue.getValue(), 255, this.lineWidth.getValue());
                }
                else if (this.mode.getValue().equalsIgnoreCase("top")) {
                    Render3DUtil.render3DTop(this.getCamera(), pos.x, pos.y, pos.z, this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue());
                }
                else if (this.mode.getValue().equalsIgnoreCase("gradient")) {
                    Render3DUtil.drawGradientBox(pos, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), 0));
                }
            }
        }
    }
    
    @Override
    public String getHudData() {
        return this.mode.getValue();
    }
}
