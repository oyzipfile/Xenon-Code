// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.lwjgl.opengl.Display;
import club.max.xenon.api.config.impl.ShutdownHook;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import club.max.xenon.client.gui.console.ConsoleScreen;
import club.max.xenon.api.config.management.ConfigManager;
import net.minecraft.client.Minecraft;
import club.max.xenon.client.gui.ClickGui;
import club.max.xenon.api.module.management.ModuleManager;
import club.max.xenon.api.command.management.CommandManager;
import club.max.xenon.api.event.management.EventManager;
import me.yagel15637.blitz.dispatcher.EventDispatcher;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "xenon", name = "Xenon", version = "0.0.6")
public class XenonClient
{
    public static final String NAME = "XENON";
    public static final String VERSION = "0.0.6";
    public static final String CHAT;
    public static final EventDispatcher EVENT_BUS;
    private static EventManager clientEventManager;
    private static CommandManager commandManager;
    public static ModuleManager moduleManager;
    public static ClickGui gui;
    public static Minecraft mc;
    public static ConfigManager configManager;
    public static XenonDiscordRPC rpc;
    public static ConsoleScreen console;
    
    @Mod.EventHandler
    public static void preInit(final FMLPreInitializationEvent event) {
    }
    
    @Mod.EventHandler
    public static void onClientStarted(final FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)new XenonClient());
        XenonClient.mc = Minecraft.getMinecraft();
        XenonClient.clientEventManager = new EventManager();
        System.out.println("Initialized Event Manager");
        (XenonClient.commandManager = new CommandManager()).initCommands();
        System.out.println("Initialized Commands");
        (XenonClient.moduleManager = new ModuleManager()).initMods();
        System.out.println("Initialized Modules");
        XenonClient.gui = new ClickGui();
        XenonClient.console = new ConsoleScreen();
        System.out.println("Initialized GUI");
        (XenonClient.configManager = new ConfigManager()).onStartup();
        System.out.println("Initialized Config");
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
        MinecraftForge.EVENT_BUS.register((Object)XenonClient.clientEventManager);
        MinecraftForge.EVENT_BUS.register((Object)XenonClient.commandManager);
        Display.setTitle("XENON 0.0.6");
    }
    
    public static ModuleManager getModuleManager() {
        return XenonClient.moduleManager;
    }
    
    public static EventManager getClientEventManager() {
        return XenonClient.clientEventManager;
    }
    
    public static CommandManager getCommandManager() {
        return XenonClient.commandManager;
    }
    
    public static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }
    
    static {
        CHAT = ChatFormatting.GRAY + "{" + ChatFormatting.BLUE + ChatFormatting.BOLD + "XENON" + ChatFormatting.RESET + ChatFormatting.GRAY + "} ";
        EVENT_BUS = new EventDispatcher();
    }
}
