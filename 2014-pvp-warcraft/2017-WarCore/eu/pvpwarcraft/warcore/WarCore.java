package eu.pvpwarcraft.warcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import eu.pvpwarcraft.warcore.command.SanctionCommand;

public class WarCore extends JavaPlugin {
	
	private static WarCore instance;
	public static WarCore getInstance(){
		return instance;
	}
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "ModSanc");
		
		getCommand("sanction").setExecutor(new SanctionCommand());
		
	}

}
