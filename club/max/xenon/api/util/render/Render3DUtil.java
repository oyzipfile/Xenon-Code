// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.util.render;

import club.max.xenon.XenonClient;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.math.AxisAlignedBB;
import java.awt.Color;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.renderer.culling.ICamera;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;

public class Render3DUtil
{
    private static final Minecraft mc;
    
    public static void prepare(final float line) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(line);
    }
    
    public static void release() {
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void render3DSolid(final ICamera camera, final BlockPos blockpos, final int r, final int g, final int b, final int a) {
        render3DSolid(camera, blockpos.x, blockpos.y, blockpos.z, 1.0, 1.0, 1.0, r, g, b, a, 1.0f);
    }
    
    public static void render3DSolid(final ICamera camera, final BlockPos blockpos, final int r, final int g, final int b, final int a, final float line) {
        render3DSolid(camera, blockpos.x, blockpos.y, blockpos.z, 1.0, 1.0, 1.0, r, g, b, a, line);
    }
    
    public static void render3DSolid(final ICamera camera, final int x, final int y, final int z, final int r, final int g, final int b, final int a) {
        render3DSolid(camera, x, y, z, 1.0, 1.0, 1.0, r, g, b, a, 1.0f);
    }
    
    public static void render3DSolid(final ICamera camera, final int x, final int y, final int z, final int r, final int g, final int b, final int a, final float line) {
        render3DSolid(camera, x, y, z, 1.0, 1.0, 1.0, r, g, b, a, line);
    }
    
    public static void render3DSolid(final ICamera camera, final int x, final int y, final int z, final double offsetX, final double offsetY, final double offsetZ, final int r, final int g, final int b, final int a) {
        render3DSolid(camera, x, y, z, offsetX, offsetY, offsetZ, r, g, b, a, 1.0f);
    }
    
    public static void render3DSolid(final ICamera camera, final int x, final int y, final int z, final double offsetX, final double offsetY, final double offsetZ, final int r, final int g, final int b, final int a, final float line) {
        final Color color = new Color(r, g, b, a);
        final AxisAlignedBB bb = new AxisAlignedBB(x - Render3DUtil.mc.getRenderManager().viewerPosX, y - Render3DUtil.mc.getRenderManager().viewerPosY, z - Render3DUtil.mc.getRenderManager().viewerPosZ, x + offsetX - Render3DUtil.mc.getRenderManager().viewerPosX, y + offsetY - Render3DUtil.mc.getRenderManager().viewerPosY, z + offsetZ - Render3DUtil.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Render3DUtil.mc.getRenderViewEntity().posX, Render3DUtil.mc.getRenderViewEntity().posY, Render3DUtil.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(bb.minX + Render3DUtil.mc.getRenderManager().viewerPosX, bb.minY + Render3DUtil.mc.getRenderManager().viewerPosY, bb.minZ + Render3DUtil.mc.getRenderManager().viewerPosZ, bb.maxX + Render3DUtil.mc.getRenderManager().viewerPosX, bb.maxY + Render3DUtil.mc.getRenderManager().viewerPosY, bb.maxZ + Render3DUtil.mc.getRenderManager().viewerPosZ))) {
            prepare(line);
            RenderGlobal.renderFilledBox(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            release();
        }
    }
    
    public static void render3DOutline(final ICamera camera, final BlockPos blockpos, final int r, final int g, final int b, final int a) {
        render3DOutline(camera, blockpos.x, blockpos.y, blockpos.z, 1.0, 1.0, 1.0, r, g, b, a, 1.0f);
    }
    
    public static void render3DOutline(final ICamera camera, final BlockPos blockpos, final int r, final int g, final int b, final int a, final float line) {
        render3DOutline(camera, blockpos.x, blockpos.y, blockpos.z, 1.0, 1.0, 1.0, r, g, b, a, line);
    }
    
    public static void render3DOutline(final ICamera camera, final int x, final int y, final int z, final int r, final int g, final int b, final int a) {
        render3DOutline(camera, x, y, z, 1.0, 1.0, 1.0, r, g, b, a, 1.0f);
    }
    
    public static void render3DOutline(final ICamera camera, final int x, final int y, final int z, final int r, final int g, final int b, final int a, final float line) {
        render3DOutline(camera, x, y, z, 1.0, 1.0, 1.0, r, g, b, a, line);
    }
    
    public static void render3DOutline(final ICamera camera, final int x, final int y, final int z, final double offsetX, final double offsetY, final double offsetZ, final int r, final int g, final int b, final int a) {
        render3DOutline(camera, x, y, z, offsetX, offsetY, offsetZ, r, g, b, a, 1.0f);
    }
    
    public static void render3DOutline(final ICamera camera, final int x, final int y, final int z, final double offsetX, final double offsetY, final double offsetZ, final int r, final int g, final int b, final int a, final float line) {
        final Color color = new Color(r, g, b, a);
        final AxisAlignedBB bb = new AxisAlignedBB(x - Render3DUtil.mc.getRenderManager().viewerPosX, y - Render3DUtil.mc.getRenderManager().viewerPosY, z - Render3DUtil.mc.getRenderManager().viewerPosZ, x + offsetX - Render3DUtil.mc.getRenderManager().viewerPosX, y + offsetY - Render3DUtil.mc.getRenderManager().viewerPosY, z + offsetZ - Render3DUtil.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Render3DUtil.mc.getRenderViewEntity().posX, Render3DUtil.mc.getRenderViewEntity().posY, Render3DUtil.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(bb.minX + Render3DUtil.mc.getRenderManager().viewerPosX, bb.minY + Render3DUtil.mc.getRenderManager().viewerPosY, bb.minZ + Render3DUtil.mc.getRenderManager().viewerPosZ, bb.maxX + Render3DUtil.mc.getRenderManager().viewerPosX, bb.maxY + Render3DUtil.mc.getRenderManager().viewerPosY, bb.maxZ + Render3DUtil.mc.getRenderManager().viewerPosZ))) {
            prepare(line);
            RenderGlobal.drawBoundingBox(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            release();
        }
    }
    
    public static void render3DTop(final ICamera camera, final BlockPos pos, final int r, final int g, final int b, final int a) {
        render3DTop(camera, pos.x, pos.y, pos.z, r, g, b, a);
    }
    
    public static void render3DTop(final ICamera camera, final int x, final int y, final int z, final int r, final int g, final int b, final int a) {
        final Color color = new Color(r, g, b, a);
        final AxisAlignedBB bb = new AxisAlignedBB(x - Render3DUtil.mc.getRenderManager().viewerPosX, y + 0.95f - Render3DUtil.mc.getRenderManager().viewerPosY, z - Render3DUtil.mc.getRenderManager().viewerPosZ, x + 1 - Render3DUtil.mc.getRenderManager().viewerPosX, y + 1 - Render3DUtil.mc.getRenderManager().viewerPosY, z + 1 - Render3DUtil.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Render3DUtil.mc.getRenderViewEntity().posX, Render3DUtil.mc.getRenderViewEntity().posY, Render3DUtil.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(bb.minX + Render3DUtil.mc.getRenderManager().viewerPosX, bb.minY + Render3DUtil.mc.getRenderManager().viewerPosY, bb.minZ + Render3DUtil.mc.getRenderManager().viewerPosZ, bb.maxX + Render3DUtil.mc.getRenderManager().viewerPosX, bb.maxY + Render3DUtil.mc.getRenderManager().viewerPosY, bb.maxZ + Render3DUtil.mc.getRenderManager().viewerPosZ))) {
            prepare(0.1f);
            RenderGlobal.renderFilledBox(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ, color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            release();
        }
    }
    
    public static void drawGradientBox(final BlockPos pos, final Color startColor, final Color endColor) {
        final float red = startColor.getRed() / 255.0f;
        final float green = startColor.getGreen() / 255.0f;
        final float blue = startColor.getBlue() / 255.0f;
        final float alpha = startColor.getAlpha() / 255.0f;
        final float red2 = endColor.getRed() / 255.0f;
        final float green2 = endColor.getGreen() / 255.0f;
        final float blue2 = endColor.getBlue() / 255.0f;
        final float alpha2 = endColor.getAlpha() / 255.0f;
        final AxisAlignedBB bb = new AxisAlignedBB(pos.getX() - Render3DUtil.mc.getRenderManager().viewerPosX, pos.getY() - Render3DUtil.mc.getRenderManager().viewerPosY, pos.getZ() - Render3DUtil.mc.getRenderManager().viewerPosZ, pos.getX() + 1 - Render3DUtil.mc.getRenderManager().viewerPosX, pos.getY() + 1 - Render3DUtil.mc.getRenderManager().viewerPosY, pos.getZ() + 1 - Render3DUtil.mc.getRenderManager().viewerPosZ);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GlStateManager.shadeModel(7425);
        GL11.glBegin(7);
        GL11.glColor4f(red2, green2, blue2, alpha2);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glEnd();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.shadeModel(7424);
    }
    
    public static void drawGradientQuadVertical(final double x, final double y, final double z, final double x2, final double y2, final double z2, final float red, final float green, final float blue, final float alpha, final float red2, final float green2, final float blue2, final float alpha2) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder builder = tessellator.getBuffer();
        builder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        builder.pos(x, y, z).color(red, green, blue, alpha).endVertex();
        builder.pos(x, y2, z).color(red2, green2, blue2, alpha2).endVertex();
        builder.pos(x2, y, z2).color(red, green, blue, alpha).endVertex();
        builder.pos(x2, y2, z2).color(red2, green2, blue2, alpha2).endVertex();
        tessellator.draw();
    }
    
    public static void drawQuadHorizontal(final double x, final double y, final double z, final double x2, final double z2, final float red, final float green, final float blue, final float alpha) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder builder = tessellator.getBuffer();
        builder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        builder.pos(x, y, z).color(red, green, blue, alpha).endVertex();
        builder.pos(x, y, z2).color(red, green, blue, alpha).endVertex();
        builder.pos(x2, y, z).color(red, green, blue, alpha).endVertex();
        builder.pos(x2, y, z2).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
    }
    
    static {
        mc = XenonClient.mc;
    }
}
