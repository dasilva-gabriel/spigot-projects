package eu.pvpwarcraft.warfight.managers.lobby;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import eu.pvpwarcraft.warfight.PlayerWrapper;
import eu.pvpwarcraft.warfight.PlayerWrapper.PlayerStates;
import eu.pvpwarcraft.warfight.WarFight;
import eu.pvpwarcraft.warfight.managers.Locations;
import eu.pvpwarcraft.warfight.managers.LocationsManager;
import eu.pvpwarcraft.warfight.managers.queues.QueuesManager;
import eu.pvpwarcraft.warfight.utils.HeadBuilder;
import eu.pvpwarcraft.warfight.utils.ItemBuilder;

public class LobbyManager {
	
	public static void giveLobbyItems(Player player){
		
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		
		player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setName("§c§lRanked §8• §7Clique droit").build());
		player.getInventory().setItem(1, new ItemBuilder(Material.IRON_SWORD).setName("§e§lUnRanked §8• §7Clique droit").build());
		
		player.getInventory().setItem(4, new HeadBuilder(player.getName()).setName("§3§lProfil §8• §7Clique droit").build());
		
		player.getInventory().setItem(8, new ItemBuilder(Material.ANVIL).setName("§d§lEditeur §8• §7Clique droit").build());
	}
	
	public static void goToLobby(Player player, boolean teleport, boolean removeFromQueue){
		
		PlayerWrapper pl = WarFight.getPlayer(player);
		
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		
		player.setGameMode(GameMode.SPECTATOR);
		
		if(teleport){
			player.teleport(LocationsManager.getSavedLocation(Locations.SPAWN.getPath(), true));
		}
		
		if(removeFromQueue){
			QueuesManager.removeFromQueue(player);
		}
		
		player.setGameMode(GameMode.ADVENTURE);
		
		for(PotionEffect pe : player.getActivePotionEffects()){
			player.getActivePotionEffects().remove(pe);
		}
		
		pl.setState(PlayerStates.LOBBY);
		
		giveLobbyItems(player);
		
	}

}
