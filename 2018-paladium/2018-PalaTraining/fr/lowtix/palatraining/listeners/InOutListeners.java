package fr.lowtix.palatraining.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.handlers.GamePlayer;
import fr.lowtix.palatraining.handlers.Team;

public class InOutListeners implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		GamePlayer gplayer = PalaTraining.getInstance().getGamePlayer(event.getPlayer());
		
		PalaTraining.getInstance().getLobbyManager().teleportToLobby(gplayer.getPlayer());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
		GamePlayer gplayer = PalaTraining.getInstance().getGamePlayer(event.getPlayer());
		
		if(gplayer.isInTeam()) {
			Team team = gplayer.getTeam();
			team.removeMember(event.getPlayer().getUniqueId());
		}
	}

}
