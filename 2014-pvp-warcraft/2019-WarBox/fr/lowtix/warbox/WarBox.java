package fr.lowtix.warbox;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.lowtix.warbox.commands.BoxEconomyCommand;
import fr.lowtix.warbox.commands.BroadcastCommand;
import fr.lowtix.warbox.commands.GiftCommand;
import fr.lowtix.warbox.commands.MenusManagerCommand;
import fr.lowtix.warbox.commands.SpawnCommand;
import fr.lowtix.warbox.enums.Groups;
import fr.lowtix.warbox.events.UserJoinEvent;
import fr.lowtix.warbox.listeners.BowTrailListeners;
import fr.lowtix.warbox.listeners.ChatListeners;
import fr.lowtix.warbox.listeners.InOutListeners;
import fr.lowtix.warbox.listeners.KillListeners;
import fr.lowtix.warbox.listeners.ResetListeners;
import fr.lowtix.warbox.listeners.SpawnListeners;
import fr.lowtix.warbox.managers.BoardManager;
import fr.lowtix.warbox.managers.BowTrailsManager;
import fr.lowtix.warbox.managers.DailyDropsManager;
import fr.lowtix.warbox.managers.LevelManager;
import fr.lowtix.warbox.managers.SpawnManager;
import fr.lowtix.warbox.managers.WelcomeManager;
import fr.lowtix.warbox.players.WarPlayer;
import fr.lowtix.warbox.sql.PlayersSQL;
import fr.lowtix.warbox.tasks.BoardTask;
import fr.lowtix.warbox.tasks.BowTask;
import fr.lowtix.warbox.tasks.SaveTask;
import fr.lowtix.warbox.tasks.WorldTask;

public class WarBox extends JavaPlugin {
	
	private static WarBox instance;
	
	private HashMap<String, WarPlayer> players = new HashMap<String, WarPlayer>();
	
	private Location spawn;
	
	private PlayersSQL sql;
	private LevelManager levelManager;
	private BowTrailsManager bowTrailsManager;
	private WelcomeManager welcomeManager;
	private SpawnManager spawnManager;
	private DailyDropsManager dailyDropsManager;
	private BoardManager boardManager;
	
	@Override
	public void onEnable() {
		instance = this;
		
		getCommand("menusmngr").setExecutor(new MenusManagerCommand());
		getCommand("cratesgift").setExecutor(new GiftCommand());
		getCommand("boxeconomy").setExecutor(new BoxEconomyCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("broadcast").setExecutor(new BroadcastCommand());
		
		saveDefaultConfig();
		
		sql = new PlayersSQL();
		sql.createTable();
		
		levelManager = new LevelManager();
		bowTrailsManager = new BowTrailsManager();
		welcomeManager = new WelcomeManager();
		spawnManager = new SpawnManager();
		dailyDropsManager = new DailyDropsManager();
		boardManager = new BoardManager();
		
		for (Groups group : Groups.values()) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex group " + group.getDisplayName() + " create");
		}
		
		FileConfiguration c = getConfig();
		spawn = new Location(Bukkit.getWorld(c.getString("spawn.world")), c.getDouble("spawn.x"), c.getDouble("spawn.y"), c.getDouble("spawn.z"), (float)c.getDouble("spawn.pitch"), (float)c.getDouble("spawn.yaw"));
		
		initListeners();
		
		new BowTask().runTaskTimerAsynchronously(this, 1L, 1L);
		new SaveTask().runTaskTimerAsynchronously(this, 300L, 300L);
		new BoardTask().runTaskTimer(this, 40L, 40L);
		new WorldTask().runTaskTimer(this, 50L, 50L);
	}

	private void initListeners() {
		
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new InOutListeners(), this);
		pm.registerEvents(new ChatListeners(), this);
		pm.registerEvents(new BowTrailListeners(), this);
		pm.registerEvents(new KillListeners(), this);
		pm.registerEvents(new ResetListeners(), this);
		pm.registerEvents(new SpawnListeners(), this);
	}

	public static WarBox getInstance() {
		return instance;
	}
	
	public PlayersSQL getSql() {
		return sql;
	}
	
	public LevelManager getLevelManager() {
		return levelManager;
	}
	
	public BowTrailsManager getBowTrailsManager() {
		return bowTrailsManager;
	}
	
	public WelcomeManager getWelcomeManager() {
		return welcomeManager;
	}
	
	public Location getSpawn() {
		return spawn;
	}
	
	public SpawnManager getSpawnManager() {
		return spawnManager;
	}
	
	public DailyDropsManager getDailyDropsManager() {
		return dailyDropsManager;
	}
	
	public BoardManager getBoardManager() {
		return boardManager;
	}
	
	public void createPlayer(WarPlayer user) {
		players.put(user.getPlayer().getName(), user);
		Bukkit.getPluginManager().callEvent(new UserJoinEvent(user));
	}

	public void deletePlayer(WarPlayer user) {
		if (containsPlayer(user.getPlayer())) {
			players.remove(user.getPlayer().getName());
		}
	}

	public boolean containsPlayer(Player player) {
		return players.containsKey(player.getName());
	}

	public WarPlayer getUser(Player player) {
		if (containsPlayer(player)) {
			return players.get(player.getName());
		}
		return new WarPlayer(player);
	}

	public HashMap<String, WarPlayer> getUsers() {
		return this.players;
	}
	
}
