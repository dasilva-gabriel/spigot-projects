package eu.pvpwarcraft.thetowers.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import eu.pvpwarcraft.thetowers.TheTowers;
import eu.pvpwarcraft.thetowers.handler.Step;
import eu.pvpwarcraft.thetowers.handler.TowerPlayer;
import eu.pvpwarcraft.thetowers.scoreboard.ScoreboardManager;
import eu.pvpwarcraft.thetowers.utils.HeadBuilder;
import eu.pvpwarcraft.thetowers.utils.ItemBuilder;
import eu.pvpwarcraft.warcraftapi.configuration.User;
import eu.pvpwarcraft.warcraftapi.events.UserJoinEvent;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onPreJoin(PlayerLoginEvent event){
		Player player = event.getPlayer();
		if (TheTowers.getInstance().getPlayers().size() >= 32) {
			player.kickPlayer("La partie est pleine");
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		if (TheTowers.getInstance().getPlayers().size() >= 32) {
			event.getPlayer().kickPlayer("VOus ne pouvez pas rejoindre le serveur.");
		}
		if (!Step.isStep(Step.LOBBY)) {
			ScoreboardManager.setupScoreboard(event.getPlayer());
			TowerPlayer towerpl = TheTowers.getPlayer(event.getPlayer());
			towerpl.getPlayer().setGameMode(GameMode.SPECTATOR);
		}
	}

	@EventHandler
	public void onUserJoin(UserJoinEvent event) {
		
		User user = event.getUser();
		Player player = user.getPlayer();

		player.getInventory().clear();
		player.getInventory().setArmorContents(null);

		player.setGameMode(GameMode.SPECTATOR);

		player.setMaxHealth(20);
		player.setHealth(player.getMaxHealth());

		player.setFoodLevel(20);
		
		player.sendMessage("§8§m--+-------------------------------------+--");
		player.sendMessage(" §8» §7Vous rejoignez une partie de §6§lTheTowers§7.");
		player.sendMessage("  ");
		player.sendMessage(" §8» §7Vous avez §a"+user.getPoints()+" points§7.");
		player.sendMessage("    §8• §7Parties jouées: §d§l"+user.getStats().getTowersParties());
		player.sendMessage("    §8• §7Kills: §2§l"+user.getStats().getTowerspKills());
		player.sendMessage("    §8• §7Points d'équipes: §3§l"+user.getStats().getTowersPoints());
		player.sendMessage("§8§m--+-------------------------------------+--");

		if (Step.isStep(Step.LOBBY)) {

			player.setGameMode(GameMode.ADVENTURE);
			
			TowerPlayer towerpl = TheTowers.getPlayer(player);

			player.teleport(towerpl.getTeam().getSpawn());

			Bukkit.broadcastMessage("§6TheTowers §8» §b" + player.getName() + " §7à rejoint la partie. §8(§a"+ TheTowers.getInstance().getPlayers().size() + "§7/§a32§8)");

			player.getInventory().setItem(2, new ItemBuilder(Material.STAINED_CLAY).setDamage(1)
					.setName("§6§lEquipe Orange §8• §7Clique droit").build());
			player.getInventory().setItem(6, new ItemBuilder(Material.STAINED_CLAY).setDamage(3)
					.setName("§3§lEquipe Cyan §8• §7Clique droit").build());
			player.getInventory().setItem(4,
					new HeadBuilder("MHF_Question").setName("§f§lEquipe aléatoire §8• §7Clique droit").build());
		}else{
			TowerPlayer towerpl = TheTowers.getPlayer(player);
			player.teleport(towerpl.getTeam().getSpawn());
			
			player.sendMessage("§6TheTowers §8» §7Vous rejoignez la partie en tant que §fSpectateur§7.");
			player.sendMessage("§6TheTowers §8» §cLe chat Spectateur est désactivé pour cette partie.");
		}
	}

}
