// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.gui.component.frame;

import org.lwjgl.input.Keyboard;
import java.util.Iterator;
import club.max.xenon.client.gui.component.Components.IntegerComponent;
import club.max.xenon.client.gui.component.Components.ModeComponent;
import club.max.xenon.client.gui.component.Components.BooleanComponent;
import club.max.xenon.client.gui.component.Components.KeybindComponent;
import club.max.xenon.api.setting.Value;
import club.max.turok.render.font.management.TurokFontManager;
import org.lwjgl.opengl.GL11;
import club.max.turok.render.opengl.TurokRenderGL;
import org.lwjgl.input.Mouse;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Font;
import club.max.xenon.client.module.client.ClickGuiModule;
import club.max.xenon.XenonClient;
import club.max.turok.util.TurokRect;
import club.max.turok.render.font.TurokFont;
import java.awt.Color;
import club.max.xenon.api.module.impl.Category;
import club.max.xenon.api.module.Module;
import java.util.ArrayList;
import club.max.xenon.client.gui.component.Component;

public class Frame extends Component
{
    int x;
    int y;
    public int width;
    int height;
    int heightBar;
    int offsetX;
    ArrayList<CategoryButton> subcomponents;
    ArrayList<ModuleButton> moduleButtons;
    ArrayList<Module> modulesToRender;
    ArrayList<Component> setComps;
    Category focused;
    public int dragX;
    public int dragY;
    public int MouseX;
    public int MouseY;
    public int scrollModule;
    public int scrollSettings;
    public boolean darkMode;
    boolean dragging;
    int red;
    int green;
    int blue;
    int alpha;
    public Color color1;
    public Color color2;
    public Color color3;
    public Color colorText;
    public String ChatFormattingColor;
    public Color catColor;
    public static TurokFont fontRenderer;
    public static TurokFont fontRendererBig;
    TurokRect selectedCategory;
    
    public Frame(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.width = XenonClient.mc.displayWidth / 3;
        this.heightBar = 25;
        this.offsetX = 0;
        this.dragX = 0;
        this.dragY = 0;
        this.MouseX = 0;
        this.MouseY = 0;
        this.dragging = false;
        this.darkMode = ClickGuiModule.darkMode.getValue();
        this.subcomponents = new ArrayList<CategoryButton>();
        this.modulesToRender = new ArrayList<Module>();
        this.moduleButtons = new ArrayList<ModuleButton>();
        this.focused = Category.Movement;
        this.scrollModule = 0;
        this.scrollSettings = 0;
        this.setComps = new ArrayList<Component>();
        this.red = ClickGuiModule.Red.getValue();
        this.green = ClickGuiModule.Green.getValue();
        this.blue = ClickGuiModule.Blue.getValue();
        this.alpha = ClickGuiModule.Alpha.getValue();
        this.color1 = null;
        this.color2 = null;
        this.color3 = null;
        this.ChatFormattingColor = null;
        this.colorText = null;
        this.catColor = new Color(this.red, this.green, this.blue, this.alpha);
        Frame.fontRenderer = new TurokFont(new Font("Whispy", 0, 16), true, true);
        Frame.fontRendererBig = new TurokFont(new Font("Whispy", 0, 18), true, true);
        this.height = XenonClient.mc.displayHeight / 3 + 25;
        this.selectedCategory = new TurokRect("", 0, 0, 0, 0);
    }
    
    public void updateColors() {
        this.darkMode = ClickGuiModule.darkMode.getValue();
        this.red = ClickGuiModule.Red.getValue();
        this.green = ClickGuiModule.Green.getValue();
        this.blue = ClickGuiModule.Blue.getValue();
        this.alpha = ClickGuiModule.Alpha.getValue();
        if (!this.darkMode) {
            this.color1 = new Color(220, 220, 220, 230);
            this.color2 = new Color(229, 229, 229, 255);
            this.color3 = new Color(250, 250, 250, 230);
            this.colorText = new Color(0, 0, 0, 255);
            this.ChatFormattingColor = ChatFormatting.DARK_GRAY.toString();
        }
        else {
            this.color1 = new Color(33, 33, 33, 232);
            this.color2 = new Color(26, 26, 26, 255);
            this.color3 = new Color(9, 9, 9, 230);
            this.colorText = new Color(255, 255, 255, 255);
            this.ChatFormattingColor = ChatFormatting.GRAY.toString();
        }
        this.catColor = new Color(this.red, this.green, this.blue, this.alpha);
    }
    
