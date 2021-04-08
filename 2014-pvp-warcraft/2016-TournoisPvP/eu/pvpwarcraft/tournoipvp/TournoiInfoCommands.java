package eu.pvpwarcraft.tournoipvp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TournoiInfoCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (sender instanceof Player) {

			Player player = (Player) sender;
			
			String team_name = "§cVous n'êtes pas inscris";
			String team_first = "§4✖";
			String team_second = "§4✖";
			
			if(RegistersManager.containPlayer(player)){
				team_name = RegistersManager.getSavedName(player);
				team_first = RegistersManager.getSavedFirstPlayerName(team_name);
				team_second = RegistersManager.getSavedSecondPlayerName(team_name);
			}
			
			player.sendMessage("§8» §2§lTournoi PvP 2v2");
			player.sendMessage("§8» §7Votre team:");
			player.sendMessage("   §8• §7Nom: §b"+team_name);
			player.sendMessage("   §8• §7Participant 1: §e"+team_first);
			player.sendMessage("   §8• §7Participant 2: §e"+team_second);
			
		}
		return true;
	}

}
