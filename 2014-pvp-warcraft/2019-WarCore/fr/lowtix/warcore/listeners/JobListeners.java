package fr.lowtix.warcore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gamingmesh.jobs.api.JobsLevelUpEvent;
import com.gamingmesh.jobs.api.JobsPaymentEvent;
import com.gamingmesh.jobs.container.JobsPlayer;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Boosters;
import fr.lowtix.warcore.utils.PlayerUtils;

public class JobListeners implements Listener {
	
	@EventHandler
	public void onLevelUp(JobsLevelUpEvent event) {
		JobsPlayer jobPlayer = event.getPlayer();
		Player player = jobPlayer.getPlayer();
		WarPlayer wPlayer = WarCore.getInstance().getUser(player);
		
		double gain = 100 * event.getLevel();
		gain = Math.floor((gain *100)/100);
		wPlayer.addPointsEffect(gain);
	}
	
	@EventHandler
	public void onPay(JobsPaymentEvent event) {
		Player player = (Player) event.getPlayer();
		WarPlayer wPlayer = WarCore.getInstance().getUser(player);
		
		double pointsAmmount = 1 + (event.getAmount() * 1.5);
		pointsAmmount = Math.floor(pointsAmmount *100)/100;
		
		double coinsAmmount = Math.floor(event.getAmount() *100)/100;
		
		if(WarCore.getInstance().booster.isActive(Boosters.JOBS_COINS)) {
			coinsAmmount *= 2;
		}
		
		if(WarCore.getInstance().booster.isActive(Boosters.JOBS_POINTS)) {
			pointsAmmount *= 2;
		}
		
		wPlayer.addPoints(pointsAmmount);
		
		PlayerUtils.sendActionBar(player, "§bVous avez reçu: §e"+coinsAmmount+" ⛃ §bet §f"+pointsAmmount+" ✴");
	}

}
