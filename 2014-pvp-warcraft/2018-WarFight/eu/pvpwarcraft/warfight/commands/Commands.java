package eu.pvpwarcraft.warfight.commands;

import eu.pvpwarcraft.warfight.WarFight;

public class Commands {

	public static void init() {
		
		WarFight.getInstance().getCommand("wararena").setExecutor(new WarArenaCommand());
		
	}

}
