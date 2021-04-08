package fr.lowtix.warlobby.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.lowtix.warlobby.WarLobby;
import fr.lowtix.warlobby.utils.ParticlesUtils;
import net.minecraft.server.v1_8_R3.EnumParticle;

public class MoveListener implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if(event.getTo().getY() <= 10) {
			event.getPlayer().teleport(new Location(event.getFrom().getWorld(), event.getFrom().getX(), 78, event.getFrom().getX()));
		} else if(event.getTo().getY() <= 80) {
			Player player = event.getPlayer();
			
			player.setVelocity(player.getLocation().getDirection().multiply(0.1).setY(2));
			player.playSound(player.getLocation(), Sound.GHAST_FIREBALL, 10.0F, 1.0F);
			
			Bukkit.getScheduler().runTaskLaterAsynchronously(WarLobby.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					
					ParticlesUtils.circle(player.getLocation(), 10, EnumParticle.FLAME);
					ParticlesUtils.circle(player.getLocation(), 7, EnumParticle.FIREWORKS_SPARK);
				}
			}, 1);
			
		} else if(event.getFrom().subtract(0, 1, 0).getBlock().getType().equals(Material.SLIME_BLOCK)) {
			
			Player player = event.getPlayer();
			
			
			if(event.getFrom().getBlockY() < event.getTo().getBlockY()) {
				
				player.setVelocity(player.getLocation().getDirection().multiply(3).setY(0.8));
				player.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 10.0F, 10.0F);
				
				Bukkit.getScheduler().runTaskLaterAsynchronously(WarLobby.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						
						ParticlesUtils.circle(player.getLocation(), 5, EnumParticle.EXPLOSION_NORMAL);
						ParticlesUtils.circle(player.getLocation(), 7, EnumParticle.VILLAGER_HAPPY);
					}
				}, 1);
			} else {
				player.playSound(player.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
			}
			
		}
	}
	
}
