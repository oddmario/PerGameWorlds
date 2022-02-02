package me.mariodev.pergameworlds;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class PerGameWorlds extends JavaPlugin {
    private static PerGameWorlds instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        
        // Events
        getServer().getPluginManager().registerEvents(new WorldChangeEvent(), this);
        getServer().getPluginManager().registerEvents(new OnJoinEvent(), this);
        
        // Commands
        this.getCommand("pgwreload").setExecutor(new CommandReload());
        
        getLogger().info(ChatColor.GREEN + "Enabled " + this.getName() + " " + this.getDescription().getVersion() + " by Mario");
    }
    
    @Override
    public void onDisable() {
        getLogger().info(ChatColor.GREEN + "Disabled " + this.getName());
    }
    
    public static PerGameWorlds getInstance() {
        return instance;
    }

}
