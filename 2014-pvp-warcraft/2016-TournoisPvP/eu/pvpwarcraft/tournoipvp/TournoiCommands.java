package eu.pvpwarcraft.tournoipvp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TournoiCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (sender instanceof Player) {

			Player player = (Player) sender;
			
			if(args.length == 1 && player.isOp()){
				if(args[0].contains("all")){
					player.sendMessage("§8§m--------------------------------------------------");
					for(String keys : ConfigManager.getRegistersConfig().getKeys(false)){
						player.sendMessage("   §8• §e"+keys+" §8[§b"+RegistersManager.getSavedFirstPlayerName(keys)+"§7, §b"+RegistersManager.getSavedSecondPlayerName(keys)+"§8]");
					}
					player.sendMessage("§8§m--------------------------------------------------");
				}
				if(args[0].contains("online")){
					
					player.sendMessage("§8§m--------------------------------------------------");
					
					for(String keys : ConfigManager.getRegistersConfig().getKeys(false)){
						String first = RegistersManager.getSavedFirstPlayerName(keys);
						String second = RegistersManager.getSavedSecondPlayerName(keys);
						if(Bukkit.getPlayer(first) != null && Bukkit.getPlayer(first).isOnline()){
							if(Bukkit.getPlayer(second) != null && Bukkit.getPlayer(second).isOnline()){
								player.sendMessage("   §8• §e"+keys+" §8[§b"+first+"§7, §b"+second+"§8]");
							}
						}
					}
					player.sendMessage("§8§m--------------------------------------------------");
				}
			}else{
				TournoiPvP.sendHelp(player);
				return true;
			}
			
		}
		return true;
	}

}
