// 
// Decompiled by Procyon v0.5.36
// 

package club.max.turok;

public class Turok
{
    public static String AUTHOR;
    public static String VERSION;
    public static String NAME;
    
    public static String getAuthor() {
        return Turok.AUTHOR;
    }
    
    public static String getVersion() {
        return Turok.VERSION;
    }
    
    public static String getName() {
        return Turok.NAME;
    }
    
    static {
        Turok.AUTHOR = "SrRina";
        Turok.VERSION = "5.0.0 Official Version";
        Turok.NAME = "Turok Framework";
    }
}
