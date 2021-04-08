package eu.pvpwarcraft.dayz.configuration;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import eu.pvpwarcraft.dayz.DayZ;

public class ConfigManager {

	private static File settingsFile;
	private static FileConfiguration settingsConfig;
	private static File sqlFile;
	private static FileConfiguration sqlConfig;
	private static File locationsFile;
	private static FileConfiguration locationsConfig;

	public static void init() {
		try {
			if (!DayZ.getInstance().getDataFolder().exists()) {
				DayZ.getInstance().getDataFolder().mkdir();
			}
			
			settingsFile = new File(DayZ.getInstance().getDataFolder().getPath() + "/settings.yml");
			if (!settingsFile.exists()) {
				DayZ.getInstance().saveResource("settings.yml", false);
			}
			settingsConfig = YamlConfiguration.loadConfiguration(settingsFile);
			
			sqlFile = new File(DayZ.getInstance().getDataFolder(), "sql.yml");
			if(!sqlFile.exists()){
				DayZ.getInstance().saveResource("sql.yml", false);
			}
			sqlConfig = YamlConfiguration.loadConfiguration(sqlFile);
			
			locationsFile = new File(DayZ.getInstance().getDataFolder().getPath() + "/locations.yml");
			if(!locationsFile.exists()){
				locationsFile.createNewFile();
			}
			locationsConfig = YamlConfiguration.loadConfiguration(locationsFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static FileConfiguration getSettingsConfig() {
		return settingsConfig;
	}
	
	public static FileConfiguration getSqlConfig(){
		return sqlConfig;
	}
	
	public static FileConfiguration getLocationsConfig(){
		return locationsConfig;
	}
	
	public static void saveLocationConfig(){
		try {
			locationsConfig.save(locationsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
