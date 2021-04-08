package eu.pvpwarcraft.skypvp.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import eu.pvpwarcraft.skypvp.managers.top.TopKill;

public class TopCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		List<String> messages = TopKill.getTop();
		for(String s : messages){
			sender.sendMessage(s);
		}
		return true;
	}

}
