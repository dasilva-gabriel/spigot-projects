package eu.pvpwarcraft.warfight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import eu.pvpwarcraft.warfight.commands.Commands;
import eu.pvpwarcraft.warfight.listeners.Listeners;
import eu.pvpwarcraft.warfight.managers.Configuration;
import eu.pvpwarcraft.warfight.managers.Locations;
import eu.pvpwarcraft.warfight.managers.LocationsManager;
import eu.pvpwarcraft.warfight.managers.arenas.ArenasManager;
import eu.pvpwarcraft.warfight.managers.lobby.LobbyManager;
import eu.pvpwarcraft.warfight.managers.queues.QueuesManager;

public class WarFight extends JavaPlugin {

	public static List<String> errors = new ArrayList<String>();
	private static WarFight instance;
	public static boolean isReady;
	public static HashMap<String, PlayerWrapper> players = new HashMap<>();

	public static WarFight getInstance() {
		return instance;
	}

	// BUILDUHC, ARCHER, NODEBUFF, DEBUFF, COMBO, GAPPLE, SOUP, DIAMOND

	@Override
	public void onEnable() {

		isReady = false;

		instance = this;

		Bukkit.broadcastMessage("§8[§6WarFightLog§8] §7Démarrage du plugin en cours...");

		Listeners.init(instance, instance.getServer().getPluginManager());

		Commands.init();

		Configuration.init();

		try {
			QueuesManager.init();
			Bukkit.broadcastMessage("§8[§6WarFightLog§8] §aFiles d'attentes chargées.");
		} catch (Exception e) {
			e.printStackTrace();
			isReady = false;
			Bukkit.broadcastMessage("§8[§6WarFightLog§8] §cErreur dans l'initialisation des files d'attentes !");
		}

		try {
			ArenasManager.initArenas();
			Bukkit.broadcastMessage("§8[§6WarFightLog§8] §aArènes chargées.");
		} catch (Exception e) {
			e.printStackTrace();
			isReady = false;
			Bukkit.broadcastMessage("§8[§6WarFightLog§8] §cErreur dans l'initialisation des arènes !");
		}

		for (Locations locs : Locations.values()) {
			checkLocation(locs);
		}

		if (!errors.isEmpty() || errors.size() > 0) {
			isReady = false;
		} else {
			isReady = true;
		}

		for (World all : Bukkit.getWorlds()) {
			Bukkit.getServer().getWorld(all.getName()).setAutoSave(false);
		}

		if (!isReady) {
			Bukkit.broadcastMessage("§8[§6WarFightLog§8] §7Résultat: §cPlugin non-opérationnel");
		} else {
			Bukkit.broadcastMessage("§8[§6WarFightLog§8] §7Résultat: §2Plugin opérationnel");
		}
		
		new ServerTask().runTaskTimer(getInstance(), 0L, 2L);
		
		for(Player all : Bukkit.getOnlinePlayers()){
			LobbyManager.goToLobby(all, true, false);
		}

	}

	@Override
	public void onDisable() {

		Configuration.saveLocationsConfig();

		for (World all : Bukkit.getWorlds()) {
			Bukkit.getServer().getWorld(all.getName()).setAutoSave(false);
		}
	}

	private static void checkLocation(Locations loc) {
		if (!LocationsManager.hasLocation(loc.getPath())) {
			errors.add("§8[§6WarFightLog§8] §7La position §a" + loc.getName() + " §7est manquante.");
		}
	}

	public static HashMap<String, PlayerWrapper> getPlayers() {
		return players;
	}

	public static PlayerWrapper getPlayer(Player player) {
		if (players.containsKey(player.getName())) {
			return (PlayerWrapper) players.get(player.getName());
		}
		return new PlayerWrapper(player);
	}

	public static void addPlayer(PlayerWrapper player) {
		players.put(player.getPlayer().getName(), player);
	}

}
