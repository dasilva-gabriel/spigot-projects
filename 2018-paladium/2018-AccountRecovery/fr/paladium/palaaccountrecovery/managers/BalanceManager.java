package fr.paladium.palaaccountrecovery.managers;

import org.bukkit.entity.Player;

import fr.paladium.palaaccountrecovery.PalaAccountRecovery;

public class BalanceManager {

	public double getMoney(Player player) {
		return PalaAccountRecovery.getInstance().getEconomy().getBalance(player);
	}
	
	public void addMoney(Player player, double amount) {
		PalaAccountRecovery.getInstance().getEconomy().depositPlayer(player, amount);
	}
	
	public void removeMoney(Player player, double amount) {
		PalaAccountRecovery.getInstance().getEconomy().withdrawPlayer(player, amount);
	}
	
}
