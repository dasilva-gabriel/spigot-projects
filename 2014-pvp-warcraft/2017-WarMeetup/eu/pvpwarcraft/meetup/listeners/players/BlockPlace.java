package eu.pvpwarcraft.meetup.listeners.players;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import eu.pvpwarcraft.meetup.handler.Step;

public class BlockPlace implements Listener {
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		if(Step.isStep(Step.LOBBY)){
			event.setCancelled(true);
		}
		if(Step.isStep(Step.IN_GAME) || Step.isStep(Step.PRE_GAME)){
			int block = 0;
			for(int i = 0; i <= 6; i++){
				if(event.getBlock().getLocation().subtract(0, i, 0).getBlock().getType().equals(Material.COBBLESTONE) || event.getBlock().getLocation().subtract(0, i, 0).getBlock().getType().equals(Material.WOOD) || event.getBlock().getLocation().subtract(0, i, 0).getBlock().getType().equals(Material.AIR)){
					block ++;
				}
			}
			if(block >= 6 && (event.getBlock().getLocation().getY() < 60)){
				event.setCancelled(true);
				player.updateInventory();
				player.sendMessage("§4Attention §8» §7Les Towers sont §cinterdites§7.");
			}
		}
	}

}
