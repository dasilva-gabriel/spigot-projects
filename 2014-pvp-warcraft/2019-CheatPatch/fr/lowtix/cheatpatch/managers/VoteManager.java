package fr.lowtix.cheatpatch.managers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import fr.lowtix.cheatpatch.CheatPatch;
import fr.lowtix.cheatpatch.schedulers.VoteScheduler;
import fr.lowtix.cheatpatch.sql.VoteSQL;

public class VoteManager {
	
	private int vote;
	private int max;
	private String last_vote;
	
	public VoteManager(int vote, int max, String last_vote) {
		this.vote = vote;
		this.max = max;
		this.last_vote = last_vote;
		VoteSQL.load(this);
		new VoteScheduler().runTaskTimerAsynchronously(CheatPatch.getInstance(), 0, 600);
	}

	public void addVote(int i) {
		this.vote += i;
		
		if(vote >= max) {
			
			vote = 0;
			
			for(Player all : Bukkit.getOnlinePlayers()) {
				all.playSound(all.getLocation(), Sound.NOTE_PLING, 10.0F, 10.0F);
			}
			
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage("§8» §dVous avez atteint les §b§l"+max+" §bvotes §d! Merci a vous !");
			Bukkit.broadcastMessage("§8» §eVous gagnez tous une §fclefs BigBox§e.");
			Bukkit.broadcastMessage(" ");
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate giveallkey bigbox 1");
			
		}
		
	}

	public int getVote() {
		return vote;
	}

	public String getLast_vote() {
		return last_vote;
	}
	
	public int getMax() {
		return max;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setLast_vote(String last_vote) {
		this.last_vote = last_vote;
	}
	
	
}
