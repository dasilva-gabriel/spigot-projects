package eu.pvpwarcraft.meetup.listeners.players;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import eu.pvpwarcraft.meetup.handler.Step;

public class PlayerLogin implements Listener {
	
	@EventHandler
	  public void onPlayerLogin(PlayerLoginEvent event) {
	    if ((Step.isStep(Step.LOBBY) || Step.isStep(Step.IN_GAME)) && (event.getResult() == PlayerLoginEvent.Result.KICK_FULL)) {
	      event.allow();
	    } else if ((!Step.isStep(Step.LOBBY) && !Step.isStep(Step.IN_GAME))) {
	      event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
	      event.setKickMessage("§cVous ne pouvez pas rejoindre ce serveur. Patientez... §8[§e"+Step.getName()+"§8]");
	    }
	  }

}
