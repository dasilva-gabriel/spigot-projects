package eu.pvpwarcraft.warfight.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import eu.pvpwarcraft.warfight.WarFight;
import eu.pvpwarcraft.warfight.managers.kits.Kits;

public class Configuration {

	private static File sqlFile = new File(WarFight.getInstance().getDataFolder(), "config/sql.yml");
	private static FileConfiguration sqlConfig;
	private static File messageFile = new File(WarFight.getInstance().getDataFolder(), "config/messages.yml");
	private static FileConfiguration messageConfig;
	private static File permissionsFile = new File(WarFight.getInstance().getDataFolder(), "config/permissions.yml");
	private static FileConfiguration permissionsConfig;
	private static File locationsFile = new File(WarFight.getInstance().getDataFolder(), "config/locations.yml");
	private static FileConfiguration locationsConfig;

	public static void init() {
		try {
			if (!WarFight.getInstance().getDataFolder().exists()) {
				WarFight.getInstance().getDataFolder().mkdir();
			}
			
			sqlFile = new File(WarFight.getInstance().getDataFolder(), "sql.yml");
			if(!sqlFile.exists()){
				WarFight.getInstance().saveResource("sql.yml", false);
			}
			sqlConfig = YamlConfiguration.loadConfiguration(sqlFile);
			
			messageFile = new File(WarFight.getInstance().getDataFolder(), "messages.yml");
			if(!messageFile.exists()){
				WarFight.getInstance().saveResource("messages.yml", false);
			}
			messageConfig = YamlConfiguration.loadConfiguration(messageFile);
			
			locationsFile = new File(WarFight.getInstance().getDataFolder(), "locations.yml");
			if(!locationsFile.exists()){
				WarFight.getInstance().saveResource("locations.yml", false);
			}
			locationsConfig = YamlConfiguration.loadConfiguration(locationsFile);
			
			permissionsFile = new File(WarFight.getInstance().getDataFolder(), "permissions.yml");
			if(!permissionsFile.exists()){
				WarFight.getInstance().saveResource("permissions.yml", false);
			}
			permissionsConfig = YamlConfiguration.loadConfiguration(permissionsFile);
			
			for(Kits kits : Kits.values()){
				File f = new File(WarFight.getInstance().getDataFolder() + "/kits/");
				if (!f.exists()) {
					f.mkdirs();
				}
				f = new File(WarFight.getInstance().getDataFolder() + "/kits/", kits.getName() + ".yml");
				if(!f.exists()){
					try {
						f.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					Object config = YamlConfiguration.loadConfiguration(f);
					((FileConfiguration) config).options().copyDefaults(true);
					((FileConfiguration) config).addDefault("name", kits.getName());
					((FileConfiguration) config).addDefault("contents", "");
					((FileConfiguration) config).addDefault("armor", "");
					((FileConfiguration) config).addDefault("hit", "20");
					try {
						((FileConfiguration) config).save(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FileConfiguration getSQLConfig() {
		return sqlConfig;
	}

	public static FileConfiguration getMessagesConfig() {
		return messageConfig;
	}
	
	public static FileConfiguration getPermissionsConfig() {
		return permissionsConfig;
	}
	
	public static FileConfiguration getLocationsConfig() {
		return locationsConfig;
	}
	
	public static void saveLocationsConfig() {
		try {
			locationsConfig.save(locationsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void savePermissionsConfig() {
		try {
			permissionsConfig.save(permissionsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveMessagesConfig() {
		try {
			messageConfig.save(messageFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
