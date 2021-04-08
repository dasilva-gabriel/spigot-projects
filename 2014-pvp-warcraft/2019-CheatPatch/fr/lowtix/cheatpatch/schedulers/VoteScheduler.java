package fr.lowtix.cheatpatch.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.cheatpatch.CheatPatch;
import fr.lowtix.cheatpatch.sql.VoteSQL;

public class VoteScheduler extends BukkitRunnable {

	private int count = 0;
	
	@Override
	public void run() {
		
		VoteSQL.save(CheatPatch.getInstance().getVoteManager().getVote(), CheatPatch.getInstance().getVoteManager().getLast_vote());
		
		if(count == 3) {
			count = 0;
			
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage("§8» §eVous avez fais §6" + CheatPatch.getInstance().getVoteManager().getVote() + "§f/§6300 §e!");
			Bukkit.broadcastMessage("§8» §7Aidez nous a être référencé en votant §dsur le site§7.");
			Bukkit.broadcastMessage(" ");
			
		}
		
	}

}
