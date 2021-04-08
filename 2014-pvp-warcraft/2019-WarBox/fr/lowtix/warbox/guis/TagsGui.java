package fr.lowtix.warbox.guis;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.enums.Tags;
import fr.lowtix.warbox.players.WarPlayer;
import fr.lowtix.warbox.utils.Gui;
import fr.lowtix.warbox.utils.GuiManager;
import fr.lowtix.warbox.utils.ItemBuilder;

public class TagsGui extends Gui {

	private WarPlayer wp;
	private HashMap<Integer, Tags> posIcon = new HashMap<Integer, Tags>();
	
	public TagsGui(Player player) {
		super("§8Profil - Tags", 6, player);
		
		wp = WarBox.getInstance().getUser(player);
	}

	@Override
	public void drawScreen() {
		
		for(int i = 0; i <= 53; i++) {
			if((i >=0 && i<=8) || (i%9 == 0) || ((i+1) %9 == 0) || (i >= 44 && i <= 53)) {
				setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).build());
			}
		}
		
		setItem(48, new ItemBuilder(Material.BOOK).setName("§6Informations").setLore(new String[] {
				"§7Le Tag est un cosmétique qui, devant votre pseudo,",
				"§7l'affiche. Vous pouvez las gagner dans les box ou",
				"§7les acheter en boutique."
		}).build());
		setItem(49, new ItemBuilder(Material.ARROW).setName("§cFermer").build());
		setItem(50, new ItemBuilder(Material.INK_SACK).setDamage(1).setName("§6Retirer votre Tag").setLore(new String[] {
				"§7En cliquant ici, aucun Tag ne sera affiché en jeu."
		}).build());
		
		int i = 10;
		for(Tags tag: Tags.values()) {
			while((i >=0 && i<=8) || (i%9 == 0) || ((i+1) %9 == 0) || (i >= 44 && i <= 53)) {
				i++;
			}
			
			if(!tag.equals(Tags.NONE)) {
				if(getPlayer().hasPermission("warbox.tags."+tag.name().toLowerCase())) {
					
					setItem(i, new ItemBuilder(Material.INK_SACK).setDamage(8).setName(tag.getDisplay()).setLore(new String[] { "§7"+tag.getType().getDisplay(), "", "§2✔ §aVous avez ce Tag" }).build());
					posIcon.put(i, tag);
				} else {
					setItem(i, new ItemBuilder(Material.BARRIER).setName(tag.getDisplay()).setLore(new String[] { "§7"+tag.getType().getDisplay(), "", "§4✕ §cVous n'avez pas ce Tag" }).build());
				}
				
				if(tag.equals(wp.getStats().getActive_tag())) {
					setItem(i, new ItemBuilder(Material.INK_SACK).setDamage(10).setName(tag.getDisplay()).setLore(new String[] { "§7"+tag.getType().getDisplay(), "", "§2✔ §aCe Tag est sélectionné" }).build());
				}
				
				i++;
				
			}
			
		}

	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		
		if(position == 49) {
			close();
		}
		
		if(position == 50) {
			close();
			wp.getStats().setActive_tag(Tags.NONE);
			player.sendMessage("§8[§3§lW§fInfo§8] §6Vous avez §cdésactivé §6votre tag.");
		} else if(posIcon.containsKey(position)) {
			Tags tag = posIcon.get(position);
			wp.getStats().setActive_tag(tag);
			GuiManager.closePlayer(getPlayer());
			getPlayer().playSound(getPlayer().getLocation(), Sound.NOTE_PLING, 1.0F, 10.0F);
			player.sendMessage("§8[§3§lW§fInfo§8] §6Vous avez sélectionné le tag "+tag.getDisplay()+"§6.");
		}
		
		
		
		
	}

}
