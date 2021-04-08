package eu.pvpwarcraft.meetup.listeners.players;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import eu.pvpwarcraft.meetup.handler.Step;

public class FoodLoss implements Listener {
	
	@EventHandler
	public void onFoodLoss(FoodLevelChangeEvent event){
		if(Step.isStep(Step.LOBBY)){
			event.setCancelled(true);
		}
	}

}
