package fr.paladium.palaaccountrecovery.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.paladium.palaaccountrecovery.PalaAccountRecovery;
import fr.paladium.palaaccountrecovery.managers.usernamehistory.Username;
import fr.paladium.palaaccountrecovery.managers.usernamehistory.UsernameHistory;

public class InOutEvents implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		
		player.sendMessage("§7Tentative de connexion avec Mojang §6§koOo§r§7...");
		
		try {
			
			UsernameHistory history = PalaAccountRecovery.getInstance().getUsernameHistoryManager().getNameHistory(player);
			
			if(history == null) {
				player.sendMessage("§cErreur serveur de Mojang!");
			} else {
				player.sendMessage("§aConnexion réussie !");
				player.sendMessage("§fHistorique trouvé: ");
	            for(Username usr : history.getHistory()){
	            	player.sendMessage(" §8> §e" + usr.getName() +" §7- §d" + usr.getTime());
	            }
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
