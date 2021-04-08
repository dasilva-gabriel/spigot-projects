package eu.pvpwarcraft.warfight;

import java.util.Map.Entry;

import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.warfight.games.GamesManager;
import eu.pvpwarcraft.warfight.utils.PlayerUtils;

public class ServerTask extends BukkitRunnable {
	
	private int secondsState = 0;
	private boolean loopIsSecond;

	@Override
	public void run() {
		
		loopIsSecond = false;
		
		if(secondsState < 10){
			secondsState++;
		}else{
			secondsState = 0;
			loopIsSecond = true;
			System.out.println("SECOND");
		}
		
		if(loopIsSecond){
			GamesManager.actionOnGames();
		}
		
		for(Entry<String, PlayerWrapper> entry : WarFight.getPlayers().entrySet()) {
			
			PlayerWrapper pws = entry.getValue();
			
			if(pws.getEnder_cooldown() > 0){
				pws.setEnder_cooldown((pws.getEnder_cooldown() - 0.1));
				PlayerUtils.sendActionBar(pws.getPlayer(), pws.getPearlBar());
			}
			
		}
		
		
	}

}
