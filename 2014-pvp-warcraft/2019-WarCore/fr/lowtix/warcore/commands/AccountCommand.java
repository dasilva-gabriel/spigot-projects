package fr.lowtix.warcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;

public class AccountCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			WarPlayer wp = WarCore.getInstance().getUser(player);
			
			player.sendMessage("§8§l«§6-§8§l»§8§m------------------------------------------§8§l«§6-§8§l»");
			player.sendMessage("   §8• §7Coins: §e"+wp.getCoins());
			player.sendMessage("   §8• §7Points: §e"+wp.getPoints());
			player.sendMessage("   §8• §7Gemmes: §e"+wp.getGemmes());
			player.sendMessage("§8§l«§6-§8§l»§8§m------------------------------------------§8§l«§6-§8§l»");
		}
		return true;
	}

}
