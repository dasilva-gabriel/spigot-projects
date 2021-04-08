package fr.lowtix.warcore.guis;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warbox.enums.Tags;
import fr.lowtix.warbox.enums.TagsType;
import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;
import fr.lowtix.warcore.utils.Gui;
import fr.lowtix.warcore.utils.GuiManager;
import fr.lowtix.wartracker.utils.ItemBuilder;

public class TagsGui extends Gui {

	private HashMap<Integer, Tags> posIcon = new HashMap<Integer, Tags>();
	private WarPlayer wPlayer;
	
	public TagsGui(Player player) {
		super("§8» §eTags", 3, player);
		this.wPlayer = WarCore.getInstance().getUser(player);
	}

	@Override
	public void drawScreen() {
		
		int i = 0;
		for(Tags tag : Tags.values()) {
			
			if(!tag.equals(Tags.NONE)) {
				if((tag.getType().equals(TagsType.SILVER) && wPlayer.getRank().isHigher(Ranks.SILVER)) 
						|| (tag.getType().equals(TagsType.GOLD) && wPlayer.getRank().isHigher(Ranks.GOLD)) 
						|| (tag.getType().equals(TagsType.DIAMOND) && wPlayer.getRank().isHigher(Ranks.DIAMOND))
						|| getPlayer().hasPermission("warcore.tags."+tag.name().toLowerCase())) {
					
					setItem(i, new ItemBuilder(Material.INK_SACK).setDamage(8).setName(tag.getDisplay()).setLore(new String[] { "§7"+tag.getType().getDisplay() }).build());
					posIcon.put(i, tag);
				} else {
					setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).setName(tag.getDisplay()).setLore(new String[] { "§7"+tag.getType().getDisplay() }).build());
				}
				
				if(tag.equals(wPlayer.getPlayerStats().getTag())) {
					setItem(i, new ItemBuilder(Material.INK_SACK).setDamage(10).setName(tag.getDisplay()).setLore(new String[] { "§7"+tag.getType().getDisplay() }).build());
				}
				
				i++;
			}
			
		}
		
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		if(posIcon.containsKey(position)) {
			Tags tag = posIcon.get(position);
			wPlayer.getPlayerStats().setTag(tag);
			GuiManager.closePlayer(getPlayer());
			getPlayer().playSound(getPlayer().getLocation(), Sound.NOTE_PLING, 1.0F, 10.0F);
			player.sendMessage("§6Options §8» §7Vous avez mis votre tag en "+tag.getDisplay()+"§7.");
		}
	}
	

}
