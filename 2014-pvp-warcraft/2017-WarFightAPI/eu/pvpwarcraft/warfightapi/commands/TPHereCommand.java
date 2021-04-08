package eu.pvpwarcraft.warfightapi.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.warfightapi.Messages;

public class TPHereCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(!player.isOp()){
				player.sendMessage(Messages.prefix + "" + Messages.permission_admin_only);
				return true;
			}else{
				if(args.length > 0 && Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[0]).isOnline()){
					Player target = Bukkit.getPlayer(args[0]);
					player.sendMessage(Messages.prefix + "§7Joueur: §b" + target.getName());
					player.sendMessage(Messages.prefix + "" + Messages.teleportation);
					target.teleport(player);
					return true;
				}else{
					player.sendMessage(Messages.prefix + "" + Messages.not_online);
					return true;
				}
			}
		}
		return true;
	}

}
