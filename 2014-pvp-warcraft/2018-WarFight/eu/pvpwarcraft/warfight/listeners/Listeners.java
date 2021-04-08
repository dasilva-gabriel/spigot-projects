package eu.pvpwarcraft.warfight.listeners;

import org.bukkit.plugin.PluginManager;

import eu.pvpwarcraft.warfight.WarFight;

public class Listeners {

	public static void init(WarFight instance, PluginManager pm) {
		
		pm.registerEvents(new InOutListener(), instance);
		pm.registerEvents(new LobbyListener(), instance);
		pm.registerEvents(new PearlListener(), instance);
		
	}

}
