package eu.pvpwarcraft.skypvp.listeners.sort;

import org.bukkit.Bukkit;

import eu.pvpwarcraft.skypvp.SkyPvP;

public class Sorts {
	
	public static void init(){
		Bukkit.getServer().getPluginManager().registerEvents(new PoisonSort(), SkyPvP.getInstance());
	}

}
