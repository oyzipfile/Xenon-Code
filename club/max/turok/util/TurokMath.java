// 
// Decompiled by Procyon v0.5.36
// 

package club.max.turok.util;

import net.minecraft.util.math.Vec3d;
import java.math.RoundingMode;
import java.math.BigDecimal;

public class TurokMath
{
    public static int clamp(final int value, final int minimum, final int maximum) {
        return (value <= minimum) ? minimum : ((value >= maximum) ? maximum : value);
    }
    
    public static double clamp(final double value, final double minimum, final double maximum) {
        return (value <= minimum) ? minimum : ((value >= maximum) ? maximum : value);
    }
    
    public static float clamp(final float value, final float minimum, final float maximum) {
        return (value <= minimum) ? minimum : ((value >= maximum) ? maximum : value);
    }
    
    public static double round(final double vDouble) {
        BigDecimal decimal = new BigDecimal(vDouble);
        decimal = decimal.setScale(2, RoundingMode.HALF_UP);
        return decimal.doubleValue();
    }
    
    public static Vec3d lerp(final Vec3d a, final Vec3d b, final float ticks) {
        return new Vec3d(a.x + (b.x - a.x) * ticks, a.y + (b.y - a.y) * ticks, a.z + (b.z - a.z) * ticks);
    }
    
    public static float lerp(final int a, final int b, final float ticks) {
        return a + (b - a) * ticks;
    }
    
    public static float lerp(final float a, final float b, final float ticks) {
        return a + (b - a) * ticks;
    }
    
    public static int normalize(final int... value) {
        int normalizedValue = 0;
        int cachedValue = 0;
        for (int length = value.length, i = 0; i < length; ++i) {
            final int values = cachedValue = value[i];
            normalizedValue = values / cachedValue * cachedValue;
        }
        return normalizedValue;
    }
    
    public static double normalize(final double... value) {
        double normalizedValue = 0.0;
        double cachedValue = 0.0;
        for (int length = value.length, i = 0; i < length; ++i) {
            final double values = cachedValue = value[i];
            normalizedValue = values / cachedValue * cachedValue;
        }
        return normalizedValue;
    }
    
    public static float normalize(final float... value) {
        float normalizedValue = 0.0f;
        float cachedValue = 0.0f;
        for (int length = value.length, i = 0; i < length; ++i) {
            final float values = cachedValue = value[i];
            normalizedValue = values / cachedValue * cachedValue;
        }
        return normalizedValue;
    }
    
    public static int ceiling(final double value) {
        final int valueInt = (int)value;
        return (value >= valueInt) ? (valueInt + 1) : valueInt;
    }
    
    public static int ceiling(final float value) {
        final int valueInt = (int)value;
        return (value >= valueInt) ? (valueInt + 1) : valueInt;
    }
    
    public static double sqrt(final double a) {
        return Math.sqrt(a);
    }
    
    public static float sqrt(final float a) {
        return (float)Math.sqrt(a);
    }
    
    public static int sqrt(final int a) {
        return (int)Math.sqrt(a);
    }
    
    public static int min(final int value, final int minimum) {
        return (value <= minimum) ? minimum : value;
    }
    
    public static float min(final float value, final float minimum) {
        return (value <= minimum) ? minimum : value;
    }
    
    public static double min(final double value, final double minimum) {
        return (value <= minimum) ? minimum : value;
    }
    
    public static int max(final int value, final int maximum) {
        return (value >= maximum) ? maximum : value;
    }
    
    public static double max(final double value, final double maximum) {
        return (value >= maximum) ? maximum : value;
    }
    
    public static float max(final float value, final float maximum) {
        return (value >= maximum) ? maximum : value;
    }
}
