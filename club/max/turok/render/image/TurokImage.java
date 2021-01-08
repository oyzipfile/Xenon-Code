// 
// Decompiled by Procyon v0.5.36
// 

package club.max.turok.render.image;

import net.minecraft.client.Minecraft;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import java.awt.image.BufferedImage;

public class TurokImage
{
    private String path;
    private BufferedImage bufferedImage;
    private ResourceLocation resourceLocation;
    private DynamicTexture dynamicTexture;
    
    public TurokImage(final String path) {
        this.path = path;
        try {
            this.bufferedImage = ImageIO.read(TurokImage.class.getResourceAsStream(this.path));
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
        this.dynamicTexture = new DynamicTexture(this.bufferedImage);
        this.resourceLocation = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("", this.dynamicTexture);
    }
    
    public int getWidth() {
        return this.bufferedImage.getWidth();
    }
    
    public int getHeight() {
        return this.bufferedImage.getHeight();
    }
    
    public String getPath() {
        return this.path;
    }
    
    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }
    
    public ResourceLocation getResourceLocation() {
        return this.resourceLocation;
    }
    
    public DynamicTexture getDynamicTexture() {
        return this.dynamicTexture;
    }
}
