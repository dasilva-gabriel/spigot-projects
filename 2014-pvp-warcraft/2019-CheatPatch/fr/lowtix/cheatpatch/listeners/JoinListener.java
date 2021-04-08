package fr.lowtix.cheatpatch.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.lowtix.cheatpatch.CheatPatch;

public class JoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		CheatPatch.getInstance().getPlayer(player.getName());
		
		CheatPatch.getInstance().getCheatScoreboard().setup(player, true);
		
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		
		CheatPatch.getInstance().getPlayer(player.getName()).destruct();
		
		player.sendMessage(" ");
		player.sendMessage("§cVous avez déconnecté, vous récupérez tout de même votre miner.");
		player.sendMessage(" ");
	}

}
