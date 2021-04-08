package fr.lowtix.warcore.guis;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.ColorTab;
import fr.lowtix.warcore.enums.Ranks;
import fr.lowtix.warcore.enums.SymbolTab;
import fr.lowtix.warcore.utils.Gui;
import fr.lowtix.wartracker.utils.ItemBuilder;

public class TabCustomGui extends Gui{

	private WarPlayer wPlayer;
	
	private HashMap<Integer, ColorTab> colorsPos = new HashMap<>();
	private HashMap<Integer, SymbolTab> symbolPos = new HashMap<>();
	
	public TabCustomGui(Player player) {
		super("§8» §eCustom. Tab et Nametag", 6, player);
		this.wPlayer = WarCore.getInstance().getUser(player);
	}

	@Override
	public void drawScreen() {
		
		setItem(4, new ItemBuilder(Material.SIGN).setName("§e§nInformation").setLore(new String[] {
				"§7La customisation de la tabulation et du nametag",
				"§7se fait ici et vous permet de customiser votre",
				"§7grade. Choisissez une couleur et un symbole",
				"§7et si ça ne fonctionne pas désactivez puis réactivez.",
				"§7Si ça ne fonctionne toujours pas déconnectez et reconnectez vous.",
				" ",
				"§f§oNote: §7§oLe nametag est le pseudo affiché au dessus",
				"§7§ode votre tête :D",
		}).build());
		
		if(wPlayer.getTabCustom().isActive()) {
			setItem(8, new ItemBuilder(Material.SLIME_BALL).setName("§7Etat de votre tag:").setLore(new String[] {
					"",
					" §8|§2»§8| §aActivé",
					" §8|§7»§8| §7§mDésactivé",
					"§7§oCliquez pour modifier"
			}).build());
		} else {
			setItem(8, new ItemBuilder(Material.SLIME_BALL).setName("§7Etat de votre tag:").setLore(new String[] {
					"",
					" §8|§7»§8| §7§mActivé",
					" §8|§4»§8| §cDésactivé",
					"§7§oCliquez pour modifier"
			}).build());
		}

		int i = 18;
		for(ColorTab color : ColorTab.values()) {
			ItemBuilder builder = new ItemBuilder(Material.INK_SACK).setDamage(color.getData()).setName("§7Couleur: "+color.getDisplayName());
			
			if(wPlayer.getTabCustom().getColor().equals(color)) {
				builder.addEnchant(Enchantment.ARROW_DAMAGE, 10).addFlag(ItemFlag.HIDE_ENCHANTS);
				builder.setLore(new String[] {
						"  §f> §aSélectionné"
				});
			}
			
			setItem(i, builder.build());
			colorsPos.put(i, color);
			i++;
		}
		
		i = 27;
		
		for(i = 27; i < 36; i++) {
			setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName(" ").build());
		}
		
		i = 36;
		
		for(SymbolTab symbol : SymbolTab.values()) {
			
			ItemBuilder builder = new ItemBuilder(Material.INK_SACK).setName("§7Symbole: §f"+symbol.getDisplayName());
			
			if(wPlayer.getTabCustom().getSymbol().equals(symbol)) {
				builder.setDamage(10);
				builder.addEnchant(Enchantment.ARROW_DAMAGE, 10).addFlag(ItemFlag.HIDE_ENCHANTS);
				builder.setLore(new String[] {
						"  §f> §aSélectionné"
				});
			} else {
				builder.setDamage(8);
			}
			
			setItem(i, builder.build());
			symbolPos.put(i, symbol);
			i++;
		}
		
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		
		if(!wPlayer.getRank().isHigher(Ranks.GOLD)) {
			close();
			player.sendMessage("§bInfo §8» §cVous devez avoir le grade §e§lGOLD §cou supérieur pour faire cela.");
			return;
		}
		
		if(position == 8) {
			if(wPlayer.getTabCustom().isActive()) {
				wPlayer.getTabCustom().setActive(false);
				wPlayer.getTabCustom().reload();
				drawScreen();
			} else {
				wPlayer.getTabCustom().setActive(true);
				wPlayer.getTabCustom().reload();
				drawScreen();
			}
		}
		
		if(colorsPos.containsKey(position)) {
			ColorTab color = colorsPos.get(position);
			wPlayer.getTabCustom().setColor(color);
			player.sendMessage("§6Options §8» §7Vous avez choisi la couleur "+color.getDisplayName());
			drawScreen();
		}
		if(symbolPos.containsKey(position)) {
			SymbolTab symbol = symbolPos.get(position);
			wPlayer.getTabCustom().setSymbol(symbol);
			player.sendMessage("§6Options §8» §7Vous avez choisi le symbole §e"+symbol.getDisplayName());
			drawScreen();
		}
	}
	
	

}
