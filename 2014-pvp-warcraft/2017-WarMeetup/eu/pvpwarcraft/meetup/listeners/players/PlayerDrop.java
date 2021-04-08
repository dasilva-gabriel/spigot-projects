package eu.pvpwarcraft.meetup.listeners.players;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import eu.pvpwarcraft.meetup.handler.Step;

public class PlayerDrop implements Listener {
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event){
		Player player = event.getPlayer();
		if(!Step.isStep(Step.IN_GAME)){
			event.setCancelled(true);;
			player.updateInventory();
		}
	}

}
