package eu.pvpwarcraft.warfightapi.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.warfightapi.Messages;

public class FlyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(!player.isOp()){
				player.sendMessage(Messages.prefix + "" + Messages.permission_mod_only);
				return true;
			}else{
				if(player.getAllowFlight()){
					player.sendMessage(Messages.prefix + "" + Messages.fly_set.replace("%mode%", "Faux"));
					player.setAllowFlight(false);
					player.setFlying(false);
				}else{
					player.sendMessage(Messages.prefix + "" + Messages.fly_set.replace("%mode%", "Vrai"));
					player.setAllowFlight(true);
					player.setFlying(true);
				}
			}
		}
		return true;
	}

}
