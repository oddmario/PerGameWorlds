package me.mariodev.pergameworlds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandReload implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(player.isOp() || player.hasPermission("pergameworlds.reload")) {
                PerGameWorlds.getInstance().reloadConfig();
                player.sendMessage(ChatColor.GREEN + "Successfully reloaded the PGW configuration file.");
            } else {
                player.sendMessage(ChatColor.RED + "[ERROR] You don't have permission to run this command.");
            }
        } else {
            PerGameWorlds.getInstance().getLogger().info(ChatColor.RED + "[ERROR] This command must be executed as a player.");
        }

        return true;
    }
    
}
