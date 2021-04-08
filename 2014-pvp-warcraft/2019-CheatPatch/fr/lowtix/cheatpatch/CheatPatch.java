package fr.lowtix.cheatpatch;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.lowtix.cheatpatch.commands.ScoreboardCommand;
import fr.lowtix.cheatpatch.commands.WarVoteCommand;
import fr.lowtix.cheatpatch.listeners.Listeners;
import fr.lowtix.cheatpatch.managers.CheatScoreboard;
import fr.lowtix.cheatpatch.managers.CheatTopManager;
import fr.lowtix.cheatpatch.managers.MinerManager;
import fr.lowtix.cheatpatch.managers.VoteManager;
import fr.lowtix.cheatpatch.sql.CheatSQL;
import fr.lowtix.cheatpatch.sql.MinerSQL;
import fr.lowtix.cheatpatch.sql.VoteSQL;
import net.milkbowl.vault.economy.Economy;

public class CheatPatch extends JavaPlugin {

	private static CheatPatch instance;
	private Economy eco;

	private CheatScoreboard cheatScoreboard;
	private CheatTopManager topManager;
	private MinerManager minerManager;
	private VoteManager voteManager;

	private HashMap<String, PlayerWrapper> players = new HashMap<String, PlayerWrapper>();

	@Override
	public void onEnable() {
		instance = this;

		new Listeners();

		getCommand("warvote").setExecutor(new WarVoteCommand());
		//getCommand("warminer").setExecutor(new MinerCommand());
		getCommand("sb").setExecutor(new ScoreboardCommand());

		cheatScoreboard = new CheatScoreboard();
		topManager = new CheatTopManager();
		minerManager = new MinerManager();
		voteManager = new VoteManager(0, 300, "Personne");

		CheatSQL.createTableIfNotExist();
		MinerSQL.createTableIfNotExist();
		VoteSQL.createTableIfNotExist();
		
		saveDefaultConfig();
		setupEconomy();
		getTopManager().reloadTop();
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			CheatPatch.getInstance().getCheatScoreboard().setup(all, true);
		}
	}

	public static CheatPatch getInstance() {
		return instance;
	}

	public CheatScoreboard getCheatScoreboard() {
		return cheatScoreboard;
	}

	public CheatTopManager getTopManager() {
		return topManager;
	}
	
	public MinerManager getMinerManager() {
		return minerManager;
	}

	public HashMap<String, PlayerWrapper> getPlayers() {
		return players;
	}
	
	public VoteManager getVoteManager() {
		return voteManager;
	}
	
	public void addPlayer(PlayerWrapper pw) {
		players.put(pw.getName(), pw);
	}
	
	public void removePlayer(PlayerWrapper pw) {
		if (players.containsKey(pw.getName())) {
			players.remove(pw.getName());
		}
	}

	public PlayerWrapper getPlayer(String name) {
		if (players.containsKey(name)) {
			return players.get(name);
		}
		return new PlayerWrapper(name);
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
		if (economyProvider != null) {
			this.eco = ((Economy) economyProvider.getProvider());
		}
		return this.eco != null;
	}

	public Economy getEconomy() {
		return this.eco;
	}
}
