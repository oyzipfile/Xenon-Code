// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.module.misc;

import me.yagel15637.blitz.dispatcher.DispatcherEntry;
import net.minecraft.network.play.client.CPacketChatMessage;
import club.max.xenon.client.event.network.EventPacket;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.ClientChatEvent;
import club.max.xenon.api.module.impl.Category;
import java.util.ArrayList;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.module.Module;

public class ChatSuffix extends Module
{
    public Value Divider;
    ArrayList<String> dividers;
    public Value font;
    ArrayList<String> fonts;
    
    public ChatSuffix() {
        super("Chat Suffix", "Lets everyone know about your based client", "chatsuffix", Category.Misc);
        (this.dividers = new ArrayList<String>()).add(" >> ");
        this.dividers.add(" // ");
        this.dividers.add(" || ");
        this.dividers.add(" | ");
        this.dividers.add(" : ");
        this.Divider = this.initValue("Divider", "The char in between message and suffix", "divider", this.dividers, " >> ");
        (this.fonts = new ArrayList<String>()).add("Small");
        this.fonts.add("Circled");
        this.font = this.initValue("Font", "The type of font to use for the suffix", "font", this.fonts, "Circled");
    }
    
    @SubscribeEvent
    public void onChat(final ClientChatEvent event) {
        String originalMessage = event.getMessage();
        boolean acceptMessage = true;
        if (originalMessage.startsWith("/") || originalMessage.startsWith("\\") || originalMessage.startsWith("!") || originalMessage.startsWith(":") || originalMessage.startsWith(";") || originalMessage.startsWith(".") || originalMessage.startsWith(",") || originalMessage.startsWith("@") || originalMessage.startsWith("&") || originalMessage.startsWith("*") || originalMessage.startsWith("$") || originalMessage.startsWith("#") || originalMessage.startsWith("(") || originalMessage.startsWith(")")) {
            acceptMessage = false;
        }
        if (acceptMessage) {
            String suffix = "";
            if (this.font.getValue().equalsIgnoreCase("Small")) {
                suffix = this.fontHephaestus("xenon".toLowerCase());
            }
            else if (this.font.getValue().equalsIgnoreCase("Circled")) {
                suffix = this.fontCircle("xenon".toLowerCase());
            }
            originalMessage = originalMessage + this.Divider.getValue() + suffix;
        }
        event.setMessage(originalMessage);
    }
    
    @DispatcherEntry
    public void onPacket(final EventPacket.Send packet) {
        if (packet.getPacket() instanceof CPacketChatMessage) {
            String originalMessage = ((CPacketChatMessage)packet.getPacket()).getMessage();
            boolean acceptMessage = true;
            if (originalMessage.startsWith("/") || originalMessage.startsWith("\\") || originalMessage.startsWith("!") || originalMessage.startsWith(":") || originalMessage.startsWith(";") || originalMessage.startsWith(".") || originalMessage.startsWith(",") || originalMessage.startsWith("@") || originalMessage.startsWith("&") || originalMessage.startsWith("*") || originalMessage.startsWith("$") || originalMessage.startsWith("#") || originalMessage.startsWith("(") || originalMessage.startsWith(")")) {
                acceptMessage = false;
            }
            if (acceptMessage) {
                String suffix = "";
                if (this.font.getValue().equalsIgnoreCase("Small")) {
                    suffix = this.fontHephaestus("xenon".toLowerCase());
                }
                else if (this.font.getValue().equalsIgnoreCase("Circled")) {
                    suffix = this.fontCircle("xenon".toLowerCase());
                }
                originalMessage = originalMessage + this.Divider.getValue() + suffix;
            }
            ((CPacketChatMessage)packet.getPacket()).message = originalMessage;
        }
    }
    
    public String fontHephaestus(final String args) {
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
    
    public String fontCircle(final String args) {
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
