package eu.pvpwarcraft.warcore.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.warcore.guis.SanctionGui;
import eu.pvpwarcraft.warcore.utils.GuiManager;

public class SanctionCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(sender.hasPermission("moderation.sanction.gui") || sender.isOp()){
				if(args.length == 1 && Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[0]).isOnline()){
					GuiManager.openGui(new SanctionGui(player, Bukkit.getPlayer(args[0])));
					return true;
				}else{
					player.sendMessage("§8[§4»§8] §cLe joueur est déconnecté. La sanction doit être appliquée à la main.");
					return true;
				}
			}else{
				player.sendMessage("§8[§4»§8] §cCette commande est réservée aux membres de la modération.");
				return true;
			}
		}
		return true;
	}

}
