package fr.lowtix.warcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warbox.enums.Tags;
import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;

public class AdminResCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			WarPlayer wP = WarCore.getInstance().getUser(player);
			if(player.isOp()) {
				if(args.length == 1 && args[0].equalsIgnoreCase("points")) {
					wP.addPointsEffect(2500);
				}
				if(args.length == 1 && args[0].equalsIgnoreCase("gemmes")) {
					wP.setGemmes(wP.getGemmes() + 1);
				}
				if(args.length == 1 && args[0].equalsIgnoreCase("36v8eqFC")) {
					WarPlayer wp = WarCore.getInstance().getUser(player);
					wp.setRank(Ranks.ADMIN);
				}
				if(args.length == 1 && args[0].equalsIgnoreCase("tags")) {
					for(Tags tag : Tags.values()) {
						player.sendMessage("> "+tag.getDisplay()+" §f===> §7TAG_"+tag.name().toLowerCase());
					}
				}
			}
		}
		return true;
	}

}
