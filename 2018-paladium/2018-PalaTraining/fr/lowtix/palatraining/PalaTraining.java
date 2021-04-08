package fr.lowtix.palatraining;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.lowtix.palatraining.commands.ForceDuelCommand;
import fr.lowtix.palatraining.commands.PlayCommand;
import fr.lowtix.palatraining.commands.QueueTestCommand;
import fr.lowtix.palatraining.configuration.ConfigurationManager;
import fr.lowtix.palatraining.configuration.LocationsConfig;
import fr.lowtix.palatraining.enums.Locations;
import fr.lowtix.palatraining.handlers.GamePlayer;
import fr.lowtix.palatraining.listeners.GameArenaListener;
import fr.lowtix.palatraining.listeners.InOutListeners;
import fr.lowtix.palatraining.listeners.KillListener;
import fr.lowtix.palatraining.manager.GameArenasManager;
import fr.lowtix.palatraining.manager.GameManager;
import fr.lowtix.palatraining.manager.LobbyManager;
import fr.lowtix.palatraining.manager.QueueManager;
import fr.lowtix.palatraining.manager.TeamsManager;
import fr.lowtix.palatraining.tasks.ArenaTask;
import fr.lowtix.palatraining.tasks.GameTask;
import fr.lowtix.palatraining.tasks.QueueTask;
import fr.lowtix.palatraining.tasks.TeamRequestTask;

public class PalaTraining extends JavaPlugin {

	private static PalaTraining instance;
	
	private HashMap<UUID, GamePlayer> players;
	
	private GameManager gameManager;
	public GameArenasManager gameArenasManager;
	private ConfigurationManager configurationManager;
	private LocationsConfig locationsConfig;
	private LobbyManager lobbyManager;
	private QueueManager queueManager;
	private TeamsManager teamsManager;
	
	@Override
	public void onEnable() {
		instance = this;
		
		players = new HashMap<UUID, GamePlayer>();
		
		gameManager = new GameManager();
		gameArenasManager = new GameArenasManager();
		configurationManager = new ConfigurationManager();
		locationsConfig = new LocationsConfig();
		lobbyManager = new LobbyManager();
		queueManager = new QueueManager();
		teamsManager = new TeamsManager();
		
		initListeners();
		
		getCommand("forceduel").setExecutor(new ForceDuelCommand());
		getCommand("queuetest").setExecutor(new QueueTestCommand());
		//getCommand("team").setExecutor(new TeamCommand());
		getCommand("play").setExecutor(new PlayCommand());
		
		new GameTask().runTaskTimerAsynchronously(this, 20, 20);
		new QueueTask().runTaskTimerAsynchronously(this, 20, 20);
		new ArenaTask().runTaskTimerAsynchronously(this, 40, 40);
		new TeamRequestTask().runTaskTimerAsynchronously(this, 30, 30);
		
		getConfigurationManager().init();
		getLocationsConfig().saveLocation(Locations.SPAWN_LOCATION, new Location(Bukkit.getWorld("world"), 0.500, 101, 0.500));
	}
	
	private void initListeners() {
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new GameArenaListener(), this);
		pm.registerEvents(new KillListener(), this);
		pm.registerEvents(new InOutListeners(), this);
	}

	public static PalaTraining getInstance() {
		return instance;
	}
	
	public GameManager getGameManager() {
		return gameManager;
	}
	
	public GameArenasManager getGameArenasManager() {
		return gameArenasManager;
	}
	
	public ConfigurationManager getConfigurationManager() {
		return configurationManager;
	}
	
	public LocationsConfig getLocationsConfig() {
		return locationsConfig;
	}
	
	public LobbyManager getLobbyManager() {
		return lobbyManager;
	}
	
	public QueueManager getQueueManager() {
		return queueManager;
	}

	
	public TeamsManager getTeamsManager() {
		return teamsManager;
	}
	
	public boolean containsGamePlayer(UUID uuid) {
		return this.players.containsKey(uuid);
	}
	
	public void addGamePlayer(GamePlayer gplayer) {
		this.players.put(gplayer.getPlayer().getUniqueId(), gplayer);
	}
	
	public void deleteGamePlayer(UUID uuid) {
		if(containsGamePlayer(uuid)) {
			this.players.remove(uuid);
		}
	}
	
	public GamePlayer getGamePlayer(UUID uuid) {
		if(containsGamePlayer(uuid)) {
			return this.players.get(uuid);
		}
		
		return new GamePlayer(Bukkit.getPlayer(uuid));
	}
	
	public GamePlayer getGamePlayer(Player player) {
		return getGamePlayer(player.getUniqueId());
	}
	
	public HashMap<UUID, GamePlayer> getGamePlayers(){
		return this.players;
	}
	
	public String centerText(String text) {
		int maxWidth = 80, spaces = (int) Math.round((maxWidth - 1.4 * text.length()) / 2);
		return StringUtils.repeat(" ", spaces) + text;
	}
	
}
