package fr.lowtix.warbox.guis;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.players.WarPlayer;
import fr.lowtix.warbox.utils.Gui;
import fr.lowtix.warbox.utils.ItemBuilder;

public class ArmorGui extends Gui {

	private WarPlayer wp;
	private int it;
	
	public ArmorGui(Player player, int it) {
		super("§8Boutique - Equipements", 6, player);
		
		wp = WarBox.getInstance().getUser(player);
		this.it = it;
	}

	@Override
	public void drawScreen() {
		
		for(int i = 0; i <= 53; i++) {
			setItem(i, new ItemBuilder(Material.AIR).build());
			if((i >=0 && i<=8) || (i%9 == 0) || ((i+1) %9 == 0) || (i >= 44 && i <= 53)) {
				setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(6).build());
			}
		}
		
		setItem(49, new ItemBuilder(Material.ARROW).setName("§cFermer").build());
		
		// LIST
		setItem(10, new ItemBuilder(Material.IRON_HELMET).setName("§7Catégorie: §6Casque").setLore(new String[] {
				"§7Cliquez pour défiler les articles disponibles.",
		}).build());
		setItem(11, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§7Catégorie: §6Plastron").setLore(new String[] {
				"§7Cliquez pour défiler les articles disponibles.",
		}).build());
		setItem(12, new ItemBuilder(Material.IRON_LEGGINGS).setName("§7Catégorie: §6Jambières").setLore(new String[] {
				"§7Cliquez pour défiler les articles disponibles.",
		}).build());
		setItem(13, new ItemBuilder(Material.IRON_BOOTS).setName("§7Catégorie: §6Bottes").setLore(new String[] {
				"§7Cliquez pour défiler les articles disponibles.",
		}).build());
		setItem(14, new ItemBuilder(Material.DIAMOND_SWORD).setName("§7Catégorie: §6Epée").setLore(new String[] {
				"§7Cliquez pour défiler les articles disponibles.",
		}).build());
		setItem(15, new ItemBuilder(Material.BOW).setName("§7Catégorie: §6Arc").setLore(new String[] {
				"§7Cliquez pour défiler les articles disponibles.",
		}).build());
		setItem(16, new ItemBuilder(Material.FISHING_ROD).setName("§7Catégorie: §6Autres").setLore(new String[] {
				"§7Cliquez pour défiler les articles disponibles.",
		}).build());
		
		if(it > 0) {
			setItem((9+it), new ItemBuilder(Material.STAINED_GLASS).setDamage(5).setName("§aCatégorie sélectionnée").setLore(new String[] {
					"§7Vous avez sélectionné cette catégorie",
			}).build());
		}
		
		if(it == 1) {
			setItem(19, new ItemBuilder(Material.IRON_HELMET).setName("§7Casque en §fFer §9Protection 1").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e65 coins",
			}).build());
			setItem(28, new ItemBuilder(Material.IRON_HELMET).setName("§7Casque en §fFer §9Protection 2").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e80 coins",
			}).build());
			setItem(37, new ItemBuilder(Material.DIAMOND_HELMET).setName("§7Casque en §bDiamant").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e115 coins",
			}).build());
		} else if(it == 2) {
			setItem(20, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§7Plastron en §fFer §9Protection 1").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e80 coins",
			}).build());
			setItem(29, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§7Plastron en §fFer §9Protection 2").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e120 coins",
			}).build());
			setItem(38, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§7Plastron en §bDiamant").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e150 coins",
			}).build());
		} else if(it == 3) {
			setItem(21, new ItemBuilder(Material.IRON_LEGGINGS).setName("§7Jambières en §fFer §9Protection 1").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e80 coins",
			}).build());
			setItem(30, new ItemBuilder(Material.IRON_LEGGINGS).setName("§7Jambières en §fFer §9Protection 2").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e120 coins",
			}).build());
			setItem(39, new ItemBuilder(Material.DIAMOND_LEGGINGS).setName("§7Jambières en §bDiamant").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e150 coins",
			}).build());
		} else if(it == 4) {
			setItem(22, new ItemBuilder(Material.IRON_BOOTS).setName("§7Bottes en §fFer §9Protection 1").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e65 coins",
			}).build());
			setItem(31, new ItemBuilder(Material.IRON_BOOTS).setName("§7Bottes en §fFer §9Protection 2").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e80 coins",
			}).build());
			setItem(40, new ItemBuilder(Material.DIAMOND_BOOTS).setName("§7Bottes en §bDiamant").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e115 coins",
			}).build());
		} else if(it == 5) {
			setItem(23, new ItemBuilder(Material.IRON_SWORD).setName("§7Epée en §fFer §9Tranchant 1").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e70 coins",
			}).build());
			setItem(32, new ItemBuilder(Material.DIAMOND_SWORD).setName("§7Epée en §bDiamant").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e75 coins",
			}).build());
			setItem(41, new ItemBuilder(Material.DIAMOND_SWORD).setName("§7Epée en §bDiamant §9Tranchant 1").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e110 coins",
			}).build());
		} else if(it == 6) {
			setItem(24, new ItemBuilder(Material.BOW).setName("§7Arc").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e20 coins",
			}).build());
			setItem(33, new ItemBuilder(Material.BOW).setName("§7Arc §9Puissance 1").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e45 coins",
			}).build());
			setItem(42, new ItemBuilder(Material.ARROW).setName("§7x8 flèches").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e10 coins",
			}).build());
		} else if(it == 7) {
			setItem(25, new ItemBuilder(Material.FISHING_ROD).setName("§7Canne à pêche").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e25 coins",
			}).build());
		}
		

	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		
		if(position == 49) {
			close();
		}
		
		if(position >= 10 && position <= 16) {
			this.it = (position-9);
			drawScreen();
		} else {
			
			PlayerInventory pi = player.getInventory();
			ItemStack itg = null;
			
			switch (position) {
				case 19: if(needMoney(65)) { itg = new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(); } break;
				case 28: if(needMoney(80)) { itg = new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build(); } break;
				case 37: if(needMoney(115)) { itg = new ItemBuilder(Material.DIAMOND_HELMET).build(); } break;
				
				case 20: if(needMoney(80)) { itg = new ItemBuilder(Material.IRON_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(); } break;
				case 29: if(needMoney(120)) { itg = new ItemBuilder(Material.IRON_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build(); } break;
				case 38: if(needMoney(150)) { itg = new ItemBuilder(Material.DIAMOND_CHESTPLATE).build(); } break;
				
				case 21: if(needMoney(80)) { itg = new ItemBuilder(Material.IRON_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(); } break;
				case 30: if(needMoney(120)) { itg = new ItemBuilder(Material.IRON_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build(); } break;
				case 39: if(needMoney(150)) { itg = new ItemBuilder(Material.DIAMOND_LEGGINGS).build(); } break;
				
				case 22: if(needMoney(65)) { itg = new ItemBuilder(Material.IRON_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build(); } break;
				case 31: if(needMoney(80)) { itg = new ItemBuilder(Material.IRON_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build(); } break;
				case 40: if(needMoney(115)) {itg = new ItemBuilder(Material.DIAMOND_BOOTS).build(); } break;
				
				case 23: if(needMoney(20)) { itg = new ItemBuilder(Material.IRON_SWORD).build(); } break;
				case 32: if(needMoney(45)) { itg = new ItemBuilder(Material.DIAMOND_SWORD).build(); } break;
				case 41: if(needMoney(10)) { itg = new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).build(); } break;
				
				case 24: if(needMoney(20)) { itg = new ItemBuilder(Material.BOW).build(); } break;
				case 33: if(needMoney(45)) { itg = new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1).build(); } break;
				case 42: if(needMoney(10)) { itg = new ItemBuilder(Material.ARROW).setAmount(8).build(); } break;
				
				case 25: if(needMoney(25)) {itg = new ItemBuilder(Material.FISHING_ROD).setAmount(8).build(); } break;
			
			default:
				break;
			}
			
			if(itg != null) {
				pi.addItem(itg);
			}
			
		}
	}
	
	private boolean needMoney(int money) {
		if(wp.getCoins() < money) {
			player.sendMessage("§8[§3§lW§fInfo§8] §cVous n'avez pas les fonds nécéssaires...");
			return false;
		} else {
			player.sendMessage("§8[§3§lW§fInfo§8] §6Achat éffectué avec succès: §c-"+money+" coins§6.");
			wp.removeCoins(money);
		}
		return true;
	}

}
