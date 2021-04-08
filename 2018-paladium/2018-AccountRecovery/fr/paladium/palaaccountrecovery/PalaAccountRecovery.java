package fr.paladium.palaaccountrecovery;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.Essentials;

import fr.paladium.palaaccountrecovery.listeners.Listeners;
import fr.paladium.palaaccountrecovery.managers.BalanceManager;
import fr.paladium.palaaccountrecovery.managers.usernamehistory.UsernameHistoryManager;
import fr.paladium.palaaccountrecovery.requests.RecoveryRequest;
import net.milkbowl.vault.economy.Economy;

public class PalaAccountRecovery extends JavaPlugin {

	private static PalaAccountRecovery instance;
	
	private HashMap<UUID, String> playersRequests;
	private HashMap<String, RecoveryRequest> recoveryRequest;
	
	private BalanceManager balanceManager;
	public Essentials essentials;
	private Economy economy;
	private UsernameHistoryManager usernameHistoryManager;
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		enableEconomy();
		
		playersRequests = new HashMap<UUID, String>();
		recoveryRequest = new HashMap<String, RecoveryRequest>();
		
		essentials = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
		
		new Listeners().init();
		
		usernameHistoryManager = new UsernameHistoryManager();
		usernameHistoryManager.init(true);
	}
	
	public static PalaAccountRecovery getInstance() {
		
		return instance;
	
	}
	
	public Economy getEconomy() {
		return economy;
	}

	public HashMap<UUID, String> getPlayersRequests() {
		return playersRequests;
	}

	public HashMap<String, RecoveryRequest> getRecoveryRequest() {
		return recoveryRequest;
	}

	public BalanceManager getBalanceManager() {
		return balanceManager;
	}

	public Essentials getEssentials() {
		return essentials;
	}
	
	public UsernameHistoryManager getUsernameHistoryManager() {
		return usernameHistoryManager;
	}

	private void enableEconomy() {
		
		try {
			RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
			
			if (economyProvider != null) {
				economy = economyProvider.getProvider();
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new NullArgumentException("Economy was not loaded correctly!");
			
		}
		
	}
	
}
