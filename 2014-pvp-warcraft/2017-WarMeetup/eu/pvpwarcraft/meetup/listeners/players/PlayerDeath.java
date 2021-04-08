package eu.pvpwarcraft.meetup.listeners.players;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.meetup.Meetup;
import eu.pvpwarcraft.meetup.managers.PlayersInGameManager;
import eu.pvpwarcraft.meetup.managers.PlayersManager;
import eu.pvpwarcraft.meetup.managers.WinManager;
import eu.pvpwarcraft.warfightapi.WarFightAPI;
import eu.pvpwarcraft.warfightapi.managers.User;

public class PlayerDeath implements Listener {
	
	int i = 0;
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event){
		Player killed = event.getEntity();
		if(!PlayersInGameManager.isInGame(killed)){
			killed.setGameMode(GameMode.SPECTATOR);
			killed.teleport(killed.getWorld().getSpawnLocation());
			return;
		}
		Location loc = killed.getLocation();
		loc.getWorld().strikeLightningEffect(loc);
		loc.getWorld().strikeLightningEffect(loc);
		if (event.getEntity().getKiller() != null){
			event.setDeathMessage("§6WarFight §8» §a"+killed.getName()+" §7est mort par la main de §c"+killed.getKiller().getName()+"§7.");
			User userKilled = WarFightAPI.getInstance().getUsers().get(killed.getName());
			User userKiller = WarFightAPI.getInstance().getUsers().get(killed.getKiller().getName());
			userKilled.getStats().setMeetUpDeaths(Integer.valueOf(userKilled.getStats().getMeetUpDeaths() + 1));
			userKiller.getStats().setMeetUpKills(Integer.valueOf(userKiller.getStats().getMeetUpKills() + 1));
			killed.getKiller().sendMessage("§2Gemmes §8» §7Vous recevez §e+3 gemme §8(§bMort de "+killed.getName()+"§8)");
			killed.sendMessage("§2Gemmes §8» §7Vous recevez §e+1 gemme §8(§bAvoir participé§8)");
			PlayersManager.addKill(killed.getKiller());
			userKilled.addGemmes(3);
			userKiller.addGemmes(1);
			userKilled.save();
			userKiller.save();
		}else{
			event.setDeathMessage("§6WarFight §8» §a"+killed.getName()+" §7est mort§7.");
		}
		killed.setGameMode(GameMode.SPECTATOR);
		killed.teleport(killed.getWorld().getSpawnLocation());
		killed.setGameMode(GameMode.SPECTATOR);
		PlayersInGameManager.removePlayer(killed);
		killed.teleport(killed.getWorld().getSpawnLocation());
		WinManager.CheckWin();
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(killed.isDead()){
					killed.spigot().respawn();
				}
				
			}
		}.runTaskLater(Meetup.getInstance(), 7);
	}

}
