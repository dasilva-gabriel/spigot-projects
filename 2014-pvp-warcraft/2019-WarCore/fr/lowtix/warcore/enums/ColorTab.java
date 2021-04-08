package fr.lowtix.warcore.enums;

import org.bukkit.ChatColor;

public enum ColorTab {
	
	WHITE(7, "§fBlanc", ChatColor.WHITE), 
	RED(1, "§cRouge", ChatColor.RED), 
	DARK_GREEN(2, "§2Olive", ChatColor.DARK_GREEN),
	GREEN(10, "§aVert", ChatColor.GREEN),
	BLUE(4, "§1Bleu", ChatColor.BLUE),
	PURPLE(5, "§5Violet", ChatColor.DARK_PURPLE),
	PINK(9, "§dRose", ChatColor.LIGHT_PURPLE),
	CYAN(6, "§3Cyan", ChatColor.DARK_AQUA);
	
	private int data;
	private String displayName;
	private ChatColor color;

	private ColorTab(int data, String displayName, ChatColor color) {
		this.data = data;
		this.displayName = displayName;
		this.color = color;
	}

	public int getData() {
		return data;
	}

	public String getDisplayName() {
		return displayName;
	}

	public ChatColor getColor() {
		return color;
	}
	
	public static ColorTab getColorFromName(String s) {
		ColorTab result = null;
		for(ColorTab rank : ColorTab.values()) {
			if(s.equalsIgnoreCase(rank.name())) {
				result = rank;
			}
		}
		return result;
	}

}
