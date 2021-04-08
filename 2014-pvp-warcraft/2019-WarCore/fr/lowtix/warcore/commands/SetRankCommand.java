package fr.lowtix.warcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;

public class SetRankCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			WarPlayer user = WarCore.getInstance().getUser(player);
			
			if(user.getRank().isHigher(Ranks.ADMIN)) {
				
				if(args.length == 0) {
					player.sendMessage("§8§m----------------------------------------------");
					player.sendMessage(" §8» §e/setrank <joueur> <rank> §7§odéfinir un rang");
					player.sendMessage(" §8» §e/setrank list §7§oliste des rangs");
					player.sendMessage("§8§m----------------------------------------------");
					return true;
				}
				
				if(args.length == 2 && Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[0]).isOnline()) {
					Player target = Bukkit.getPlayer(args[0]);
					WarPlayer targetUser = WarCore.getInstance().getUser(target);
					
					Ranks rank = Ranks.getRankFromName(args[1]);
					
					if(rank == null) {
						target.sendMessage("§aRank §8» §7Le grade §e"+args[1]+" §7est introuvable.");
						return true;
					}
					
					targetUser.setRank(rank);
					
					target.sendMessage("§aRank §8» §7Vous avez été promu au grade de §e"+rank.getDisplayName()+"§7.");
					player.sendMessage("§aRank §8» §7Vous promu le joueur §e"+target.getName()+"§7 au grade de "+rank.getPrefixColor()+rank.getDisplayName()+"§7.");
					
					return true;
				} else {
					if(args.length == 1 && args[0].equalsIgnoreCase("list")){
						player.sendMessage("§aRank §8» §7Grades possibles §8(§e§l"+Ranks.values().length+"§8) §7:");
						for(Ranks ranks : Ranks.values()) {
							player.sendMessage(" §f§l> "+ranks.getPrefixColor() + ranks.getDisplayName() + " §8(§7§o"+ranks.name()+"§8)");
						}
						return true;
					}
					player.sendMessage("§aRank §8» §cJoueur déconnecté.");
					return true;
				}
			} else {
				player.sendMessage("§aRank §8» §cCette commande est réservée aux membres de l'Administration.");
				return true;
			}
			
		}
		return true;
	}

}
