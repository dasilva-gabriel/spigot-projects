package eu.pvpwarcraft.warfight.managers.kits;

public enum Kits {
	
	BUILDUHC("BuildUHC", true, true),
	ARCHER("Archer", true, true),
	NODEBUFF("NoDebuff", true, true),
	DEBUFF("Debuff", true, true),
	GAPPLE("GApple", true, true),
	COMBO("Combo", true, true),
	AXEPVP("AxePvP", true, true);
	
	private String name;
	private boolean ranked;
	private boolean unranked;
	
	private Kits(String name, boolean ranked, boolean unranked) {
		this.name = name;
		this.ranked = ranked;
		this.unranked = unranked;
	}

	public String getName() {
		return name;
	}

	public boolean isRanked() {
		return ranked;
	}

	public boolean isUnranked() {
		return unranked;
	}
	
	public static Kits getKitsFromString(String s) {
		Kits result = null;
		for(Kits kit : values()){
			if(kit.getName().equalsIgnoreCase(s)){
				result = kit;
			}
		}
		return result;
	}
	
	

}
