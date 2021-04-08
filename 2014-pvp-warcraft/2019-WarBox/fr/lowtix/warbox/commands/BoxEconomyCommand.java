package fr.lowtix.warbox.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.enums.Groups;
import fr.lowtix.warbox.players.WarPlayer;

public class BoxEconomyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			WarPlayer wp = WarBox.getInstance().getUser(player);
			
			if(wp.getGroup().isHigherOrEquals(Groups.ADMINISTRATOR)) {
				
				if(args.length == 3) {
					String type = args[0];
					String name = args[1];
					String ammount = args[2];
					
					give(sender, type, name, ammount);
					
				}
				
			} else {
				player.sendMessage("§8[§3§lW§fInfo§8] §cVous n'avez pas les permissions nécéssaires...");
			}
			
		} else {
			if(args.length == 3) {
				String type = args[0];
				String name = args[1];
				String ammount = args[2];
				
				give(sender, type, name, ammount);
				
			}
		}
		
		return true;
	}

	public void give(CommandSender sender, String type, String name, String ammount) {
		if(Bukkit.getPlayer(name) != null && Bukkit.getPlayer(name).isOnline()) {
			
			Player player = Bukkit.getPlayer(name);
			WarPlayer wp = WarBox.getInstance().getUser(player);
			
			if(!isInt(ammount)) {
				sender.sendMessage("§8[§3§lW§fInfo§8] §cLa valeur §f\"§e"+ammount+"§f\" §cn'est pas une valeur entière.");
			} else {
				
				int am = Integer.parseInt(ammount);
				if(type.equalsIgnoreCase("coins") || type.equalsIgnoreCase("coin") || type.equalsIgnoreCase("c")) {
					wp.addCoins(am);
					sender.sendMessage("§8[§3§lW§fInfo§8] §6Vous avez ajouté §e"+ammount+" coin(s) §6sur le compte de §a"+player.getName()+"§6.");
					player.sendMessage("§8[§3§lW§fInfo§8] §e"+ammount+" coin(s) §6ont été ajoutés a votre compte.");
				} else if(type.equalsIgnoreCase("experience") || type.equalsIgnoreCase("xp") || type.equalsIgnoreCase("e")) {
					wp.addExp(am);
					sender.sendMessage("§8[§3§lW§fInfo§8] §6Vous avez ajouté §b"+ammount+" exp §6sur le compte de §b"+player.getName()+"§6.");
					player.sendMessage("§8[§3§lW§fInfo§8] §b"+ammount+" exp §6ont été ajoutés a votre compte.");

				} else {
					sender.sendMessage("§8[§3§lW§fInfo§8] §cLe type §f\"§e"+type+"§f\" §cn'est pas recconu.");
				}
				
			}
			
		} else {
			sender.sendMessage("§8[§3§lW§fInfo§8] §cLe joueur est actuellement hors-ligne.");
		}
	}
	
	public boolean isInt(String i) {
		try {
			Integer.parseInt(i);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