    @Override
    public void render() {
        this.width = XenonClient.mc.displayWidth / 3;
        this.height = XenonClient.mc.displayHeight / 3 + 25;
        this.darkMode = ClickGuiModule.darkMode.getValue();
        int dWheel = 0;
        if (this.isInModulePanel(this.MouseX, this.MouseY)) {
            dWheel = Mouse.getDWheel();
            if (dWheel > 0) {
                this.scrollModule -= 15;
            }
            else if (dWheel < 0) {
                this.scrollModule += 15;
            }
        }
        else {
            dWheel = 0;
        }
        int dWheel2 = 0;
        if (this.isInSettingPanel(this.MouseX, this.MouseY)) {
            dWheel2 = Mouse.getDWheel();
            if (dWheel2 > 0) {
                this.scrollSettings -= 15;
            }
            else if (dWheel2 < 0) {
                this.scrollSettings += 15;
            }
        }
        else {
            dWheel2 = 0;
        }
        this.subcomponents = this.getCategoryButtons();
        if (this.dragging) {
            this.x = this.MouseX - this.dragX;
            this.y = this.MouseY - this.dragY;
        }
        super.render();
        this.red = ClickGuiModule.Red.getValue();
        this.green = ClickGuiModule.Green.getValue();
        this.blue = ClickGuiModule.Blue.getValue();
        this.alpha = ClickGuiModule.Alpha.getValue();
        if (!this.darkMode) {
            this.color1 = new Color(220, 220, 220, 230);
            this.color2 = new Color(229, 229, 229, 255);
            this.color3 = new Color(250, 250, 250, 230);
            this.colorText = new Color(0, 0, 0, 255);
            this.ChatFormattingColor = ChatFormatting.DARK_GRAY.toString();
        }
        else {
            this.color1 = new Color(33, 33, 33, 232);
            this.color2 = new Color(26, 26, 26, 255);
            this.color3 = new Color(9, 9, 9, 230);
            this.colorText = new Color(255, 255, 255, 255);
            this.ChatFormattingColor = ChatFormatting.GRAY.toString();
        }
        this.catColor = new Color(this.red, this.green, this.blue, this.alpha);
        TurokRenderGL.color(this.color1.getRed(), this.color1.getGreen(), this.color1.getBlue(), this.color1.getAlpha());
        final TurokRect rect1 = new TurokRect("rect", this.x, this.y, this.width, this.height);
        TurokRenderGL.drawRoundedRect(rect1, ClickGuiModule.rounded.getValue());
        TurokRenderGL.color(this.color2.getRed(), this.color2.getGreen(), this.color2.getBlue(), this.color2.getAlpha());
        final TurokRect rect2 = new TurokRect("rectt", this.x, this.y, this.width, this.heightBar);
        TurokRenderGL.drawSolidRect(rect2);
        TurokRenderGL.color(255, 255, 255, 255);
        GL11.glLineWidth(2.0f);
        TurokRenderGL.drawOutlineRect(rect2);
        for (final CategoryButton categoryButton : this.subcomponents) {
            final int wid = TurokFontManager.getStringWidth(Frame.fontRenderer, categoryButton.category.name());
            final int centered = (this.width / 6 - wid) / 2;
            GL11.glEnable(3553);
            TurokFontManager.render(Frame.fontRendererBig, categoryButton.category.name(), categoryButton.x + centered, categoryButton.y + 10, false, this.colorText);
            GL11.glDisable(3553);
            if (categoryButton.category == this.focused) {
                TurokRenderGL.color(this.catColor.getRed(), this.catColor.getGreen(), this.catColor.getBlue(), this.catColor.getAlpha());
                if (this.selectedCategory.x != categoryButton.x + 3) {
                    float addAmount = (categoryButton.x + 3 - (float)this.selectedCategory.x) / 4.0f;
                    if (addAmount > 0.0f && addAmount < 1.0f) {
                        addAmount = 1.0f;
                    }
                    else if (addAmount < 0.0f && addAmount > -1.0f) {
                        addAmount = -1.0f;
                    }
                    final TurokRect selectedCategory = this.selectedCategory;
                    selectedCategory.x += (int)addAmount;
                }
                this.selectedCategory.setY(categoryButton.y + 3);
                this.selectedCategory.setWidth(this.width / 6 - 6);
                this.selectedCategory.setHeight(3);
                TurokRenderGL.drawSolidRect(this.selectedCategory);
            }
        }
        TurokRenderGL.color(this.color2.getRed(), this.color2.getGreen(), this.color2.getBlue(), this.color2.getAlpha());
        final TurokRect modulePanelRect = new TurokRect("modulePanelRect", this.x + 10, this.y + this.heightBar + 10, this.width / 3, this.height - this.heightBar - 20);
        TurokRenderGL.drawRoundedRect(modulePanelRect, ClickGuiModule.rounded.getValue());
        final int modulePanelX = this.x + 10;
        final int modulePanelY = this.y + this.heightBar + 10;
        this.modulesToRender = XenonClient.moduleManager.getModulesByCategory(this.focused);
        int moduleOffset = 0;
        TurokRenderGL.drawScissor(this.x + 10, this.y + this.heightBar + 13, this.width / 3, this.height - this.heightBar - 26);
        GL11.glEnable(3089);
        for (final Module module : this.modulesToRender) {
            this.moduleButtons = this.getModuleButtons(this.modulesToRender, this.scrollModule);
            TurokRenderGL.color(this.color3.getRed(), this.color3.getGreen(), this.color3.getBlue(), this.color3.getAlpha());
            final TurokRect moduleRect = new TurokRect("moduleButton", this.x + 10 + 3, this.y + this.heightBar + 13 + moduleOffset + this.scrollModule, this.width / 3 - 6, 16);
            TurokRenderGL.drawRoundedRect(moduleRect, 1.0f);
            final int strHigh = TurokFontManager.getStringHeight(Frame.fontRenderer, module.name);
            final int strCenter = (16 - strHigh) / 2;
            GL11.glEnable(3553);
            Color moduleNameColor;
            if (module.isToggled()) {
                moduleNameColor = new Color(this.catColor.getRed(), this.catColor.getGreen(), this.catColor.getBlue(), 255);
            }
            else if (this.darkMode) {
                moduleNameColor = new Color(255, 255, 255, 255);
            }
            else {
                moduleNameColor = new Color(0, 0, 0, 255);
            }
            TurokFontManager.render(Frame.fontRenderer, module.name, modulePanelX + 5, modulePanelY + 3 + strCenter + moduleOffset + this.scrollModule, false, moduleNameColor);
            GL11.glDisable(3553);
            moduleOffset += 18;
        }
        if (this.scrollModule < 0 && moduleOffset + 3 < this.height - this.heightBar - 20 - 2) {
            this.scrollModule += 2;
        }
        else if (moduleOffset + 3 + this.scrollModule < this.height - this.heightBar - 20 - 2) {
            this.scrollModule += 2;
        }
        if (this.scrollModule > 0) {
            this.scrollModule -= 2;
        }
        if (moduleOffset + 3 < this.height - this.heightBar - 20 - 2 && this.scrollModule > 0) {
            this.scrollModule -= 2;
        }
        GL11.glDisable(3089);
        TurokRenderGL.color(this.color2.getRed(), this.color2.getGreen(), this.color2.getBlue(), this.color2.getAlpha());
        final TurokRect settingPanelRect = new TurokRect("settingPanelRect", this.x + this.width / 3 + 20, this.y + this.heightBar + 10, this.width - this.width / 3 - 30, this.height - this.heightBar - 20);
        TurokRenderGL.drawRoundedRect(settingPanelRect, ClickGuiModule.rounded.getValue());
        TurokRenderGL.drawScissor(this.x + this.width / 3 + 20, this.y + this.heightBar + 13, this.width - this.width / 3 - 30, this.height - this.heightBar - 20 - 6);
        GL11.glEnable(3089);
        ArrayList<Value> valueList = new ArrayList<Value>();
        final ArrayList<Component> settings = new ArrayList<Component>();
        valueList = this.getOpenValueList();
        if (this.getOpenModule() != null) {
            this.renderModuleName(this.getOpenModule(), this.x + (this.width / 3 + 22), this.y + this.heightBar + 13 + this.scrollSettings, this.color3);
            this.setComps = this.getSettingComponents(this.x + this.width / 3 + 20 + 2, this.y + this.heightBar + 13 + this.scrollSettings, this.getOpenModule());
            for (final Component component : this.setComps) {
                if (component instanceof KeybindComponent) {
                    this.renderKeybindComponent((KeybindComponent)component, this.x + (this.width / 3 + 22), ((KeybindComponent)component).getY(), this.color3, this.getOpenModule());
                }
                else if (component instanceof BooleanComponent) {
                    this.renderBooleanComponent((BooleanComponent)component, this.x + (this.width / 3 + 22), ((BooleanComponent)component).getY(), this.color3, this.getOpenModule());
                }
                else if (component instanceof ModeComponent) {
                    this.renderModeComponent((ModeComponent)component, this.x + (this.width / 3 + 22), ((ModeComponent)component).getY(), this.color3, this.getOpenModule());
                }
                else {
                    if (!(component instanceof IntegerComponent)) {
                        continue;
                    }
                    this.renderIntegerComponent((IntegerComponent)component, this.x + (this.width / 3 + 22), ((IntegerComponent)component).getY(), this.color3, this.getOpenModule());
                    component.update(this.MouseX, this.MouseY);
                }
            }
        }
        final int settOffset = this.getSettingOffset();
        if (this.scrollSettings < 0 && settOffset + 3 < this.height - this.heightBar - 20 - 2) {
            this.scrollSettings += 2;
        }
        else if (settOffset + 2 + this.scrollSettings < this.height - this.heightBar - 20 - 2) {
            this.scrollSettings += 2;
        }
        if (this.scrollSettings > 0) {
            this.scrollSettings -= 2;
        }
        if (this.scrollSettings > 0 && settOffset + 3 < this.height - this.heightBar - 20 - 2) {
            this.scrollSettings -= 2;
        }
        GL11.glDisable(3089);
    }
    
