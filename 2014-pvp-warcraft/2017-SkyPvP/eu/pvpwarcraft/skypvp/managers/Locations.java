package eu.pvpwarcraft.skypvp.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Locations {
	
	public static Location getLocation(EnumLocations loc){
		if(loc == EnumLocations.SPAWN){
			Location locreturn = new Location(Bukkit.getWorld("world"), 1.500, 150, -15.500, (float) 0, (float) 5);
			return locreturn;
		}
		if(loc == EnumLocations.SKYBOX1){
			Location locreturn = new Location(Bukkit.getWorld("world"), -5, 92, 23);
			return locreturn;
		}
		if(loc == EnumLocations.SKYBOX2){
			Location locreturn = new Location(Bukkit.getWorld("world"), -18, 124, 55);
			return locreturn;
		}
		if(loc == EnumLocations.SKYBOX3){
			Location locreturn = new Location(Bukkit.getWorld("world"), -11, 103, 120);
			return locreturn;
		}
		if(loc == EnumLocations.SKYBOX4){
			Location locreturn = new Location(Bukkit.getWorld("world"), 4, 132, -114);
			return locreturn;
		}
		if(loc == EnumLocations.SKYBOX5){
			Location locreturn = new Location(Bukkit.getWorld("world"), 134, 98, -2);
			return locreturn;
		}
		if(loc == EnumLocations.SKYBOX6){
			Location locreturn = new Location(Bukkit.getWorld("world"), -114, 137, 4);
			return locreturn;
		}
		if(loc == EnumLocations.SKYBOX7){
			Location locreturn = new Location(Bukkit.getWorld("world"), -62, 110, 64);
			return locreturn;
		}
		if(loc == EnumLocations.SKYBOX8){
			Location locreturn = new Location(Bukkit.getWorld("world"), 71, 93, -70);
			return locreturn;
		}
		
		return null;
	}

}
