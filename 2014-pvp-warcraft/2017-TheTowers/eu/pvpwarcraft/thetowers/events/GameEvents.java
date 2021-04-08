package eu.pvpwarcraft.thetowers.events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.mojang.authlib.yggdrasil.response.User;

import eu.pvpwarcraft.thetowers.TheTowers;
import eu.pvpwarcraft.thetowers.handler.Step;
import eu.pvpwarcraft.thetowers.handler.Team;
import eu.pvpwarcraft.thetowers.handler.TowerPlayer;
import eu.pvpwarcraft.thetowers.managers.PlayerManager;
import eu.pvpwarcraft.thetowers.utils.PlayerUtils;

public class GameEvents implements Listener {

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		Player p = event.getPlayer();
		TowerPlayer player = TheTowers.getPlayer(p);
		p.teleport(player.getTeam().getSpawn());
		PlayerManager.respawnPlayer(TheTowers.getPlayer(event.getPlayer()));
		
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player victim = event.getEntity();
		TowerPlayer twvic = TheTowers.getPlayer(victim);
		
		event.setDeathMessage(null);
		twvic.addDeaths(1);
		if (victim.getKiller() != null) {
			if (victim.getKiller() instanceof Player) {
				Player attacker = victim.getKiller();
				TowerPlayer twatt = TheTowers.getPlayer(attacker);
				
				twatt.addEarned_points(0.1);
				
				User uatt = WarCraftAPI.getInstance().getUsers().get(attacker.getName());
				
				twatt.addKills(1);
				uatt.getStats().setTowerspKills(Integer.valueOf((uatt.getStats().getTowerspKills())+1));
				new ArrayList<>(event.getDrops()).forEach((ItemStack item) -> {
					if (item.getType().name().contains("LEATHER_") || item.getType() == Material.BAKED_POTATO
							|| item.getType() == Material.WOOD_SWORD || item.getType() == Material.WOOD_PICKAXE) {
						event.getDrops().remove(item);
					}
				});
				event.setDeathMessage("§6TheTowers §8» §f"+twvic.getTeam().getColor() + twvic.getTeam().getName() + " " + victim.getName() + " §7a succombé(e) sous les coups de §f"+twatt.getTeam().getColor() + twatt.getTeam().getName() + " " + attacker.getName());
			}
		}else{
			event.setDeathMessage("§6TheTowers §8» §f"+twvic.getTeam().getColor() + twvic.getTeam().getName() + " " + victim.getName() + " §7est mort...");
		}
		new BukkitRunnable() {
			
			@Override
			public void run() {
				victim.spigot().respawn();
				victim.teleport(twvic.getTeam().getSpawn());
				PlayerManager.respawnPlayer(TheTowers.getPlayer(victim));
				
			}
		}.runTaskLater(TheTowers.getInstance(), 5);
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event){
		if(Step.isStep(Step.IN_GAME)){
			if(event.getEntity() instanceof Player){
				Player victim = (Player) event.getEntity();
				TowerPlayer twvic = TheTowers.getPlayer(victim);
				if(event.getCause() == DamageCause.PROJECTILE) {
				    Arrow a = (Arrow) event.getDamager();
				    if(a.getShooter() instanceof Player) {
				        Player attacker = (Player) a.getShooter();
						TowerPlayer twatt = TheTowers.getPlayer(attacker);
						if(twatt.getTeam().equals(twvic.getTeam())){
							event.setCancelled(true);
						}
				    }
				}
				if(event.getDamager() instanceof Player){
					Player attacker = (Player) event.getDamager();
					TowerPlayer twatt = TheTowers.getPlayer(attacker);
					
					if(twatt.getTeam().equals(twvic.getTeam())){
						event.setCancelled(true);
						attacker.sendMessage("§6TheTowers §8» §cVous ne pouvez pas frapper vos alliés.");
					}
				}
			}
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		if (Step.isStep(Step.IN_GAME)) {
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().toString().equals("SPONGE")) {

				TowerPlayer twpl = TheTowers.getPlayer(player);
				User user = WarCraftAPI.getInstance().getUsers().get(twpl.getName());

				for (Team.Teams team : Team.Teams.values()) {
					if (team.getSpawn().distance(player.getLocation()) < 15) {
						if (TheTowers.getTeam(twpl.getTeam()) != TheTowers.getTeam(team)) {
							twpl.getPlayer().teleport(twpl.getTeam().getSpawn());
							TheTowers.getTeam(twpl.getTeam()).addPoints(1);
							for (Player all : TheTowers.getTeam(team).getPlayers()) {
								PlayerUtils.playSound(all, Sound.ENTITY_VILLAGER_HURT);
								PlayerUtils.sendTitle(all, twpl.getTeam().getColor() + twpl.getTeam().getName(),
										"§7+1 points", 0, 3, 2);
							}
							for (Player all : TheTowers.getTeam(twpl.getTeam()).getPlayers()) {
								PlayerUtils.playSound(all, Sound.ENTITY_PLAYER_LEVELUP);
								PlayerUtils.sendTitle(all, twpl.getTeam().getColor() + twpl.getTeam().getName(),
										"§7+1 point", 0, 3, 2);
							}
							Bukkit.broadcastMessage("§6TheTowers §8» §f" + twpl.getTeam().getColor() + player.getName()
									+ " §7à fais gagné un point à son équipe.");
							twpl.addPoints(1);
							user.getStats().setTowersPoints(Integer.valueOf((user.getStats().getTowersPoints())+1));
							twpl.addEarned_points(2);
						}

					}
				}

			}

		}
	}

}
