// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.setting;

import java.util.Iterator;
import java.awt.Color;
import java.util.ArrayList;
import club.max.xenon.api.module.Module;

public class Value<E>
{
    String name;
    String tag;
    String desc;
    Module parent;
    String type;
    boolean boolValue;
    public boolean dragging;
    int intValue;
    int intMin;
    int intMax;
    double doubleValue;
    double doubleMin;
    double doubleMax;
    String modeValue;
    ArrayList<String> modes;
    int hue;
    int sat;
    int bri;
    int alpha;
    Color color;
    boolean rainbow;
    E value;
    public boolean shown;
    
    public Value(final String name, final String tag, final String desc, final Module parent) {
        this.type = "Divider";
        this.name = name;
        this.tag = tag;
        this.desc = desc;
        this.parent = parent;
        this.shown = true;
        this.dragging = false;
    }
    
    public Value(final String name, final String tag, final String desc, final Module parent, final E boolValue) {
        this.type = "Boolean";
        this.name = name;
        this.tag = tag;
        this.desc = desc;
        this.parent = parent;
        this.value = boolValue;
        this.shown = true;
        this.dragging = false;
    }
    
    public Value(final String name, final String tag, final String desc, final Module parent, final E val, final int min, final int max) {
        this.type = "Integer";
        this.name = name;
        this.tag = tag;
        this.desc = desc;
        this.parent = parent;
        this.shown = true;
        this.dragging = false;
        this.value = val;
        this.intMin = min;
        this.intMax = max;
    }
    
    public Value(final String name, final String tag, final String desc, final Module parent, final E val, final double min, final double max) {
        this.type = "Double";
        this.name = name;
        this.tag = tag;
        this.desc = desc;
        this.parent = parent;
        this.shown = true;
        this.dragging = false;
    }
    
    public Value(final String name, final String tag, final String desc, final Module parent, final ArrayList<String> modes, final E modeValue) {
        this.type = "Mode";
        this.name = name;
        this.tag = tag;
        this.desc = desc;
        this.parent = parent;
        this.value = modeValue;
        this.modes = modes;
        this.shown = true;
        this.dragging = false;
    }
    
    public Value(final String name, final String tag, final String desc, final Module parent, final int hue, final int sat, final int bri, final int alpha, final boolean rainbow) {
        this.type = "Color";
        this.name = name;
        this.tag = tag;
        this.desc = desc;
        this.parent = parent;
        this.hue = hue;
        this.sat = sat;
        this.bri = bri;
        this.alpha = alpha;
        this.rainbow = rainbow;
        this.color = Color.getHSBColor(hue / 360.0f, sat / 255.0f, bri / 255.0f);
        this.shown = true;
        this.dragging = false;
    }
    
    public E getValue() {
        return this.value;
    }
    
    public void setValue(final E valueNew) {
        if (this.type.equalsIgnoreCase("integer")) {
            this.value = (E)this.clamp((int)valueNew, this.intMin, this.intMax);
        }
        else {
            this.value = valueNew;
        }
    }
    
    public Number getMin() {
        if (this.type.equalsIgnoreCase("Integer")) {
            return this.intMin;
        }
        if (this.type.equalsIgnoreCase("double")) {
            return this.doubleMin;
        }
        return null;
    }
    
    public Number getMax() {
        if (this.type.equalsIgnoreCase("Integer")) {
            return this.intMax;
        }
        if (this.type.equalsIgnoreCase("double")) {
            return this.doubleMax;
        }
        return null;
    }
    
    public int getIntMax() {
        return this.intMax;
    }
    
    public float getDividedInt() {
        return (this.intValue - (float)this.intMin) / (this.intMax - (float)this.intMin);
    }
    
    public ArrayList<String> getModes() {
        if (this.type.equalsIgnoreCase("mode")) {
            return this.modes;
        }
        return null;
    }
    
    public void setShown(final boolean newVal) {
        this.shown = newVal;
    }
    
    public Module getParent() {
        return this.parent;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public void setDesc(final String desc) {
        this.desc = desc;
    }
    
    public void cycleModes() {
        if (this.type.equalsIgnoreCase("mode")) {
            final ArrayList<String> modes = this.getModes();
            final String currentMode = this.getValue();
            final int maxIndex = modes.size();
            int currentIndex = 1;
            for (final String string : modes) {
                if (string.equalsIgnoreCase(currentMode)) {
                    break;
                }
                ++currentIndex;
            }
            if (currentIndex < maxIndex) {
                this.setValue(modes.get(currentIndex));
                System.out.println(maxIndex + " " + currentIndex);
            }
            else {
                currentIndex = 1;
                this.setValue(modes.get(0));
            }
        }
    }
    
    public int getIntMin() {
        return this.intMin;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public void setTag(final String tag) {
        this.tag = tag;
    }
    
    public Integer clamp(final int in, final int min, final int max) {
        if (in >= min && in <= max) {
            return in;
        }
        if (in < min) {
            return min;
        }
        if (in > max) {
            return max;
        }
        return null;
    }
}
