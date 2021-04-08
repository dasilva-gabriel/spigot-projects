package eu.pvpwarcraft.tournoipvp;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.sendMessage("§8§m--------------------------------------------------");
		
		if(!RegistersManager.containPlayer(player)){
			player.sendMessage(" §8[§4§l!§8] §cVous n'êtes pas encore inscris à l'event §8[§4§l!§8]");
			player.sendMessage("   §8• §7Faites §b/inscription §7pour vous inscrire à l'event §8•");
			player.sendMessage("   §8• §7Invitez vos amis ce Samedi 17/06 §8•");
		}else{
			String team_name = RegistersManager.getSavedName(player);
			String team_first = RegistersManager.getSavedFirstPlayerName(team_name);
			String team_second = RegistersManager.getSavedSecondPlayerName(team_name);
			
			player.sendMessage(" §8[§4§l!§8] §cVous êtes inscris à l'event §8[§4§l!§8]");
			player.sendMessage("§8» §7Votre team:");
			player.sendMessage("   §8• §7Nom: §b"+team_name);
			player.sendMessage("   §8• §7Participant 1: §e"+team_first);
			player.sendMessage("   §8• §7Participant 2: §e"+team_second);
		}
		
		player.sendMessage("§8§m--------------------------------------------------");
	}

}
