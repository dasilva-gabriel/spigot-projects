package fr.lowtix.warcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;

public class TimeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			WarPlayer wPlayer = WarCore.getInstance().getUser(player);
			
			if(!wPlayer.getRank().isHigher(Ranks.MOD_P)) {
				player.sendMessage("§6Modération §8» §cCette commande est réservée aux membres de la haute Modération.");
				return true;
			}
			
			if(args.length != 1) {
				player.sendMessage("§6Modération §8» §cFaites §e/wtime <joueur>");
				return true;
			} else {
				String name = args[0];
				
				if(Bukkit.getPlayer(name) == null || !Bukkit.getPlayer(name).isOnline()) {
					player.sendMessage("§6Modération §8» §cJoueur hors-ligne.");
					return true;
				} else {
					Player target = Bukkit.getPlayer(name);
					WarPlayer wTarget = WarCore.getInstance().getUser(target);
					
					player.sendMessage("§8§l«§6-§8§l»§8§m------------------------------------------§8§l«§6-§8§l»");
					player.sendMessage("   §8• §7Information de connexion de §b"+target.getName()+"§7:");
					player.sendMessage("     §8> §7Première connexion: §a"+wTarget.getPlayerStats().firstConnect);
					player.sendMessage("     §8> §7Temps de jeu total: §a"+wTarget.getPlayerStats().timePlayed+" min");
					player.sendMessage("§8§l«§6-§8§l»§8§m------------------------------------------§8§l«§6-§8§l»");
					return true;
					
				}
				
			}
			
		}
		return true;
	}

}
