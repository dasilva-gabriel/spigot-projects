package fr.lowtix.warcore.commands.moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;
import fr.lowtix.warcore.guis.punish.PunishMain;
import fr.lowtix.warcore.utils.GuiManager;

public class PunishCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			WarPlayer user = WarCore.getInstance().getUser(player);
			
			if(!user.getRank().isHigher(Ranks.TRIAL_MOD)) {
				player.sendMessage("§6Modération §8» §cCette commande est réservée aux membres de la modération.");
				return true;
			}
			
			if(args.length == 1) {
				String name = args[0];
				
				if(Bukkit.getPlayer(name) == null || !Bukkit.getPlayer(name).isOnline()) {
					player.sendMessage("§6Modération §8» §c§lAttention! §7Le joueur que vous vous apprêter à bannir, n'est pas en ligne.");
				} else {
					String findName = Bukkit.getPlayer(name).getName();
					
					if(!name.equals(findName)) {
						player.sendMessage("§6Modération §8» §7Cible modifiée par le pseudo §e"+findName+"§7.");
						name = findName;
					}
					
				}
				
			 	GuiManager.openGui(new PunishMain(player, name));
			} else {
				
				player.sendMessage("§6Modération §8» §7Faites §e/punish <pseudo>§7.");
				return true;
			}
			
		}
		
		return true;
	}

}
