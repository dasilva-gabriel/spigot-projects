package eu.pvpwarcraft.meetup.handler;

public enum Step {
	
	PRE_LOBBY(false, "RESTART", "CREATION DE LA MAP"), LOBBY(true, "WAIT", "EN ATTENTE DE JOUEURS"), PRE_GAME(false, "GAME", "COOLDOWN LANCE"), IN_GAME(false, "GAME", "EN JEU"), POST_GAME(false, "RESTART", "LE SERVEUR REDEMARRE");

	private static Step currentStep;
	private boolean canJoin;
	private String motd;
	private String name;

	public static boolean canJoin() {
		return currentStep.canJoin;
	}
	
	public static String getName() {
		return currentStep.name;
	}

	public static String getMOTD() {
		return currentStep.motd;
	}

	public static boolean isStep(Step step) {
		return currentStep == step;
	}

	public static void setCurrentStep(Step step) {
		currentStep = step;
	}

	public static Step getCurrentStep() {
		return currentStep;
	}

	private Step(boolean canJoin, String motd, String name) {
		this.canJoin = canJoin;
		this.motd = motd;
		this.name = name;
	}

	public void setCanJoin(boolean canJoin) {
		this.canJoin = canJoin;
	}
}