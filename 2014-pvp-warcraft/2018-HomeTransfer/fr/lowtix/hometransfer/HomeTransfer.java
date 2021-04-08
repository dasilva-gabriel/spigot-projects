package fr.lowtix.hometransfer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.IEssentials;

public class HomeTransfer extends JavaPlugin {

	private static HomeTransfer instance;
	private Essentials ess;
	
	@Override
	public void onEnable() {
		instance = this;
		
		saveDefaultConfig();
		
		getCommand("hometransfer").setExecutor(new HomeCommand());
		
		loadEssentials();
	}
	
	public static HomeTransfer getInstance() {
		return instance;
	}
	
	public IEssentials getEss() {
		return ess;
	}
	
	public void loadEssentials() {
		try {
			ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
		} catch (Exception e) {
			getLogger().warning("Essentials pas trouvé !");
		}
	}
	
}
