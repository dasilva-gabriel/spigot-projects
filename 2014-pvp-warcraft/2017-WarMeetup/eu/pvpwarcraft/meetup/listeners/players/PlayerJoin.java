package eu.pvpwarcraft.meetup.listeners.players;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import eu.pvpwarcraft.meetup.Meetup;
import eu.pvpwarcraft.meetup.handler.Step;
import eu.pvpwarcraft.meetup.handler.servers.ServersManager;
import eu.pvpwarcraft.meetup.managers.PlayersInGameManager;
import eu.pvpwarcraft.meetup.managers.board.ScoreboardManager;
import eu.pvpwarcraft.meetup.schedulers.GameStart;
import eu.pvpwarcraft.meetup.utils.ItemBuilder;
import eu.pvpwarcraft.meetup.utils.PlayerUtils;
import eu.pvpwarcraft.warfightapi.events.UserJoinEvent;
import eu.pvpwarcraft.warfightapi.managers.User;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
	}

	@EventHandler
	public void onUserJoin(UserJoinEvent event) {
		User user = event.getUser();
		final Player player = user.getPlayer();
		player.sendMessage("§6WarFight §8» §aConnexion en cours...");
		player.sendMessage("§8§m---+---------------------------------------------+---");
		player.sendMessage("   §8» §7Vous avez rejoint le jeu §3UHCMeetup§7.");
		player.sendMessage("  ");
		player.sendMessage("   §8» §7Gemmes: §2§l" + user.getGemmes() + "§7.");
		player.sendMessage("   §8» §7Statistiques Meetup:");
		player.sendMessage("     §8• §7Parties jouées: §6§l" + user.getStats().getMeetUpParties() + "§7.");
		player.sendMessage("     §8• §7Kills §a§l" + user.getStats().getMeetUpKills() + "§7.");
		player.sendMessage("     §8• §7Morts §c§l" + user.getStats().getMeetUpDeaths() + "§7.");
		player.sendMessage("§8§m---+---------------------------------------------+---");
		ScoreboardManager.setupScoreboard(player);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		if (Step.canJoin()) {
			if(Meetup.getInstance().players.size() >= 20){
				ServersManager.sendToWarFightLobby(player);
			}
			player.getWorld().getChunkAt(player.getWorld().getSpawnLocation()).load(true);
			player.teleport(player.getWorld().getSpawnLocation());
			PlayersInGameManager.addPlayer(player);
			Bukkit.broadcastMessage("§6WarFight §8» §a" + player.getName() + " §7a rejoint la partie. §8(§e"
					+ PlayersInGameManager.getPlayersSize() + "§8/§e20§8)");
			player.setGameMode(GameMode.ADVENTURE);
			player.getInventory().setItem(0, new ItemBuilder(Material.BOOK).setName("§8• §bProfil UHCMeetup §8•").build());
			player.getInventory().setItem(4, new ItemBuilder(Material.ANVIL).setName("§8• §aTypes §8•").build());
			player.getInventory().setItem(8,
					new ItemBuilder(Material.BED).setName("§8• §cRetour au serveur Principal §8•").build());
			player.setFoodLevel(20);
			player.setMaxHealth(2);
			player.setHealth(player.getMaxHealth());
			player.teleport(player.getWorld().getSpawnLocation());
			PlayerUtils.sendTitle(player, "§8• §6WarFight §8•", "§eMeetup", 0, 8, 3);
			player.teleport(player.getWorld().getSpawnLocation());
			if (PlayersInGameManager.getPlayersSize() == 2) {
				GameStart start = new GameStart(Meetup.getInstance());
				start.runTaskTimer(Meetup.getInstance(), 20, 20);
			}

		} else {
			player.sendMessage("§6WarFight §8» §7Vous êtes désormais spectateur.");
			player.setGameMode(GameMode.SPECTATOR);
		}
	}

}
