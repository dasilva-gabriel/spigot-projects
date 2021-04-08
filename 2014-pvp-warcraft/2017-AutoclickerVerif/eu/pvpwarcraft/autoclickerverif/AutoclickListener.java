package eu.pvpwarcraft.autoclickerverif;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AutoclickListener implements Listener {
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_AIR) {
			Player player = e.getPlayer();
			PlayerWrapper wp = PlayerWrapper.getByPlayer(player);
			if ((wp.lastBlockInteraction > System.currentTimeMillis()) && (wp.clicks >= 10)) {
				e.setCancelled(true);
			}
			if (wp.clicks > wp.maxClicks) {
				wp.maxClicks = wp.clicks;
			}
			wp.clicks += 1;
		} else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			Player player = e.getPlayer();
			PlayerWrapper wp = PlayerWrapper.getByPlayer(player);
			wp.lastBlockInteraction = (System.currentTimeMillis() + 5000L);
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if ((e.getClickedInventory() != null) && (e.getClickedInventory().getTitle().startsWith("§cVerif >")))
			e.setCancelled(true);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		new PlayerWrapper(e.getPlayer());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		PlayerWrapper.removePlayer(e.getPlayer());
	}
}