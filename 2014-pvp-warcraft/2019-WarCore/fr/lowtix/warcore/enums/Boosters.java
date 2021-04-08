package fr.lowtix.warcore.enums;

public enum Boosters {
	
	JOBS_COINS(2, "Booster de Coins dans les Jobs"), 
	JOBS_POINTS(1, "Booster de Points dans les Jobs"),
	KILLS(1, "Booster de gains des Kills");
	
	private int cost;
	private String display;

	private Boosters(int cost, String display) {
		this.cost = cost;
		this.display = display;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

}
