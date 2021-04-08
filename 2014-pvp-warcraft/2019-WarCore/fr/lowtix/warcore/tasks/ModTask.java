package fr.lowtix.warcore.tasks;

import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;
import fr.lowtix.warcore.utils.PlayerUtils;

public class ModTask extends BukkitRunnable{

	@Override
	public void run() {
		for(WarPlayer all : WarCore.getInstance().getUsers().values()) {
			
			ProtectedRegion region = getProtectedRegion(all.getPlayer().getLocation());
			
			if(region != null && region.getId().equalsIgnoreCase("spawn")) {
				if(!all.getPlayer().getAllowFlight()) {
					all.getPlayer().sendMessage("§8[§e§l!§8] §7§oVotre fly est désormais §aactivé §7§ocar vous êtes dans une zone protégée.");
					all.getPlayer().setAllowFlight(true);
				}
			} else if(!all.getModPlayer().isMod() && !all.getRank().isHigher(Ranks.MOD)) {
				
				if(all.getPlayer().getAllowFlight() || all.getPlayer().isFlying()) {
					all.getPlayer().setFlying(false);
					all.getPlayer().setAllowFlight(false);
				}
				
				
			}	
			
			if(all.afkToKick) {
				all.getPlayer().kickPlayer("§cVous êtes inactif...");
			}			
			
			if(all.getModPlayer().isMod()) {
				if(all.getModPlayer().followTrack && all.getModPlayer().trackIsOnline()) {
					Player tracked = all.getModPlayer().getTrackedPlayer();
					
					if(all.getPlayer().getWorld().getName().equalsIgnoreCase(tracked.getWorld().getName())) {
						double dist = tracked.getLocation().distance(all.getPlayer().getLocation());
						
						if(dist > 25) {
							all.getPlayer().teleport(tracked.getLocation());
						}
					} else {
						all.getPlayer().teleport(tracked.getLocation());
					}
					
				}
				
				if(!WarCore.getInstance().essentials.getVanishedPlayers().contains(all.getPlayer().getName()))	{
					PlayerUtils.sendActionBar(all.getPlayer(), "§c§lVous n'êtes pas vanish");
				}
				
				if(all.getModPlayer().isTrack()) {
					Player track = all.getModPlayer().getTrackedPlayer();
					
					if(track != null && track.isOnline() && all.getPlayer() != null && all.getPlayer().isOnline() && track.canSee(all.getPlayer())) {
						PlayerUtils.sendActionBar(all.getPlayer(), "§8[§4§l!§8] §cVotre Track vous vois §8[§4§l!§8]");
					}
				}
			}
			
		}
		
	}

	public ProtectedRegion getProtectedRegion(Location loc)
	  {
	    ApplicableRegionSet regions = WarCore.getInstance().worldguard.getRegionManager(loc.getWorld()).getApplicableRegions(loc);
	    ProtectedRegion region = null;
	    Iterator<ProtectedRegion> regionsIterator = regions.iterator();
	    while (regionsIterator.hasNext()) {
	      if ((region == null) || (region.getPriority() < ((ProtectedRegion)regionsIterator.next()).getPriority())) {
	        region = (ProtectedRegion)regionsIterator.next();
	      }
	    }
	    return region;
	  }
	
}
