package eu.pvpwarcraft.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.skypvp.managers.configuration.ConfigManager;
import eu.pvpwarcraft.skypvp.managers.players.PlayersStats;

public class CoinsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()){
				if(args.length > 0){
					if(args[0] != null && Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[0]).isOnline()){
						int coins = (int) PlayersStats.getCoins(Bukkit.getPlayer(args[0]));
						ConfigManager.getPlayersConfig().set("Players. " + Bukkit.getPlayer(args[0]).getName() + " .Coins", coins+20);
						ConfigManager.savePlayersConfig();
						coins = (int) PlayersStats.getCoins(Bukkit.getPlayer(args[0]));
						p.sendMessage("§aVous avez give 20 coins à §e"+Bukkit.getPlayer(args[0]).getName()+"§a. Il a: §c"+coins);
					}
				}
			}
		}else{
			if(args.length > 0){
				if(args[0] != null && Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[0]).isOnline()){
					int coins = (int) PlayersStats.getCoins(Bukkit.getPlayer(args[0]));
					ConfigManager.getPlayersConfig().set("Players. " + Bukkit.getPlayer(args[0]).getName() + " .Coins", coins+20);
					ConfigManager.savePlayersConfig();
					coins = (int) PlayersStats.getCoins(Bukkit.getPlayer(args[0]));
				}
			}
		}
		return true;
	}

}
