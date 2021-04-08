package eu.pvpwarcraft.thetowers.handler;

public enum Step {
	
	LOBBY(true, "En Attente"), IN_GAME(false, "En Jeu"), POST_GAME(false, "Fin");

	private static Step currentStep;
	private boolean canJoin;
	private String motd;
	
	private Step(boolean canJoin, String motd) {
		this.canJoin = canJoin;
		this.motd = motd;
	}

	public static boolean canJoin() {
		return currentStep.canJoin;
	}
	public String getMOTD() {
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

	public void setCanJoin(boolean canJoin) {
		this.canJoin = canJoin;
	}
}