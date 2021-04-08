package fr.lowtix.palatraining.guis;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.palatraining.utils.Gui;
import fr.lowtix.palatraining.utils.ItemBuilder;

public class SoloUnRankedQueueGui extends Gui {

	public static HashMap<String, SoloUnRankedQueueGui> guis = new HashMap<String, SoloUnRankedQueueGui>();
	public static int[] caseSlots = {0, 1, 7, 8, 9, 17, 27, 35, 36, 37, 43, 44};
	
	@Override
	public void onOpen() {
		guis.put(getPlayer().getName(), this);
	}
	
	@Override
	public void onClose() {
		guis.remove(getPlayer().getName());
	}
	
	public SoloUnRankedQueueGui(Player player) {
		super("§8PalaTraining - Classé", 5, player);
	}

	@Override
	public void drawScreen() {
		
		for(int slots : caseSlots) {
			setItem(slots, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(4).build());
		}
		
		setItem(4, new ItemBuilder(Material.COMPASS).setName("§6Sélectionnez la file").setLore(new String[] {
				"§7Choisisez un kit et vous rentrerez",
				"§7dans la file d'attente "
		}).build());
		
		
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
	
		
	}

}
