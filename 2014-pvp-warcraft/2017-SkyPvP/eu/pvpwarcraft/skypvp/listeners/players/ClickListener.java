package eu.pvpwarcraft.skypvp.listeners.players;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import eu.pvpwarcraft.skypvp.utils.guis.GuiManager;
import eu.pvpwarcraft.skypvp.utils.guis.MenuGui;

public class ClickListener implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		if(p.isOp() && p.getGameMode().equals(GameMode.CREATIVE)){
			return;
		}
		e.setCancelled(true);
	}
	
	@EventHandler
	public void Inventory(InventoryClickEvent e){
		if(e.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)){
			return;
		}
		if(e.getCurrentItem().getType() == Material.NETHER_STAR && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §3Menu SkyPvP")){
			e.setCancelled(true);
		}
		if(e.getWhoClicked().getOpenInventory().getTitle().contains("SkyPvP")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClick(PlayerDropItemEvent e){
		if(e.getItemDrop().getItemStack().getType() == Material.NETHER_STAR && e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §3Menu SkyPvP")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent e){
		if(e.getItem() != null && e.getItem().getType() == Material.NETHER_STAR){
			GuiManager.openGui(new MenuGui(e.getPlayer()));
		}
		if(e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.ARMOR_STAND){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClick(PlayerInteractEntityEvent e){
		if(e.getRightClicked().getType() == EntityType.ARMOR_STAND){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onClick(EntityDamageByEntityEvent e){
		if(e.getEntity().getType() == EntityType.ARMOR_STAND){
			e.setCancelled(true);
		}
	}

}
