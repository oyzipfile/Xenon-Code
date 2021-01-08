// 
// Decompiled by Procyon v0.5.36
// 

package club.max.turok.render.opengl;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import club.max.turok.util.TurokMath;
import java.awt.Color;
import club.max.turok.util.TurokRect;
import org.lwjgl.opengl.GL20;
import java.util.HashMap;
import club.max.turok.hardware.mouse.TurokMouse;
import club.max.turok.util.TurokDisplay;
import net.minecraft.client.Minecraft;

public class TurokRenderGL
{
    private static final Minecraft mc;
    private static TurokRenderGL INSTANCE;
    protected TurokDisplay display;
    protected TurokMouse mouse;
    protected int program;
    protected HashMap<String, Integer> uniforms;
    protected boolean isShaderInitializedWithoutErrors;
    public static final int TUROKGL_NULL = 0;
    public static final int TUROKGL_INIT = 1;
    public static final int TUROKGL_SHADER = 2;
    public static final int TUROKGL_UNIFORM_NULL = -1;
    
    public static void init() {
        TurokRenderGL.INSTANCE = new TurokRenderGL();
    }
    
    public static void init(final Object object) {
        if (object instanceof TurokDisplay) {
            TurokRenderGL.INSTANCE.display = (TurokDisplay)object;
        }
        if (object instanceof TurokMouse) {
            TurokRenderGL.INSTANCE.mouse = (TurokMouse)object;
        }
        if (object instanceof Integer) {
            switch ((int)object) {
                case 1: {
                    init();
                    break;
                }
                case 2: {
                    TurokRenderGL.INSTANCE.initializeShader();
                    break;
                }
            }
        }
    }
    
    public void initializeShader() {
        this.program = GL20.glCreateProgram();
        this.uniforms = new HashMap<String, Integer>();
        switch (TurokRenderGL.INSTANCE.program) {
            case 0: {
                System.err.println("Turok: Shader creation failed, returned " + TurokRenderGL.INSTANCE.program);
                this.isShaderInitializedWithoutErrors = false;
                break;
            }
            default: {
                this.isShaderInitializedWithoutErrors = true;
                break;
            }
        }
    }
    
    public static boolean isShaderProgramInitialized() {
        return TurokRenderGL.INSTANCE.isShaderInitializedWithoutErrors;
    }
    
    public static void drawOutlineRectFadingMouse(final TurokRect rect, final int radius, final Color color) {
        drawOutlineRectFadingMouse((float)rect.getX(), (float)rect.getY(), (float)rect.getWidth(), (float)rect.getHeight(), radius, color);
    }
    
