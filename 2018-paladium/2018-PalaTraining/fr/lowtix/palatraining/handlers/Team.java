package fr.lowtix.palatraining.handlers;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Team {

	private Player leader;
	private ArrayList<UUID> members;
	private boolean inGame;
	private Game game;
	private boolean needInvitation;
	private int maxPlayers;
	
	public Team(Player player) {
		super();
		this.leader = player;
		this.members = new ArrayList<UUID>();
		this.inGame = false;
		this.game = null;
		this.needInvitation = true;
		
		this.maxPlayers = 6;
		if(player.hasPermission("palatraining.team.maxplayers")) {
			this.maxPlayers = 12;
		}
		
		addMember(player.getUniqueId());
	}

	public Player getLeader() {
		return leader;
	}

	public void setLeader(Player leader) {
		this.leader = leader;
	}

	public ArrayList<UUID> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<UUID> members) {
		this.members = members;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public boolean isNeedInvitation() {
		return needInvitation;
	}

	public void setNeedInvitation(boolean needInvitation) {
		this.needInvitation = needInvitation;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	
	/*
	 * FONCTIONS AUX
	 */

	public boolean containsMember(UUID uuid) {
		return getMembers().contains(uuid);
	}
	
	public void addMember(UUID uuid) {
		if(!containsMember(uuid)) {
			getMembers().add(uuid);
			broadcastTeam("§8[§2§l+§8] §a"+Bukkit.getPlayer(uuid)+" §7§oa rejoint la Team !");
		}
	}
	
	public void removeMember(UUID uuid) {
		if(!containsMember(uuid)) {
			if(Bukkit.getPlayer(uuid) != null && Bukkit.getPlayer(uuid).isOnline()) {
				broadcastTeam("§8[§4§l-§8] §c"+Bukkit.getPlayer(uuid)+" §7§oa quitté la Team !");
			}
			getMembers().remove(uuid);
		}
	}
	
	public void kickMember(UUID uuid) {
		if(!containsMember(uuid)) {
			if(Bukkit.getPlayer(uuid) != null && Bukkit.getPlayer(uuid).isOnline()) {
				broadcastTeam("§8[§4§l-§8] §c"+Bukkit.getPlayer(uuid)+" §7§oa quitté la Team ! (Kick)");
			}
			getMembers().remove(uuid);
		}
	}
	
	public boolean isLeader(Player player) {
		return getLeader().equals(player);
	}
	
	public void broadcastTeam(String message) {
		for(UUID uuid : getMembers()) {
			if(Bukkit.getPlayer(uuid) != null && Bukkit.getPlayer(uuid).isOnline()) {
				Bukkit.getPlayer(uuid).sendMessage(message);
			}
		}
	}
	
}
