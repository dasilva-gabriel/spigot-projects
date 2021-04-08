package eu.pvpwarcraft.warfightapi.commands;

import eu.pvpwarcraft.warfightapi.WarFightAPI;

public class Commands {

	public static void init() {
		WarFightAPI.getInstance().getCommand("creative").setExecutor(new CreativeCommand());
		WarFightAPI.getInstance().getCommand("survival").setExecutor(new SurvivalCommand());
		WarFightAPI.getInstance().getCommand("spectator").setExecutor(new SpectatorCommand());
		WarFightAPI.getInstance().getCommand("adventure").setExecutor(new AdventureCommand());
		WarFightAPI.getInstance().getCommand("tphere").setExecutor(new TPHereCommand());
		WarFightAPI.getInstance().getCommand("mod").setExecutor(new ModCommand());
		
		WarFightAPI.getInstance().getCommand("fly").setExecutor(new FlyCommand());
		WarFightAPI.getInstance().getCommand("tp").setExecutor(new TPCommand());
		WarFightAPI.getInstance().getCommand("playerinfo").setExecutor(new PlayerInfoCommand());
		WarFightAPI.getInstance().getCommand("annonce").setExecutor(new AnnonceCommand());
	}

}
