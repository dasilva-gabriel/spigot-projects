package fr.lowtix.warbox.guis;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warbox.utils.Gui;
import fr.lowtix.warbox.utils.ItemBuilder;

public class CratesGui extends Gui {

	public CratesGui(Player player) {
		super("§8Maitre des Caisses", 6, player);
		
	}

	@Override
	public void drawScreen() {
		
		for(int i = 0; i <= 53; i++) {
			setItem(i, new ItemBuilder(Material.AIR).build());
			if((i >=0 && i<=8) || (i%9 == 0) || ((i+1) %9 == 0) || (i >= 44 && i <= 53)) {
				setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(4).build());
			}
		}
		
		setItem(49, new ItemBuilder(Material.ARROW).setName("§cFermer").build());
		
		setItem(22, new ItemBuilder(Material.TRIPWIRE_HOOK).setName("§cRéclamer ses clefs").setLore(new String[] {
				"§7Vous avez gagné des clefs ?",
				"§7Cliquez ici pour les obtenir"
		}).build());
		
		setItem(29, new ItemBuilder(Material.BOW).setName("§5✩ §dCaisse Particules §5✩").addEnchant(Enchantment.ARROW_DAMAGE, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
				"§7La caisse a particules est une caisse qui vous",
				"§7permet de débloquer des particules pour votre arc.",
				"§7Vous pourrez les activer a partir du menu de profil!",
				"§7",
				"§8[§6§l?§8] §eDisponible en jeu depuis la caisse §3✩ §bSuper Caisse §3✩",
				"§2✔ §aAchetable en Boutique"
		}).build());
		
		setItem(31, new ItemBuilder(Material.DIAMOND_BLOCK).setName("§3✩ §bSuper Caisse §3✩").addEnchant(Enchantment.ARROW_DAMAGE, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
				"§7La super caisse vous permet de gagner en des coins,",
				"§7expérience ou encore des clefs pour les autres caisses.",
				"§7",
				"§8[§6§l?§8] §eGagnez en une gratuitement avec l'arrivage journalier",
				"§edisponible dans le menu profil.",
				"§7",
				"§2✔ §aObtenable en votant"
		}).build());
		
		setItem(33, new ItemBuilder(Material.NAME_TAG).setName("§6✩ §eCaisse Tags §6✩").addEnchant(Enchantment.ARROW_DAMAGE, 1).addFlag(ItemFlag.HIDE_ENCHANTS).setLore(new String[] {
				"§7La caisse tags est une caisse qui vous permet",
				"§7de débloquer des tags visible devant votre pseudo.",
				"§7Vous pourrez les activer a partir du menu de profil!",
				"§7",
				"§8[§6§l?§8] §eDisponible en jeu depuis la caisse §3✩ §bSuper Caisse §3✩",
				"§2✔ §aAchetable en Boutique"
		}).build());

	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		
		if(position == 49) {
			close();
		}
		
		if(position == 29 || position == 33) {
			getPlayer().sendMessage("§f▎ §cCette objet est achetable en boutique uniquement...");
			getPlayer().sendMessage("§f▎ §eNotre boutique: §6www.http://pvp-warcraft.net/shop");
		}
		
		if(position == 31) {
			getPlayer().sendMessage("§f▎ §cPour obtenir cet objet, votez pour le serveur!");
			getPlayer().sendMessage("§f▎ §eVoter pour le serveur: §6www.http://pvp-warcraft.net/vote");
		}
		
		if(position == 22) {
			close();
			getPlayer().performCommand("cr claim");
		}
		
	}

}
