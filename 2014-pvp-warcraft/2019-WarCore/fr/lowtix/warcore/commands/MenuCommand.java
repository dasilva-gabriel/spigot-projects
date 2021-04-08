package fr.lowtix.warcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.guis.MenuGui;
import fr.lowtix.warcore.utils.GuiManager;

public class MenuCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player) {
			GuiManager.openGui(new MenuGui((Player) sender));
		}
		return true;
	}

}
