package fr.lowtix.cheatpatch.managers;

import java.util.LinkedList;

import fr.lowtix.cheatpatch.CheatPatch;
import fr.lowtix.cheatpatch.schedulers.TopScheduler;
import fr.lowtix.cheatpatch.sql.CheatSQL;

public class CheatTopManager {
	
	private LinkedList<CheatTopResult> top = new LinkedList<CheatTopResult>();

	public CheatTopManager() {
		new TopScheduler().runTaskTimerAsynchronously(CheatPatch.getInstance(), 0, 600);
		reloadTop();
	}
	
	public LinkedList<CheatTopResult> getTop() {
		return top;
	}
	
	public void reloadTop() {
		top = CheatSQL.getTop();
		
		for(int i = 0; i < 10; i++) {
			top.addLast(new CheatTopResult("Personne", -1));
		}
	}


}
