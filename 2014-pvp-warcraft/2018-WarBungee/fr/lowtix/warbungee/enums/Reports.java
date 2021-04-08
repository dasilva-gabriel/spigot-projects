package fr.lowtix.warbungee.enums;

public enum Reports {
	
	REP_CHEAT_COMBAT("Triche (Combat)"),
	REP_CHEAT_MOVEMENT("Triche (Movement)"),
	REP_GAMEPLAY_SABOTAGE("Anti-Jeu"),
	REP_CAMP("Camp"),
	REP_CHAT_SPAM("Chat (Spam/Flood)"),
	REP_CHAT_INJURES("Chat (Langage/Insultes)"),
	REP_CHAT_ADD("Chat (Insultes)");
	
	private String reason;

	private Reports(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}
	
}
