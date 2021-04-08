package fr.paladium.palaaccountrecovery.listeners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.paladium.palaaccountrecovery.PalaAccountRecovery;

public class Listeners {

	public void init() {
		
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new InOutEvents(), PalaAccountRecovery.getInstance());
		
	}

}
