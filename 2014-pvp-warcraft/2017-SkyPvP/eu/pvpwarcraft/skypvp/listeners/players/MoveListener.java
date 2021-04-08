package eu.pvpwarcraft.skypvp.listeners.players;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.skypvp.SkyPvP;

public class MoveListener implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		final Player player = event.getPlayer();
		if(event.getFrom().getY() != event.getTo().getY()){
			if (player.getLocation().subtract(0, 1, 0).getBlock().getType().equals(Material.GOLD_BLOCK)
					&& player.getLocation().subtract(0, 2, 0).getBlock().getType().equals(Material.DIAMOND_BLOCK)) {
				player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 10.0F, 1.0F);
				player.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 10.0F, 1.0F);
				player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(1));
				new BukkitRunnable() {
					
					@Override
					public void run() {
						player.setVelocity(player.getLocation().getDirection().multiply(2).setY(0.5));
						player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 10.0F, 1.0F);
						player.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 10.0F, 1.0F);
						new BukkitRunnable() {
							
							@Override
							public void run() {
								player.setVelocity(player.getLocation().getDirection().multiply(3).setY(0.5));
								player.playSound(player.getLocation(), Sound.BAT_TAKEOFF, 10.0F, 1.0F);
								player.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 10.0F, 1.0F);
								
							}
						}.runTaskLater(SkyPvP.getInstance(), 20);
						
					}
				}.runTaskLater(SkyPvP.getInstance(), 20);
			}
		}else{
			if (player.getLocation().subtract(0, 1, 0).getBlock().getType().equals(Material.GOLD_BLOCK)
					&& player.getLocation().subtract(0, 2, 0).getBlock().getType().equals(Material.DIAMOND_BLOCK)) {
				player.playSound(player.getLocation(), Sound.NOTE_BASS, 10.0F, 1.0F);
			}
		}
	}

}
