package fr.lowtix.warcore.managers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.PlayerInventory;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;
import fr.lowtix.wartracker.utils.ItemBuilder;

public class StaffModManager implements Listener {
	
	public void track(WarPlayer wPlayer, Player tracked) {
		Player player = wPlayer.getPlayer();
		
		if(wPlayer.getModPlayer().isMod() && wPlayer.getModPlayer().getTrack().equals(tracked.getName())) {
			WarCore.getInstance().staffChatManager.sendModMessage(Ranks.TRIAL_MOD, "§d[Track] §e"+player.getName()+" §8» §7TrackMode sur §f"+tracked.getName()+" §8[§cOFF§8]");
			wPlayer.getModPlayer().removeTrack();
			return;
		}
		
		wPlayer.getModPlayer().setTrack(tracked.getName());
		WarCore.getInstance().staffChatManager.sendModMessage(Ranks.TRIAL_MOD, "§d[Track] §e"+player.getName()+" §8» §7TrackMode sur §f"+tracked.getName()+" §8[§aON§8]");
		
		if(!WarCore.getInstance().essentials.getVanishedPlayers().contains(player.getName())) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish "+player.getName()+" on");
		}
		
		if(!wPlayer.getModPlayer().isMod()) {
			activeMod(wPlayer);
		}
		
	}
	
	public void activeMod(WarPlayer wPlayer) {
		Player player = wPlayer.getPlayer();
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish "+player.getName()+" on");
		player.setGameMode(GameMode.ADVENTURE);
		wPlayer.getModPlayer().setMod(true);
		
		wPlayer.getModPlayer().armor = player.getInventory().getArmorContents();
		wPlayer.getModPlayer().contents = player.getInventory().getContents();
		wPlayer.clear();
		
		if(wPlayer.getModPlayer().isTrack() && wPlayer.getModPlayer().trackIsOnline()) {
			wPlayer.getPlayer().teleport(wPlayer.getModPlayer().getTrackedPlayer().getLocation());
		}
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish "+player.getName()+" on");
		
		updateInvMod(wPlayer);
		wPlayer.board.updateScore(true);
	}
	
	public void desactiveMod(WarPlayer wPlayer) {
		Player player = wPlayer.getPlayer();
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish "+player.getName()+" off");
		player.setGameMode(GameMode.SURVIVAL);
		wPlayer.getModPlayer().setMod(false);
		
		wPlayer.getModPlayer().removeTrack();
		
		wPlayer.clear();
		
		player.setFlying(false);
		player.setAllowFlight(false);
		
		if(wPlayer.getModPlayer().armor != null) player.getInventory().setArmorContents(wPlayer.getModPlayer().armor);
		if(wPlayer.getModPlayer().contents != null) player.getInventory().setContents(wPlayer.getModPlayer().contents);
		
		player.updateInventory();
		
		wPlayer.board.updateScore(true);
	}
	
	public void updateInvMod(WarPlayer wPlayer) {
		Player player = wPlayer.getPlayer();
		
		player.setGameMode(GameMode.ADVENTURE);
		
		player.updateInventory();
		PlayerInventory inv = player.getInventory();
		
		player.setAllowFlight(true);
		player.setFlying(true);
		
		if(wPlayer.getModPlayer().followTrack) {
			inv.setItem(0, new ItemBuilder(Material.INK_SACK).setDamage(10).setName("§7Follow track: §a§lON").build());
		} else {
			inv.setItem(0, new ItemBuilder(Material.INK_SACK).setDamage(8).setName("§7Follow track: §c§lOFF").build());
		}
		
		inv.setItem(1, new ItemBuilder(Material.EYE_OF_ENDER).setName("§7Téléportation a votre Track").build());
		
		inv.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f ").build());
		
		inv.setItem(3, new ItemBuilder(Material.STICK).setName("§7KB Test").addEnchant(Enchantment.KNOCKBACK, 3).build());
		
		inv.setItem(5, new ItemBuilder(Material.STONE_SLAB2).setName("§7Téléportation en haut").build());
		
		inv.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f ").build());
		
		inv.setItem(7, new ItemBuilder(Material.INK_SACK).setDamage(8).setName("§7Vanish: §c§lOFF").build());
		
		if(WarCore.getInstance().essentials.getVanishedPlayers().contains(player.getName())) {
			inv.setItem(7, new ItemBuilder(Material.INK_SACK).setDamage(10).setName("§7Vanish: §a§lON").build());
		}
		
		inv.setItem(8, new ItemBuilder(Material.BARRIER).setName("§cQuitter").build());
		
		player.updateInventory();
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onChest(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		
		if(event.getItem() == null) return;
		if(event.getAction() == null) return;
		if(event.getClickedBlock() == null) return;
		
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			WarPlayer wPlayer = WarCore.getInstance().getUser(player);
			
			if(event.getClickedBlock().getType().equals(Material.CHEST) && wPlayer.getModPlayer().isMod()) {
				Chest chest = (Chest) event.getClickedBlock();
				
				event.setCancelled(true);
				
				player.openInventory(chest.getBlockInventory());
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		
		if(event.getItem() == null) return;
		if(event.getAction() == null) return;
		
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			WarPlayer wPlayer = WarCore.getInstance().getUser(player);
			
			if(wPlayer.getModPlayer().isMod()) {
				
				event.setCancelled(true);
				player.updateInventory();
				
				int position = player.getInventory().getHeldItemSlot();
				
				player.getInventory().setHeldItemSlot(2);
				
				if(position == 8) {
					
					desactiveMod(wPlayer);
					
				} else if(position == 7) {
					if(WarCore.getInstance().essentials.getVanishedPlayers().contains(player.getName())) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish "+player.getName()+" off");
						updateInvMod(wPlayer);
						wPlayer.board.updateScore(true);
					} else {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish "+player.getName()+" on");
						updateInvMod(wPlayer);
						wPlayer.board.updateScore(true);
					}
				} else if(position == 5) {
					player.teleport(player.getLocation().getWorld().getHighestBlockAt(player.getLocation()).getLocation());
					player.sendMessage("§6Modération §8» §7Téléportation au point le plus haut.");
				} else if(position == 1) {
					
					if(!wPlayer.getModPlayer().isTrack()) {
						player.sendMessage("§6Modération §8» §7Vous n'êtes en train de Track personne.");
						player.getInventory().setHeldItemSlot(position);
						return;
					}
					
					if(!wPlayer.getModPlayer().trackIsOnline()) {
						player.sendMessage("§6Modération §8» §7Votre Track est actuellement hors-ligne.");
						player.getInventory().setHeldItemSlot(position);
						return;
					}
					
					if(!WarCore.getInstance().essentials.getVanishedPlayers().contains(player.getName())) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish "+player.getName()+" on");
					}
					player.teleport(wPlayer.getModPlayer().getTrackedPlayer().getLocation());
					
					player.sendMessage("§6Modération §8» §7Téléportation a votre track §c"+wPlayer.getModPlayer().getTrack()+"§7.");
				} else if(position == 0) {
					if(wPlayer.getModPlayer().followTrack) {
						player.sendMessage("§6Modération §8» §7Votre follow de track a été §cdésactivé§7.");
						wPlayer.getModPlayer().followTrack = false;
						updateInvMod(wPlayer);
					} else {
						player.sendMessage("§6Modération §8» §7Votre follow de track a été §aactivé§7.");
						wPlayer.getModPlayer().followTrack = true;
						updateInvMod(wPlayer);
					}
				}
				player.getInventory().setHeldItemSlot(position);
			}
			
		}
		
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		WarPlayer wPlayer = WarCore.getInstance().getUser(player);
		
		if(wPlayer.getModPlayer().isMod()) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		WarPlayer wPlayer = WarCore.getInstance().getUser(player);
		
		if(wPlayer.getModPlayer().isMod()) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		
		if(!(event.getEntity() instanceof Player)) {
			return;
		}
		
		Player player = (Player) event.getEntity();
		WarPlayer wPlayer = WarCore.getInstance().getUser(player);
		
		if(wPlayer.getModPlayer().isMod()) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onDamage(EntityDamageByEntityEvent event) {
		
		if(!(event.getDamager() instanceof Player)) {
			return;
		}
		
		if(!(event.getEntity() instanceof Player)) {
			return;
		}
		
		Player player = (Player) event.getDamager();
		WarPlayer wPlayer = WarCore.getInstance().getUser(player);
		
		if(wPlayer.getModPlayer().isMod()) {
			event.setCancelled(true);
			
			int position = player.getInventory().getHeldItemSlot();
			
			if(position == 3) {
				
				Player target = (Player) event.getEntity();
				
				target.setVelocity(target.getLocation().getDirection().multiply(2).setY(0.5));
				
			}
			
		}
	}
	
	@EventHandler
	public void onInv(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		WarPlayer wPlayer = WarCore.getInstance().getUser(player);
		
		if(wPlayer.getModPlayer().isMod()) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		WarPlayer wPlayer = WarCore.getInstance().getUser(player);
		
		if(wPlayer.getModPlayer().isMod()) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		WarPlayer wPlayer = WarCore.getInstance().getUser(player);
		
		if(wPlayer.getModPlayer().isMod()) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent event) {
		Player player = (Player) event.getEntity();
		WarPlayer wPlayer = WarCore.getInstance().getUser(player);
		
		if(wPlayer.getModPlayer().isMod()) {
			event.setCancelled(true);
		}
	}

}
