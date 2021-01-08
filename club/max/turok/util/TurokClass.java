// 
// Decompiled by Procyon v0.5.36
// 

package club.max.turok.util;

public class TurokClass
{
    public static Enum getEnumByName(final Enum _enum, final String name) {
        for (final Enum enums : (Enum[])_enum.getClass().getEnumConstants()) {
            if (_enum.name().equals(name)) {
                return _enum;
            }
        }
        return null;
    }
}
