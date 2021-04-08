package fr.lowtix.warcore.enums;

import org.bukkit.ChatColor;

public enum Ranks {
	
	ADMIN(9, "§c§lADMIN", "Administrateur", "§c§lA.", ChatColor.RED, ChatColor.WHITE),
	MOD_P(8, "§6§lMOD+", "Modérateur+", "§6§lM+.", ChatColor.GOLD, ChatColor.WHITE),
	MOD(7, "§6§lMOD", "Modérateur", "§6§lM.", ChatColor.GOLD, ChatColor.WHITE),
	TRIAL_MOD(7, "§6§lT-MOD", "Modérateur-Test", "§6§lT-M.", ChatColor.GOLD, ChatColor.WHITE),
	SUPPORT(6, "§a§lSUPPORT", "Support", "§a§lS.", ChatColor.GREEN, ChatColor.WHITE),
	STAFF(5, "§2§lSTAFF", "Staff", "§2§lS.", ChatColor.DARK_GREEN, ChatColor.WHITE),
	FAMOUS_P(4, "§5§lFAMOUS", "Famous+", "§5§lF.", ChatColor.DARK_PURPLE, ChatColor.WHITE),
	FAMOUS(4, "§5§lFAMOUS", "Famous", "§5§lF.", ChatColor.DARK_PURPLE, ChatColor.WHITE),
	FRIEND(4, "§b§lAMI", "Ami", "§b§lA.", ChatColor.AQUA, ChatColor.WHITE),
	DIAMOND(3, "§3§lDIAMOND", "Diamond", "§3§lD.", ChatColor.DARK_AQUA, ChatColor.WHITE),
	GOLD(2, "§e§lGOLD", "Gold", "§e§lG.", ChatColor.YELLOW, ChatColor.WHITE),
	SILVER(1, "§f§lSILVER", "Silver", "§f§lS.", ChatColor.WHITE, ChatColor.WHITE),
	DEFAULT(0, "§7", "default", "§7", ChatColor.GRAY, ChatColor.GRAY);
	
	private int place;
	private String prefix, displayName, shortCut;
	private ChatColor prefixColor, chatColor;

	private Ranks(int place, String prefix, String displayName, String shortCut, ChatColor prefixColor,
			ChatColor chatColor) {
		this.place = place;
		this.prefix = prefix;
		this.displayName = displayName;
		this.shortCut = shortCut;
		this.prefixColor = prefixColor;
		this.chatColor = chatColor;
	}

	public int getPlace() {
		return place;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getShortCut() {
		return shortCut;
	}

	public ChatColor getPrefixColor() {
		return prefixColor;
	}

	public ChatColor getChatColor() {
		return chatColor;
	}

	public boolean isHigher(Ranks rank) {
		return getPlace() >= rank.getPlace();
	}
	
	public static Ranks getRankFromName(String s) {
		Ranks result = null;
		for(Ranks rank : Ranks.values()) {
			if(s.equalsIgnoreCase(rank.name())) {
				result = rank;
			}
		}
		return result;
	}

}
