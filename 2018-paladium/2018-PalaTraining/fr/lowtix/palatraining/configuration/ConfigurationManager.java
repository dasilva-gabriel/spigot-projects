package fr.lowtix.palatraining.configuration;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.lowtix.palatraining.PalaTraining;

public class ConfigurationManager {

	private File locationsFile;
	private FileConfiguration locationsConfig;

	public void init() {
		try {
			if (!PalaTraining.getInstance().getDataFolder().exists()) {
				PalaTraining.getInstance().getDataFolder().mkdir();
			}
			
			/*
			 * FICHIER DES LOCATIONS
			 */
			
			locationsFile = new File(PalaTraining.getInstance().getDataFolder().getPath() + "/locations.yml");
			if(!locationsFile.exists()){
				locationsFile.createNewFile();
			}
			locationsConfig = YamlConfiguration.loadConfiguration(locationsFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public FileConfiguration getLocationsConfig(){
		return locationsConfig;
	}
	
	public void saveLocationConfig(){
		try {
			locationsConfig.save(locationsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
