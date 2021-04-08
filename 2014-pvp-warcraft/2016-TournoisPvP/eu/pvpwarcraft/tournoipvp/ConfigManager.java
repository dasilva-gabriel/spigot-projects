package eu.pvpwarcraft.tournoipvp;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;	

public class ConfigManager {

	private static File registersFile;
	private static FileConfiguration registersConfig;


	public static void init(){
		try {
			if(!TournoiPvP.getInstance().getDataFolder().exists()){
				TournoiPvP.getInstance().getDataFolder().mkdir();
			}

			registersFile = new File(TournoiPvP.getInstance().getDataFolder().getPath() + "/registers.yml");
			if(!registersFile.exists()){
				registersFile.createNewFile();
			}
			registersConfig = YamlConfiguration.loadConfiguration(registersFile);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FileConfiguration getRegistersConfig() {
		return registersConfig;
	}
	
	public static void saveRegistersConfig(){
		try {
			registersConfig.save(registersFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
