package fr.lowtix.warbox.enums;

public enum Tags {
	
	NONE("", TagsType.LIMITED),
	EZ1("§f#§6EZ", TagsType.BOX),
	EZ2("§f#§eEZ", TagsType.BOX),
	EZ3("§f#§cEZ", TagsType.BOX),
	EZ4("§f#§aEZ", TagsType.BOX),
	EZ5("§f#§2EZ", TagsType.BOX),
	EZ6("§f#§4§lEZ", TagsType.BOX),
	EASY("§f#§eE§6a§es§6y", TagsType.BOX),
	DROPPED("§f#§0Dropped", TagsType.BOX),
	WARCRAFT1("§f#§6§lW§ea§6r§ec§6r§ea§6f§et", TagsType.BOX),
	WARCRAFT2("§f#§d§lW§5a§dr§5c§dr§5a§df§5t", TagsType.BOX),
	PRO1("§f#§cPRO", TagsType.BOX),
	PRO2("§f#§dPRO", TagsType.BOX),
	PRO3("§f#§5PRO", TagsType.BOX),
	PRO4("§f#§6PRO", TagsType.BOX),
	NOSTUFF("§f#§3NoStuff", TagsType.BOX),
	
	REKT("§c#§4REKT", TagsType.BOX),
	NOPOT("§3#§bNoPot", TagsType.BOX),
	LS("§6#§eL", TagsType.BOX),
	FLOWER("§5#§dFlower", TagsType.BOX),
	
	GOD("§e#§6§lGOD", TagsType.PRIVATE);
	
	private String display;
	private TagsType type;

	private Tags(String display, TagsType type) {
		this.display = display;
		this.type = type;
	}

	public String getDisplay() {
		return display;
	}

	public TagsType getType() {
		return type;
	}
	
	public static Tags getTagFromName(String name) {
		Tags result = NONE;
		for(Tags rank : Tags.values()) {
			if(name.equalsIgnoreCase(rank.name())) {
				result = rank;
			}
		}
		return result;
	}
	
}
