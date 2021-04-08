package eu.pvpwarcraft.thetowers.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import eu.pvpwarcraft.thetowers.TheTowers;
import eu.pvpwarcraft.thetowers.handler.Step;
import eu.pvpwarcraft.thetowers.handler.TowerPlayer;
import eu.pvpwarcraft.warcraftapi.WarCraftAPI;
import eu.pvpwarcraft.warcraftapi.configuration.User;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		event.setQuitMessage(null);
		Player player = event.getPlayer();
		
		User user = WarCraftAPI.getInstance().getUsers().get(player.getName());
		
		
		if(TheTowers.getInstance().getPlayers().contains(player)){
			TowerPlayer twpl = TheTowers.getPlayer(player);
			user.setPoints(twpl.getEarned_points());
			user.save();
			TheTowers.removePlayer(twpl);
			
			player.sendMessage("§8§m--+-------------------------------------+--");
			player.sendMessage(" §8» §aVous avez gagné §e§l"+twpl.getEarned_points()+" point(s) §adurant la partie.");
			player.sendMessage("§8§m--+-------------------------------------+--");
			
			if(Step.isStep(Step.IN_GAME)){
				Bukkit.broadcastMessage("§6TheTowers §8» §c"+player.getName()+" §7a quitté la partie :c");
			}
		}
	}
	
}
