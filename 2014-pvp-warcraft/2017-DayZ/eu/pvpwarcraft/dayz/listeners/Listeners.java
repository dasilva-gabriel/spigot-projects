package eu.pvpwarcraft.dayz.listeners;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import eu.pvpwarcraft.dayz.listeners.player.FoodListeners;
import eu.pvpwarcraft.dayz.listeners.player.PlayerJoin;
import eu.pvpwarcraft.dayz.listeners.player.PlayerQuit;
import eu.pvpwarcraft.dayz.listeners.weapons.PlayerReloadWeapon;

public class Listeners {

	public static void init(Plugin instance) {

		PluginManager pm = instance.getServer().getPluginManager();

		/*
		 * PLAYERS LISTENERS
		 */
		instance.getLogger().info("Listeners: Starting players listeners.");

		try {
			pm.registerEvents(new PlayerJoin(), instance);
			pm.registerEvents(new FoodListeners(), instance);
			pm.registerEvents(new PlayerQuit(), instance);
		} catch (Exception e) {
			instance.getLogger().warning("Listeners: Error in the initialisation of players listeners.");
			e.printStackTrace();
		}

		/*
		 * WEAPONS LISTENERS
		 */
		instance.getLogger().info("Listeners: Starting weapons listeners.");

		try {
			pm.registerEvents(new PlayerReloadWeapon(), instance);
		} catch (Exception e) {
			instance.getLogger().warning("Listeners: Error in the initialisation of weapons listeners.");
			e.printStackTrace();
		}
	}

}
