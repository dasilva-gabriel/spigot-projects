package fr.lowtix.palatraining.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.handlers.GamePlayer;

public class KillListener implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		
		Player player = event.getEntity();
		GamePlayer gp = PalaTraining.getInstance().getGamePlayer(player);
		
		if(gp.getGame() == null) {
			Bukkit.broadcastMessage("NULL GAME");
		} else {
			Bukkit.broadcastMessage("GAME " + gp.getGame().getId());
		}
		
		Bukkit.broadcastMessage(""+gp.isInGame());
		
		if(gp.isInGame()) {
			event.setDeathMessage("MORT EN GAME " + player.getName());
			gp.getGame().playerDeath(player, player.getKiller());
			return;
		} else {
			event.setDeathMessage("MORT SANS GAME " + player.getName());
			return;
		}
	}

}
