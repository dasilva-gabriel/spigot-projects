package fr.lowtix.palatraining.configuration;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.enums.Locations;

public class LocationsConfig {
	
	private HashMap<Locations, Location> savedLocs = new HashMap<Locations, Location>();
	
	public void saveLocation(Locations path, Location location) {
		String serial = locationToString(location);
		
		FileConfiguration config = PalaTraining.getInstance().getConfigurationManager().getLocationsConfig();
		
		config.set(path.name(), serial);
		
		PalaTraining.getInstance().getConfigurationManager().saveLocationConfig();
		savedLocs.put(path, location);
	}
	
	public boolean locationExist(Locations path) {
		return PalaTraining.getInstance().getConfigurationManager().getLocationsConfig().contains(path.name());
	}
	
	public Location getSavedLocation(Locations path) {
		
		Location result = null;
		
		if(!savedLocs.containsKey(path)) {
			FileConfiguration config = PalaTraining.getInstance().getConfigurationManager().getLocationsConfig();
			
			try {
				String serial = config.getString(path.name());
				
				result = stringToLocation(serial);
				
				savedLocs.put(path, result);
			} catch (Exception e) {
				result = null;
			}
		} else {
			result = savedLocs.get(path);
		}
		
		return result;
	}
	
	private String locationToString(Location location) {
		return "LOC%" + location.getWorld().getName() + "%" + location.getX() + "%" + location.getY() + "%" + location.getZ() + "%" + location.getYaw() + "%" + location.getPitch();
	}
	
	private Location stringToLocation(String serial) {
		
		
		try {
			String[] deserialized = serial.split("%");
			return new Location(Bukkit.getWorld(deserialized[1]), 
					Double.parseDouble(deserialized[2]), 
					Double.parseDouble(deserialized[3]), 
					Double.parseDouble(deserialized[4]), 
					Float.parseFloat(deserialized[5]), 
					Float.parseFloat(deserialized[6]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
