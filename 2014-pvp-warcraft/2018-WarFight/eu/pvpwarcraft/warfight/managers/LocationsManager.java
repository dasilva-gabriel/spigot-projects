package eu.pvpwarcraft.warfight.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationsManager {
	
	private static String locations_path = "locations.";
	
	public static boolean hasLocation(String path){
		if(Configuration.getLocationsConfig().contains(locations_path + path)){
			return true;
		}
		return false;
	}
	
	public static Location getSavedLocation(String path, boolean isPlayer){
		Location location = null;
		
		String worldName = Configuration.getLocationsConfig().getString(locations_path + path + ".World");
		double x = Configuration.getLocationsConfig().getDouble(locations_path + path + ".X");
		double y = Configuration.getLocationsConfig().getDouble(locations_path + path + ".Y");
		double z = Configuration.getLocationsConfig().getDouble(locations_path + path + ".Z");
		if(isPlayer){
			float yaw = Configuration.getLocationsConfig().getInt(locations_path + path + ".Yaw");
			float pitch = Configuration.getLocationsConfig().getInt(locations_path + path + ".Pitch");
			location = new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
		} else {
			location = new Location(Bukkit.getWorld(worldName), x, y, z);
		}
		
		return location;
	}
	
	public static void saveLocation(Location location, String path, boolean isPlayer){
		String worldName = location.getWorld().getName();
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		
		Configuration.getLocationsConfig().set(locations_path + path + ".World", worldName);
		Configuration.getLocationsConfig().set(locations_path + path + ".X", x);
		Configuration.getLocationsConfig().set(locations_path + path + ".Y", y);
		Configuration.getLocationsConfig().set(locations_path + path + ".Z", z);
		
		
		if(isPlayer){
			float yaw = location.getYaw();
			float pitch = location.getPitch();
			Configuration.getLocationsConfig().set(locations_path + path + ".Yaw", yaw);
			Configuration.getLocationsConfig().set(locations_path + path + ".Pitch", pitch);
		}
	}

}
