package eu.pvpwarcraft.dayz;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.dayz.users.DayzPlayer;
import eu.pvpwarcraft.dayz.utils.PlayerUtils;

public class ServerTask extends BukkitRunnable {
	
	private static boolean state;

	@Override
	public void run() {
		
		state = !state;
		
		for(Player all : Bukkit.getOnlinePlayers()){
			DayzPlayer dp = DayZ.getPlayer(all);
			if(all.isSprinting()){
				dp.removeThirst(0.21);
			}else{
				dp.removeThirst(0.12);
			}
			PlayerUtils.sendActionBar(all, getWarn(dp.getThirst()) + " " + dp.getBar() + " " + getWarn(dp.getThirst()));
			if(!all.hasPotionEffect(PotionEffectType.CONFUSION) && dp.getThirst() <= 45){
				all.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 10));
			}
			if(dp.getThirst() <= 30){
				if(state){
					all.damage(0.3);
				}
			}
		}
		
	}
	
	private String getWarn(double i){
		if(state){
			return i <= 70 ? "§c⚠" : "";
		}
		return i <= 70 ? "§7⚠" : "";
    }

}
