package fr.lowtix.warcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SiteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player) {
			sender.sendMessage("§8[§e§l?§8] §7Le site est accessible §ahttps://www.pvp-warcraft.fr/");
		}
		return true;
	}

}
