package eu.pvpwarcraft.skypvp.managers.configuration;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import eu.pvpwarcraft.skypvp.SkyPvP;

public class ConfigManager {
	
	private static File playersFile;
	private static FileConfiguration playersConfig;

	public static void init() {
		try {

			if (!SkyPvP.getInstance().getDataFolder().exists()) {
				SkyPvP.getInstance().getDataFolder().mkdir();
			}
			
			playersFile = new File(SkyPvP.getInstance().getDataFolder().getPath() + "/players.yml");
			if(!playersFile.exists()){
				playersFile.createNewFile();
			}
			playersConfig = YamlConfiguration.loadConfiguration(playersFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static FileConfiguration getPlayersConfig(){
		return playersConfig;
	}
	
	public static void savePlayersConfig(){
		try {
			playersConfig.save(playersFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
