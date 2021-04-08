package eu.pvpwarcraft.dayz.configuration;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationsManager {
	
	public static Location getLocation(LocationsList locl, boolean forPlayer){
		return getSavedLocation(locl.getName(), forPlayer) ;
	}
	
	public static void saveLocation(LocationsList locl, Location location){
		saveLocation(location, locl.getName(), true);
		ConfigManager.saveLocationConfig();
	}
	
	public static boolean hasLocation(String path){
		if(ConfigManager.getLocationsConfig().contains(path)){
			return true;
		}
		return false;
	}
	
	public static Location getSavedLocation(String path, boolean isPlayer){
		Location location = null;
		
		String worldName = ConfigManager.getLocationsConfig().getString(path + ".World");
		double x = ConfigManager.getLocationsConfig().getDouble(path + ".X");
		double y = ConfigManager.getLocationsConfig().getDouble(path + ".Y");
		double z = ConfigManager.getLocationsConfig().getDouble(path + ".Z");
		if(isPlayer){
			float yaw = ConfigManager.getLocationsConfig().getInt(path + ".Yaw");
			float pitch = ConfigManager.getLocationsConfig().getInt(path + ".Pitch");
			location = new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
		} else {
			location = new Location(Bukkit.getWorld(worldName), x, y, z);
		}
		
		return location;
	}
	
	private static void saveLocation(Location location, String path, boolean isPlayer){
		String worldName = location.getWorld().getName();
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		
		ConfigManager.getLocationsConfig().set(path + ".World", worldName);
		ConfigManager.getLocationsConfig().set(path + ".X", x);
		ConfigManager.getLocationsConfig().set(path + ".Y", y);
		ConfigManager.getLocationsConfig().set(path + ".Z", z);
		
		
		if(isPlayer){
			float yaw = location.getYaw();
			float pitch = location.getPitch();
			ConfigManager.getLocationsConfig().set(path + ".Yaw", yaw);
			ConfigManager.getLocationsConfig().set(path + ".Pitch", pitch);
		}
	}

}
