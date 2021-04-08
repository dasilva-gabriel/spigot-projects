package fr.lowtix.warbox.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.players.WarPlayer;
import fr.lowtix.warbox.utils.Gui;
import fr.lowtix.warbox.utils.ItemBuilder;

public class ExtraGui extends Gui {

	private WarPlayer wp;
	private int it;
	
	public ExtraGui(Player player, int it) {
		super("§8Boutique - Extra", 6, player);
		
		wp = WarBox.getInstance().getUser(player);
		this.it = it;
	}

	@Override
	public void drawScreen() {
		
		for(int i = 0; i <= 53; i++) {
			setItem(i, new ItemBuilder(Material.AIR).build());
			if((i >=0 && i<=8) || (i%9 == 0) || ((i+1) %9 == 0) || (i >= 44 && i <= 53)) {
				setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(10).build());
			}
		}
		
		setItem(49, new ItemBuilder(Material.ARROW).setName("§cFermer").build());
		
		// LIST
		setItem(10, new ItemBuilder(Material.POTION).setDamage(8193).setName("§7Catégorie: §6Soins buvable").setLore(new String[] {
				"§7Cliquez pour défiler les articles disponibles.",
		}).build());
		setItem(11, new ItemBuilder(Material.POTION).setDamage(16389).setName("§7Catégorie: §6Soins splash").setLore(new String[] {
				"§7Cliquez pour défiler les articles disponibles.",
		}).build());
		setItem(12, new ItemBuilder(Material.POTION).setDamage(8194).setName("§7Catégorie: §6Rapidité").setLore(new String[] {
				"§7Cliquez pour défiler les articles disponibles.",
		}).build());
		setItem(13, new ItemBuilder(Material.GOLDEN_APPLE).setName("§7Catégorie: §6Autres").setLore(new String[] {
				"§7Cliquez pour défiler les articles disponibles.",
		}).build());
		
		if(it > 0) {
			setItem((9+it), new ItemBuilder(Material.STAINED_GLASS).setDamage(5).setName("§aCatégorie sélectionnée").setLore(new String[] {
					"§7Vous avez sélectionné cette catégorie",
			}).build());
		}
		
		if(it == 1) {
			setItem(19, new ItemBuilder(Material.POTION).setDamage(8193).setName("§7Potion buvable §dRegeneration I (45s)").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e15 coins",
			}).build());
			setItem(28, new ItemBuilder(Material.POTION).setDamage(8257).setName("§7Potion buvable §dRegeneration I (2min)").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e40 coins",
			}).build());
			setItem(37, new ItemBuilder(Material.POTION).setDamage(8225).setName("§7Potion buvable §dRegeneration II (22s)").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e45 coins",
			}).build());
		} else if(it == 2) {
			setItem(20, new ItemBuilder(Material.POTION).setDamage(16389).setName("§7Potion splash §cSoin I").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e3 coins",
			}).build());
			setItem(29, new ItemBuilder(Material.POTION).setDamage(16421).setName("§7Potion splash §cSoin II").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e6 coins",
			}).build());
		} else if(it == 3) {
			setItem(21, new ItemBuilder(Material.POTION).setDamage(8194).setName("§7Potion buvable §bRapidité I (3min)").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e8 coins",
			}).build());
			setItem(30, new ItemBuilder(Material.POTION).setDamage(8258).setName("§7Potion buvable §bRapidité I (8min)").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e15 coins",
			}).build());
			setItem(39, new ItemBuilder(Material.POTION).setDamage(8226).setName("§7Potion buvable §bRapidité II (1min et 30s)").setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e20 coins",
			}).build());
		} else if(it == 4) {
			setItem(22, new ItemBuilder(Material.GOLDEN_APPLE).setName("§7x2 §ePomme en Or").setAmount(2).setLore(new String[] {
					"",
					"§4⚠ §cSi vous mourrez vous perdrez cet item!",
					"§2✔ §aPrix: §e9 coins",
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
				case 19: if(needMoney(15)) { itg = new ItemBuilder(Material.POTION).setDamage(8193).build(); } break;
				case 28: if(needMoney(40)) { itg = new ItemBuilder(Material.POTION).setDamage(8257).build(); } break;
				case 37: if(needMoney(45)) { itg = new ItemBuilder(Material.POTION).setDamage(8225).build(); } break;
				
				case 20: if(needMoney(3)) { itg = new ItemBuilder(Material.POTION).setDamage(16389).build(); } break;
				case 29: if(needMoney(6)) { itg = new ItemBuilder(Material.POTION).setDamage(16421).build(); } break;
				
				case 21: if(needMoney(8)) { itg = new ItemBuilder(Material.POTION).setDamage(8194).build(); } break;
				case 30: if(needMoney(15)) { itg = new ItemBuilder(Material.POTION).setDamage(8258).build(); } break;
				case 39: if(needMoney(20)) { itg = new ItemBuilder(Material.POTION).setDamage(8226).build(); } break;
				
				case 22: if(needMoney(9)) { itg = new ItemBuilder(Material.GOLDEN_APPLE).setName("§7x2 §ePomme en Or").setAmount(2).build(); } break;
			
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
