package eu.pvpwarcraft.meetup.listeners.players;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import eu.pvpwarcraft.meetup.handler.Step;

public class BlockBreak implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent event){
		if(Step.isStep(Step.LOBBY)){
			event.setCancelled(true);
		}
	}

}
