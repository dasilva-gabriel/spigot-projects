package eu.pvpwarcraft.warfight.managers.guis.players;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.warfight.managers.guis.Gui;
import eu.pvpwarcraft.warfight.utils.ItemBuilder;

public class MenuGui extends Gui {
	
	public MenuGui(Player player) {
		super("§6§lW§e§lar§6§lF§e§light", 4, player);
	}

	@Override
	public void drawScreen() {
		
		setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(1).setName("  ").build());
		setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(1).setName("  ").build());
		setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(1).setName("  ").build());
		setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(1).setName("  ").build());
		setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(1).setName("  ").build());
		setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(1).setName("  ").build());
		setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(1).setName("  ").build());
		setItem(27, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(1).setName("  ").build());
		setItem(29, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(30, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(1).setName("  ").build());
		setItem(31, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(32, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(1).setName("  ").build());
		setItem(33, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(34, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(1).setName("  ").build());
		setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		
		setItem(12, new ItemBuilder(Material.DIAMOND_SWORD).setName("§8▪ §cRanked").build());
		setItem(14, new ItemBuilder(Material.IRON_SWORD).setName("§8▪ §eUnranked").build());
		setItem(20, new ItemBuilder(Material.DIAMOND_BOOTS).setName("§8▪ §6Jump").build());
		setItem(24, new ItemBuilder(Material.REDSTONE_COMPARATOR).setName("§8▪ §bParamètres").build());
		setItem(22, new ItemBuilder(Material.BOOK).setName("§8▪ §dInformations").build());
		

	}

	@Override
	public void onClick(int position, ItemStack item, ClickType click) {

		

	}

}
