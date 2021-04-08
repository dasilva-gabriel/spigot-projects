package eu.pvpwarcraft.skypvp.managers.players;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.skypvp.managers.configuration.ConfigManager;

public class PlayersStats {

	public static int getKills(OfflinePlayer p) {
		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Kills")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Kills", 0);
		}

		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Deaths")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Deaths", 0);
		}
		
		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Coins")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", 0);
		}
		
		
		int kills = ConfigManager.getPlayersConfig().getInt("Players. " + p.getName() + " .Kills");
		return kills;
	}

	public static int getDeaths(Player p) {
		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Kills")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Kills", 0);
		}

		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Deaths")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Deaths", 0);
		}
		
		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Coins")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", 0);
		}
		
		
		int deaths = ConfigManager.getPlayersConfig().getInt("Players. " + p.getName() + " .Deaths");
		return deaths;
	}
	
	public static int getCoins(Player p) {
		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Kills")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Kills", 0);
		}

		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Deaths")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Deaths", 0);
		}
		
		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Coins")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", 0);
		}
		
		
		int coins = ConfigManager.getPlayersConfig().getInt("Players. " + p.getName() + " .Coins");
		return coins;
	}

	public static double getRatio(Player p) {
		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Kills")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Kills", 0);
		}

		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Deaths")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Deaths", 0);
		}
		
		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Coins")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", 0);
		}
		
		
		int deaths = ConfigManager.getPlayersConfig().getInt("Players. " + p.getName() + " .Deaths");
		int kills = ConfigManager.getPlayersConfig().getInt("Players. " + p.getName() + " .Kills");
		double ratio = 0;
		if (kills > 0 && deaths > 0) {
			ratio = ((double) kills / (double) deaths);
		} else if (kills == 0 && deaths > 0) {
			ratio = -deaths;
		} else if (deaths == 0 && kills > 0) {
			ratio = kills;
		} else {
			ratio = 0;
		}
		if(ratio >= 0){
			return ratio;
		}else{
			return 0.0;
		}
	}

}
