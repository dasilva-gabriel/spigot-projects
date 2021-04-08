package eu.pvpwarcraft.warfightapi.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.warfightapi.Messages;

public class AdventureCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(!player.isOp()){
				player.sendMessage(Messages.prefix + "" + Messages.permission_admin_only);
				return true;
			}else{
				player.setGameMode(GameMode.ADVENTURE);
				player.sendMessage(Messages.prefix + "" + Messages.gamemode_set.replace("%mode%", "Aventure"));
			}
		}
		return true;
	}

}
