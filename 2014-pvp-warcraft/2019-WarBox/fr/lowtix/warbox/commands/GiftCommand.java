package fr.lowtix.warbox.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.enums.BowTrail;
import fr.lowtix.warbox.enums.Tags;
import fr.lowtix.warbox.players.WarPlayer;

public class GiftCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(!(sender instanceof Player)) {
			
			if(args.length == 3) {
				
				String name = args[0];
				String type = args[1];
				String s = args[2];
				
				if(Bukkit.getPlayer(name) == null || !Bukkit.getPlayer(name).isOnline()) {
					sender.sendMessage("§c§lJoueur déconnecté !");
				} else {
					
					Player player = Bukkit.getPlayer(name);
					WarPlayer wp = WarBox.getInstance().getUser(player);
					
					if(type.equalsIgnoreCase("tags")) {
						Tags tag = Tags.getTagFromName(s);
						if(!tag.equals(Tags.NONE)) {
							String permission = "warbox.tags."+tag.name().toLowerCase();
							
							if(player.hasPermission(permission)) {
								player.sendMessage("§8[§3§lW§5Crates§8] §6Vous avez déjà le tag "+tag.getDisplay()+"§6... Nous vous offrons alors §e75 coins§6!");
								wp.addCoins(75);
							} else {
								player.sendMessage("§8[§3§lW§5Crates§8] §6Vous avez gagné le tag "+tag.getDisplay()+"§6! Nous vous offrons aussi §e10 coins§6!");
								wp.addCoins(10);
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user "+ player.getName()+" add "+permission);
							}
							
						}
					} else if(type.equalsIgnoreCase("trails")) {
						BowTrail trail = BowTrail.getTrail(s);
						if(!trail.equals(BowTrail.NONE)) {
							String permission = "warbox.trails."+trail.name().toLowerCase();
							
							if(player.hasPermission(permission)) {
								player.sendMessage("§8[§3§lW§5Crates§8] §6Vous avez déjà la particule §b"+trail.getDisplayName()+"§6... Nous vous offrons alors §e75 coins§6!");
								wp.addCoins(75);
							} else {
								player.sendMessage("§8[§3§lW§5Crates§8] §6Vous avez gagné le tag "+trail.getDisplayName()+"§6! Nous vous offrons aussi §e10 coins§6!");
								wp.addCoins(10);
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user "+ player.getName()+" add "+permission);
							}
							
						}
					}
					
				}
				
			}
			
		} else {
			
			sender.sendMessage("§8[§3§lW§fInfo§8] §cVous ne pouvez pas faire cela en tant que joueur.");
			
		}
		
		return true;
	}

}