    public int getSettingOffset() {
        int offsetY = 18;
        if (this.getOpenValueList() != null) {
            for (final Value value : this.getOpenValueList()) {
                if (value.shown) {
                    if (value.getType().equalsIgnoreCase("integer")) {
                        offsetY += 16;
                    }
                    offsetY += 18;
                }
            }
        }
        offsetY += 18;
        return offsetY;
    }
    
    public boolean isInSettingPanel(final int x, final int y) {
        return x >= this.x + this.width / 3 + 20 && x <= this.x + this.width / 3 + 20 + (this.width - this.width / 3) - 30 && y >= this.y + this.heightBar + 10 && y <= this.y + this.height - 20;
    }
    
    @Override
    public void onClick(final int MouseX, final int MouseY, final int Button) {
        for (final CategoryButton categoryButton : this.subcomponents) {
            categoryButton.onClick(MouseX, MouseY, Button);
        }
        if (this.isInModulePanel(MouseX, MouseY)) {
            for (final ModuleButton moduleButton : this.moduleButtons) {
                if (this.isInModulePanel(MouseX, MouseY)) {
                    if (Button == 1) {
                        moduleButton.onClick(MouseX, MouseY, Button);
                    }
                    else {
                        moduleButton.onClick(MouseX, MouseY, Button);
                    }
                }
            }
        }
        if (this.isInSettingPanel(MouseX, MouseY)) {
            for (final Component component : this.setComps) {
                component.onClick(MouseX, MouseY, Button);
            }
        }
        if (this.isWithingFrame(MouseX, MouseY) && Button == 1) {
            this.dragging = true;
            this.dragX = MouseX - this.x;
            this.dragY = MouseY - this.y;
        }
        super.onClick(MouseX, MouseY, Button);
    }
    
