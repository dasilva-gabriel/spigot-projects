package eu.pvpwarcraft.dayz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.spawn.EssentialsSpawn;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import eu.pvpwarcraft.dayz.commands.Commands;
import eu.pvpwarcraft.dayz.configuration.ConfigManager;
import eu.pvpwarcraft.dayz.configuration.LocationsList;
import eu.pvpwarcraft.dayz.configuration.LocationsManager;
import eu.pvpwarcraft.dayz.events.UserFirstJoinEvent;
import eu.pvpwarcraft.dayz.events.UserJoinEvent;
import eu.pvpwarcraft.dayz.listeners.Listeners;
import eu.pvpwarcraft.dayz.sql.DayzSQL;
import eu.pvpwarcraft.dayz.users.DayzPlayer;

public class DayZ extends JavaPlugin {

	private Economy eco;
	public String ligne = "§8§m--+------------------------------------------------+--";
	public String prefix = "§a§o§lDay§2§o§lZ§r §8» ";
	public WorldGuardPlugin worldguard;
	public EssentialsSpawn essentialsSpawn;
	public Essentials essentials;

	public List<String> errors = new ArrayList<String>();
	private static Map<String, DayzPlayer> players = new HashMap<String, DayzPlayer>();

	private static DayZ instance;

	public static DayZ getInstance() {
		return instance;
	}

	@Override
	public void onDisable() {
		for(Player all : Bukkit.getOnlinePlayers()){
			DayzSQL.registerPlayer(DayZ.getPlayer(all));
		}
	}
	
	@Override
	public void onEnable() {
		getLogger().info("Starting...");

		instance = this;

		try {

			ConfigManager.init();

			Listeners.init(this);

			Commands.init();

			ServerTask start = new ServerTask();
			start.runTaskTimer(this, 10, 10);

			worldguard = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
			essentialsSpawn = (EssentialsSpawn) Bukkit.getServer().getPluginManager().getPlugin("EssentialsSpawn");
			essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");

			DayzSQL.createTableIfNotExist();
			checkErrors();

		} catch (Exception e) {
			e.printStackTrace();
			getLogger().warning("Error in enabling");
		}

	}
	
	public void checkErrors(){
		errors.clear();
		
		if (!LocationsManager.hasLocation(LocationsList.SPAWN.getName())) {
			errors.add(prefix + "§cErreur de configuration: Veuillez mettre la position du Spawn");
		}

		if (!LocationsManager.hasLocation(LocationsList.FIRST_JOIN.getName())) {
			errors.add(prefix + "§cErreur de configuration: Veuillez mettre la position du First_Join");
		}
	}

	public Economy getEconomy() {
		return this.eco;
	}

	public List<Player> getPlayers() {
		ArrayList<Player> rplayers = new ArrayList<Player>();
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (players.containsKey(all.getName())) {
				rplayers.add(all);
			}
		}
		return rplayers;
	}

	public void joinedAPlayer(Player player) {
		DayzPlayer user = getPlayer(player);
		Bukkit.getPluginManager().callEvent(new UserJoinEvent(user));
	}

	public static DayzPlayer getPlayer(Player player) {
		DayzPlayer result = null;
		boolean first = false;
		if (players.containsKey(player.getName())) {
			result = (DayzPlayer) players.get(player.getName());
			return result;
		}
		if (!DayzSQL.containsPlayer(player.getName())) {
			first = true;
			DayzSQL.createPlayer(player.getName());
		} else {
			first = false;
		}

		result = DayzSQL.getUser(player.getName());

		if (first) {
			Bukkit.getPluginManager().callEvent(new UserFirstJoinEvent(result));
		}

		return result;
	}

	public static void addPlayer(DayzPlayer player) {
		players.put(player.getPlayer().getName(), player);
	}

	public static void removePlayer(DayzPlayer player) {
		players.remove(player.getPlayer().getName());
	}

}
