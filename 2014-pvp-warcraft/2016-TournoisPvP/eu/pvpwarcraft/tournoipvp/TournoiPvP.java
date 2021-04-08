package eu.pvpwarcraft.tournoipvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TournoiPvP extends JavaPlugin {
	
	private static TournoiPvP instance;
	public static TournoiPvP getInstance(){
		return instance;
	}
	
	@Override
	public void onDisable() {
		ConfigManager.saveRegistersConfig();
	}
	
	@Override
	public void onEnable() {
		instance = this;
		
		ConfigManager.init();
		
		ConfigManager.saveRegistersConfig();
		
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), this);
		
		getCommand("inscription").setExecutor(new InscriptionCommands());
		getCommand("tournoiinfo").setExecutor(new TournoiInfoCommands());
		getCommand("tournoi").setExecutor(new TournoiCommands());
	}
	
	public static void sendHelp(Player player){
		player.sendMessage("§8» §2§lTournoi PvP 2v2");
		player.sendMessage("§8» §b/inscription <nom d'équipe> <cooéquipier> §7§om'inscrire au tournoi");
		player.sendMessage("§8» §b/tournoiinfo §7§osavoir si vous êtes inscris");
		if(player.isOp()){
			player.sendMessage("§8» §b/tournoi all §7§ovoir la liste des inscris connectés et déconnectés");
			player.sendMessage("§8» §b/tournoi online §7§ovoir la liste des inscris connectés");
		}else{
			player.sendMessage(" ");
			player.sendMessage("§8» §7Assurez vous d'être présent le §317/06 à 15 heures");
		}
	}

}
