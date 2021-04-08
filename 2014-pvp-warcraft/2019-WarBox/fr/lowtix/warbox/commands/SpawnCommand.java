package fr.lowtix.warbox.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warbox.WarBox;

public class SpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(args.length == 0 || !player.isOp()) {
				WarBox.getInstance().getSpawnManager().teleportToSpawn(player);
			} else {
				if(args.length == 1) {
					String name = args[0];
					
					if(Bukkit.getPlayer(name) != null && Bukkit.getPlayer(name).isOnline()) {
						Player target = Bukkit.getPlayer(name);
						player.sendMessage("§8[§3§lW§fInfo§8] §6Téléportation de §b"+target.getName()+" §6au spawn...");
						target.sendMessage("§8[§3§lW§fInfo§8] §6Vous avez été téléporté au spawn!");
						target.teleport(WarBox.getInstance().getSpawn());
					}
				}
			}
			
		}
		return true;
	}

}
