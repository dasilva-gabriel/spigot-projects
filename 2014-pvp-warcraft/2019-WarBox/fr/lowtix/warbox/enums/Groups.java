package fr.lowtix.warbox.enums;

import org.bukkit.ChatColor;

public enum Groups {

	ADMINISTRATOR(7, "§8[§4A§8]", "Administrateur", ChatColor.DARK_RED, ChatColor.WHITE),
	RESPONSABLE(6, "§8[§cR§8]", "Responsable", ChatColor.RED, ChatColor.WHITE),
	MODERATOR(5, "§8[§6M§8]", "Modérateur", ChatColor.GOLD, ChatColor.WHITE),
	STAFF(4, "§8[§2S§8]", "Staff", ChatColor.DARK_GREEN, ChatColor.WHITE),
	FAMOUS(3, "§d[Famous]", "Famous", ChatColor.LIGHT_PURPLE, ChatColor.WHITE),
	VIP_PLUS(2, "§a[VIP§6+§a]", "VIP+", ChatColor.GREEN, ChatColor.WHITE),
	VIP(1, "§e[VIP]", "VIP", ChatColor.YELLOW, ChatColor.WHITE),
	DEFAULT(0, "§7", "default", ChatColor.GRAY, ChatColor.GRAY);

	private int power;
	private String prefix, displayName;
	private ChatColor groupColor, chatColor;

	private Groups(int power, String prefix, String displayName, ChatColor groupColor, ChatColor chatColor) {
		this.power = power;
		this.prefix = prefix;
		this.displayName = displayName;
		this.groupColor = groupColor;
		this.chatColor = chatColor;
	}

	public int getPower() {
		return power;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getDisplayName() {
		return displayName;
	}

	public ChatColor getGroupColor() {
		return groupColor;
	}

	public ChatColor getChatColor() {
		return chatColor;
	}
	
	public boolean isHigherOrEquals(Groups rank) {
		return getPower() >= rank.getPower();
	}
	
	public static Groups getRankFromName(String s) {
		for(Groups rank : Groups.values()) {
			if(s.equalsIgnoreCase(rank.displayName)) {
				return rank;
			}
		}
		return null;
	}
	
}
