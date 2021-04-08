package fr.cmuagab.sheepwars.handler;

public enum Step {
    LOBBY(true), IN_GAME(false), POST_GAME(false);

    private static Step currentStep;

    public static boolean canJoin() {
        return Step.currentStep.canJoin;
    }

    public static String getMOTD() {
        return Step.currentStep.motd;
    }

    public static boolean isStep(final Step step) {
        return Step.currentStep == step;
    }

    public static void setCurrentStep(final Step currentStep) {
        Step.currentStep = currentStep;
    }

    private boolean canJoin;

    private String motd;

    Step(final boolean canJoin) {
        this.canJoin = canJoin;
        this.motd = this.name().toLowerCase();
    }
}
