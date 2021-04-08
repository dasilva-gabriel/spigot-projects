package fr.paladium.palaaccountrecovery.managers.usernamehistory;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.parser.ParseException;

import fr.paladium.palaaccountrecovery.PalaAccountRecovery;

public class UsernameHistoryManager {
	
	private boolean active;
	
	private JSONUtility jsonUtility;
	private HashMap<UUID, UsernameHistory> usernames;
	
	public void init(boolean active) {
		
		this.active = active;
		
		usernames = new HashMap<UUID, UsernameHistory>();
		
	}

	public boolean isActive() {
		return active;
	}

	public JSONUtility getJsonUtility() {
		return jsonUtility;
	}

	public HashMap<UUID, UsernameHistory> getUsernamesHistory() {
		return usernames;
	}

	public UsernameHistory getNameHistory(Player player) {
		
		Bukkit.getScheduler().runTaskAsynchronously(PalaAccountRecovery.getInstance(), new Runnable() {
            public void run() {
				
            	UsernameHistory history = null;
            	
				try {
					history = new UsernameHistory(player.getName());
				} catch (IOException e) {
					PalaAccountRecovery.getInstance().getLogger().warning("Erreur lors du contact avec les serveurs de Mojang: " + e.getLocalizedMessage());
					return;
				} catch (ParseException e) {
					PalaAccountRecovery.getInstance().getLogger().warning("Impossible de comprendre la réponse de Mojang: " + e.getLocalizedMessage());
					return;
				}
				
				usernames.put(player.getUniqueId(), history);
				
            }
        });
		
		return usernames.get(player.getUniqueId());
	}
	
}
