package fr.lowtix.warbox.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.enums.Groups;
import fr.lowtix.warbox.players.WarPlayer;
import fr.lowtix.warbox.utils.ItemBuilder;

public class KillListeners implements Listener {

	public void respawn(final Player player, int Time) {
		Bukkit.getScheduler().runTaskLater(WarBox.getInstance(), new Runnable() {
			public void run() {
				player.spigot().respawn();
			}
		}, Time);
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		WarPlayer wp = WarBox.getInstance().getUser(player);
		
		wp.getStats().setDeaths(wp.getStats().getDeaths() + 1);
		wp.setKillstreak(0);
		
		respawn(player, 6);
		
		event.getDrops().clear();
		event.setDroppedExp(0);
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		event.getPlayer().teleport(WarBox.getInstance().getSpawn());
		event.getPlayer().setMaxHealth(40);
		event.getPlayer().setHealth(40);
	}
	
	@EventHandler
	public void onKill(PlayerDeathEvent event) {
		Player victim = event.getEntity();
		event.setDeathMessage(null);
		if(victim.getKiller() != null) {
			Player killer = victim.getKiller();
			WarPlayer wp = WarBox.getInstance().getUser(killer);
			
			wp.getStats().setKills(wp.getStats().getKills() + 1);
			wp.setKillstreak(wp.getKillstreak() + 1);
			
			if(wp.getStats().getBest_killstreak() < wp.getKillstreak()) {
				wp.getStats().setBest_killstreak(wp.getKillstreak());
			}
			
			if(wp.getKillstreak() % 5 == 0) {
				Bukkit.broadcastMessage("§8[§6§l!§8] §b"+killer.getName()+" §eest désormais en série de §6"+wp.getKillstreak()+" kills §e!");
			}
			
			killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2));
			
			killer.getInventory().addItem(new ItemBuilder(Material.GOLDEN_APPLE).build());
			
			killer.sendMessage("§8[§3§lW§8] §7Vous avez tué §e"+victim.getName()+"§7:");
			victim.sendMessage("§8[§3§lW§8] §6Vous avez été tué par §b"+killer.getName()+"§6.");
			
			int coins = 1 + new Random().nextInt(3);
			int exp = 2;
			
			int seu = (int) (1 + (2.9 * WarBox.getInstance().getLevelManager().getExpForLevel(wp.getLevel()+1)));
			if(wp.getExp() <= seu) {
				if(wp.getGroup().isHigherOrEquals(Groups.VIP_PLUS)) {
					coins *= 2;
					exp *= 2;
					killer.sendMessage("  §8» §b+"+exp+" exp §7(§fBooster VIP+§7) §e+"+coins+" coins §7(§fBooster VIP+§7)");
				} else if(wp.getGroup().isHigherOrEquals(Groups.VIP)) {
					coins *= 1.5;
					killer.sendMessage("  §8» §b+"+exp+" exp §e+"+coins+" coins §7(§fBooster VIP§7)");
				} else {
					killer.sendMessage("  §8» §b+"+exp+" exp §e+"+coins+" coins");
				}
				
				wp.addCoins(coins);
				wp.addExp(exp);
				
			} else {
				killer.sendMessage("  §8» §cVous avez atteint le seuil d'expérience qui est de §e"+seu+" §cpour le niveau " + WarBox.getInstance().getLevelManager().getPrefix(wp.getLevel()));
				killer.sendMessage("  §8» §e+"+coins+" coins");
				wp.addCoins(coins);
			}
			
			if(wp.getKillstreak() >= 3) {
				int ksCoins = (int) (wp.getKillstreak()/1.9);
				int ksExp = (int) (wp.getKillstreak()/1.5);
				killer.sendMessage("  §8» §7Bonus série de Kills: §e"+ksCoins+" coins §7et §b"+ksExp+" exp");
				
				wp.addCoins(ksCoins);
				wp.addExp(ksExp);
			}
			
			
		} else {
			victim.sendMessage("§8[§3§lW§8] §6Vous êtes mort.");
		}
		
	}

}
