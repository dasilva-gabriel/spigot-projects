package eu.pvpwarcraft.warfightapi.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.warfightapi.Messages;

public class ModCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if(!player.isOp()){
				player.sendMessage(Messages.prefix + "" + Messages.permission_mod_only);
				return true;
			} else {
				if (args.length >= 1) {
					Player s = (Player) sender;
					String reason = "";
					for (int i = 0; i < args.length; i++) {
						reason += args[i] + " ";
					}
					Bukkit.broadcastMessage("§8[§6§lModeration§8] §e" + s.getName() + "§7: §b" + reason.replace("&", "§"));
					return true;
				}
			}
		}
		return true;
	}

}
