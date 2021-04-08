package fr.lowtix.warbox.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(args.length > 0) {
			String msg = "";
			
			for(int i = 0; i < args.length; i++) {
				msg += args[i] + " ";
			}
			
			msg = ChatColor.translateAlternateColorCodes('&', msg);
			
			Bukkit.broadcastMessage(msg);
			
		}
		
		return true;
	}

}
