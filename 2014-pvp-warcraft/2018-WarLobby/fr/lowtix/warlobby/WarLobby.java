package fr.lowtix.warlobby;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.lowtix.warlobby.listeners.ChatListener;
import fr.lowtix.warlobby.listeners.InteractListener;
import fr.lowtix.warlobby.listeners.JoinListener;
import fr.lowtix.warlobby.listeners.MoveListener;
import fr.lowtix.warlobby.listeners.QuitListener;
import fr.lowtix.warlobby.listeners.ResetListener;
import fr.lowtix.warlobby.managers.ServersManagers;

public class WarLobby extends JavaPlugin {

	private static WarLobby instance;
	
	private ServersManagers serversManager;
	
	@Override
	public void onEnable() {
		instance = this;
		
		initLinisters();
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		serversManager = new ServersManagers();
	}
	
	private void initLinisters() {
		
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
		Bukkit.getPluginManager().registerEvents(new ResetListener(), this);
		Bukkit.getPluginManager().registerEvents(new MoveListener(), this);
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
	}

	public static WarLobby getInstance() {
		return instance;
	}
	
	public ServersManagers getServersManager() {
		return serversManager;
	}
	
}
