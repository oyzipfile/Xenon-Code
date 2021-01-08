// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.gui.component.Components;

import java.math.RoundingMode;
import java.math.BigDecimal;
import club.max.xenon.client.gui.component.frame.Frame;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;
import club.max.xenon.client.gui.component.Component;

public class IntegerComponent extends Component
{
    int x;
    int y;
    int width;
    int height;
    public Module module;
    public Value value;
    public int drag;
    public int sliderButtonX;
    public boolean dragging;
    public int sliderX;
    public int sliderY;
    public int sliderWidth;
    public int sliderHeight;
    float sliderButtonOffset;
    Frame parent;
    
    public IntegerComponent(final int x, final int y, final Module module, final Value value, final Frame parent) {
        this.x = x;
        this.y = y;
        this.dragging = false;
        this.parent = parent;
        this.width = this.parent.width - this.parent.width / 3 - 30 - 4;
        this.height = 32;
        this.module = module;
        this.value = value;
        this.sliderX = this.x + 2;
        this.sliderY = this.y + 18;
        this.sliderWidth = this.width - 2;
        this.sliderHeight = 12;
        this.sliderButtonOffset = this.value.getDividedInt() * 358.0f;
    }
    
    private static double roundToPlace(final double value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public int getSliderButtonX() {
        return this.sliderButtonX;
    }
    
    @Override
    public void onClick(final int MouseX, final int MouseY, final int Button) {
        if (this.module.open && this.isInSliderButton(MouseX, MouseY) && Button == 0) {
            this.value.dragging = true;
        }
        super.onClick(MouseX, MouseY, Button);
    }
    
    @Override
    public void update(final int MouseX, final int MouseY) {
        super.update(MouseX, MouseY);
        if (this.value.dragging) {
            final int dragMouseX = MouseX - this.sliderX;
            if (dragMouseX == 0) {
                this.value.setValue((int)this.value.getMin());
            }
            else {
                final float newVal = dragMouseX / (float)(this.parent.width - this.parent.width / 3 - 30 - 15) * (this.value.getIntMax() - this.value.getIntMin()) + this.value.getIntMin();
                this.value.setValue(this.roundInt(newVal));
            }
        }
    }
    
    @Override
    public void onRelease(final int MouseX, final int MouseY, final int state) {
        this.value.dragging = false;
        super.onRelease(MouseX, MouseY, state);
    }
    
    boolean isInSliderButton(final int x, final int y) {
        return x >= this.sliderX && x <= this.sliderX + this.sliderWidth && y >= this.sliderY && y <= this.sliderY + this.sliderHeight;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int roundInt(final double vDouble) {
        BigDecimal decimal = new BigDecimal(vDouble);
        decimal = decimal.setScale(2, RoundingMode.HALF_UP);
        return decimal.intValue();
    }
}
