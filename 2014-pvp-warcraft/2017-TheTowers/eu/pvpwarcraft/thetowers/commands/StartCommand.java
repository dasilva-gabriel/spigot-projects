package eu.pvpwarcraft.thetowers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.thetowers.handler.Step;
import eu.pvpwarcraft.thetowers.schedulers.StartScheduler;

public class StartCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {

		if (sender instanceof Player){
			
			Player player = (Player) sender;
			
			if(Step.isStep(Step.LOBBY) && player.hasPermission("warcraft.mj_thetowers.forcestart")){
				StartScheduler.timer = 5;
			}
		}
		
		return true;
	}

}
