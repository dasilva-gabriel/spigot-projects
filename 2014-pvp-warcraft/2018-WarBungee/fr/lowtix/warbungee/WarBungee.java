package fr.lowtix.warbungee;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import fr.lowtix.warbungee.commands.IgnoreCommand;
import fr.lowtix.warbungee.commands.LobbyCommand;
import fr.lowtix.warbungee.commands.MessageCommand;
import fr.lowtix.warbungee.commands.ReportCommand;
import fr.lowtix.warbungee.commands.RespondCommand;
import fr.lowtix.warbungee.listeners.InOutListeners;
import fr.lowtix.warbungee.listeners.KickListener;
import fr.lowtix.warbungee.managers.ConfigurationManager;
import fr.lowtix.warbungee.players.BungeeProfile;
import fr.lowtix.warbungee.sql.SQLManager;
import fr.lowtix.warbungee.tasks.TabulationTask;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class WarBungee extends Plugin {

	private static WarBungee instance;
	private HashMap<String, BungeeProfile> profiles = new HashMap<String, BungeeProfile>();
	
	private ConfigurationManager configurationManager;
	private SQLManager sqlManager;
	
	@Override
	public void onEnable() {
		instance = this;
		
		initListeners();
		
		configurationManager = new ConfigurationManager();
		sqlManager = new SQLManager();
		
		try {
			ProxyServer.getInstance().getScheduler().schedule(this, new TabulationTask(), 1, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initListeners() {
		
		PluginManager pm = ProxyServer.getInstance().getPluginManager();
		
		pm.registerListener(this, new InOutListeners());
		pm.registerListener(this, new KickListener());
		
		pm.registerCommand(this, new MessageCommand());
		pm.registerCommand(this, new RespondCommand());
		pm.registerCommand(this, new IgnoreCommand());
		pm.registerCommand(this, new ReportCommand());
		pm.registerCommand(this, new LobbyCommand());
		
	}

	public static WarBungee getInstance() {
		return instance;
	}
	
	public HashMap<String, BungeeProfile> getProfiles() {
		return profiles;
	}
	
	public ConfigurationManager getConfigurationManager() {
		return configurationManager;
	}
	
	public SQLManager getSqlManager() {
		return sqlManager;
	}
	
	public BungeeProfile getProfile(String name) {
		
		if(profiles.containsKey(name)) {
			return profiles.get(name);
		}
		return new BungeeProfile(name);
	}
	
	public void addProfile(BungeeProfile profile) {
		profiles.put(profile.getName(), profile);
	}
	
}
