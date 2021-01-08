// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.gui.console;

import club.max.xenon.api.command.management.CommandManager;
import club.max.turok.hardware.mouse.TurokMouse;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;
import club.max.turok.render.font.management.TurokFontManager;
import club.max.xenon.client.module.client.ClickGuiModule;
import club.max.turok.render.opengl.TurokRenderGL;
import club.max.xenon.XenonClient;
import club.max.xenon.client.gui.component.frame.Frame;
import club.max.turok.render.font.TurokFont;
import club.max.turok.util.TurokRect;
import club.max.xenon.client.gui.component.Component;

public class InputFrame extends Component
{
    public int x;
    public int y;
    public int width;
    public int height;
    boolean typing;
    String typed;
    TurokRect inputRect;
    TurokFont fontRenderer;
    
    public InputFrame(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.inputRect = new TurokRect(x, y, width, height);
        this.typing = false;
        this.typed = "";
        this.fontRenderer = Frame.fontRenderer;
    }
    
    @Override
    public void render() {
        super.render();
        this.inputRect = new TurokRect(this.x, this.y, this.width, this.height);
        TurokRenderGL.color(XenonClient.gui.frame.color2.getRed(), XenonClient.gui.frame.color2.getGreen(), XenonClient.gui.frame.color2.getBlue(), XenonClient.gui.frame.color2.getAlpha());
        final TurokRect inputRect = this.inputRect;
        final ClickGuiModule clickGuiModule = (ClickGuiModule)XenonClient.moduleManager.getByTag("ClickGui");
        TurokRenderGL.drawRoundedRect(inputRect, ClickGuiModule.rounded.getValue());
        TurokRenderGL.color(255, 255, 255, 255);
        final int center = (this.height - TurokFontManager.getStringHeight(this.fontRenderer, this.typed)) / 2;
        TurokRenderGL.drawScissor(this.inputRect.x + 1, this.inputRect.y, this.inputRect.width - 2, this.inputRect.height);
        GL11.glEnable(3089);
        XenonClient.console.drawString(this.typed, this.x + 4, this.y + center, false, this.fontRenderer);
        GL11.glDisable(3089);
    }
    
    @Override
    public void onKey(final char typedChar, final int keyCode) {
        super.onKey(typedChar, keyCode);
        if (this.typing) {
            if (keyCode == 28) {
                this.flush();
            }
            else {
                String key = "";
                if (GuiScreen.isKeyComboCtrlV(keyCode)) {
                    key = GuiScreen.getClipboardString();
                }
                if (ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
                    key = typedChar + "";
                }
                else if ((keyCode == 211 || keyCode == 14) && this.typed.length() >= 1) {
                    this.typed = this.typed.substring(0, this.typed.length() - 1);
                }
                this.typed += key;
            }
        }
    }
    
    @Override
    public void onClick(final int MouseX, final int MouseY, final int Button) {
        super.onClick(MouseX, MouseY, Button);
        final TurokMouse mouse = new TurokMouse(MouseX, MouseY);
        if (this.inputRect.collideWithMouse(mouse)) {
            this.typing = true;
        }
        else {
            this.typing = false;
        }
    }
    
    public void setDims(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void flush() {
        this.runCommands(this.typed);
        this.typed = "";
    }
    
    public void runCommands(final String string) {
        XenonClient.console.outFrame.println("> " + string);
        final CommandManager commandManager = XenonClient.getCommandManager();
        final CommandManager commandManager2 = XenonClient.getCommandManager();
        final StringBuilder sb = new StringBuilder();
        XenonClient.getCommandManager();
        commandManager.onCommand(commandManager2.split(sb.append(CommandManager.getCommandPrefix().getPrefix()).append(string).toString()));
    }
}
