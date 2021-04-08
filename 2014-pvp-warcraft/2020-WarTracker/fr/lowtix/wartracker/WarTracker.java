package fr.lowtix.wartracker;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.lowtix.wartracker.listeners.InventoryListener;
import fr.lowtix.wartracker.tasks.CompassTask;
import fr.lowtix.wartracker.tasks.TrackerTask;

public class WarTracker extends JavaPlugin {
	
	private static WarTracker INSTANCE;
	private HashMap<String, String> trackers = new HashMap<String, String>();
	private HashMap<String, Long> time = new HashMap<String, Long>();
	private HashMap<String, Long> cooldown = new HashMap<String, Long>();
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		
		initListeners();
		
		new TrackerTask().runTaskTimerAsynchronously(this, 200, 200);
		new CompassTask().runTaskTimerAsynchronously(this, 20, 20);
	}
	
	private void initListeners() {
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
	}

	public static WarTracker getInstance() {
		return INSTANCE;
	}
	
	public HashMap<String, String> getTrackers() {
		return trackers;
	}
	
	public HashMap<String, Long> getTime() {
		return time;
	}
	
	public HashMap<String, Long> getCooldown() {
		return cooldown;
	}

}
