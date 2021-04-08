package fr.lowtix.warcore.guis;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warbox.enums.BowTrail;
import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;
import fr.lowtix.warcore.utils.Gui;
import fr.lowtix.warcore.utils.GuiManager;
import fr.lowtix.wartracker.utils.ItemBuilder;

public class SettingsGui extends Gui{

	private WarPlayer wPlayer;
	
	public SettingsGui(Player player) {
		super("§8» §eParamètres avancés", 4, player);
		this.wPlayer = WarCore.getInstance().getUser(player);
	}

	@Override
	public void drawScreen() {
		
		if(!wPlayer.getRank().isHigher(Ranks.SILVER)) {
			close();
			GuiManager.closePlayer(player);
			player.sendMessage("§bInfo §8» §cVous devez avoir le grade §f§lSILVER §cou supérieur pour faire cela.");
			return;
		}
		
		setItem(4, new ItemBuilder(Material.REDSTONE_COMPARATOR).setName("§e§nParamètres avancés").build());
		
		ItemBuilder joinMessage = new ItemBuilder(Material.NAME_TAG).setName("§3Annonce de connexion");
		
		if(wPlayer.getPlayerStats().joinMessage) {
			joinMessage.setLore(new String[] {
					"",
					" §8|§2»§8| §aActivé",
					" §8|§7»§8| §7§mDésactivé",
					"§7§oCliquez pour modifier"
			});
		} else {
			joinMessage.setLore(new String[] {
					"",
					" §8|§7»§8| §7§mActivé",
					" §8|§4»§8| §cDésactivé",
					"§7§oCliquez pour modifier"
			});
		}
		
		setItem(21, joinMessage.build());

		int serialTrailSize = 0;
		String[] serialTrail = new String[] {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
		
		for(BowTrail trails : BowTrail.values()) {
			if(wPlayer.getPlayerStats().trail.equals(trails)) {
				serialTrail[serialTrailSize] = " §8|§2»§8| §a"+trails.getDisplayName();
			} else {
				serialTrail[serialTrailSize] = " §8|§7»§8| §7§o"+trails.getDisplayName();
			}
			serialTrailSize++;
		}
		
		setItem(23, new ItemBuilder(Material.BOW).setName("§e§nEffets d'Arc").setLore(serialTrail).build());
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		
		if(!wPlayer.getRank().isHigher(Ranks.SILVER)) {
			close();
			GuiManager.closePlayer(player);
			player.sendMessage("§bInfo §8» §cVous devez avoir le grade §f§lSILVER §cou supérieur pour faire cela.");
			return;
		}
		
		if(position == 21) {
			wPlayer.getPlayerStats().joinMessage = !wPlayer.getPlayerStats().joinMessage;
			getPlayer().playSound(player.getLocation(), Sound.NOTE_PLING, 1.0F, 10.0F);
			drawScreen();
		}
		if(position == 23) {
			wPlayer.getPlayerStats().trail = BowTrail.getNext(wPlayer.getPlayerStats().trail);
			getPlayer().playSound(player.getLocation(), Sound.NOTE_PLING, 1.0F, 10.0F);
			drawScreen();
		}
	}
	
	

}
