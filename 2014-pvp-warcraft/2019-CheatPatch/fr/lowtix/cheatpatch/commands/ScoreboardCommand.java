package fr.lowtix.cheatpatch.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.cheatpatch.CheatPatch;
import fr.lowtix.cheatpatch.PlayerWrapper;

public class ScoreboardCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {

			Player p = (Player) sender;
			PlayerWrapper wp = CheatPatch.getInstance().getPlayer(p.getName());
			
			if (args.length == 0) {

				if(wp.getScoreboard()) {
					wp.setScoreboard(false);
					p.sendMessage("§cVous ne verrez plus le scoreboard!");
				} else {
					wp.setScoreboard(true);
					p.sendMessage("§aVous avez activé le scordboard!");
				}

			} else if(args.length == 1 && args[0].equalsIgnoreCase("on")) {
				wp.setScoreboard(true);
				p.sendMessage("§aVous avez activé le scordboard!");
			} else if(args.length == 1 && args[0].equalsIgnoreCase("off")) {
				wp.setScoreboard(false);
				p.sendMessage("§cVous ne verrez plus le scoreboard!");
			}

		}

		return true;
	}

}
