package eu.pvpwarcraft.thetowers.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum Locations {
	
	IRON_INGOT(new Location(Bukkit.getWorld("world"), 0.5, 210, 1138.5)),
	EXP(new Location(Bukkit.getWorld("world"), 0.5, 210, 1166.5));
	
	private Location loc;

	private Locations(Location loc) {
		this.loc = loc;
	}

	public Location getLoc() {
		return loc;
	}
	
	

}
