package fr.lowtix.warcore.commands.moderation;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;

public class QuestionCommand implements CommandExecutor {
	
	private HashMap<String, Long> cooldown = new HashMap<String, Long>();
	private int cmdCooldown = 300;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (sender instanceof Player) {

			Player player = (Player) sender;

			if (args.length > 0) {

				String message = "";

				for (int i = 0; i < args.length; i++) {
					message += args[i] + " ";
				}

				if(cooldown.containsKey(player.getName())) {
					Long past = cooldown.get(player.getName());
					Long elapsed = (System.currentTimeMillis() - past);
					elapsed /= 1000;
					
					if(elapsed < cmdCooldown) {
						player.sendMessage("§6Modération §8» §cVous devez attendre §e"+(cmdCooldown - elapsed)+" seconde(s) §cavant de pouvoir reposer une question.");
						return true;
					}
					
				}
				
				cooldown.put(player.getName(), System.currentTimeMillis());
				
				for(WarPlayer players : WarCore.getInstance().getUsers().values()) {
					if(players.getRank().isHigher(Ranks.SUPPORT) && players.getModPlayer().isChatActive()) {
						players.getPlayer().sendMessage("§f(§cModération§f) §e[Quest] §a"+player.getName()+" §8» §f"+message);
					}
				}

			} else {
				player.sendMessage("§6Modération §8» §cFaites §e/question <votre question>.");
				return true;
			}

		}
		return true;
	}

}
