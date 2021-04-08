package fr.lowtix.warcore.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.utils.Gui;
import fr.lowtix.warcore.utils.GuiManager;
import fr.lowtix.wartracker.utils.ItemBuilder;

public class RandomTPGui extends Gui{

	private WarPlayer wPlayer;
	
	public RandomTPGui(Player player) {
		super("§8» §eTéléportation aléatoire", 4, player);
		this.wPlayer = WarCore.getInstance().getUser(player);
	}

	@Override
	public void drawScreen() {
		
		if(!player.getWorld().getName().equalsIgnoreCase("world")) {
			close();
			GuiManager.openGui(new MenuGui(player));
			player.sendMessage("§8[§e§l!§8] §cFonctionnalité désactivée ici.");
			return;
		}
		
		setItem(13, new ItemBuilder(Material.SIGN).setName("§e§nAchat de la téléporation aléatoire").setLore(new String[] {
				"§7Prix: §e500 ⛃ §7ou §f1'500 ✴",
				"§7Rayon: §d3500",
		}).build());

		setItem(21, new ItemBuilder(Material.GOLD_INGOT).setName("§7Acheter la téléportation a §e50 ⛃").build());
		setItem(23, new ItemBuilder(Material.QUARTZ).setName("§7Acheter la téléportation a §f250 ✴").build());

	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		if(position == 21) {
			if(wPlayer.hasCoins(50)) {
				player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
				player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7la §etéléportation aléatoire§7. §8(§c-50⛃§8)");
				wPlayer.removeCoins(50);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rtp "+player.getName());
			}else {
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
				player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de coins.");
			}
		}
		if(position == 23) {
			if(wPlayer.hasPoints(250)) {
				player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
				player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7la §etéléportation aléatoire§7. §8(§c-250 ✴§8)");
				wPlayer.removePoints(250);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rtp "+player.getName());
			}else {
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
				player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de points.");
			}
		}
	}
	
	

}
