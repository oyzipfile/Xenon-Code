// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.util.chat;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import com.mojang.realmsclient.gui.ChatFormatting;
import club.max.xenon.XenonClient;

public class ChatUtil
{
    public static void print(final String message) {
        if (XenonClient.getMinecraft().ingameGUI == null) {
            return;
        }
        if (XenonClient.mc.currentScreen == XenonClient.console) {
            if (XenonClient.gui.frame.darkMode) {
                XenonClient.console.outFrame.println(XenonClient.CHAT + ChatFormatting.GRAY + message);
            }
            else {
                XenonClient.console.outFrame.println(XenonClient.CHAT + ChatFormatting.DARK_GRAY + message);
            }
        }
        else {
            XenonClient.getMinecraft().ingameGUI.getChatGUI().printChatMessage((ITextComponent)new TextComponentString(XenonClient.CHAT + ChatFormatting.GRAY + message));
        }
    }
    
    public static void malloc(final String message) {
        if (XenonClient.getMinecraft().ingameGUI == null) {
            return;
        }
        XenonClient.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(message);
    }
    
    public static String fontHephaestus(final String args) {
        String converted_string = args;
        converted_string = converted_string.replace("a", "\u1d00");
        converted_string = converted_string.replace("b", "\u0299");
        converted_string = converted_string.replace("c", "\u1d04");
        converted_string = converted_string.replace("d", "\u1d05");
        converted_string = converted_string.replace("e", "\u1d07");
        converted_string = converted_string.replace("f", "\u0493");
        converted_string = converted_string.replace("g", "\u0262");
        converted_string = converted_string.replace("h", "\u029c");
        converted_string = converted_string.replace("i", "\u026a");
        converted_string = converted_string.replace("j", "\u1d0a");
        converted_string = converted_string.replace("k", "\u1d0b");
        converted_string = converted_string.replace("l", "\u029f");
        converted_string = converted_string.replace("m", "\u1d0d");
        converted_string = converted_string.replace("n", "\u0274");
        converted_string = converted_string.replace("o", "\u1d0f");
        converted_string = converted_string.replace("p", "\u1d18");
        converted_string = converted_string.replace("q", "\u01eb");
        converted_string = converted_string.replace("r", "\u0280");
        converted_string = converted_string.replace("s", "\u0455");
        converted_string = converted_string.replace("t", "\u1d1b");
        converted_string = converted_string.replace("u", "\u1d1c");
        converted_string = converted_string.replace("v", "\u1d20");
        converted_string = converted_string.replace("w", "\u1d21");
        converted_string = converted_string.replace("x", "\u0445");
        converted_string = converted_string.replace("y", "\u028f");
        converted_string = converted_string.replace("z", "\u1d22");
        return converted_string;
    }
    
    public static String fontCircle(final String args) {
        String converted_string = args;
        converted_string = converted_string.replace("a", "\u24b6");
        converted_string = converted_string.replace("b", "\u24b7");
        converted_string = converted_string.replace("c", "\u24b8");
        converted_string = converted_string.replace("d", "\u24b9");
        converted_string = converted_string.replace("e", "\u24ba");
        converted_string = converted_string.replace("f", "\u24bb");
        converted_string = converted_string.replace("g", "\u24bc");
        converted_string = converted_string.replace("h", "\u24bd");
        converted_string = converted_string.replace("i", "\u24be");
        converted_string = converted_string.replace("j", "\u24bf");
        converted_string = converted_string.replace("k", "\u24c0");
        converted_string = converted_string.replace("l", "\u24c1");
        converted_string = converted_string.replace("m", "\u24c2");
        converted_string = converted_string.replace("n", "\u24c3");
        converted_string = converted_string.replace("o", "\u24c4");
        converted_string = converted_string.replace("p", "\u24c5");
        converted_string = converted_string.replace("q", "\u24c6");
        converted_string = converted_string.replace("r", "\u24c7");
        converted_string = converted_string.replace("s", "\u24c8");
        converted_string = converted_string.replace("t", "\u24c9");
        converted_string = converted_string.replace("u", "\u24ca");
        converted_string = converted_string.replace("v", "\u24cb");
        converted_string = converted_string.replace("w", "\u24cc");
        converted_string = converted_string.replace("x", "\u24cd");
        converted_string = converted_string.replace("y", "\u24ce");
        converted_string = converted_string.replace("z", "\u24cf");
        return converted_string;
    }
}
