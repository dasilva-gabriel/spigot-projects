package eu.pvpwarcraft.warfight.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadBuilder extends ItemBuilder {

	private String name;
	
	public HeadBuilder(String name) {
		super(Material.SKULL_ITEM);
		super.setDamage(3);
		this.name = name;
	}
	
	@Override
	public ItemStack build(){
		ItemStack head = super.build();
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		meta.setOwner(name);
		head.setItemMeta(meta);
		return head;
	}
}
