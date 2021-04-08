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

public class KitsGui extends Gui {

	private WarPlayer wp;
	
	public KitsGui(Player player) {
		super("§8PnJ - Kits", 6, player);
		
		wp = WarBox.getInstance().getUser(player);
	}

	@Override
	public void drawScreen() {
		
		for(int i = 0; i <= 53; i++) {
			if((i >=0 && i<=8) || (i%9 == 0) || ((i+1) %9 == 0) || (i >= 44 && i <= 53)) {
				setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(11).build());
			}
		}
		
		setItem(49, new ItemBuilder(Material.ARROW).setName("§cFermer").build());
		
		// KIT 1
		setItem(19, new ItemBuilder(Material.LEATHER_HELMET).setName("§6Kit n°§e1").setLore(new String[] {
				"",
				"§a§l+ §7Casque en §fFer",
				"§a§l+ §7Plastron en §8Maille",
				"§a§l+ §7Jambière en §8Maille",
				"§a§l+ §7Bottes en §8Maille",
				"§a§l+ §7Epée en §fFer",
				"§a§l+ §7x1 §ePomme en Or",
				"§7",
				"§4⚠ §cAttention votre inventaire va être clear!",
				"§2✔ §aPrix: §eGratuit",
		}).build());
		
		// KIT 2
		if(wp.getLevel() >= 12) {
			setItem(22, new ItemBuilder(Material.CHAINMAIL_HELMET).setName("§6Kit n°§e2").setLore(new String[] {
					"",
					"§a§l+ §7Casque en §fFer",
					"§a§l+ §7Plastron en §8Maille",
					"§a§l+ §7Jambière en §8Maille",
					"§a§l+ §7Bottes en §fFer",
					"§a§l+ §7Epée en §fFer",
					"§a§l+ §7x2 §ePomme en Or",
					"§7",
					"§4⚠ §cAttention votre inventaire va être clear!",
					"§2✔ §aPrix: §e40 coins",
			}).build());
		} else {
			setItem(22, new ItemBuilder(Material.CHAINMAIL_HELMET).setName("§6Kit n°§e2").setLore(new String[] {
					"",
					"§a§l+ §7Casque en §fFer",
					"§a§l+ §7Plastron en §8Maille",
					"§a§l+ §7Jambière en §8Maille",
					"§a§l+ §7Bottes en §fFer",
					"§a§l+ §7Epée en §fFer",
					"§a§l+ §7x2 §ePomme en Or",
					"§7",
					"§4✕ §cDisponible à partir du niveau " + WarBox.getInstance().getLevelManager().getPrefix(12),
			}).build());
		}
		
		// KIT 3
		if(wp.getLevel() >= 45) {
			setItem(25, new ItemBuilder(Material.GOLD_HELMET).setName("§6Kit n°§e3").setLore(new String[] {
					"",
					"§a§l+ §7Casque en §fFer",
					"§a§l+ §7Plastron en §8Maille",
					"§a§l+ §7Jambière en §fFer",
					"§a§l+ §7Bottes en §fFer",
					"§a§l+ §7Epée en §fFer",
					"§a§l+ §7x3 §ePomme en Or",
					"§7",
					"§4⚠ §cAttention votre inventaire va être clear!",
					"§2✔ §aPrix: §e50 coins",
			}).build());
		} else {
			setItem(25, new ItemBuilder(Material.GOLD_HELMET).setName("§6Kit n°§e3").setLore(new String[] {
					"",
					"§a§l+ §7Casque en §fFer",
					"§a§l+ §7Plastron en §8Maille",
					"§a§l+ §7Jambière en §fFer",
					"§a§l+ §7Bottes en §fFer",
					"§a§l+ §7Epée en §fFer",
					"§a§l+ §7x3 §ePomme en Or",
					"§7",
					"§4✕ §cDisponible à partir du niveau " + WarBox.getInstance().getLevelManager().getPrefix(45),
			}).build());
		}
		
		// KIT 4
		if(wp.getLevel() >= 100) {
			setItem(29, new ItemBuilder(Material.IRON_HELMET).setName("§6Kit n°§e4").setLore(new String[] {
					"",
					"§a§l+ §7Casque en §fFer",
					"§a§l+ §7Plastron en §fFer",
					"§a§l+ §7Jambière en §fFer",
					"§a§l+ §7Bottes en §fFer",
					"§a§l+ §7Epée en §fFer",
					"§a§l+ §7x5 §ePomme en Or",
					"§7",
					"§4⚠ §cAttention votre inventaire va être clear!",
					"§2✔ §aPrix: §e70 coins",
			}).build());
		} else {
			setItem(29, new ItemBuilder(Material.IRON_HELMET).setName("§6Kit n°§e4").setLore(new String[] {
					"",
					"§a§l+ §7Casque en §fFer",
					"§a§l+ §7Plastron en §fFer",
					"§a§l+ §7Jambière en §fFer",
					"§a§l+ §7Bottes en §fFer",
					"§a§l+ §7Epée en §fFer",
					"§a§l+ §7x5 §ePomme en Or",
					"§7",
					"§4✕ §cDisponible à partir du niveau " + WarBox.getInstance().getLevelManager().getPrefix(100),
			}).build());
		} 
		
