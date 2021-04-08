package eu.pvpwarcraft.skypvp.listeners.hitman;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.skypvp.SkyPvP;
import eu.pvpwarcraft.skypvp.managers.configuration.ConfigManager;
import eu.pvpwarcraft.skypvp.managers.players.PlayersStats;
import eu.pvpwarcraft.skypvp.utils.PlayerManager;

public class HitmanListener {

	public static Map<String, HitmanStates> state = new HashMap<String, HitmanStates>();
	public static Map<String, String> cible = new HashMap<String, String>();
	public static Map<String, Integer> time = new HashMap<String, Integer>();

	public static void checkHitman(Player p, Player check) {
		if (state.get(p.getName()) == null || state.get(p.getName()) == HitmanStates.DISABLE) {
			return;
		}
		if (cible.get(p.getName()) == check.getName()) {
			p.sendMessage("§b§lSky§3§lPvP §8▶ §e+40§6✪ §8(§aMission réussie§8)");
			int coins = (int) PlayersStats.getCoins(p);
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", coins+40);
			
			ConfigManager.savePlayersConfig();
			state.put(p.getName(), HitmanStates.DISABLE);
			Bukkit.broadcastMessage(
					"§b§lSky§3§lPvP §8▶ §e" + p.getName() + " §7à réussi sa mission en tuant §c" + check.getName());
		}else{
			return;
		}
	}

	public static void startHitman(final Player p) {
		if (state.get(p.getName()) == null || state.get(p.getName()) == HitmanStates.DISABLE) {
			PlayerManager.sendTitle(p, "§c§lHITMAN", "§eOrdre de mission dans le chat...", 0, 5, 1);
			int randomNumer = new Random().nextInt(Bukkit.getOnlinePlayers().size());
			Player p1 = (Player) Bukkit.getServer().getOnlinePlayers().toArray()[randomNumer];
			if (p1 == p) {
				p.sendMessage("§8§m--+-----------------------------------+--");
				p.sendMessage("  ");
				p.sendMessage("§8§m--+-----------------------------------+--");
				return;
			}
			state.put(p.getName(), HitmanStates.ENABLE);
			p.playSound(p.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
			p.sendMessage("§8§m--+-----------------------------------+--");
			p.sendMessage("");
			p.sendMessage("    §8* §3§lORDRE DE MISSION §8*");
			p.sendMessage("");
			p.sendMessage(" §8» §7Votre mission:");
			p.sendMessage("   §8• §7Cible: §c" + p1.getName());
			p.sendMessage("   §8• §7Sa position: §6X: §e" + p1.getLocation().getBlockX() + " §6Y: §e"
					+ p1.getLocation().getBlockY() + " §6Z: §e" + p1.getLocation().getBlockZ());
			p.sendMessage("");
			p.sendMessage(" §8» §7Votre but:");
			p.sendMessage("   §8• §7Le §4§lTUER §7en moins de §e2 minutes§7.");
			p.sendMessage("");
			p.sendMessage("§8§m--+-----------------------------------+--");
			cible.put(p.getName(), p1.getName());
			time.put(p.getName(), 120);
			PlayerManager.sendTitle(p, "§7Votre cible", "§c" + p1.getName(), 1, 5, 3);
			new BukkitRunnable() {

				@Override
				public void run() {
					if (state.get(p.getName()) == null || state.get(p.getName()) == HitmanStates.DISABLE) {
						cancel();
						return;
					}
					PlayerManager.sendTitle(p, "", "§6" + time.get(p.getName()) + " seconde(s)", 1, 5, 3);
					if (time.get(p.getName()) == 0) {
						state.put(p.getName(), HitmanStates.DISABLE);
						p.playSound(p.getLocation(), Sound.WITHER_SHOOT, 1.0F, 1.0F);
						p.sendMessage("§8§m--+-----------------------------------+--");
						p.sendMessage("");
						p.sendMessage(" §8» §cVous avez échoué :c");
						p.sendMessage("");
						p.sendMessage("§8§m--+-----------------------------------+--");
					}
					PlayerManager.sendActionBar(p, "§7Il vous reste §3"+time.get(p.getName())+" secondes");
					time.put(p.getName(), Integer.valueOf(time.get(p.getName())-2));
				}

			}.runTaskTimer(SkyPvP.getInstance(), 40, 40);
		}else{
			return;
		}
	}

}
