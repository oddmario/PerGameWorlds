package me.mariodev.pergameworlds;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinEvent implements Listener {
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String uuid = e.getPlayer().getUniqueId().toString();
        String currentWorld = e.getPlayer().getWorld().getName();
        Boolean isBedrock = false;
        FileConfiguration config = PerGameWorlds.getInstance().getConfig();
        
        if(uuid.startsWith("00000000-0000-0000")) {
            // A hacky method to check whether the player is a Java one or a Bedrock one
            isBedrock = true;
        }
        
        if(isBedrock) {
            if(!currentWorld.equals(config.getString("bedrock.JoinWorld"))) {
                Location location = Bukkit.getServer().getWorld(config.getString("bedrock.JoinWorld")).getSpawnLocation();
                e.getPlayer().teleport(location);
            }
        } else {
            if(!currentWorld.equals(config.getString("java.JoinWorld"))) {
                Location location = Bukkit.getServer().getWorld(config.getString("java.JoinWorld")).getSpawnLocation();
                e.getPlayer().teleport(location);
            }
        }
    }

}