package fr.lowtix.palatraining.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.palatraining.utils.GuiManager;
import fr.lowtix.palatraining.guis.MainGui;

public class PlayCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			GuiManager.openGui(new MainGui(player));
		}
		
		return true;
	}

}
