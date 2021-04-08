package eu.pvpwarcraft.meetup.managers.types;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import eu.pvpwarcraft.meetup.utils.ItemBuilder;

public enum Types {

	AUCUNS(true, "Aucuns", new ItemBuilder(Material.BARRIER)), BOW_LESS(false, "Sans arcs",
			new ItemBuilder(Material.BOW).setLore(new String[] { "", "§7Prix: §a15 gemmes" })), ROD_LESS(false,
					"Sans Canne à Pêche",
					new ItemBuilder(Material.FISHING_ROD).setLore(
							new String[] { "", "§7Prix: §a15 gemmes" })), FLAME_LESS(false, "Sans Feu", new ItemBuilder(
									Material.FIREBALL).setLore(new String[] { "", "§7Prix: §a15 gemmes" })), ANVIL_LESS(
											false, "Sans Enclume",
											new ItemBuilder(Material.ANVIL).setLore(
													new String[] { "", "§7Prix: §a15 gemmes" })), APPLE_LESS(false,
															"Sans Pommes", new ItemBuilder(Material.APPLE).setLore(
																	new String[] { "", "§7Prix: §a15 gemmes" }));

	private static Types currentTypes;
	private boolean isDefault;
	private String name;
	private ItemBuilder item;
	public static List<Types> types = new ArrayList<Types>();

	public static void init() {
		types.clear();
		types.add(AUCUNS);
		types.add(BOW_LESS);
		types.add(ROD_LESS);
		types.add(FLAME_LESS);
		types.add(ANVIL_LESS);
	}

	public static boolean isDefault() {
		return currentTypes.isDefault;
	}

	public static String getName(Types type) {
		return type.name;
	}

	public static ItemBuilder getItem(Types type) {
		return type.item;
	}

	public static boolean isCurrentTypes(Types Types) {
		return currentTypes == Types;
	}

	public static void setCurrentTypes(Types Types) {
		currentTypes = Types;
	}

	public static Types getCurrentTypes() {
		return currentTypes;
	}

	private Types(boolean isDefault, String name, ItemBuilder item) {
		this.isDefault = isDefault;
		this.name = name;
		this.item = item;
	}
}
