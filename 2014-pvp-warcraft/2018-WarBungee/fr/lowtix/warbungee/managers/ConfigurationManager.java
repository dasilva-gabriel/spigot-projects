package fr.lowtix.warbungee.managers;

import java.io.File;

import fr.lowtix.warbungee.WarBungee;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigurationManager {

	private File sqlFile;
	private Configuration sqlConfig;
	
	public ConfigurationManager() {

		try {
			if (!WarBungee.getInstance().getDataFolder().exists()) {
				WarBungee.getInstance().getDataFolder().mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			sqlFile = new File(WarBungee.getInstance().getDataFolder(), "/sql.yml");
			if (!sqlFile.exists()) {
				sqlFile.createNewFile();
				sqlConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(sqlFile);
				sqlConfig.set("username", "warbungee");
				sqlConfig.set("password", "6ICflRz9Dr7q7KLN");
				sqlConfig.set("host", "localhost");
				sqlConfig.set("database", "warbungee");
				ConfigurationProvider.getProvider(YamlConfiguration.class).save(sqlConfig, sqlFile);
			} else {
				sqlConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(sqlFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public Configuration getSqlConfig() {
		return sqlConfig;
	}

}
