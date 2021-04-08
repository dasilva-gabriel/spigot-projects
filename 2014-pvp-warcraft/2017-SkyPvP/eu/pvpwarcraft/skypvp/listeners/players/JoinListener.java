package eu.pvpwarcraft.skypvp.listeners.players;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.skypvp.SkyPvP;
import eu.pvpwarcraft.skypvp.managers.EnumLocations;
import eu.pvpwarcraft.skypvp.managers.Locations;
import eu.pvpwarcraft.skypvp.managers.configuration.ConfigManager;
import eu.pvpwarcraft.skypvp.managers.scoreboard.ScoreboardManager;
import eu.pvpwarcraft.skypvp.utils.ItemBuilder;
import eu.pvpwarcraft.skypvp.utils.PlayerManager;
import eu.pvpwarcraft.skypvp.utils.guis.GuiManager;
import eu.pvpwarcraft.skypvp.utils.guis.MenuGui;

public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		final Player p = e.getPlayer();
		if(p.isDead()){
			p.spigot().respawn();
		}
		if (p.hasPermission("group.default")) {
			p.setPlayerListName("§7 " + p.getName());
		}
		if (p.hasPermission("group.skyknight")) {
			p.setPlayerListName("§3⚔ §b" + p.getName());
		}
		if (p.hasPermission("group.skyking")) {
			p.setPlayerListName("§b✩ §3" + p.getName());
		}
		if (p.hasPermission("group.mod")) {
			p.setPlayerListName("§8» §2" + p.getName());
		}
		if (p.hasPermission("group.admin")) {
			p.setPlayerListName("§8» §c" + p.getName());
		}
		if (p.hasPermission("group.fonda")) {
			p.setPlayerListName("§8» §4" + p.getName());
		}

		p.setMaxHealth(20);
		p.setHealth(20);
		p.getInventory().setItem(8,
				new ItemBuilder(Material.NETHER_STAR).setName("§8» §3Menu SkyPvP")
						.addEnchant(Enchantment.DURABILITY, 999).addFlag(ItemFlag.HIDE_ENCHANTS)
						.addFlag(ItemFlag.HIDE_DESTROYS).build());
		ScoreboardManager.setupScoreboard(p);
		PlayerManager.setupTabulation(p,
				"§6§l§m----------§r §e» §6SkyPvP §e« §6§l§m----------§r\n \n§7Bienvenue §7sur le §b§lSky§3§lPvP§7.\n",
				"\n§3TS §8» §ets.pvp-warcraft.eu\n§3Site §8» §ewww.pvp-warcraft.eu\n§3Shop §8» §ewww.pvp-warcraft.eu/store\n\n§amc.pvp-warcraft.eu\n \n§6§l§m----------§r §e» §6SkyPvP §e« §6§l§m----------");
		p.teleport(Locations.getLocation(EnumLocations.SPAWN));
		p.setGameMode(GameMode.ADVENTURE);
		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Kills")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Kills", 0);
		}

		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Deaths")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Deaths", 0);
		}
		
		if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Coins")) {
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", 0);
		}
		

		new BukkitRunnable() {

			@Override
			public void run() {
				p.sendMessage("\n \n \n \n \n \n \n \n \n§b§lSky§3§lPvP §8▶ §7Vous rejoignez le serveur §eSkyPvP§7.");
				p.sendMessage("§8§m--+-----------------------------------+--");
				p.sendMessage("");
				p.sendMessage(" §8» §7Bienvenue sur le §b§lSky§3§lPvP§7.");
				p.sendMessage("");
				p.sendMessage(" §8» §7L'§eétoile du Nether §7vous permet d'accéder au Menu.");
				p.sendMessage(" §8» §7Toutes les informations sont inscrites au Spawn.");
				p.sendMessage("");
				p.sendMessage(" §8» §d§lINFO §7Bêta terminée");
				p.sendMessage(" §8» §9§lNEW §7Des clefs de Lottery sont en vente.");
				p.sendMessage("");
				p.sendMessage("§8§m--+-----------------------------------+--");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 10.0F, 1.0F);
				GuiManager.openGui(new MenuGui(p));
			}
		}.runTaskLater(SkyPvP.getInstance(), 10);
	}

}
