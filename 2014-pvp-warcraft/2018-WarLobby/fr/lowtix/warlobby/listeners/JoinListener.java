package fr.lowtix.warlobby.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.lowtix.warlobby.utils.ItemBuilder;

public class JoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		event.setJoinMessage(null);
		
		Player player = event.getPlayer();
		player.getInventory().clear();
		
		player.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setName("§aMenu des serveurs §8(Clic-droit)").build());
		player.getInventory().setItem(1, new ItemBuilder(Material.GOLD_INGOT).setName("§aInformations §8(Clic-droit)").build());
		player.getInventory().setItem(8, new ItemBuilder(Material.REDSTONE_COMPARATOR).setName("§aChanger votre mot de passe §8(Clic-droit)").build());
		
		player.getInventory().setHeldItemSlot(0);
		
		player.setGameMode(GameMode.ADVENTURE);
		
		player.setMaxHealth(2);
		player.setHealth(2);
		player.setFoodLevel(20);
		
		player.teleport(player.getWorld().getSpawnLocation());
		
	}

}
