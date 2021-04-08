package eu.pvpwarcraft.skypvp.listeners.kill;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.skypvp.SkyPvP;
import eu.pvpwarcraft.skypvp.listeners.hitman.HitmanListener;
import eu.pvpwarcraft.skypvp.managers.EnumLocations;
import eu.pvpwarcraft.skypvp.managers.Locations;
import eu.pvpwarcraft.skypvp.managers.configuration.ConfigManager;
import eu.pvpwarcraft.skypvp.managers.configuration.KillCount;
import eu.pvpwarcraft.skypvp.managers.players.PlayersStats;
import eu.pvpwarcraft.skypvp.utils.ItemBuilder;
import eu.pvpwarcraft.skypvp.utils.PlayerManager;

public class KillListener implements Listener {

	private Map<String, String> lastdamage = new HashMap<String, String>();

	private void coinsexecute(Player attacker, String reason, int chance) {
		Random rand = new Random();
		int randomNumber = rand.nextInt(chance) + 1;
		attacker.sendMessage("§b§lSky§3§lPvP §8▶ §e+" + randomNumber + "§6✪ §8(§a" + reason + "§8)");
		int coins = (int) PlayersStats.getCoins(attacker);
		ConfigManager.getPlayersConfig().set("Players. " + attacker.getName() + " .Coins", coins+randomNumber);
		
		ConfigManager.savePlayersConfig();
		spawnASCoins(attacker.getLocation(), randomNumber);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player victim = e.getPlayer();
		
		victim.setNoDamageTicks(30);
		victim.setHealth(victim.getHealth());
		if (victim.getLocation().distance(Locations.getLocation(EnumLocations.SPAWN)) > 20) {
			victim.getInventory().clear();
			victim.getInventory().setArmorContents(null);
			Player attacker = Bukkit.getPlayer(lastdamage.get(victim.getName()));
			coinsexecute(attacker, "Déconnection", 1);
			if(attacker.hasPermission("skypvp.booster")){
				Random rand2 = new Random();
				int randomNumber2 = rand2.nextInt(2);
				if(randomNumber2 == 1){
					coinsexecute(attacker, "Booster", 1);
				}
			}
			attacker.playSound(attacker.getLocation(), Sound.LEVEL_UP, 10.0F, 10.0F);
			attacker.sendMessage("§b§lSky§3§lPvP §8▶ §aVous avez tué §e" + victim.getName());
			victim.sendMessage("§b§lSky§3§lPvP §8▶ §cVous avez été tué par §4" + attacker.getName());
			PlayerManager.sendTitle(victim, " ", "§cVous êtes mort", 0, 10, 5);
			KillCount.addKill(attacker);
			KillCount.addDeath(victim);
			HitmanListener.checkHitman(attacker, victim);
			victim.teleport(Locations.getLocation(EnumLocations.SPAWN));
			victim.setGameMode(GameMode.ADVENTURE);
			for(Player all : Bukkit.getOnlinePlayers() ){
				PlayerManager.sendActionBar(all, "§3"+victim.getName()+" §7a tué(e) §3"+attacker.getName());
			}
			if(attacker.getHealth() < 5){
				attacker.setHealth(5);
			}
		}
		victim.teleport(Locations.getLocation(EnumLocations.SPAWN).add(0, 1, 0));
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player victim = e.getPlayer();
		if (victim.getLocation().getBlockY() < 50 && !victim.getGameMode().equals(GameMode.CREATIVE)) {
			victim.setNoDamageTicks(30);
			victim.teleport(Locations.getLocation(EnumLocations.SPAWN).add(0, 1, 0));
			victim.setHealth(victim.getHealth());
			if (lastdamage.get(victim.getName()) != null) {
				Player attacker = Bukkit.getPlayer(lastdamage.get(victim.getName()));
				coinsexecute(attacker, "Ejecté", 1);
				if(attacker.hasPermission("skypvp.booster")){
					Random rand2 = new Random();
					int randomNumber2 = rand2.nextInt(2);
					if(randomNumber2 == 1){
						coinsexecute(attacker, "Booster", 1);
					}
				}
				victim.getInventory().clear();
				victim.getInventory().setArmorContents(null);
				attacker.playSound(attacker.getLocation(), Sound.LEVEL_UP, 10.0F, 10.0F);
				attacker.sendMessage("§b§lSky§3§lPvP §8▶ §aVous avez tué §e" + victim.getName());
				victim.sendMessage("§b§lSky§3§lPvP §8▶ §cVous avez été tué par §4" + attacker.getName());
				PlayerManager.sendTitle(victim, " ", "§cVous êtes mort", 0, 10, 5);
				KillCount.addKill(attacker);
				KillCount.addDeath(victim);
				HitmanListener.checkHitman(attacker, victim);
				victim.teleport(Locations.getLocation(EnumLocations.SPAWN));
				victim.setGameMode(GameMode.ADVENTURE);
				victim.setHealth(victim.getMaxHealth());
				victim.getInventory().clear();
				victim.getInventory().setArmorContents(null);
				for(Player all : Bukkit.getOnlinePlayers() ){
					PlayerManager.sendActionBar(all, "§3"+victim.getName()+" §7a tué(e) §3"+attacker.getName());
				}
				if(attacker.getHealth() < 5){
					attacker.setHealth(5);
				}
			} else {
				victim.getInventory().clear();
				victim.getInventory().setArmorContents(null);
				KillCount.addDeath(victim);
				victim.teleport(Locations.getLocation(EnumLocations.SPAWN).add(0, 1, 0));
				victim.setGameMode(GameMode.ADVENTURE);
				victim.setHealth(victim.getHealth());
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onHit(EntityDamageByEntityEvent e){
		if(!(e.getEntity() instanceof Player)){
			return;
		}
		if(!(e.getDamager() instanceof Player)){
			return;
		}
		Player victim = (Player) e.getEntity();
		Player attacker = (Player) e.getDamager();
		if(e.isCancelled()){
			lastdamage.put(victim.getName(), null);
		}
		lastdamage.put(victim.getName(), attacker.getName());
	}
	
	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		e.setDeathMessage(null);
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (!(e.getEntity().getKiller() instanceof Player)) {
			return;
		}
		Player attacker = (Player) e.getEntity().getKiller();
		final Player victim = (Player) e.getEntity();
		victim.getInventory().setItem(8, new ItemBuilder(Material.AIR).build());

		if (victim.isDead()) {
			victim.teleport(Locations.getLocation(EnumLocations.SPAWN));
			for (ItemStack all : e.getDrops()) {
				if (all.getType() == Material.NETHER_STAR) {
					all.setType(Material.AIR);
				}
			}
			attacker.playSound(attacker.getLocation(), Sound.LEVEL_UP, 10.0F, 10.0F);
			if (attacker.getHealth() < 10) {
				attacker.setHealth(10);
			}
			attacker.sendMessage("§b§lSky§3§lPvP §8▶ §aVous avez tué §e" + victim.getName());
			victim.sendMessage("§b§lSky§3§lPvP §8▶ §cVous avez été tué par §4" + attacker.getName());
			spawnAS(victim.getLocation());
			victim.spigot().respawn();
			victim.setGameMode(GameMode.SPECTATOR);
			victim.teleport(attacker);
			victim.getInventory().clear();
			victim.getInventory().setArmorContents(null);
			PlayerManager.sendTitle(victim, " ", "§cVous êtes mort", 0, 10, 5);
			KillCount.addKill(attacker);
			KillCount.addDeath(victim);
			HitmanListener.checkHitman(attacker, victim);
			lastdamage.put(victim.getName(), null);
			victim.setHealth(victim.getMaxHealth());
			if(attacker.getHealth() < 5){
				attacker.setHealth(5);
			}
			for(Player all : Bukkit.getOnlinePlayers() ){
				PlayerManager.sendActionBar(all, "§8▶ §b"+victim.getName()+" §c⚔ §3"+attacker.getName()+"§7.");
			}
			coinsexecute(attacker, "Corps à Corps", 1);
			if(attacker.hasPermission("skypvp.booster")){
				Random rand2 = new Random();
				int randomNumber2 = rand2.nextInt(2);
				if(randomNumber2 == 1){
					coinsexecute(attacker, "Booster", 1);
				}
			}
			new BukkitRunnable() {

				@Override
				public void run() {
					victim.teleport(Locations.getLocation(EnumLocations.SPAWN));
					PlayerManager.sendTitle(victim, " ", " ", 0, 1, 0);
					victim.setGameMode(GameMode.ADVENTURE);
				}
			}.runTaskLater(SkyPvP.getInstance(), 60);
		}
	}
	
	private void spawnASCoins(final Location loc, int coins) {
		final ArmorStand as2 = (ArmorStand) loc.getWorld().spawnEntity(loc.add(1, 1.5, 0), EntityType.ARMOR_STAND);
		as2.setSmall(true);
		as2.setBasePlate(false);
		as2.setGravity(false);
		as2.setVisible(false);
		as2.setCustomName("§a+"+coins+" coins");
		as2.setCustomNameVisible(true);
		new BukkitRunnable() {

			@Override
			public void run() {
				as2.remove();

			}
		}.runTaskLater(SkyPvP.getInstance(), 30);
	}

	private void spawnAS(final Location loc) {
		final ArmorStand as2 = (ArmorStand) loc.getWorld().spawnEntity(loc.add(1, 0.5, 0), EntityType.ARMOR_STAND);
		as2.setSmall(true);
		as2.setBasePlate(false);
		as2.setGravity(false);
		as2.setVisible(false);
		as2.setCustomName("§e+1 kill");
		as2.setCustomNameVisible(true);
		new BukkitRunnable() {

			@Override
			public void run() {
				as2.remove();

			}
		}.runTaskLater(SkyPvP.getInstance(), 30);
	}

}
