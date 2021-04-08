package fr.lowtix.palatraining.manager;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.handlers.GamePlayer;
import fr.lowtix.palatraining.handlers.Team;
import fr.lowtix.palatraining.handlers.TeamRequest;

public class TeamsManager {

	private HashMap<UUID, Team> teams = new HashMap<UUID, Team>();
	private HashMap<UUID, TeamRequest> requests = new HashMap<UUID, TeamRequest>();
	
	public HashMap<UUID, Team> getTeams() {
		return teams;
	}
	
	public HashMap<UUID, TeamRequest> getRequests() {
		return requests;
	}
	
	public void createTeam(Player player) {
		GamePlayer gPlayer = PalaTraining.getInstance().getGamePlayer(player);
		
		if(gPlayer.isInGame()) {
			player.sendMessage("§dTeam §f» §cVous êtes actuellement en jeu, vous ne pouvez pas faire cela.");
			return;
		}
		
		if(gPlayer.isInTeam()) {
			player.sendMessage("§dTeam §f» §cVous êtes actuellement en team, vous ne pouvez pas faire cela.");
			return;
		}
		
		if(PalaTraining.getInstance().getQueueManager().getQueue(player) != null) {
			player.sendMessage("§3Recherche §f» §cVous ne pouvez pas faire cela quand vous êtes en recherche d'adversaire.");
			return;
		}
		
		Team team = new Team(player);
		teams.put(player.getUniqueId(), team);
		player.sendMessage("§dTeam §f» §aVotre team a été créée.");
		
		gPlayer.setInTeam(true);
		gPlayer.setTeam(team);
	}
	
	public void sendRequest(Player sender, Player receiver) {
		
		if(requests.containsKey(receiver.getUniqueId())) {
			
			sender.sendMessage("§dTeam §f» §cLe joueur a déjà une demande en cours...");
			return;
			
		} else {
			
			GamePlayer gPlayer = PalaTraining.getInstance().getGamePlayer(sender);
			
			if(!gPlayer.isInTeam()) {
				sender.sendMessage("§dTeam §f» §cVous n'êtes pas dans une team.");
				return;
			}
			
			Team team = gPlayer.getTeam();
			
			if(team.getLeader().equals(sender)) {
				sender.sendMessage("§dTeam §f» §cVous n'êtes pas le chef de cette Team.");
				return;
			}
			
			if(!team.isNeedInvitation()) {
				sender.sendMessage("§dTeam §f» §cVotre Team est en mode §f§nLibre§r§c, les joueurs peuvent rejoindre sans invitation.");
				return;
			}
			
			GamePlayer gTarget = PalaTraining.getInstance().getGamePlayer(receiver);
			
			if(gTarget.isInTeam()) {
				sender.sendMessage("§dTeam §f» §cCette personne est déjà dans une Team.");
				return;
			}
			
			if(team.getMembers().size() >= team.getMaxPlayers()) {
				sender.sendMessage("§dTeam §f» §cVous ne pouvez pas avoir plus de §f"+team.getMaxPlayers()+" joueurs §cdans votre équipe!");
				return;
			}
			
			TeamRequest req = new TeamRequest(receiver.getUniqueId(), sender.getUniqueId(), System.currentTimeMillis());
			requests.put(receiver.getUniqueId(), req);
			
			sender.sendMessage("§dTeam §f» §7Vous avez envoyé une requête a §f" + receiver.getName() + "§7.");
			
			receiver.sendMessage("§f§l| §e§lRequête de Tem reçue:");
			receiver.sendMessage("§f§l| §7Vous avez reçu une requête de Team de §6" + sender.getName() + "§7.");
			receiver.sendMessage("§f§l| §7Vous avez §a15s §7pour accepter avec §e/team accept§7.");
			
		}
		
	}
	
	public void joinTeamFromCommand(Player player, Player target) {
		GamePlayer gameTarget = PalaTraining.getInstance().getGamePlayer(target);
		
		if(!gameTarget.isInTeam()) {
			player.sendMessage("§dTeam §f» §cCe joueur n'est pas dans une Team.");
			return;
		}
		
		Team team = gameTarget.getTeam();
		
		if(requests.containsKey(player.getUniqueId())) {
			if(requests.get(player.getUniqueId()).getSender().equals(target.getUniqueId())) {
				joinTeam(player, team, true);
			} else {
				joinTeam(player, team, false);
			}
		} else {
			joinTeam(player, team, false);
		}
		
	}
	
	public void joinTeam(Player player, Team team, boolean hasAnInvite) {
		
		GamePlayer gTarget = PalaTraining.getInstance().getGamePlayer(player);
		
		if(gTarget.isInTeam() || gTarget.isInGame()) {
			player.sendMessage("§dTeam §f» §cVous ne pouvez pas rejoindre une Team maintenant.");
			return;
		}
		
		if(!hasAnInvite && team.isNeedInvitation()) {
			player.sendMessage("§dTeam §f» §7Il vous faut une invitation pour rejoindre cette Team!");
			return;
		}
		
		if(team.getMembers().size() >= team.getMaxPlayers()) {
			player.sendMessage("§dTeam §f» §cLa Team a déjà §f"+team.getMaxPlayers()+" joueurs §cvous ne pouvez donc pas la rejoindre.");
			return;
		}
		
		team.addMember(player.getUniqueId());
		
	}
	
	public void leaveTeam(Player player) {
		
		GamePlayer gTarget = PalaTraining.getInstance().getGamePlayer(player);
		
		if(!gTarget.isInTeam()) {
			player.sendMessage("§dTeam §f» §cVous n'êtes dans aucune team.");
			return;
		}
		
		Team team = gTarget.getTeam();
		team.removeMember(player.getUniqueId());
	}
	
	public void kickFromTeam(Player player) {
		
		GamePlayer gTarget = PalaTraining.getInstance().getGamePlayer(player);
		
		if(!gTarget.isInTeam()) {
			player.sendMessage("§dTeam §f» §cVous n'êtes dans aucune team.");
			return;
		}
		
		Team team = gTarget.getTeam();
		team.removeMember(player.getUniqueId());
	}
	
	
}
