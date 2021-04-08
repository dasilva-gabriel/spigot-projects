package eu.pvpwarcraft.skypvp.listeners.players;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
	
	@EventHandler
	public void onQui(PlayerQuitEvent e){
		Player p = e.getPlayer();
		e.setQuitMessage(null);
		p.setGameMode(GameMode.ADVENTURE);
	}

}
