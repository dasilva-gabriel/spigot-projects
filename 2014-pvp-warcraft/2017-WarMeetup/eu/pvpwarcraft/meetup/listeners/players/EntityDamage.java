package eu.pvpwarcraft.meetup.listeners.players;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import eu.pvpwarcraft.meetup.handler.Step;

public class EntityDamage implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent event){
		if(!Step.isStep(Step.IN_GAME)){
			event.setCancelled(true);
		}
	}

}
