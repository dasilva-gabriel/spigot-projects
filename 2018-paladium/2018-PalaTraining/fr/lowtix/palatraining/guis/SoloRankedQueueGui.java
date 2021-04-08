package fr.lowtix.palatraining.guis;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.palatraining.utils.Gui;
import fr.lowtix.palatraining.utils.ItemBuilder;

public class SoloRankedQueueGui extends Gui {

	public static HashMap<String, SoloRankedQueueGui> guis = new HashMap<String, SoloRankedQueueGui>();
	public static int[] caseSlots = {0, 1, 7, 8, 9, 17, 27, 35, 36, 37, 43, 44};
	
	@Override
	public void onOpen() {
		guis.put(getPlayer().getName(), this);
	}
	
	@Override
	public void onClose() {
		guis.remove(getPlayer().getName());
	}
	
	public SoloRankedQueueGui(Player player) {
		super("§8PalaTraining - 1vs1", 5, player);
	}

	@Override
	public void drawScreen() {
		
		for(int slots : caseSlots) {
			setItem(slots, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(9).build());
		}
		
		setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(9).build());
		
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
	
		
	}

}
