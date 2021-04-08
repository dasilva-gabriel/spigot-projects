package fr.lowtix.warcore.enums;

public enum SymbolTab {
	
	HEARTH_1("❤"), 
	HEARTH_2("❥"),
	POINT("•"),
	WRONG("✖"),
	TREFLE("✣"),
	CURSOR("✥"),
	STAR_1("✦"),
	STAR_2("✩"),
	STAR_3("✫"),
	STAR_4("✮"),
	FLOWER("❃"),
	FIGHTER("⚔"),
	SIGMA("Σ"),
	NOTE("♬");
	
	private String displayName;

	private SymbolTab(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public static SymbolTab getSymbolFromName(String s) {
		SymbolTab result = HEARTH_1;
		for(SymbolTab rank : SymbolTab.values()) {
			if(s.equalsIgnoreCase(rank.name())) {
				result = rank;
			}
		}
		return result;
	}

}
