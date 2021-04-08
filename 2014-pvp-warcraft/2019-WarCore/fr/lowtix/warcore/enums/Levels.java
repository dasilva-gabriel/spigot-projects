package fr.lowtix.warcore.enums;

public enum Levels {
	
	LEVEL_0(0, 0, 0, "-", new String[] { "" }),
	LEVEL_1(1, 1000, 2000, "I", new String[] { " ", " §f- §b3 homes", " §f- §7Kit §eNiveau 1" }),
	LEVEL_2(2, 2500, 6000, "II", new String[] { " ", " §f- §b4 homes", " §f- §7Kit §eNiveau 2" }),
	LEVEL_3(3, 6000, 25000, "III", new String[] { " ", " §f- §b5 homes", " §f- §7Accès au §e/ec", " §f- §7Kit §eNiveau 3" }),
	LEVEL_4(4, 12500, 40000, "IV", new String[] { " ", " §f- §b6 homes", " §f- §7Kit §eNiveau 4" }),
	LEVEL_5(5, 15500, 100000, "V", new String[] { " ", " §f- §b7 homes", " §f- §7Kit §eNiveau 5", " §f- §7Accès au §e/feed §8(§f§oSous cooldown§8)" }),
	LEVEL_6(6, 32000, 500000, "VI", new String[] { " ", " §f- §b8 homes", " §f- §7Kit §eNiveau 6" }),
	LEVEL_7(7, 44000, 750000, "VII", new String[] { " ", " §f- §b9 homes", " §f- §7Kit §eNiveau 7" }),
	LEVEL_8(8, 88000, 1000000, "VIII", new String[] { " ", " §f- §b10 homes", " §f- §7Kit §eNiveau 8" }),
	LEVEL_9(9, 1000000, 2000000, "IX", new String[] { " ", " §f- §b15 homes", " §f- §7Kit §eNiveau 8", " §f- §7Accès au §e/heal §8(§f§oSous cooldown§8)", " §f- §7Accès au §e/hat" }),
	LEVEL_10(10, 1500000, 3500000, "X", new String[] { " ", " §f- §b20 homes", " §f- §7Kit §eNiveau 8", " §f- §7Accès au §e/heal §8(§f§oSous cooldown§8)", " §f- §7Accès au §e/hat", " §f- §7Accès au §e/back §8(§f§oAprès mort§8)" });
	
	private double id, pointsForUp, moneyForUp;
	private String displayName;
	private String[] desc;

	private Levels(double id, double pointsForUp, double moneyForUp, String displayName, String[] desc) {
		this.id = id;
		this.pointsForUp = pointsForUp;
		this.moneyForUp = moneyForUp;
		this.displayName = displayName;
		this.desc = desc;
	}

	public double getId() {
		return id;
	}

	public double getPointsForUp() {
		return pointsForUp;
	}

	public double getMoneyForUp() {
		return moneyForUp;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String[] getDesc() {
		return desc;
	}

	public static Levels getLevelFromName(String s) {
		Levels result = null;
		for(Levels rank : Levels.values()) {
			if(s.equalsIgnoreCase(rank.name())) {
				result = rank;
			}
		}
		return result;
	}
	
	public static Levels getLevel(double d) {
		Levels rank = Levels.LEVEL_0;
		
		for(Levels ranks : Levels.values()) {
			if(ranks.getId() == d) {
				rank = ranks;
			}
		}
		
		return rank;
	}
	
	public boolean isHigher(Levels rank) {
		return getId() >= rank.getId();
	}

}
