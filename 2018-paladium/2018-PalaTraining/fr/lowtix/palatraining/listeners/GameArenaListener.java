package fr.lowtix.palatraining.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.lowtix.palaarenasmanager.events.PalaArenaReadyEvent;
import fr.lowtix.palaarenasmanager.managers.Arena;
import fr.lowtix.palatraining.PalaTraining;

public class GameArenaListener implements Listener {
	
	@EventHandler
	public void onArenaCreate(PalaArenaReadyEvent event) {
		
		Arena arena = event.getArena();
		
		if(PalaTraining.getInstance().getGameArenasManager().getArenaCreationIDs().contains(arena.getId())) {
			
			
			PalaTraining.getInstance().getGameArenasManager().readyArena(arena);
		}
		
	}

}