		// KIT 5
		if (wp.getLevel() >= 200) {
			setItem(31, new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setName("§6Kit n°§e5").setLore(new String[] {
					"",
					"§a§l+ §7Casque en §fFer§9Protection I",
					"§a§l+ §7Plastron en §fFer",
					"§a§l+ §7Jambière en §fFer",
					"§a§l+ §7Bottes en §fFer §9Protection I",
					"§a§l+ §7Epée en §fFer §9Sharpness I",
					"§a§l+ §7x8 §ePomme en Or",
					"§7",
					"§4⚠ §cAttention votre inventaire va être clear!",
					"§2✔ §aPrix: §e125 coins",
			}).build());
		} else {
			setItem(31, new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setName("§6Kit n°§e5").setLore(new String[] {
					"", 
					"§a§l+ §7Casque en §fFer§9Protection I",
					"§a§l+ §7Plastron en §fFer",
					"§a§l+ §7Jambière en §fFer",
					"§a§l+ §7Bottes en §fFer §9Protection I",
					"§a§l+ §7Epée en §fFer §9Sharpness I",
					"§a§l+ §7x8 §ePomme en Or",
					"§7",
					"§4✕ §cDisponible à partir du niveau " + WarBox.getInstance().getLevelManager().getPrefix(200),
			}).build());
		}
		
		// KIT 6
		if (wp.getLevel() >= 300) {
			setItem(33, new ItemBuilder(Material.DIAMOND_HELMET).setName("§6Kit n°§e6").setLore(new String[] {
					"",
					"§a§l+ §7Casque en §fFer§9Protection I",
					"§a§l+ §7Plastron en §fFer",
					"§a§l+ §7Jambière en §bDiamant",
					"§a§l+ §7Bottes en §fFer §9Protection I",
					"§a§l+ §7Epée en §fFer §9Sharpness I",
					"§a§l+ §7x10 §ePomme en Or",
					"§7",
					"§4⚠ §cAttention votre inventaire va être clear!",
					"§2✔ §aPrix: §e300 coins",
			}).build());
		} else {
			setItem(33, new ItemBuilder(Material.DIAMOND_HELMET).setName("§6Kit n°§e6").setLore(new String[] {
					"", 
					"§a§l+ §7Casque en §fFer§9Protection I",
					"§a§l+ §7Plastron en §fFer",
					"§a§l+ §7Jambière en §bDiamant",
					"§a§l+ §7Bottes en §fFer §9Protection I",
					"§a§l+ §7Epée en §fFer §9Sharpness I",
					"§a§l+ §7x10 §ePomme en Or",
					"§7",
					"§4✕ §cDisponible à partir du niveau " + WarBox.getInstance().getLevelManager().getPrefix(300),
			}).build());
		}

	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		
		if(position == 49) {
			close();
		}
		
		PlayerInventory pi = player.getInventory();
		
		// KIT 1
		if(position == 19) {
			
			wp.clear();
			
			pi.setHelmet(new ItemBuilder(Material.IRON_HELMET).build());
			pi.setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).build());
			pi.setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).build());
			pi.setBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS).build());
			
			pi.setItem(0, new ItemBuilder(Material.IRON_SWORD).build());
			pi.setItem(1, new ItemBuilder(Material.GOLDEN_APPLE).setAmount(1).build());
		
		// KIT 2
		} else if(position == 22 && wp.getLevel() >= 12) {
			if(needMoney(40)) {
				
				wp.clear();
				
				pi.setHelmet(new ItemBuilder(Material.IRON_HELMET).build());
				pi.setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).build());
				pi.setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).build());
				pi.setBoots(new ItemBuilder(Material.IRON_BOOTS).build());
				
				pi.setItem(0, new ItemBuilder(Material.IRON_SWORD).build());
				pi.setItem(1, new ItemBuilder(Material.GOLDEN_APPLE).setAmount(2).build());
			}
		
		// KIT 3
		} else if(position == 26 && wp.getLevel() >= 45) {
			if(needMoney(50)) {
				
				wp.clear();
				
				pi.setHelmet(new ItemBuilder(Material.IRON_HELMET).build());
				pi.setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).build());
				pi.setLeggings(new ItemBuilder(Material.IRON_LEGGINGS).build());
				pi.setBoots(new ItemBuilder(Material.IRON_BOOTS).build());
				
				pi.setItem(0, new ItemBuilder(Material.IRON_SWORD).build());
				pi.setItem(1, new ItemBuilder(Material.GOLDEN_APPLE).setAmount(3).build());
			}
		
		// KIT 4
		} else if(position == 29 && wp.getLevel() >= 100) {
			if(needMoney(70)) {
				
				wp.clear();
				
				pi.setHelmet(new ItemBuilder(Material.IRON_HELMET).build());
				pi.setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE).build());
				pi.setLeggings(new ItemBuilder(Material.IRON_LEGGINGS).build());
				pi.setBoots(new ItemBuilder(Material.IRON_BOOTS).build());
				
				pi.setItem(0, new ItemBuilder(Material.IRON_SWORD).build());
				pi.setItem(1, new ItemBuilder(Material.GOLDEN_APPLE).setAmount(5).build());
			}
		
		// KIT 5
		} else if(position == 31 && wp.getLevel() >= 200) {
			if(needMoney(125)) {
				
				wp.clear();
				
				pi.setHelmet(new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
				pi.setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE).build());
				pi.setLeggings(new ItemBuilder(Material.IRON_LEGGINGS).build());
				pi.setBoots(new ItemBuilder(Material.IRON_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
				
				pi.setItem(0, new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).build());
				pi.setItem(1, new ItemBuilder(Material.GOLDEN_APPLE).setAmount(8).build());
			}
		
		// KIT 6
		} else if(position == 33 && wp.getLevel() >= 300) {
			if(needMoney(300)) {
				
				wp.clear();
				
				pi.setHelmet(new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
				pi.setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE).build());
				pi.setLeggings(new ItemBuilder(Material.DIAMOND_LEGGINGS).build());
				pi.setBoots(new ItemBuilder(Material.IRON_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
				
				pi.setItem(0, new ItemBuilder(Material.IRON_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).build());
				pi.setItem(1, new ItemBuilder(Material.GOLDEN_APPLE).setAmount(10).build());
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
