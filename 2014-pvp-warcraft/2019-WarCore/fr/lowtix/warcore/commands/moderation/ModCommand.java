package fr.lowtix.warcore.commands.moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;

public class ModCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			WarPlayer wPlayer = WarCore.getInstance().getUser(player);
			
			if(wPlayer.getRank().isHigher(Ranks.TRIAL_MOD)) {
				
				if(args.length > 0) {
					
					String message = "";
					
					for(int i = 0; i < args.length; i++) {
						message += args[i] + " ";
					}
					
					Bukkit.broadcastMessage("§f[§6§lModération§f] §c"+player.getName()+"§7: §b"+message);
					
				} else {
					player.sendMessage("§6Modération §8» §cFaites §e/mod <message>.");
					return true;
				}
				
			} else {
				player.sendMessage("§6Modération §8» §cCette commande est réservée aux membres de la Modération.");
				return true;
			}
			
		}
		return true;
	}

}
