package fr.lowtix.warbox.guis;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.enums.BowTrail;
import fr.lowtix.warbox.enums.Tags;
import fr.lowtix.warbox.players.WarPlayer;
import fr.lowtix.warbox.utils.Gui;
import fr.lowtix.warbox.utils.GuiManager;
import fr.lowtix.warbox.utils.ItemBuilder;

public class TrailsGui extends Gui {

	private WarPlayer wp;
	private HashMap<Integer, BowTrail> posIcon = new HashMap<Integer, BowTrail>();
	
	public TrailsGui(Player player) {
		super("§8Profil - Particules", 6, player);
		
		wp = WarBox.getInstance().getUser(player);
	}

	@Override
	public void drawScreen() {
		
		for(int i = 0; i <= 53; i++) {
			if((i >=0 && i<=8) || (i%9 == 0) || ((i+1) %9 == 0) || (i >= 44 && i <= 53)) {
				setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(1).build());
			}
		}
		
		setItem(48, new ItemBuilder(Material.BOOK).setName("§6Informations").setLore(new String[] {
				"§7Choisissez vos particules pour votre arc. Elles",
				"§7s'afficherons lorsque vous tirez une flèche.",
		}).build());
		setItem(49, new ItemBuilder(Material.ARROW).setName("§cFermer").build());
		setItem(50, new ItemBuilder(Material.INK_SACK).setDamage(1).setName("§6Retirer votre particule").setLore(new String[] {
				"§7En cliquant ici, aucune particule ne sera affiché en jeu."
		}).build());
		
		int i = 10;
		for(BowTrail trail: BowTrail.values()) {
			while((i >=0 && i<=8) || (i%9 == 0) || ((i+1) %9 == 0) || (i >= 44 && i <= 53)) {
				i++;
			}
			
			if(!trail.equals(BowTrail.NONE)) {
				if(getPlayer().hasPermission("warbox.trails."+trail.name().toLowerCase())) {
					
					setItem(i, new ItemBuilder(Material.INK_SACK).setDamage(8).setName("§6"+trail.getDisplayName()).setLore(new String[] { "", "§2✔ §aVous avez cette particule" }).build());
					posIcon.put(i, trail);
				} else {
					setItem(i, new ItemBuilder(Material.BARRIER).setName("§6"+trail.getDisplayName()).setLore(new String[] { "", "§4✕ §cVous n'avez pas cette particule" }).build());
				}
				
				if(trail.equals(wp.getStats().getTrail())) {
					setItem(i, new ItemBuilder(Material.INK_SACK).setDamage(10).setName("§6"+trail.getDisplayName()).setLore(new String[] { "", "§2✔ §aCette particule est sélectionné" }).build());
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
			player.sendMessage("§8[§3§lW§fInfo§8] §6Vous avez §cdésactivé §6votre particule.");
		} else if(posIcon.containsKey(position)) {
			BowTrail trail = posIcon.get(position);
			wp.getStats().setTrail(trail);
			GuiManager.closePlayer(getPlayer());
			getPlayer().playSound(getPlayer().getLocation(), Sound.NOTE_PLING, 1.0F, 10.0F);
			player.sendMessage("§8[§3§lW§fInfo§8] §6Vous avez sélectionné la particule d'arc §e"+trail.getDisplayName()+"§6.");
		}
		
		
		
		
	}

}
