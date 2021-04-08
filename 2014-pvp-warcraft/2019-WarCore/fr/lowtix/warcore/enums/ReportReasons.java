package fr.lowtix.warcore.enums;

import org.bukkit.Material;

public enum ReportReasons {

	FLY("Fly", Material.FEATHER), 
	SPEED("Speed", Material.CHAINMAIL_BOOTS), 
	FORCEFIELD("ForceField", Material.IRON_SWORD), 
	AIMBOT("AimBot", Material.COMPASS), 
	RAPIDBOW("RapidBow", Material.BOW), 
	REACH("Reach", Material.BLAZE_ROD), 
	NOKNOCKBACK("NoKnockback", Material.FISHING_ROD), 
	TP_KILL("TP-Kill", Material.ENDER_PEARL), 
	AUTRE("TP-Kill", Material.BANNER);
	
	private String display;
	private Material icon;

	private ReportReasons(String display, Material icon) {
		this.display = display;
		this.icon = icon;
	}

	public String getDisplay() {
		return display;
	}

	public Material getIcon() {
		return icon;
	}

}
