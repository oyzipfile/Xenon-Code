// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.config.management;

import club.max.xenon.api.command.management.CommandManager;
import net.minecraftforge.common.MinecraftForge;
import java.util.Scanner;
import java.util.Iterator;
import club.max.xenon.api.setting.Value;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import club.max.xenon.api.module.Module;
import java.util.ArrayList;
import club.max.xenon.XenonClient;
import java.io.File;
import net.minecraft.client.Minecraft;

public class ConfigManager
{
    Minecraft mc;
    public static File main;
    public static File modules;
    public static File misc;
    
    public ConfigManager() {
        this.mc = XenonClient.mc;
        ConfigManager.main = new File(System.getProperty("user.dir") + File.separator + "XENON");
        if (!ConfigManager.main.exists()) {
            ConfigManager.main.mkdirs();
        }
        ConfigManager.modules = new File(System.getProperty("user.dir") + File.separator + "XENON" + File.separator + "Modules");
        if (!ConfigManager.modules.exists()) {
            ConfigManager.modules.mkdirs();
        }
        ConfigManager.misc = new File(System.getProperty("user.dir") + File.separator + "XENON" + File.separator + "MISC");
        if (!ConfigManager.misc.exists()) {
            ConfigManager.misc.mkdirs();
        }
    }
    
    public void onStartup() {
        this.loadModules();
        this.loadPrefix();
    }
    
    public void saveModules() {
        try {
            final ArrayList<File> files = new ArrayList<File>();
            for (final Module module : XenonClient.moduleManager.getModules()) {
                final String fileName = module.getTag() + ".json";
                final File file = new File(ConfigManager.modules.getAbsolutePath(), fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                final BufferedWriter out = new BufferedWriter(new FileWriter(file));
                out.write("toggled:" + module.isToggled());
                out.write("\r\n");
                out.write("key:" + module.getKey());
                out.write("\r\n");
                out.flush();
                for (final Value value : module.getValueList()) {
                    out.write(value.getTag() + ":" + value.getValue());
                    out.write("\r\n");
                    out.flush();
                }
                out.close();
                files.add(file);
            }
        }
        catch (Exception ex) {}
    }
    
    public void loadModules() {
        try {
            for (final Module module : XenonClient.moduleManager.getModules()) {
                final String fileName = module.getTag() + ".json";
                final File file = new File(ConfigManager.modules.getAbsolutePath(), fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                final Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    final String data = scanner.nextLine();
                    final String[] args = data.split(":");
                    if (args[0].equalsIgnoreCase("toggled")) {
                        module.toggled = Boolean.parseBoolean(args[1]);
                        if (!module.toggled) {
                            continue;
                        }
                        MinecraftForge.EVENT_BUS.register((Object)module);
                    }
                    else if (args[0].equalsIgnoreCase("key")) {
                        module.setKey(Integer.parseInt(args[1]));
                    }
                    else {
                        for (final Value value : module.getValueList()) {
                            if (value.getTag().equalsIgnoreCase(args[0])) {
                                if (value.getType().equalsIgnoreCase("boolean")) {
                                    value.setValue(Boolean.parseBoolean(args[1]));
                                }
                                else if (value.getType().equalsIgnoreCase("integer")) {
                                    value.setValue(Integer.parseInt(args[1]));
                                }
                                else if (value.getType().equalsIgnoreCase("double")) {
                                    value.setValue(Double.parseDouble(args[1]));
                                }
                                else {
                                    if (!value.getType().equalsIgnoreCase("mode")) {
                                        continue;
                                    }
                                    value.setValue(args[1]);
                                }
                            }
                        }
                    }
                }
                module.onStartUp();
            }
        }
        catch (Exception ex) {}
    }
    
    public void loadPrefix() {
        try {
            final File fileP = new File(ConfigManager.misc.getAbsolutePath(), "prefix");
            if (!fileP.exists()) {
                fileP.createNewFile();
                final BufferedWriter out = new BufferedWriter(new FileWriter(fileP));
                out.write(CommandManager.getCommandPrefix().getPrefix());
                out.close();
            }
            final Scanner scanner = new Scanner(fileP);
            while (scanner.hasNextLine()) {
                CommandManager.getCommandPrefix().setPrefix(scanner.nextLine());
            }
        }
        catch (Exception ex) {}
    }
    
    public void savePrefix() {
        try {
            final File fileP = new File(ConfigManager.misc.getAbsolutePath(), "prefix");
            if (!fileP.exists()) {
                fileP.createNewFile();
            }
            final BufferedWriter out = new BufferedWriter(new FileWriter(fileP));
            out.write(CommandManager.getCommandPrefix().getPrefix());
            out.close();
        }
        catch (Exception ex) {}
    }
    
    public File getMain() {
        return ConfigManager.main;
    }
    
    public File getMisc() {
        return ConfigManager.misc;
    }
}
