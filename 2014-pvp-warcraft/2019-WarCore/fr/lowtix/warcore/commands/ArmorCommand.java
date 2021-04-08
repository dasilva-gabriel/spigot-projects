package fr.lowtix.warcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Levels;
import fr.lowtix.warcore.enums.Ranks;
import fr.lowtix.warcore.guis.ArmorGui;
import fr.lowtix.warcore.utils.GuiManager;

public class ArmorCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			WarPlayer wp = WarCore.getInstance().getUser(player);
			
			if(wp.getRank().isHigher(Ranks.MOD) || wp.getLevel().isHigher(Levels.LEVEL_10)) {
				
				if(args.length == 1) {
					
					String name = args[0];
					
					if(Bukkit.getPlayer(name) != null && Bukkit.getPlayer(name).isOnline()) {
						
						GuiManager.openGui(new ArmorGui(player, Bukkit.getPlayer(name)));
						player.sendMessage("§bInfo §8» §7Vous avez ouvert l'inventaire des armures de §a"+Bukkit.getPlayer(name).getName()+"§7.");
						return true;
						
					} else {
						player.sendMessage("§bInfo §8» §7Faites §e/armor <joueur> §7pour voir son armure");
						return true;
					}
					
				}
				
			} else {
				player.sendMessage("§bInfo §8» §cVous devez être au §bNiveau 10 §cpour faire cela.");
				return true;
			}
			
		}
		return true;
	}

}
