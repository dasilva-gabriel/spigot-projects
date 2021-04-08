package fr.lowtix.warcore.guis;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warcore.utils.Gui;
import fr.lowtix.warcore.utils.GuiManager;
import fr.lowtix.wartracker.utils.ItemBuilder;

public class MenuGui extends Gui{

	public MenuGui(Player player) {
		super("§8» §eMenu", 4, player);
	}

	@Override
	public void drawScreen() {
		
		for(int i = 0; i < 36; i++) {
			setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName(" ").build());
		}
		
		setItem(0, new ItemBuilder(Material.DIAMOND).setName("§8» §7Boutique et engagements PvP-Warcraft").addEnchant(Enchantment.ARROW_DAMAGE, 4000).addFlag(ItemFlag.HIDE_ENCHANTS).build());
		setItem(4, new ItemBuilder(Material.EYE_OF_ENDER).setName("§8» §7Téléportation aléatoire").build());
		setItem(12, new ItemBuilder(Material.GRASS).setName("§8» §7Boutique en jeu").build());
		setItem(13, new ItemBuilder(Material.GOLD_HELMET).setName("§8» §7Mes kits disponibles").build());
		setItem(14, new ItemBuilder(Material.BED).setName("§8» §7Mes homes").build());
		setItem(18, new ItemBuilder(Material.NAME_TAG).setName("§8» §7Customisation Tab et Nametag").build());
		setItem(22, new ItemBuilder(Material.EXP_BOTTLE).setName("§8» §7Les niveaux").build());
		
		setItem(8, new ItemBuilder(Material.REDSTONE_COMPARATOR).setName("§8» §7Paramètres avancés").build());
		setItem(26, new ItemBuilder(Material.TRIPWIRE_HOOK).setName("§8» §7Achat de clefs").build());
		
		setItem(29, new ItemBuilder(Material.PAPER).setName("§8» §7Tags du Chat").build());
		setItem(33, new ItemBuilder(Material.ARMOR_STAND).setName("§8» §7Shop de Kits").build());
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		if(position == 0) {
			GuiManager.closePlayer(getPlayer());
			GuiManager.openGui(new RanksGui(getPlayer()));
		}
		if(position == 4) {
			GuiManager.closePlayer(getPlayer());
			GuiManager.openGui(new RandomTPGui(getPlayer()));
		}
		if(position == 12) {
			GuiManager.closePlayer(getPlayer());
			getPlayer().performCommand("shop");
		}
		if(position == 13) {
			GuiManager.closePlayer(getPlayer());
			getPlayer().performCommand("kit");
			getPlayer().sendMessage("§bInfo §8» §7Pour prendre votre kit faites §a/kit <nom du kit>§7.");
		}
		if(position == 14) {
			GuiManager.closePlayer(getPlayer());
			getPlayer().performCommand("home");
			getPlayer().sendMessage("§bInfo §8» §7Pour vous téléportez a un home faites §a/home <nom du kit>§7.");
		}
		if(position == 22) {
			GuiManager.closePlayer(getPlayer());
			GuiManager.openGui(new LevelsGui(getPlayer()));
		}
		if(position == 29) {
			GuiManager.closePlayer(getPlayer());
			GuiManager.openGui(new TagsGui(getPlayer()));
		}
		if(position == 33) {
			GuiManager.closePlayer(getPlayer());
			GuiManager.openGui(new KitShopGui(getPlayer()));
		}
		if(position == 18) {
			GuiManager.closePlayer(getPlayer());
			GuiManager.openGui(new TabCustomGui(getPlayer()));
		}
		if(position == 8) {
			GuiManager.closePlayer(getPlayer());
			GuiManager.openGui(new SettingsGui(getPlayer()));
		}
		if(position == 26) {
			GuiManager.closePlayer(getPlayer());
			GuiManager.openGui(new KeysGui(getPlayer()));
		}
	}
	
	

}
