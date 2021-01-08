// 
// Decompiled by Procyon v0.5.36
// 

package club.max.cape.players;

import java.net.MalformedURLException;
import club.max.cape.cape.CapeTypes;
import java.util.concurrent.ConcurrentHashMap;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import java.util.ArrayList;
import java.net.URL;
import net.minecraft.client.Minecraft;

public class CapeUtils
{
    static Minecraft mc;
    public static URL pastebin;
    
    public static ArrayList<EntityPlayer> getPlayersInWorld() {
        final ArrayList<EntityPlayer> list = new ArrayList<EntityPlayer>();
        for (final Entity ent : CapeUtils.mc.world.getLoadedEntityList()) {
            if (ent instanceof EntityPlayer) {
                list.add((EntityPlayer)ent);
            }
        }
        return list;
    }
    
    public static ArrayList<String> getNames() {
        final ArrayList<String> lines = new ArrayList<String>();
        if (CapeUtils.pastebin != null) {
            try {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(CapeUtils.pastebin.openStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                    System.out.println(line);
                }
            }
            catch (Exception ex) {}
        }
        return lines;
    }
    
    public static ConcurrentHashMap<EntityPlayer, CapeTypes> getCapedPlayers(final ArrayList<String> list) {
        final ConcurrentHashMap<EntityPlayer, CapeTypes> capedPlayers = new ConcurrentHashMap<EntityPlayer, CapeTypes>();
        for (final EntityPlayer player : getPlayersInWorld()) {
            for (final String string : list) {
                final String[] args = string.split(":");
                if (args[0].equalsIgnoreCase(player.getUniqueID().toString())) {
                    for (final CapeTypes type : CapeTypes.values()) {
                        if (args[1].equalsIgnoreCase(type.toString())) {
                            capedPlayers.put(player, type);
                            System.out.println("added player " + player.getName() + " with cape " + type.toString());
                        }
                    }
                }
            }
        }
        return capedPlayers;
    }
    
    static {
        CapeUtils.mc = Minecraft.getMinecraft();
        try {
            CapeUtils.pastebin = new URL("https://pastebin.com/raw/zGEE0ZYm");
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
