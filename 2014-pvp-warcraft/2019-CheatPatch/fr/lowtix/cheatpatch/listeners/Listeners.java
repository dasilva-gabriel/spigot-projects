package fr.lowtix.cheatpatch.listeners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import fr.lowtix.cheatpatch.CheatPatch;

public class Listeners {

	public Listeners() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new BedrockbreakListener(), CheatPatch.getInstance());
		pm.registerEvents(new JoinProtection(), CheatPatch.getInstance());
		pm.registerEvents(new TabulationListener(), CheatPatch.getInstance());
		pm.registerEvents(new KillListener(), CheatPatch.getInstance());
		pm.registerEvents(new JoinListener(), CheatPatch.getInstance());
		pm.registerEvents(new MinerListener(), CheatPatch.getInstance());
	}
}
