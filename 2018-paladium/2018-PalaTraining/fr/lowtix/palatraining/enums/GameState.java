package fr.lowtix.palatraining.enums;

public enum GameState {
	
	DEBUG, COUNTDOWN, GAME;
	
	public static GameState getStateFromTimer(int max_timer, int timer) {
		
		if(timer < (max_timer-7)) {
			return GAME;
		} if(timer < (max_timer-2)) {
			return COUNTDOWN;
		} else if(timer >= (max_timer-2)) {
			return DEBUG;
		}
		
		return DEBUG;
		
	}

}
