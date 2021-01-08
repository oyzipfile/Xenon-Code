// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.client.command;

import java.util.Iterator;
import club.max.xenon.api.module.Module;
import club.max.xenon.api.setting.Value;
import club.max.xenon.api.util.chat.ChatUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import club.max.xenon.api.module.management.ModuleManager;
import club.max.xenon.XenonClient;
import club.max.xenon.api.command.Command;

public class CommandSet extends Command
{
    public CommandSet() {
        super("Set", new String[] { "set", "setting" }, "Allows you to change settings in a module");
    }
    
    @Override
    public String setSyntax() {
        return "set/setting <module> <setting> <value>";
    }
    
    @Override
    public void onCommand(final String[] args) {
        String tag = null;
        String valueTag = null;
        String value = null;
        if (args.length > 3) {
            tag = args[1];
            valueTag = args[2];
            value = args[3];
        }
        if (args.length > 4 || tag == null || valueTag == null || value == null) {
            this.splash();
            return;
        }
        final ModuleManager moduleManager = XenonClient.moduleManager;
        final Module module = ModuleManager.get(tag);
        if (module == null) {
            ChatUtil.print(ChatFormatting.RED + "Unknown module.");
            return;
        }
        boolean set = false;
        for (final Value val : module.getValueList()) {
            if (val.getTag().equalsIgnoreCase(valueTag)) {
                if (val.getType().equalsIgnoreCase("boolean")) {
                    val.setValue(Boolean.parseBoolean(value));
                }
                else if (val.getType().equalsIgnoreCase("integer")) {
                    val.setValue(Integer.parseInt(value));
                }
                else if (val.getType().equalsIgnoreCase("double")) {
                    val.setValue(Double.parseDouble(value));
                }
                else if (val.getType().equalsIgnoreCase("mode")) {
                    val.setValue(value);
                }
                set = true;
                ChatUtil.print("Set " + val.getName() + " to " + value);
                break;
            }
        }
        if (!set) {
            ChatUtil.print(ChatFormatting.RED + "Unknown Setting");
        }
    }
}
