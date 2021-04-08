package fr.lowtix.warcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;

public class CreatifCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			WarPlayer wp = WarCore.getInstance().getUser(player);
			
			if(wp.getRank().isHigher(Ranks.GOLD)) {
				
				player.sendMessage("§bInfo §8» §7Tentative de connexion au serveur §eCréatif §7en cours...");
				
				try {
					ByteArrayDataOutput out = ByteStreams.newDataOutput();
					out.writeUTF("Connect");
					out.writeUTF("creatif");
					player.sendPluginMessage(WarCore.getInstance(), "BungeeCord", out.toByteArray());
				} catch (Exception e) {
					player.sendMessage("§bInfo §8» §cServeur actuellement éteint.");
				}
				
			} else {
				player.sendMessage("§bInfo §8» §cVous devez avoir le grade §e§lGOLD §cou supérieur pour faire cela.");
				return true;
			}
			
		}
		return true;
	}

}
