package fr.lowtix.warbox.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warbox.guis.ArmorGui;
import fr.lowtix.warbox.guis.CratesGui;
import fr.lowtix.warbox.guis.ExtraGui;
import fr.lowtix.warbox.guis.KitsGui;
import fr.lowtix.warbox.guis.ProfilGui;
import fr.lowtix.warbox.utils.GuiManager;

public class MenusManagerCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(!(sender instanceof Player)) {
			
			if(args.length == 2) {
				
				String name = args[0];
				String menu = args[1];
				
				if(Bukkit.getPlayer(name) == null || !Bukkit.getPlayer(name).isOnline()) {
					sender.sendMessage("§c§lJoueur déconnecté !");
				} else {
					
					Player player = Bukkit.getPlayer(name);
					
					try {
						switch (menu) {
						case "kits": GuiManager.openGui(new KitsGui(player)); break;
						case "equipements": GuiManager.openGui(new ArmorGui(player, 0)); break;
						case "extra": GuiManager.openGui(new ExtraGui(player, 0)); break;
						case "profil": GuiManager.openGui(new ProfilGui(player)); break;
						case "crates": GuiManager.openGui(new CratesGui(player)); break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
			}
			
		} else {
			
			sender.sendMessage("§8[§3§lW§fInfo§8] §cVous ne pouvez pas faire cela en tant que joueur.");
			
		}
		
		return true;
	}

}
