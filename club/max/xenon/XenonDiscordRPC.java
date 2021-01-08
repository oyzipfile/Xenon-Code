// 
// Decompiled by Procyon v0.5.36
// 

package club.max.xenon;

import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;

public class XenonDiscordRPC
{
    DiscordRPC rpc;
    String id;
    private DiscordEventHandlers handlers;
    DiscordRichPresence presence;
    
    public XenonDiscordRPC() {
        this.rpc = DiscordRPC.INSTANCE;
        this.id = "788145749699067924";
        this.handlers = new DiscordEventHandlers();
        this.presence = new DiscordRichPresence();
    }
    
    public void start() {
        this.rpc.Discord_Initialize(this.id, this.handlers, true, "");
        this.presence.details = "Xenon is super based";
        this.presence.largeImageKey = "xenonlogo";
        this.presence.largeImageText = "Xenon client on top";
        this.rpc.Discord_UpdatePresence(this.presence);
    }
    
    public void update(final String string) {
        this.presence.details = string;
        this.presence.largeImageKey = "xenonlogo";
        this.presence.largeImageText = "Xenon client on top";
        this.rpc.Discord_UpdatePresence(this.presence);
    }
}
