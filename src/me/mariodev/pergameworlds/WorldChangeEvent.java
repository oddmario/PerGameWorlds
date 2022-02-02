package me.mariodev.pergameworlds;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChangeEvent implements Listener {
    
    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        String uuid = e.getPlayer().getUniqueId().toString();
        String to = e.getPlayer().getWorld().getName();
        Boolean isBedrock = false;
        FileConfiguration config = PerGameWorlds.getInstance().getConfig();
        
        if(uuid.startsWith("00000000-0000-0000")) {
            // A hacky method to check whether the player is a Java one or a Bedrock one
            isBedrock = true;
        }
        
        if(isBedrock) {
            if(!config.getStringList("bedrock.AllowedWorlds").contains(to)) {
                Location location = Bukkit.getServer().getWorld(config.getString("bedrock.KickWorld")).getSpawnLocation();
                e.getPlayer().teleport(location);
                e.getPlayer().sendMessage(config.getString("bedrock.KickMessage"));
            }
        } else {
            if(!config.getStringList("java.AllowedWorlds").contains(to)) {
                Location location = Bukkit.getServer().getWorld(config.getString("java.KickWorld")).getSpawnLocation();
                e.getPlayer().teleport(location);
                e.getPlayer().sendMessage(config.getString("java.KickMessage"));
            }
        }
    }

}