package eu.pvpwarcraft.warfightapi.managers;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import eu.pvpwarcraft.warfightapi.WarFightAPI;

public class Configuration {
	
	private static File sqlFile;
	private static FileConfiguration sqlConfig;

	public static void init() {
		try {

			if (!WarFightAPI.getInstance().getDataFolder().exists()) {
				WarFightAPI.getInstance().getDataFolder().mkdir();
			}
			
			sqlFile = new File(WarFightAPI.getInstance().getDataFolder(), "sql.yml");
			if(!sqlFile.exists()){
				WarFightAPI.getInstance().saveResource("sql.yml", false);
			}
			sqlConfig = YamlConfiguration.loadConfiguration(sqlFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static FileConfiguration getSqlConfig(){
		return sqlConfig;
	}
	

}
