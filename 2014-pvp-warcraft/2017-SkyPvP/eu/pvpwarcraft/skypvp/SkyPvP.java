package eu.pvpwarcraft.skypvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import eu.pvpwarcraft.skypvp.commands.Commands;
import eu.pvpwarcraft.skypvp.listeners.Listeners;
import eu.pvpwarcraft.skypvp.managers.configuration.ConfigManager;
import eu.pvpwarcraft.skypvp.managers.scoreboard.ScoreboardManager;
import eu.pvpwarcraft.skypvp.managers.scoreboard.ScoreboardScheduler;
import eu.pvpwarcraft.skypvp.utils.skybox.BlockScheduler;
import eu.pvpwarcraft.skypvp.utils.skybox.SkyBoxSpawn;

public class SkyPvP extends JavaPlugin{
	
	private BukkitTask scoreboard;
	private BukkitTask block;
	private static SkyPvP instance;
	public static SkyPvP getInstance(){
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		Bukkit.getWorld("world").setAutoSave(false);
		if (this.scoreboard != null) {
		      this.scoreboard.cancel();
		}
		this.scoreboard = new ScoreboardScheduler().runTaskTimer(this, 20L, 20L);
		this.block = new BlockScheduler().runTaskTimer(this, 90L, 90L);
		Listeners.init();
		SkyBoxSpawn.spawn();
		SkyBoxSpawn.setup();
		ConfigManager.init();
		ConfigManager.savePlayersConfig();
		Commands.init();
		for(Player p : Bukkit.getOnlinePlayers()){
			ScoreboardManager.setupScoreboard(p);
			if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Kills")) {
				ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Kills", 0);
			}

			if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Deaths")) {
				ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Deaths", 0);
			}
			
			if (!ConfigManager.getPlayersConfig().isSet("Players. " + p.getName() + " .Coins")) {
				ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", 0);
			}
		}
	}
	
	@Override
	public void onDisable() {
		Bukkit.getWorld("world").setAutoSave(false);
		ConfigManager.savePlayersConfig();
		if (this.scoreboard != null) {
		      this.scoreboard.cancel();
		}
		if (this.block != null) {
		      this.block.cancel();
		}
	}

}
