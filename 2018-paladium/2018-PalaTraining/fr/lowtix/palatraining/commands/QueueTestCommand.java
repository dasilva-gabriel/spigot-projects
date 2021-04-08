package fr.lowtix.palatraining.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.enums.UnrankedQueueType;
import fr.lowtix.palatraining.handlers.GamePlayer;

public class QueueTestCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			GamePlayer gp = PalaTraining.getInstance().getGamePlayer(player);
			
			Bukkit.broadcastMessage("QUEUE > " + player.getName());
			
			PalaTraining.getInstance().getQueueManager().addInQueue(gp, PalaTraining.getInstance().getQueueManager().getQueue(false, UnrankedQueueType.MONEY));
			
			
		}
		
		return true;
	}

}
