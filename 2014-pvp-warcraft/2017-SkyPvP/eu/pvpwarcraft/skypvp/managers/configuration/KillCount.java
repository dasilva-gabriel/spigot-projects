package eu.pvpwarcraft.skypvp.managers.configuration;

import org.bukkit.entity.Player;

public class KillCount {
	
	public static void addKill(Player p){
		if(!ConfigManager.getPlayersConfig().isSet("Players. "+p.getName()+" .Kills")){
			ConfigManager.getPlayersConfig().set("Players. "+p.getName()+" .Kills", 0);
		}
		
		if(!ConfigManager.getPlayersConfig().isSet("Players. "+p.getName()+" .Deaths")){
			ConfigManager.getPlayersConfig().set("Players. "+p.getName()+" .Deaths", 0);
		}
		ConfigManager.getPlayersConfig().set("Players. "+p.getName()+" .Kills", ConfigManager.getPlayersConfig().getInt("Players. "+p.getName()+" .Kills")+1);
	}
	
	public static void addDeath(Player p){
		if(!ConfigManager.getPlayersConfig().isSet("Players. "+p.getName()+" .Kills")){
			ConfigManager.getPlayersConfig().set("Players. "+p.getName()+" .Kills", 0);
		}
		
		if(!ConfigManager.getPlayersConfig().isSet("Players. "+p.getName()+" .Deaths")){
			ConfigManager.getPlayersConfig().set("Players. "+p.getName()+" .Deaths", 0);
		}
		ConfigManager.getPlayersConfig().set("Players. "+p.getName()+" .Deaths", ConfigManager.getPlayersConfig().getInt("Players. "+p.getName()+" .Deaths")+1);
	}

}