    @Override
    public void onKey(final char typedChar, final int keyCode) {
        if (this.getOpenModule() != null) {
            for (final Component component : this.setComps) {
                if (component instanceof KeybindComponent) {
                    component.onKey(typedChar, keyCode);
                }
            }
        }
        super.onKey(typedChar, keyCode);
    }
    
    boolean isWithingFrame(final int x, final int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.width;
    }
    
    @Override
    public void onRelease(final int MouseX, final int MouseY, final int state) {
        if (this.dragging) {
            this.dragging = false;
        }
        for (final Component component : this.setComps) {
            component.onRelease(MouseX, MouseY, state);
        }
        super.onRelease(MouseX, MouseY, state);
    }
    
    ArrayList<ModuleButton> getModuleButtons(final ArrayList<Module> modules, final int scrollOffset) {
        final ArrayList<ModuleButton> moduleButtons = new ArrayList<ModuleButton>();
        int moduleOffset = 0;
        for (final Module module : modules) {
            moduleButtons.add(new ModuleButton(true, this.x + 10 + 3, this.y + this.heightBar + 13 + moduleOffset + scrollOffset, this.width / 3 - 6, 16, this, module));
            moduleOffset += 18;
        }
        return moduleButtons;
    }
    
    ArrayList<CategoryButton> getCategoryButtons() {
        final ArrayList<CategoryButton> buttons = new ArrayList<CategoryButton>();
        this.offsetX = 0;
        for (final Category category : Category.values()) {
            buttons.add(new CategoryButton(this.x + this.offsetX, this.y, category, this));
            this.offsetX += this.width / 6;
        }
        return buttons;
    }
    
