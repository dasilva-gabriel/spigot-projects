package fr.lowtix.warcore.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Levels;
import fr.lowtix.warcore.enums.Ranks;
import fr.lowtix.warcore.utils.Gui;
import fr.lowtix.wartracker.utils.ItemBuilder;

public class ArmorGui extends Gui{

	private WarPlayer wPlayer;
	private Player target;
	
	public ArmorGui(Player player, Player target) {
		super("§8» §eArmure", 4, player);
		this.wPlayer = WarCore.getInstance().getUser(player);
	}

	@Override
	public void drawScreen() {
		
		if(!wPlayer.getLevel().isHigher(Levels.LEVEL_10) && !wPlayer.getRank().isHigher(Ranks.MOD)) {
			close();
			return;
		}
		
		setItem(8, new ItemBuilder(Material.SLIME_BALL).setName("§aActualiser").build());
		
		setItem(4, new ItemBuilder(Material.BARRIER).setName("§cPas de casque").build());
		setItem(13, new ItemBuilder(Material.BARRIER).setName("§cPas de plastron").build());
		setItem(22, new ItemBuilder(Material.BARRIER).setName("§cPas de pantalon").build());
		setItem(31, new ItemBuilder(Material.BARRIER).setName("§cPas de bottes").build());
		
		if(target.getInventory().getHelmet() != null && !target.getInventory().getHelmet().getType().equals(Material.AIR)) {
			setItem(4, target.getInventory().getHelmet());
		}
		if(target.getInventory().getChestplate() != null && !target.getInventory().getChestplate().getType().equals(Material.AIR)) {
			setItem(13, target.getInventory().getChestplate());
		}
		if(target.getInventory().getLeggings() != null && !target.getInventory().getLeggings().getType().equals(Material.AIR)) {
			setItem(22, target.getInventory().getLeggings());
		}
		if(target.getInventory().getBoots() != null && !target.getInventory().getBoots().getType().equals(Material.AIR)) {
			setItem(31, target.getInventory().getBoots());
		}
		
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		if(position == 8) {
			drawScreen();
		}
	}

}
