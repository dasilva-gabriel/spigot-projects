package eu.pvpwarcraft.warfight.managers.queues;

import org.bukkit.Bukkit;

import eu.pvpwarcraft.warfight.managers.kits.Kits;

public class Queue {
	
	private String name;
	private Kits kit;
	private boolean isRanked;
	private String player_in_queue;
	
	public Queue(String name, Kits kit, boolean isRanked) {
		super();
		this.name = name;
		this.kit = kit;
		this.isRanked = isRanked;
		this.player_in_queue = "none";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Kits getKit() {
		return kit;
	}

	public void setKit(Kits kit) {
		this.kit = kit;
	}

	public boolean isRanked() {
		return isRanked;
	}

	public void setRanked(boolean isRanked) {
		this.isRanked = isRanked;
	}

	public String getPlayer_in_queue() {
		if(Bukkit.getPlayer(player_in_queue) == null || !Bukkit.getPlayer(player_in_queue).isOnline()){
			player_in_queue = "none";
		}
		return player_in_queue;
	}

	public void setPlayer_in_queue(String player_in_queue) {
		this.player_in_queue = player_in_queue;
	}
	
	

}
