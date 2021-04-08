package fr.lowtix.warcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;

public class NoAFKCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			WarPlayer wp = WarCore.getInstance().getUser(player);
			
			if(wp.afkMustCommand) {
				
				player.sendMessage("§bInfo §8» §7Confirmation en cours...");
				
				if(args.length == 1 && args[0].equalsIgnoreCase(wp.afkMsg)) {
					player.sendMessage("§bInfo §8» §aCode confirmé! Vous n'êtes plus considéré comme AFK.");
					
					wp.afkPoints = -1;
					wp.reloadAfkMsg();
					wp.afkMustCommand = false;
					
				} else {
					wp.reloadAfkMsg();
					player.sendMessage("§bInfo §8» §cCode incorrect. Nouevau code: §b/noafk "+wp.afkMsg);
				}
				
			} else {
				player.sendMessage("§bInfo §8» §cVous n'êtes pas encore considéré comme AFK.");
				return true;
			}
			
		}
		return true;
	}

}
