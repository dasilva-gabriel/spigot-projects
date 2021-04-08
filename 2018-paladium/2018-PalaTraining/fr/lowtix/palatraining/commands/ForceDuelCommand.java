package fr.lowtix.palatraining.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.enums.UnrankedQueueType;

public class ForceDuelCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(args.length == 1) {
				String name = args[0];
				if(Bukkit.getPlayer(name) != null && Bukkit.getPlayer(name).isOnline()) {
					
					Player target = Bukkit.getPlayer(name);
					Bukkit.broadcastMessage("TEST > " + player.getName() + " VS " + target.getName());
					
					PalaTraining.getInstance().getGameManager().createOneVsOne(player, target, false, UnrankedQueueType.MONEY);
					
				} else {
					player.sendMessage("§cHors-Ligne !");
				}
			}
			
			
		}
		
		return true;
	}

}
