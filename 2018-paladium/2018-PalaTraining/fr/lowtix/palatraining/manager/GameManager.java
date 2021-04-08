package fr.lowtix.palatraining.manager;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.lowtix.palaarenasmanager.managers.Arena;
import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.enums.UnrankedQueueType;
import fr.lowtix.palatraining.handlers.Game;

public class GameManager {
	
	private HashMap<String, Game> games = new HashMap<String, Game>();
	
	private int gameIDindex = 0;
	
	
	public boolean createOneVsOne(Player first, Player second, boolean eloChange, UnrankedQueueType unrankedType) {
		
		HashMap<UUID, Boolean> teamA = new HashMap<UUID, Boolean>();
		HashMap<UUID, Boolean> teamB = new HashMap<UUID, Boolean>();
		
		teamA.put(first.getUniqueId(), true);
		teamB.put(second.getUniqueId(), true);
		
		if(PalaTraining.getInstance().getGameArenasManager().getReadyArenas(false).size() == 0) {
			return false;
		} else {
			createGame(teamA, teamB, PalaTraining.getInstance().getGameArenasManager().getArenaForGame(false), eloChange, unrankedType);
			return true;
		}
	}
	
	public void createTeamVsTeam(List<Player> first, List<Player> second) {
		
		//HashMap<UUID, Boolean> teamA = new HashMap<UUID, Boolean>();
		//HashMap<UUID, Boolean> teamB = new HashMap<UUID, Boolean>();
		
	}
	
	public void createGame(HashMap<UUID, Boolean> teamA, HashMap<UUID, Boolean> teamB, Arena arena, boolean eloChange, UnrankedQueueType unrankedType) {
		gameIDindex++;
		Game game = new Game("GAME-"+gameIDindex, teamA, teamB, arena, eloChange, unrankedType);
		games.put(game.getId(), game);
	}
	
	public HashMap<String, Game> getGames() {
		return games;
	}

	public void deleteGame(Game game) {
		
		PalaTraining.getInstance().getGameArenasManager().readyArenaPostGame(game.getArena());
		games.remove(game.getId());
		
	}
}