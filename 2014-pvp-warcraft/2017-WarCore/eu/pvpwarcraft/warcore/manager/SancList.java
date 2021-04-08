package eu.pvpwarcraft.warcore.manager;

public enum SancList {

	CHAT_SPAM("Spam", 2, "30m"), CHAT_FLOOD("Flood", 2, "30m"), CHAT_INSULTES("Insulte(s)", 2, "1h"), CHAT_LANGUAGE("Language incorrect", 2, "30m"), CHAT_MAJUSCULES("Mauscules", 2, "30m"), 
	RULES_SPAWNKILL("SpawnKill", 1, "12h"), RULES_USEBUG("UseBug", 1, "12h"), RULES_GLITCH("Glitch", 1, "12h"), RULES_ANTIJEU("AntiJeu", 1, "2h"),
	RULES_RDF("Refus de Vérification", 1, "7d"), 
	CHEAT_KILLAURA("Cheat (KillAura)", 1, "7d"), CHEAT_AIMBOT("Cheat (AimBot)", 1, "7d"), CHEAT_NOKNOCKBACK("Cheat (NoKnockback)", 1, "7d"), 
	CHEAT_SPEEDHACK("Cheat (SpeedHack)", 1, "7d"), CHEAT_FLYHACK("Cheat (FlyHack)", 1, "7d"), CHEAT_FASTBOW("Cheat (FastBow)", 1, "7d"),
	CHEAT_FASTCONSUME("Cheat (FastConsume)", 1, "7d"), CHEAT_AUTOCLICK("Cheat (Autocliker)", 1, "7d"), CHEAT_OTHER("Cheat/Hack", 1, "7d"), 
	ADMIN_FRAUDE("Fraude boutique [Aucune demande de déban ne sera acceptée]", 1, "36mo"), ADMIN_INSOLENT("Quelle insolence !", 1, "12h");

	private String name;
	private int type;
	private String time;
	
	private SancList(String name, int type, String time){
		this.name = name;
		this.time = time;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public String getTime() {
		return time;
	}
	
}
