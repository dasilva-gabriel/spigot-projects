package fr.lowtix.warcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.enums.Boosters;
import fr.lowtix.warcore.managers.boosters.Booster;

public class BoosterCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			player.sendMessage("§8§l«§6-§8§l»§8§m------------------------------------------§8§l«§6-§8§l»");
			player.sendMessage("  §7Pour remercier le(s) booster(s) faites §6/merci");
			player.sendMessage("  ");
			
			if(WarCore.getInstance().booster.getBoostersActive().size() == 0) {
				player.sendMessage("  §4> §cAucun booster n'est actif :c");
			} else {
				for(Boosters boost : WarCore.getInstance().booster.getBoostersActive()) {
					Booster booster = WarCore.getInstance().booster.boosters.get(boost);
					player.sendMessage("  §8• §a§lACTIF §8| §e"+boost.getDisplay()+" §8(§c"+(60-booster.getMinuteLived())+" min restantes§8)");
				}
			}
			
			player.sendMessage("§8§l«§6-§8§l»§8§m------------------------------------------§8§l«§6-§8§l»");
			
		}
		return true;
	}

}
