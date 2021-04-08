package eu.pvpwarcraft.meetup.listeners.players;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import eu.pvpwarcraft.meetup.handler.Step;
import eu.pvpwarcraft.meetup.managers.PlayersInGameManager;
import eu.pvpwarcraft.meetup.managers.WinManager;
import eu.pvpwarcraft.warfightapi.WarFightAPI;
import eu.pvpwarcraft.warfightapi.managers.User;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		User user = WarFightAPI.getInstance().getUsers().get(player.getName());
		user.save();
		event.setQuitMessage(null);
		if(Step.isStep(Step.IN_GAME) || Step.isStep(Step.PRE_GAME)){
			if (PlayersInGameManager.isInGame(player)) {
				PlayersInGameManager.removePlayer(player);
				event.setQuitMessage("§6WarFight §8» §c" + player.getName() + " §7à quitté la partie :c");
			}
			
			WinManager.CheckWin();
		}else{
			event.setQuitMessage(null);
			if (PlayersInGameManager.isInGame(player)) {
				PlayersInGameManager.removePlayer(player);
			}
		}
		
	}

}