    public boolean isInModulePanel(final int x, final int y) {
        return x >= this.x + 10 && x <= this.x + this.width / 3 + 10 && y >= this.y + this.heightBar + 10 && y <= this.y + this.heightBar + this.height - 30;
    }
    
    public ArrayList<Value> getOpenValueList() {
        for (final ModuleButton moduleButton : this.moduleButtons) {
            if (moduleButton.open) {
                return moduleButton.module.getValueList();
            }
        }
        return null;
    }
    
    public ArrayList<Component> getSettingComponents(final int x, final int y, final Module module) {
        final ArrayList<Component> settingComponents = new ArrayList<Component>();
        int offsetY = 18;
        if (this.getOpenValueList() != null) {
            for (final Value value : this.getOpenValueList()) {
                if (value.shown) {
                    if (value.getType().equalsIgnoreCase("boolean")) {
                        settingComponents.add(new BooleanComponent(x, y + offsetY, module, value, this));
                    }
                    else if (value.getType().equalsIgnoreCase("mode")) {
                        settingComponents.add(new ModeComponent(x, y + offsetY, module, value, this));
                    }
                    else if (value.getType().equalsIgnoreCase("integer")) {
                        settingComponents.add(new IntegerComponent(x, y + offsetY, module, value, this));
                        offsetY += 16;
                    }
                    offsetY += 18;
                }
            }
        }
        settingComponents.add(new KeybindComponent(x, y + offsetY, module, this));
        return settingComponents;
    }
    
    public Module getOpenModule() {
        for (final ModuleButton moduleButton : this.moduleButtons) {
            if (moduleButton.open) {
                return moduleButton.module;
            }
        }
        return null;
    }
    
    public void renderModuleName(final Module module, final int x, final int y, final Color color) {
        final TurokRect rect = new TurokRect("rect", x, y, this.width - this.width / 3 - 30 - 4, 16);
        TurokRenderGL.color(color.getRed(), color.getGreen(), color.getBlue());
        TurokRenderGL.drawRoundedRect(rect, 1.0f);
        GL11.glEnable(3553);
        final String string = ChatFormatting.BOLD + module.name + " : " + ChatFormatting.RESET + this.ChatFormattingColor + module.desc;
        final int strHigh = TurokFontManager.getStringHeight(Frame.fontRenderer, string);
        final int strCenter = (16 - strHigh) / 2;
        TurokFontManager.render(Frame.fontRenderer, string, x + 2, y + strCenter, false, this.colorText);
        GL11.glDisable(3553);
    }
    
    public void renderKeybindComponent(final KeybindComponent keybindComponent, final int x, final int y, final Color color, final Module module) {
        final TurokRect rect = new TurokRect("rect", x, y, this.width - this.width / 3 - 30 - 4, 16);
        TurokRenderGL.color(color.getRed(), color.getGreen(), color.getBlue());
        TurokRenderGL.drawRoundedRect(rect, 1.0f);
        String string = null;
        GL11.glEnable(3553);
        String keyname = null;
        if (module.getKey() == -1) {
            keyname = "NONE";
        }
        else {
            keyname = Keyboard.getKeyName(module.getKey());
        }
        if (!keybindComponent.binding) {
            string = "BIND : " + this.ChatFormattingColor + keyname;
        }
        else {
            string = "BINDING...";
        }
        final int strHigh = TurokFontManager.getStringHeight(Frame.fontRenderer, string);
        final int strCenter = (16 - strHigh) / 2;
        TurokFontManager.render(Frame.fontRenderer, string, x + 2, y + strCenter, false, this.colorText);
        GL11.glDisable(3553);
    }
    
