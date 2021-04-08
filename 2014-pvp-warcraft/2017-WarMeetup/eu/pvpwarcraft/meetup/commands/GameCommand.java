package eu.pvpwarcraft.meetup.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.meetup.handler.Step;
import eu.pvpwarcraft.meetup.managers.PlayersInGameManager;
import eu.pvpwarcraft.meetup.schedulers.GameStart;
import eu.pvpwarcraft.meetup.utils.UserManager;

public class GameCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(!UserManager.isAdmin(player) && !UserManager.isFonda(player)){
				player.sendMessage("§6WarFight §8» §cErreur, cette commande est réservée aux membres de l'Administration.");
				return true;
			}
			if(args.length == 1){
				String subCommand = args[0];
				if(subCommand.equalsIgnoreCase("start")){
					if(!Step.isStep(Step.LOBBY) || GameStart.timer <= 1){
						player.sendMessage("§6WarFight §8» §cVous ne pouvez pas faire cela: La partie est déjà commencée.");
						return true;
					}
					if(PlayersInGameManager.getPlayersSize() <= 1){
						player.sendMessage("§6WarFight §8» §cVous ne pouvez pas faire cela: Vous êtes seul.");
						return true;
					}
					Bukkit.broadcastMessage("§6WarFight §8» §aLa partie à été forcée de commencée par §b"+player.getName()+"§a.");
					GameStart.timer = 0;
				}
			}
		}
		return true;
	}

}
