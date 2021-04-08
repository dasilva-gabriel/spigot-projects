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
import fr.lowtix.wartracker.utils.ItemBuilder;

public class KeysGui extends Gui {

	private WarPlayer wPlayer;
	
	public KeysGui(Player player) {
		super("§8» §eBoutique de Kits", 4, player);
		this.wPlayer = WarCore.getInstance().getUser(player);
	}
	
	@Override
	public void drawScreen() {

		setItem(11, new ItemBuilder(Material.TRIPWIRE_HOOK).setAmount(1).setName("§eClé Chance §8[§6✯§7✯✯§8]").setLore(new String[] {
				"§7Prix: §e200 ⛃"
		}).build());
		setItem(13, new ItemBuilder(Material.TRIPWIRE_HOOK).setAmount(2).setName("§eClé Chance §8[§6✯✯§7✯§8]").setLore(new String[] {
				"§7Prix: §e1600 ⛃"
		}).build());
		setItem(15, new ItemBuilder(Material.TRIPWIRE_HOOK).setAmount(3).setName("§eClé Chance §8[§6✯✯✯§8]").setLore(new String[] {
				"§7Prix: §e3000 ⛃"
		}).build());

	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		
		if(position == 11) {
			if(wPlayer.hasCoins(200)) {
				player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
				player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7la clé §eChance 1§7. §8(§c-200⛃§8)");
				wPlayer.removeCoins(200);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate givekey "+player.getName()+" Chance1 1");
			} else {
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
				player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de coins.");
			}
		} else if(position == 13) {
			if(wPlayer.hasCoins(1600)) {
				player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
				player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7la clé §eChance 2§7. §8(§c-200⛃§8)");
				wPlayer.removeCoins(1600);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate givekey "+player.getName()+" Chance2 1");
			} else {
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
				player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de coins.");
			}
		} else if(position == 15) {
			if(wPlayer.hasCoins(3000)) {
				player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
				player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7la clé §eChance 3§7. §8(§c-200⛃§8)");
				wPlayer.removeCoins(3000);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate givekey "+player.getName()+" Chance3 1");
			} else {
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
				player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de coins.");
			}
		}
		
	}

}
