package fr.lowtix.warcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Levels;
import fr.lowtix.warcore.utils.PlayerUtils;

public class LevelUpCommand implements CommandExecutor {
	
	int min = 43200;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			WarPlayer wp = WarCore.getInstance().getUser(player);
			
			if(!player.isOp() || args.length < 1) {
				int last = wp.getPlayerStats().getSecondsLastRankup();
				if(last < min) {
					int time =  (((min - last)/60)/60);
					String unit = "heures";
					if(time <= 2) {
						time =  ((min - last)/60);
						unit = "minutes";
						
						if(time <= 2) {
							time =  ((min - last));
							unit = "seconde(s)";
						}
					}
					player.sendMessage("§aLevel §8» §cVous ne pourrez LevelUp que dans §e"+ time + " "+unit+"§c.");
					return true;
				}
				
				if(wp.getLevel().equals(Levels.LEVEL_10)) {
					player.sendMessage("§aLevel §8» §bVous avez déjà le niveau maximal.");
					return true;
				}
				
				Levels level = Levels.getLevel(wp.getLevel().getId() + 1);
				
				player.sendMessage("§aLevel §8» §7Calculs de vos fonds en cours...");
				
				if(wp.getPoints() < level.getPointsForUp() || wp.getCoins() < level.getMoneyForUp()) {
					player.sendMessage("§bInfo §8» §cVous n'avez pas les fonds nécéssaires.");
					player.sendMessage("§8§l«§6-§8§l»§8§m------------------------------------------§8§l«§6-§8§l»");
					player.sendMessage("  §8[§b§l?§8] §7Rendez-vous dans le §e/menu §7pour connaitre les requis nécéssaires pour ce LevelUp.");
					player.sendMessage("§8§l«§6-§8§l»§8§m------------------------------------------§8§l«§6-§8§l»");
					return true;
				} else {
					PlayerUtils.spawnFirework(player.getLocation(), FireworkEffect.Type.STAR, 3, Color.YELLOW, Color.RED, true, true);
					wp.removeCoins(level.getMoneyForUp());
					wp.setPoints(wp.getPoints() - level.getPointsForUp());
					player.sendMessage("§aLevel §8» §bPaiement accepté!");
					wp.setLevel(level);
					Bukkit.broadcastMessage("§8[§e§l!§8] §6"+player.getName()+" §7passe au niveau §d§l"+level.getDisplayName()+"§7, faites de même avec le §a/levelup§7.");
				}
				
				wp.getPlayerStats().setLastLevelUp(System.currentTimeMillis());
			}
			
			
		}
		return true;
	}

}
