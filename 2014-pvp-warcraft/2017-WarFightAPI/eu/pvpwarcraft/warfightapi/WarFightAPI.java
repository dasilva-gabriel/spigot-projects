package eu.pvpwarcraft.warfightapi;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import eu.pvpwarcraft.warfightapi.commands.Commands;
import eu.pvpwarcraft.warfightapi.listeners.Listeners;
import eu.pvpwarcraft.warfightapi.managers.Configuration;
import eu.pvpwarcraft.warfightapi.managers.User;
import eu.pvpwarcraft.warfightapi.sql.WarFightSQL;
public class WarFightAPI extends JavaPlugin {

	private static WarFightAPI instance;
	
	private HashMap<String, User> users = new HashMap<String, User>();

	public static WarFightAPI getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		
		Configuration.init();
		
		WarFightSQL.createTableIfNotExist();
		
		Listeners.init();
		
		Commands.init();
	}
	
	public HashMap<String, User> getUsers(){
		return users;
	}

}
