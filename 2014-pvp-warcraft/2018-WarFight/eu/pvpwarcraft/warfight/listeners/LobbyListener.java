package eu.pvpwarcraft.warfight.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.warfight.PlayerWrapper;
import eu.pvpwarcraft.warfight.PlayerWrapper.PlayerStates;
import eu.pvpwarcraft.warfight.managers.guis.GuiManager;
import eu.pvpwarcraft.warfight.managers.guis.players.RankedGui;
import eu.pvpwarcraft.warfight.managers.guis.players.UnRankedGui;
import eu.pvpwarcraft.warfight.managers.queues.QueuesManager;
import eu.pvpwarcraft.warfight.WarFight;

public class LobbyListener implements Listener {
	
	@EventHandler
	public static void onInteraction(PlayerInteractEvent event){
		
		Player player = event.getPlayer();
		PlayerWrapper pw = WarFight.getPlayer(player);
		
		Action action = event.getAction();
		if(action != null && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)){
			ItemStack item = event.getItem();
			if(item != null){
				if(PlayerStates.isState(pw, PlayerStates.LOBBY)){
					event.setCancelled(true);
					if(item.getItemMeta().getDisplayName().contains("§e§lUnRanked")){
						GuiManager.openGui(new UnRankedGui(player));
					}
					if(item.getItemMeta().getDisplayName().contains("§c§lRanked")){
						GuiManager.openGui(new RankedGui(player));
					}
				} else if(PlayerStates.isState(pw, PlayerStates.IN_QUEUE)){
					event.setCancelled(true);
					if(item.getItemMeta().getDisplayName().contains("Quitter la file")){
						QueuesManager.removeFromQueue(player);
					}
				}
			}
		}
	}

}