    public static void drawOutlineRectFadingMouse(final float x, final float y, final float w, final float h, final int radius, final Color color) {
        final float offset = 0.5f;
        final float vx = x - TurokRenderGL.INSTANCE.mouse.getX();
        final float vy = y - TurokRenderGL.INSTANCE.mouse.getY();
        final float vw = x + w - TurokRenderGL.INSTANCE.mouse.getX();
        final float vh = y + h - TurokRenderGL.INSTANCE.mouse.getY();
        final int valueAlpha = color.getAlpha();
        enable(3042);
        blendFunc(770, 771);
        shaderMode(7425);
        color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        lineSize(1.0f);
        prepare(2);
        color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), valueAlpha - TurokMath.clamp(TurokMath.sqrt(vx * vx + vy * vy) / (radius / 100.0f), 0.0f, (float)valueAlpha));
        addVertex(x + offset, y);
        color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), valueAlpha - TurokMath.clamp(TurokMath.sqrt(vx * vx + vh * vh) / (radius / 100.0f), 0.0f, (float)valueAlpha));
        addVertex(x + offset, y + h + offset);
        color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), valueAlpha - TurokMath.clamp(TurokMath.sqrt(vw * vw + vh * vh) / (radius / 100.0f), 0.0f, (float)valueAlpha));
        addVertex(x + w, y + h);
        color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), valueAlpha - TurokMath.clamp(TurokMath.sqrt(vw * vw + vy * vy) / (radius / 100.0f), 0.0f, (float)valueAlpha));
        addVertex(x + w, y);
        release();
    }
    
    public static void drawSolidRectFadingMouse(final TurokRect rect, final int radius, final Color color) {
        drawSolidRectFadingMouse((float)rect.getX(), (float)rect.getY(), (float)rect.getWidth(), (float)rect.getHeight(), radius, color);
    }
    
    public static void drawSolidRectFadingMouse(final float x, final float y, final float w, final float h, final int radius, final Color color) {
        final float vx = x - TurokRenderGL.INSTANCE.mouse.getX();
        final float vy = y - TurokRenderGL.INSTANCE.mouse.getY();
        final float vw = x + w - TurokRenderGL.INSTANCE.mouse.getX();
        final float vh = y + h - TurokRenderGL.INSTANCE.mouse.getY();
        final int valueAlpha = color.getAlpha();
        enable(3042);
        blendFunc(770, 771);
        prepare(7);
        color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), valueAlpha - TurokMath.clamp(TurokMath.sqrt(vx * vx + vy * vy) / (radius / 100.0f), 0.0f, (float)valueAlpha));
        addVertex(x, y);
        color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), valueAlpha - TurokMath.clamp(TurokMath.sqrt(vx * vx + vh * vh) / (radius / 100.0f), 0.0f, (float)valueAlpha));
        addVertex(x, y + h);
        color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), valueAlpha - TurokMath.clamp(TurokMath.sqrt(vw * vw + vh * vh) / (radius / 100.0f), 0.0f, (float)valueAlpha));
        addVertex(x + w, y + h);
        color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), valueAlpha - TurokMath.clamp(TurokMath.sqrt(vw * vw + vy * vy) / (radius / 100.0f), 0.0f, (float)valueAlpha));
        addVertex(x + w, y);
        release();
    }
    
    public static void drawScissor(final int x, final int y, final int w, final int h) {
        final int calculatedX = x;
        final int calculatedY = y;
        final int calculatedW = calculatedX + w;
        final int calculatedH = calculatedY + h;
        GL11.glScissor(calculatedX * TurokRenderGL.INSTANCE.display.getScaleFactor(), TurokRenderGL.INSTANCE.display.getHeight() - calculatedH * TurokRenderGL.INSTANCE.display.getScaleFactor(), (calculatedW - calculatedX) * TurokRenderGL.INSTANCE.display.getScaleFactor(), (calculatedH - calculatedY) * TurokRenderGL.INSTANCE.display.getScaleFactor());
    }
    
    public static void drawTexture(final float x, final float y, final float width, final float height) {
        prepare(7);
        sewTexture(0, 0);
        addVertex(x, y);
        sewTexture(0, 1);
        addVertex(x, y + height);
        sewTexture(1, 1);
        addVertex(x + width, y + height);
        sewTexture(1, 0);
        addVertex(x + width, y);
        release();
    }
    
    public static void drawTextureInterpolated(final float x, final float y, final float xx, final float yy, final float width, final float height, final float ww, final float hh) {
        prepare(7);
        sewTexture(0.0f + xx, 0.0f + hh);
        addVertex(x, y);
        sewTexture(0.0f + xx, 1.0f + hh);
        addVertex(x, y + height);
        sewTexture(1.0f + ww, 1.0f + hh);
        addVertex(x + width, y + height);
        sewTexture(1.0f + ww, 0.0f + hh);
        addVertex(x + width, y);
        release();
    }
    
    public static void drawUpTriangle(final float x, final float y, final float width, final float height, final int offsetX) {
        enable(3042);
        blendFunc(770, 771);
        prepare(6);
        addVertex(x + width, y + height);
        addVertex(x + width, y);
        addVertex(x - offsetX, y);
        release();
    }
    
    public static void drawDownTriangle(final float x, final float y, final float width, final float height, final int offsetX) {
        enable(3042);
        blendFunc(770, 771);
        prepare(6);
        addVertex(x, y);
        addVertex(x, y + height);
        addVertex(x + width + offsetX, y + height);
        release();
    }
    
    public static void drawArc(final float cx, final float cy, final float r, final float start_angle, final float end_angle, final float num_segments) {
        prepare(4);
        for (int i = (int)(num_segments / (360.0f / start_angle)) + 1; i <= num_segments / (360.0f / end_angle); ++i) {
            final double previousAngle = 6.283185307179586 * (i - 1) / num_segments;
            final double angle = 6.283185307179586 * i / num_segments;
            addVertex(cx, cy);
            addVertex(cx + Math.cos(angle) * r, cy + Math.sin(angle) * r);
            addVertex(cx + Math.cos(previousAngle) * r, cy + Math.sin(previousAngle) * r);
        }
        release();
    }
    
    public static void drawArc(final float x, final float y, final float radius) {
        drawArc(x, y, radius, 0.0f, 360.0f, 40.0f);
    }
    
    public static void drawArcOutline(final float cx, final float cy, final float r, final float start_angle, final float end_angle, final float num_segments) {
        prepare(3);
        for (int i = (int)(num_segments / (360.0f / start_angle)) + 1; i <= num_segments / (360.0f / end_angle); ++i) {
            final double angle = 6.283185307179586 * i / num_segments;
            addVertex(cx + Math.cos(angle) * r, cy + Math.sin(angle) * r);
        }
        release();
    }
    
    public static void drawArcOutline(final float x, final float y, final float radius) {
        drawArcOutline(x, y, radius, 0.0f, 360.0f, 40.0f);
    }
    
    public static void drawOutlineRect(final float x, final float y, final float width, final float height) {
        final float offset = 0.5f;
        enable(3042);
        blendFunc(770, 771);
        prepare(2);
        addVertex(x + offset, y);
        addVertex(x + offset, y + height + offset);
        addVertex(x + width, y + height + offset);
        addVertex(x + width, y);
        release();
    }
    
    public static void drawOutlineRect(final int x, final int y, final int width, final int height) {
        drawOutlineRect((float)x, (float)y, (float)width, (float)height);
    }
    
    public static void drawOutlineRect(final TurokRect rect) {
        drawOutlineRect((float)rect.getX(), (float)rect.getY(), (float)rect.getWidth(), (float)rect.getHeight());
    }
    
    public static void drawOutlineRoundedRect(final float x, final float y, final float width, final float height, final float radius, final float dR, final float dG, final float dB, final float dA, final float line_width) {
        drawRoundedRect(x, y, width, height, radius);
        color(dR, dG, dB, dA);
        drawRoundedRect(x + line_width, y + line_width, width - line_width * 2.0f, height - line_width * 2.0f, radius);
    }
    
    public static void drawRoundedRect(final float x, final float y, final float width, final float height, final float radius) {
        enable(3042);
        blendFunc(770, 771);
        drawArc(x + width - radius, y + height - radius, radius, 0.0f, 90.0f, 16.0f);
        drawArc(x + radius, y + height - radius, radius, 90.0f, 180.0f, 16.0f);
        drawArc(x + radius, y + radius, radius, 180.0f, 270.0f, 16.0f);
        drawArc(x + width - radius, y + radius, radius, 270.0f, 360.0f, 16.0f);
        prepare(4);
        addVertex(x + width - radius, y);
        addVertex(x + radius, y);
        addVertex(x + width - radius, y + radius);
        addVertex(x + width - radius, y + radius);
        addVertex(x + radius, y);
        addVertex(x + radius, y + radius);
        addVertex(x + width, y + radius);
        addVertex(x, y + radius);
        addVertex(x, y + height - radius);
        addVertex(x + width, y + radius);
        addVertex(x, y + height - radius);
        addVertex(x + width, y + height - radius);
        addVertex(x + width - radius, y + height - radius);
        addVertex(x + radius, y + height - radius);
        addVertex(x + width - radius, y + height);
        addVertex(x + width - radius, y + height);
        addVertex(x + radius, y + height - radius);
        addVertex(x + radius, y + height);
        release();
    }
    
    public static void drawRoundedRectLine(final float x, final float y, final float width, final float height, final float radius, final float thick) {
        final float oldLinWid = GL11.glGetFloat(2849);
        enable(3042);
        GL11.glLineWidth(thick);
        drawArcOutline(x + width - radius, y + height - radius, radius, 0.0f, 90.0f, 40.0f);
        drawArcOutline(x + radius, y + height - radius, radius, 90.0f, 180.0f, 40.0f);
        drawArcOutline(x + radius, y + radius, radius, 180.0f, 270.0f, 40.0f);
        drawArcOutline(x + width - radius, y + radius, radius, 270.0f, 360.0f, 40.0f);
        prepare(1);
        addVertex(x + radius, y);
        addVertex(x + width - radius, y);
        addVertex(x + width, y + radius);
        addVertex(x + width, y + height - radius);
        addVertex(x + radius, y + height);
        addVertex(x + width - radius, y + height);
        addVertex(x, y + radius);
        addVertex(x, y + height - radius);
        release();
        GL11.glLineWidth(oldLinWid);
    }
    
    public static void drawRoundedRect(final TurokRect rect, final float size) {
        drawRoundedRect((float)rect.getX(), (float)rect.getY(), (float)rect.getWidth(), (float)rect.getHeight(), size);
    }
    
    public static void drawGradientSolidRectVertical(final float x, final float y, final float width, final float height, final Color colorTop, final Color colorBottom) {
    }
    
    public static void drawSolidRect(final float x, final float y, final float width, final float height) {
        enable(3042);
        blendFunc(770, 771);
        prepare(7);
        addVertex(x, y);
        addVertex(x, y + height);
        addVertex(x + width, y + height);
        addVertex(x + width, y);
        release();
    }
    
    public static void drawSolidRect(final int x, final int y, final int width, final int height) {
        drawSolidRect((float)x, (float)y, (float)width, (float)height);
    }
    
    public static void drawSolidRect(final TurokRect rect) {
        drawSolidRect((float)rect.getX(), (float)rect.getY(), (float)rect.getWidth(), (float)rect.getHeight());
    }
    
    public static void drawLine(final int x, final int y, final int x1, final int xy, final float line) {
        enableAlphaBlend();
        lineSize(line);
        prepare(2848);
        addVertex(x, y);
        addVertex(x1, xy);
        release();
    }
    
    public static void drawLine3D(final double x, final double y, final double z, final double x1, final double y1, final double z1, final int r, final int g, final int b, final int a, final float line) {
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        lineSize(line);
        enable(2848);
        hint(3154, 4354);
        GlStateManager.disableDepth();
        enable(34383);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(x, y, z).color(r, g, b, a).endVertex();
        bufferBuilder.pos(x1, y1, z1).color(r, g, b, a).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        disable(2848);
        GlStateManager.enableDepth();
        disable(34383);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }
    
    public static void autoScale() {
        pushMatrix();
        translate(TurokRenderGL.INSTANCE.display.getScaledWidth(), TurokRenderGL.INSTANCE.display.getScaledHeight());
        scale(0.5f, 0.5f, 0.5f);
        popMatrix();
    }
    
    public static void addVertexShader(final String text) {
        try {
            addProgram(text, 35633);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    
    public static void addGeometryShader(final String text) {
        try {
            addProgram(text, 36313);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    
    public static void addFragmentShader(final String text) {
        try {
            addProgram(text, 35632);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    
    public static void addTessellationControlShader(final String text) {
        try {
            addProgram(text, 36488);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    
    public static void addTessellationEvaluationShader(final String text) {
        try {
            addProgram(text, 36487);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    
    public void addComputeShader(final String text) {
        try {
            addProgram(text, 37305);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    
    public static void compileShader() throws Exception {
        GL20.glLinkProgram(TurokRenderGL.INSTANCE.program);
        if (GL20.glGetProgrami(TurokRenderGL.INSTANCE.program, 35714) == 0) {
            throw new Exception("Turok: Failed to compile shader, " + TurokRenderGL.INSTANCE.getClass().getName() + " " + GL20.glGetProgramInfoLog(TurokRenderGL.INSTANCE.program, 1024));
        }
        GL20.glValidateProgram(TurokRenderGL.INSTANCE.program);
        if (GL20.glGetProgrami(TurokRenderGL.INSTANCE.program, 35715) == 0) {
            throw new Exception("Turok: Failed to compile shader, " + TurokRenderGL.INSTANCE.getClass().getName() + " " + GL20.glGetProgramInfoLog(TurokRenderGL.INSTANCE.program, 1024));
        }
    }
    
    public static void addUniform(final String uniform) throws Exception {
        final int uniformLocation = GL20.glGetUniformLocation(TurokRenderGL.INSTANCE.program, (CharSequence)uniform);
        if (uniformLocation != -1) {
            TurokRenderGL.INSTANCE.uniforms.put(uniform, uniformLocation);
            return;
        }
        throw new Exception("Turok: Failed to load uniform.");
    }
    
    public static void addProgram(final String program, final int type) throws Exception {
        final int shader = GL20.glCreateShader(type);
        if (shader == 0) {
            throw new Exception("Turok: Failed to load shader.");
        }
        GL20.glShaderSource(shader, (CharSequence)program);
        GL20.glCompileShader(shader);
        if (GL20.glGetShaderi(shader, 35713) == 0) {
            GL20.glAttachShader(TurokRenderGL.INSTANCE.program, shader);
            return;
        }
        throw new Exception("Turok: " + TurokRenderGL.INSTANCE.getClass().getName() + " " + GL20.glGetShaderInfoLog(shader, 1024));
    }
    
    public static void bind() {
        GL20.glUseProgram(TurokRenderGL.INSTANCE.program);
    }
    
    public static void color(final float r, final float g, final float b, final float a) {
        GL11.glColor4f(r / 255.0f, g / 255.0f, b / 255.0f, a / 255.0f);
    }
    
    public static void color(final double r, final double g, final double b, final double a) {
        GL11.glColor4f((float)r / 255.0f, (float)g / 255.0f, (float)b / 255.0f, (float)a / 255.0f);
    }
    
    public static void color(final int r, final int g, final int b, final int a) {
        GL11.glColor4f(r / 255.0f, g / 255.0f, b / 255.0f, a / 255.0f);
    }
    
    public static void color(final float r, final float g, final float b) {
        GL11.glColor3f(r / 255.0f, g / 255.0f, b / 255.0f);
    }
    
    public static void color(final double r, final double g, final double b) {
        GL11.glColor3f((float)r / 255.0f, (float)g / 255.0f, (float)b / 255.0f);
    }
    
    public static void color(final int r, final int g, final int b) {
        GL11.glColor3f(r / 255.0f, g / 255.0f, b / 255.0f);
    }
    
    public static void prepare(final int mode) {
        GL11.glBegin(mode);
    }
    
    public static void release() {
        GL11.glEnd();
    }
    
    public static void sewTexture(final float s, final float t, final float r) {
        GL11.glTexCoord3f(s, t, r);
    }
    
    public static void sewTexture(final float s, final float t) {
        GL11.glTexCoord2f(s, t);
    }
    
    public static void sewTexture(final float s) {
        GL11.glTexCoord1f(s);
    }
    
    public static void sewTexture(final double s, final double t, final double r) {
        sewTexture((float)s, (float)t, (float)r);
    }
    
    public static void sewTexture(final double s, final double t) {
        sewTexture((float)s, (float)t);
    }
    
    public static void sewTexture(final double s) {
        sewTexture((float)s);
    }
    
    public static void sewTexture(final int s, final int t, final int r) {
        sewTexture((float)s, (float)t, (float)r);
    }
    
    public static void sewTexture(final int s, final int t) {
        sewTexture((float)s, (float)t);
    }
    
    public static void sewTexture(final int s) {
        sewTexture((float)s);
    }
    
    public static void addVertex(final float x, final float y, final float z) {
        sewTexture(x, y, z);
    }
    
    public static void addVertex(final float x, final float y) {
        GL11.glVertex2f(x, y);
    }
    
    public static void addVertex(final double x, final double y, final double z) {
        addVertex((float)x, (float)y, (float)z);
    }
    
    public static void addVertex(final double x, final double y) {
        addVertex((float)x, (float)y);
    }
    
    public static void addVertex(final int x, final int y, final int z) {
        addVertex((float)x, (float)y, (float)z);
    }
    
    public static void addVertex(final int x, final int y) {
        addVertex((float)x, (float)y);
    }
    
    public static void hint(final int target, final int target1) {
        GL11.glHint(target, target1);
    }
    
    public static void translate(final float x, final float y, final float z) {
        GL11.glTranslated((double)x, (double)y, (double)z);
    }
    
    public static void translate(final double x, final double y, final double z) {
        GL11.glTranslated(x, y, z);
    }
    
    public static void translate(final int x, final int y, final int z) {
        GL11.glTranslated((double)x, (double)y, (double)z);
    }
    
    public static void translate(final float x, final float y) {
        GL11.glTranslated((double)x, (double)y, 0.0);
    }
    
    public static void translate(final double x, final double y) {
        GL11.glTranslated(x, y, 0.0);
    }
    
    public static void translate(final int x, final int y) {
        GL11.glTranslated((double)x, (double)y, 0.0);
    }
    
    public static void scale(final float scaledPosX, final float scaledPosY, final float scaledPosZ) {
        GL11.glScaled((double)scaledPosX, (double)scaledPosY, (double)scaledPosZ);
    }
    
    public static void scale(final double scaledPosX, final double scaledPosY, final double scaledPosZ) {
        GL11.glScaled(scaledPosX, scaledPosY, scaledPosZ);
    }
    
    public static void scale(final int scaledPosX, final int scaledPosY, final int scaledPosZ) {
        GL11.glScaled((double)scaledPosX, (double)scaledPosY, (double)scaledPosZ);
    }
    
    public static void lineSize(final float width) {
        GL11.glLineWidth(width);
    }
    
    public static void pushMatrix() {
        GL11.glPushMatrix();
    }
    
    public static void popMatrix() {
        GL11.glPopMatrix();
    }
    
    public static void enable(final int glState) {
        GL11.glEnable(glState);
    }
    
    public static void disable(final int glState) {
        GL11.glDisable(glState);
    }
    
    public static void blendFunc(final int glState, final int glState1) {
        GL11.glBlendFunc(glState, glState1);
    }
    
    public static void polygonOffset(final float factor, final float units) {
        GL11.glPolygonOffset(factor, units);
    }
    
    public static void polygonOffset(final double factor, final double units) {
        GL11.glPolygonOffset((float)factor, (float)units);
    }
    
    public static void polygonOffset(final int factor, final int units) {
        GL11.glPolygonOffset((float)factor, (float)units);
    }
    
    public static void polygonMode(final int face, final int mode) {
        GL11.glPolygonMode(face, mode);
    }
    
    public static void shaderMode(final int mode) {
        GL11.glShadeModel(mode);
    }
    
    public static void enableAlphaBlend() {
        enable(3042);
        blendFunc(770, 771);
    }
    
    public static void disableAlphaBlend() {
        disable(3042);
    }
    
    public static void prepareOverlay() {
    }
    
    public static void releaseOverlay() {
    }
    
    public static void prepare3D(final float size) {
        blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth(size);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
    }
    
    public static void release3D() {
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
