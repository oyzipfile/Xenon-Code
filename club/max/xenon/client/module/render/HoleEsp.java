// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.render;

import club.max.xenon.api.util.render.Render3DUtil;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import club.max.xenon.api.util.world.BlockUtils;
import club.max.xenon.api.util.player.PlayerUtils;
import net.minecraft.util.NonNullList;
import java.util.Iterator;
import java.awt.Color;
import club.max.xenon.client.module.client.RainbowModule;
import club.max.xenon.api.module.impl.Category;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.util.math.BlockPos;
import java.util.List;
import java.util.ArrayList;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class HoleEsp extends Module
{
    public Value redSafe;
    public Value greenSafe;
    public Value blueSafe;
    public Value alphaSafe;
    public Value rainbowSafe;
    public Value red;
    public Value green;
    public Value blue;
    public Value alpha;
    public Value rainbow;
    public Value linWid;
    public Value renderMode;
    ArrayList<String> renderModes;
    public Value renderModeSelf;
    ArrayList<String> renderModesSelf;
    List<BlockPos> bedrock;
    List<BlockPos> obby;
    public Value range;
    private ConcurrentHashMap<BlockPos, Boolean> renderHoles;
    
    public HoleEsp() {
        super("Hole ESP", "Renders the safe holes", "HoleEsp", Category.Render);
        this.redSafe = this.initValue("Safe Red", "Red value for safe holes", "safeRed", 255, 0, 255);
        this.greenSafe = this.initValue("Safe Green", "Green value for safe holes", "safeGreen", 255, 0, 255);
        this.blueSafe = this.initValue("Safe Blue", "Blue value for safe holes", "safeBlue", 255, 0, 255);
        this.alphaSafe = this.initValue("Safe Alpha", "Alpha value for safe holes", "safeAlpha", 150, 0, 255);
        this.rainbowSafe = this.initValue("Safe Rainbow", "Rainbow for safe holes", "saferainbow", false);
        this.red = this.initValue("Red", "Red value for holes", "Red", 255, 0, 255);
        this.green = this.initValue("Green", "Green value for holes", "Green", 0, 0, 255);
        this.blue = this.initValue("Blue", "Blue value for holes", "Blue", 0, 0, 255);
        this.alpha = this.initValue("Alpha", "Alpha value for holes", "Alpha", 150, 0, 255);
        this.rainbow = this.initValue("Rainbow", "Rainbow for holes", "rainbow", false);
        this.linWid = this.initValue("Line Width", "The width for outlines", "linewidth", 5, 1, 10);
        (this.renderModes = new ArrayList<String>()).add("Outline");
        this.renderModes.add("Box");
        this.renderModes.add("Both");
        this.renderModes.add("Bottom");
        this.renderModes.add("Glow");
        (this.renderModesSelf = new ArrayList<String>()).add("Outline");
        this.renderModesSelf.add("Box");
        this.renderModesSelf.add("Both");
        this.renderModesSelf.add("Bottom");
        this.renderModesSelf.add("Glow");
        this.renderMode = this.initValue("Render Mode", "The mode to render the holes", "renderMode", this.renderModes, "Both");
        this.renderModeSelf = this.initValue("Render Mode Self", "Render mode for your own hole", "renderModeSelf", this.renderModesSelf, "Outline");
        this.range = this.initValue("Range", "The range to render the holes", "range", 10, 0, 30);
    }
    
    @Override
    public void onUpdateConstant() {
        super.onUpdateConstant();
        if (this.rainbow.getValue()) {
            this.red.setValue(RainbowModule.getColor().getRed());
            this.green.setValue(RainbowModule.getColor().getGreen());
            this.blue.setValue(RainbowModule.getColor().getBlue());
        }
        if (this.rainbowSafe.getValue()) {
            this.redSafe.setValue(RainbowModule.getColor().getRed());
            this.greenSafe.setValue(RainbowModule.getColor().getGreen());
            this.blueSafe.setValue(RainbowModule.getColor().getBlue());
        }
    }
    
    @Override
    public void onWorldRender3D() {
        super.onWorldRender3D();
        for (final BlockPos pos : this.getObbyHoles()) {
            this.render(pos, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()));
        }
        for (final BlockPos pos : this.getBedrockHoles()) {
            this.render(pos, new Color(this.redSafe.getValue(), this.greenSafe.getValue(), this.blueSafe.getValue(), this.alphaSafe.getValue()));
        }
    }
    
    public List<BlockPos> getObbyHoles() {
        final NonNullList positions = NonNullList.create();
        positions.addAll((Collection)BlockUtils.getSphere(PlayerUtils.getPlayerPos(), this.range.getValue(), this.range.getValue(), false, true, 0).stream().filter((Predicate<? super Object>)BlockUtils::IsObbyHole).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)positions;
    }
    
    public List<BlockPos> getBedrockHoles() {
        final NonNullList positions = NonNullList.create();
        positions.addAll((Collection)BlockUtils.getSphere(PlayerUtils.getPlayerPos(), this.range.getValue(), this.range.getValue(), false, true, 0).stream().filter((Predicate<? super Object>)BlockUtils::IsBRockHole).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)positions;
    }
    
    public void render(final BlockPos pos, final Color color) {
        if (PlayerUtils.getPlayerPos().getX() == pos.x && PlayerUtils.getPlayerPos().getZ() == pos.z && PlayerUtils.getPlayerPos().getY() == pos.y) {
            if (this.renderModeSelf.getValue().equalsIgnoreCase("outline")) {
                Render3DUtil.render3DOutline(this.getCamera(), pos, color.getRed(), color.getGreen(), color.getBlue(), 255, this.linWid.getValue());
            }
            else if (this.renderModeSelf.getValue().equalsIgnoreCase("Box")) {
                Render3DUtil.render3DSolid(this.getCamera(), pos, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
            }
            else if (this.renderModeSelf.getValue().equalsIgnoreCase("both")) {
                Render3DUtil.render3DSolid(this.getCamera(), pos, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
                Render3DUtil.render3DOutline(this.getCamera(), pos, color.getRed(), color.getGreen(), color.getBlue(), 255, this.linWid.getValue());
            }
            else if (this.renderModeSelf.getValue().equalsIgnoreCase("bottom")) {
                Render3DUtil.render3DTop(this.getCamera(), pos.add(0, -1, 0), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
            }
            else if (this.renderMode.getValue().equalsIgnoreCase("glow")) {
                Render3DUtil.drawGradientBox(pos.add(0, 0, 0), color, new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
            }
        }
        else if (this.renderMode.getValue().equalsIgnoreCase("outline")) {
            Render3DUtil.render3DOutline(this.getCamera(), pos, color.getRed(), color.getGreen(), color.getBlue(), 255, this.linWid.getValue());
        }
        else if (this.renderMode.getValue().equalsIgnoreCase("Box")) {
            Render3DUtil.render3DSolid(this.getCamera(), pos, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        }
        else if (this.renderMode.getValue().equalsIgnoreCase("both")) {
            Render3DUtil.render3DSolid(this.getCamera(), pos, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
            Render3DUtil.render3DOutline(this.getCamera(), pos, color.getRed(), color.getGreen(), color.getBlue(), 255, this.linWid.getValue());
        }
        else if (this.renderMode.getValue().equalsIgnoreCase("bottom")) {
            Render3DUtil.render3DTop(this.getCamera(), pos.add(0, -1, 0), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        }
        else if (this.renderMode.getValue().equalsIgnoreCase("glow")) {
            Render3DUtil.drawGradientBox(pos, color, new Color(color.getRed(), color.getGreen(), color.getBlue(), 0));
        }
    }
}
