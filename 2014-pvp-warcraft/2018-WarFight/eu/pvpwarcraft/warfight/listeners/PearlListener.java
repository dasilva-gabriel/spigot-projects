package eu.pvpwarcraft.warfight.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import eu.pvpwarcraft.warfight.Param;
import eu.pvpwarcraft.warfight.PlayerWrapper;
import eu.pvpwarcraft.warfight.PlayerWrapper.PlayerStates;
import eu.pvpwarcraft.warfight.WarFight;

public class PearlListener implements Listener {
	
	@EventHandler
	public void onPearl(PlayerInteractEvent event){
		
		Player player = event.getPlayer();
		PlayerWrapper pw = WarFight.getPlayer(player);
		
		if((event.getAction() != null && (event.getAction().equals(Action.RIGHT_CLICK_AIR))) && (!PlayerStates.isState(pw, PlayerStates.LOBBY) || !PlayerStates.isState(pw, PlayerStates.IN_QUEUE))){
			if(event.getItem() != null && (event.getItem().getType().equals(Material.ENDER_PEARL))){
				if(pw.getEnder_cooldown() > 0){
					event.setCancelled(true);
					player.sendMessage("§2Information §8» §7Cooldown enderpearl: §a"+((int) pw.getEnder_cooldown())+" seconde(s)");
					player.updateInventory();
					return;
				}else{
					pw.setEnder_cooldown(Param.ender_cooldown);
				}
			}
		}
		
	}

}
