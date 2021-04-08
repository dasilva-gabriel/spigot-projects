package fr.lowtix.palatraining.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import fr.lowtix.palaarenasmanager.managers.Arena;
import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.enums.GameState;
import fr.lowtix.palatraining.enums.UnrankedQueueType;

public class Game {
	
	public static int game_duration = 607;
	
	private String id;
	private HashMap<UUID, Boolean> teamA;
	private HashMap<UUID, Boolean> teamB;
	private List<UUID> spectators;
	private int timer;
	private GameState state;
	private boolean gameActive;
	private Arena arena;
	private boolean eloChange;
	private UnrankedQueueType unrankedType;
	
	public Game(String id, HashMap<UUID, Boolean> teamA, HashMap<UUID, Boolean> teamB, Arena arena, boolean eloChange, UnrankedQueueType unrankedType) {
		this.id = id;
		this.teamA = teamA;
		this.teamB = teamB;
		this.spectators = new ArrayList<UUID>();
		this.timer = game_duration;
		this.state = GameState.DEBUG;
		this.gameActive = true;
		this.arena = arena;
		this.eloChange = eloChange;
		this.unrankedType = unrankedType;
		
		for(Player player : getPlayersFromUUIDs(getAllUUID())) {
			GamePlayer gplayer = PalaTraining.getInstance().getGamePlayer(player);
			gplayer.setGame(this);
			gplayer.setInGame(true);
			player.sendMessage("§6PalaTraining §f» §aVotre arène a été générée avec succès, place au combat!");
			
		}
		
		teleportToSpawn();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashMap<UUID, Boolean> getTeamA() {
		return teamA;
	}

	public void setTeamA(HashMap<UUID, Boolean> teamA) {
		this.teamA = teamA;
	}

	public HashMap<UUID, Boolean> getTeamB() {
		return teamB;
	}

	public void setTeamB(HashMap<UUID, Boolean> teamB) {
		this.teamB = teamB;
	}

	public List<UUID> getSpectators() {
		return spectators;
	}

	public void setSpectators(List<UUID> spectators) {
		this.spectators = spectators;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public static int getGame_duration() {
		return game_duration;
	}

	public static void setGame_duration(int game_duration) {
		Game.game_duration = game_duration;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public boolean isGameActive() {
		return gameActive;
	}

	public void setGameActive(boolean gameActive) {
		this.gameActive = gameActive;
	}
	
	public Arena getArena() {
		return arena;
	}
	
	public void setArena(Arena arena) {
		this.arena = arena;
	}
	
	
	public boolean isEloChange() {
		return eloChange;
	}

	public void setEloChange(boolean eloChange) {
		this.eloChange = eloChange;
	}

	public UnrankedQueueType getUnrankedType() {
		return unrankedType;
	}

	public void setUnrankedType(UnrankedQueueType unrankedType) {
		this.unrankedType = unrankedType;
	}

	/*
	 * FONCTIONS UTILITAIRES
	 */ 
	public boolean isPlayerOnline(UUID uuid) {
		return (Bukkit.getPlayer(uuid) != null && Bukkit.getPlayer(uuid).isOnline());
	}
	
	public Player getPlayer(UUID uuid) {
		return (Bukkit.getPlayer(uuid));
	}
	
	public List<Player> getPlayersFromUUIDs(Set<UUID> uuidTeam) {
		List<Player> team = new ArrayList<Player>();
		
		for(UUID uuid: uuidTeam) {
			if(isPlayerOnline(uuid)) {
				team.add(getPlayer(uuid));
			}
		}
		
		return team;
	}
	
	public Set<UUID> getAllUUID() {
		ArrayList<UUID> uuids = new ArrayList<UUID>();
		
		for(UUID teamA : getTeamA().keySet()) {
			uuids.add(teamA);
		}
		for(UUID teamB : getTeamB().keySet()) {
			uuids.add(teamB);
		}
		
		return new HashSet<UUID>(uuids);
	}
	
	
	public boolean isDead(HashMap<UUID, Boolean> team) {
		for(Boolean state : team.values()) {
			if(state) {
				return false;
			}
		}
		return true;
	}
	
	public void gameBroadcast(String message) {
		for(Player player : getPlayersFromUUIDs(getAllUUID())) {
			player.sendMessage(message);
		}
		for(UUID uuids : getSpectators()) {
			if(isPlayerOnline(uuids)) {
				getPlayer(uuids).sendMessage(message);
			}
		}
	}
	
	/*
	 * ACTIONS DANS LA PARTIE
	 */
	
	public void playerDeath(Player player, Player killer) {
		
		player.teleport(arena.getTeleportation());
		
		if(killer == null) {
			gameBroadcast("§f§l» §c"+player.getName()+" §7est mort.");
		} else {
			gameBroadcast("§f§l» §c"+player.getName()+" §7a été tué(e) par §b"+killer.getName());
		}
		
		if(teamA.containsKey(player.getUniqueId())) {
			teamA.put(player.getUniqueId(), false);
		} else if(teamB.containsKey(player.getUniqueId())) {
			teamB.put(player.getUniqueId(), false);
		}
		
		for(Player playersInGame : getPlayersFromUUIDs(getAllUUID())) {
			playersInGame.hidePlayer(player);
			player.showPlayer(playersInGame);
		}
		
		for(UUID uuids : getSpectators()) {
			if(isPlayerOnline(uuids)) {
				getPlayer(uuids).hidePlayer(player);
				player.hidePlayer(getPlayer(uuids));
			}
		}
		
		if(isDead(teamA)) {
			win(teamB);
		} else if(isDead(teamB)) {
			win(teamA);
		}
		
	}
	
	/*
	 * GESTION DU TEMPS
	 */
	public void tickTimer() {
		this.timer--;
		
		if(isGameActive()) {
			GameState state = GameState.getStateFromTimer(game_duration, timer);
			
			switch (state) {
			case COUNTDOWN:
				countdown();
				break;
			case GAME:
				game();
				break;
			case DEBUG:
				break;
			default:
				
				for(Player player : getPlayersFromUUIDs(getAllUUID())) {
					player.sendMessage("§f§l| §cLa partie rencontre un problème...");
					player.sendMessage("§f§l| §cContactez un administrateur ou un développeur au plus vite!");
				}
				
				break;
			}
			
		} else {
			
			if(timer > 23) {
				timer = 23;
			}
			
			int count = timer;
			
			if(count == 20) {
				gameBroadcast("§f§l» §bRetour au spawn dans §e10 seconde(s)§b...");
			}
			
			if(count == 13) {
				gameBroadcast("§f§l» §bRetour au spawn dans §e3 seconde(s)§b...");
			}
			
			if(count == 10) {
				for(Player player : getPlayersFromUUIDs(getAllUUID())) {
					PalaTraining.getInstance().getLobbyManager().teleportToLobby(player);
				}
				for(UUID uuids : getSpectators()) {
					if(isPlayerOnline(uuids)) {
						getPlayer(uuids).sendMessage("§6PalaTraining §f» §cPartie terminée. §7Mode Spectateur stoppé...");
						PalaTraining.getInstance().getLobbyManager().teleportToLobby(getPlayer(uuids));
					}
				}
			}
			
			if(count <= 1) {
				gameBroadcast("§6PalaTraining §f» §7Fin de partie!");
				gameKill();
			}
			
		}
	}

	private void gameKill() {
		gameBroadcast("§c§oLa partie a été détruite. Vous avez été renvoyé au spawn !");
		for(Player player : getPlayersFromUUIDs(getAllUUID())) {
			PalaTraining.getInstance().getLobbyManager().teleportToLobby(player);
		}
		for(UUID uuids : getSpectators()) {
			if(isPlayerOnline(uuids)) {
				getPlayer(uuids).sendMessage("§6PalaTraining §f» §cPartie introuvable. §7Mode Spectateur stoppé...");
				PalaTraining.getInstance().getLobbyManager().teleportToLobby(getPlayer(uuids));
			}
		}
		
		this.timer = 0;
		setGameActive(false);
		PalaTraining.getInstance().getGameManager().deleteGame(this);
	}

	private void teleportToSpawn() {
		for(Player players: getPlayersFromUUIDs(this.teamA.keySet())) {
			players.teleport(getArena().getSpawnA());
		}
		for(Player players: getPlayersFromUUIDs(this.teamB.keySet())) {
			players.teleport(getArena().getSpawnB());
		}
	}
	
	private void game() {
		gameBroadcast("Temps: " + this.timer + " ("+this.getState().name()+")");
	}

	private void countdown() {
		int count = 6 - ((game_duration-2) - timer);
		
		if(count%3 == 0 || count <= 1) {
			teleportToSpawn();
		}
		
		for(Player player : getPlayersFromUUIDs(getAllUUID())) {
			player.sendMessage("§f§l» §eDébut du combat dans §6"+count+" seconde(s)§e...");
			player.playSound(player.getLocation(), Sound.NOTE_BASS, 1.0F, (count*2));
		}
		
		if(count == 1) {
			for(Player player : getPlayersFromUUIDs(getAllUUID())) {
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
			}
		}
		
	}
	
	private void win(HashMap<UUID, Boolean> winners) {
		
		for(Player playersInGame : getPlayersFromUUIDs(getAllUUID())) {
			for(Player all : getPlayersFromUUIDs(getAllUUID())) {
				all.showPlayer(playersInGame);
			}
			
		}
		
		String broadcast = "§eBravo au(x) gagant(s) ";
		
		for(UUID uuid: winners.keySet()) {
			if(isPlayerOnline(uuid)) {
				broadcast += "§6" + getPlayer(uuid).getName() + " ";
			}
		}
		
		gameBroadcast("§f§r");
		gameBroadcast(PalaTraining.getInstance().centerText("§3§k||||§r §f§lCOMBAT TERMINE §3§k||||§r"));
		gameBroadcast(broadcast);
		gameBroadcast("§f§r");
		
		end();
	}
	
	private void end() {
		this.timer = 23;
		setGameActive(false);
	}
	
}
