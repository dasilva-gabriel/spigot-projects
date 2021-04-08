package fr.lowtix.palatraining.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.enums.Locations;
import fr.lowtix.palatraining.handlers.GamePlayer;

public class LobbyManager {

	@SuppressWarnings("deprecation")
	public void teleportToLobby(Player player) {
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			all.showPlayer(player);
			player.showPlayer(all);
		}
		
		GamePlayer gplayer = PalaTraining.getInstance().getGamePlayer(player);
		
		gplayer.setInGame(false);
		gplayer.setGame(null);
		
		player.teleport(PalaTraining.getInstance().getLocationsConfig().getSavedLocation(Locations.SPAWN_LOCATION));
		gplayer.setInGame(false);
		
	}
}
