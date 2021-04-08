package eu.pvpwarcraft.skypvp.managers.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import eu.pvpwarcraft.skypvp.managers.players.PlayersStats;
import eu.pvpwarcraft.skypvp.utils.ItemBuilder;

public class ScoreboardScheduler extends BukkitRunnable {

	@Override
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!p.getInventory().contains(Material.NETHER_STAR)) {
				p.getInventory().setItem(8,
						new ItemBuilder(Material.NETHER_STAR).setName("§8» §3Menu SkyPvP")
								.addEnchant(Enchantment.DURABILITY, 999).addFlag(ItemFlag.HIDE_ENCHANTS)
								.addFlag(ItemFlag.HIDE_DESTROYS).build());
			}
			Scoreboard board = p.getScoreboard();

			if (board == null) {
				board = ScoreboardManager.setupScoreboard(p);
			}

			int kills = PlayersStats.getKills(p);
			int deaths = PlayersStats.getDeaths(p);
			int coins = PlayersStats.getCoins(p);

			if (board.getTeam("coins").getSuffix() != "§e" + coins +" §6✪") {
				if (coins > 999) {
					board.getTeam("coins").setSuffix("§e+999 §6✪");
				} else {
					board.getTeam("coins").setSuffix("§e" + coins + " §6✪");
				}
			}

			if (board.getTeam("kills").getSuffix() != "§a" + kills) {
				if (kills > 999) {
					board.getTeam("kills").setSuffix("§a+999");
				} else {
					board.getTeam("kills").setSuffix("§a" + kills);
				}
			}
			if (board.getTeam("deaths").getSuffix() != "§c" + deaths) {
				if (deaths > 999) {
					board.getTeam("deaths").setSuffix("§c+999");
				} else {
					board.getTeam("deaths").setSuffix("§c" + deaths);
				}
			}

		}

	}

}
