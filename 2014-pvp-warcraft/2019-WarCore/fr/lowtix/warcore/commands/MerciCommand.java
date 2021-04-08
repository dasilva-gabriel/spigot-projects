package fr.lowtix.warcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.enums.Boosters;
import fr.lowtix.warcore.managers.boosters.Booster;

public class MerciCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(WarCore.getInstance().booster.getBoostersActive().size() == 0) {
				player.sendMessage("§8[§e§l!§8] §cAucun booster n'est actif :c");
			} else {
				for(Boosters boost : WarCore.getInstance().booster.getBoostersActive()) {
					Booster booster = WarCore.getInstance().booster.boosters.get(boost);
					booster.thanks(player);
				}
			}
			
		}
		return true;
	}

}
