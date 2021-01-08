// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon.api.event.management;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import club.max.xenon.api.util.chat.ChatUtil;
import club.max.xenon.api.command.management.CommandManager;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import club.max.xenon.XenonClient;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class EventManager
{
    @SubscribeEvent
    public void onRenderEvent2D(final RenderGameOverlayEvent.Text event) {
        XenonClient.getModuleManager().onRender2D();
    }
    
    @SubscribeEvent
    public void onRenderEvent3D(final RenderWorldLastEvent event) {
        XenonClient.getModuleManager().onRender3D();
    }
    
    @SubscribeEvent
    public void onKeyEvent(final InputEvent.KeyInputEvent event) {
        final int key = Keyboard.getEventKey();
        if (Keyboard.getEventKeyState()) {
            XenonClient.getModuleManager().onKey(key);
        }
    }
    
    @SubscribeEvent
    public void onTickEvent(final TickEvent.ClientTickEvent event) {
        if (XenonClient.mc.player != null) {
            XenonClient.getModuleManager().onPlayerTick();
        }
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onChat(final ClientChatEvent event) {
        final String message = event.getMessage();
        final String currentPrefix = CommandManager.getCommandPrefix().getPrefix();
        if (message.startsWith(currentPrefix)) {
            event.setCanceled(true);
            ChatUtil.malloc(message);
            final String[] args = XenonClient.getCommandManager().split(message);
            XenonClient.getCommandManager().onCommand(args);
        }
    }
}