    public void renderBooleanComponent(final BooleanComponent booleanComponent, final int x, final int y, final Color color, final Module module) {
        final TurokRect rect = new TurokRect("rect", x, y, this.width - this.width / 3 - 30 - 4, 16);
        TurokRenderGL.color(color.getRed(), color.getGreen(), color.getBlue());
        TurokRenderGL.drawRoundedRect(rect, 1.0f);
        String string = null;
        GL11.glEnable(3553);
        string = booleanComponent.value.getName() + " - " + booleanComponent.value.getDesc() + ChatFormatting.RESET + " : " + this.ChatFormattingColor + (boolean)booleanComponent.value.getValue();
        final int strHigh = TurokFontManager.getStringHeight(Frame.fontRenderer, string);
        final int strCenter = (16 - strHigh) / 2;
        TurokFontManager.render(Frame.fontRenderer, string, x + 2, y + strCenter, false, this.colorText);
        GL11.glDisable(3553);
    }
    
    public void renderModeComponent(final ModeComponent modeComponent, final int x, final int y, final Color color, final Module module) {
        final TurokRect rect = new TurokRect("rect", x, y, this.width - this.width / 3 - 30 - 4, 16);
        TurokRenderGL.color(color.getRed(), color.getGreen(), color.getBlue());
        TurokRenderGL.drawRoundedRect(rect, 1.0f);
        String string = null;
        GL11.glEnable(3553);
        string = modeComponent.value.getName() + " - " + modeComponent.value.getDesc() + " : " + this.ChatFormattingColor + modeComponent.value.getValue();
        final int strHigh = TurokFontManager.getStringHeight(Frame.fontRenderer, string);
        final int strCenter = (16 - strHigh) / 2;
        TurokFontManager.render(Frame.fontRenderer, string, x + 2, y + strCenter, false, this.colorText);
        GL11.glDisable(3553);
    }
    
    public void renderIntegerComponent(final IntegerComponent integerComponent, final int x, final int y, final Color color, final Module module) {
        final TurokRect rect = new TurokRect("rect", x, y, this.width - this.width / 3 - 30 - 4, 32);
        TurokRenderGL.color(color.getRed(), color.getGreen(), color.getBlue());
        TurokRenderGL.drawRoundedRect(rect, 1.0f);
        final TurokRect sliderBG = new TurokRect("SliderRect", x + 4, y + 18, this.width - this.width / 3 - 30 - 12, 12);
        TurokRenderGL.color(ClickGuiModule.sliderRed.getValue(), ClickGuiModule.sliderGreen.getValue(), ClickGuiModule.sliderBlue.getValue(), ClickGuiModule.sliderAlpha.getValue());
        TurokRenderGL.drawSolidRect(sliderBG);
        final int val = integerComponent.value.getValue();
        final float cast = (float)val;
        final float divided = (cast - integerComponent.value.getIntMin()) / (integerComponent.value.getIntMax() - (float)integerComponent.value.getIntMin());
        final float sliderButtonOffset = divided * (this.width - this.width / 3 - 30 - 15);
        final TurokRect sliderButton = new TurokRect("SliderButton", x + ((int)sliderButtonOffset + 3), y + 17, 6, 14);
        TurokRenderGL.color(this.red, this.green, this.blue, 255);
        TurokRenderGL.drawSolidRect(sliderButton);
        String nameString = null;
        GL11.glEnable(3553);
        nameString = integerComponent.value.getName() + " - " + integerComponent.value.getDesc() + " : " + this.ChatFormattingColor + integerComponent.value.getValue();
        final int strHigh = TurokFontManager.getStringHeight(Frame.fontRenderer, nameString);
        final int strCenter = (16 - strHigh) / 2;
        TurokFontManager.render(Frame.fontRenderer, nameString, x + 2, y + strCenter, false, this.colorText);
        GL11.glDisable(3553);
    }
    
    public void renderColorComponent(final int x, final int y, final Color color, final Module module) {
    }
}
