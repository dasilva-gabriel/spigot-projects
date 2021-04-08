package eu.pvpwarcraft.warfightapi.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.warfightapi.Messages;

public class AnnonceCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if(!player.isOp()){
				player.sendMessage(Messages.prefix + "" + Messages.permission_admin_only);
				return true;
			} else {
				if (args.length >= 1) {
					String reason = "";
					for (int i = 0; i < args.length; i++) {
						reason += args[i] + " ";
					}
					Bukkit.broadcastMessage("§8[§2§lAnnonce§8] §a" + reason.replace("&", "§"));
					return true;
				}
			}
		}
		return true;
	}

}
