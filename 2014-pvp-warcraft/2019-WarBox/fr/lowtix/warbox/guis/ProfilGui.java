package fr.lowtix.warbox.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warbox.WarBox;
import fr.lowtix.warbox.enums.BowTrail;
import fr.lowtix.warbox.enums.Groups;
import fr.lowtix.warbox.enums.Tags;
import fr.lowtix.warbox.managers.DailyDropsManager;
import fr.lowtix.warbox.players.WarPlayer;
import fr.lowtix.warbox.utils.Gui;
import fr.lowtix.warbox.utils.GuiManager;
import fr.lowtix.warbox.utils.HeadBuilder;
import fr.lowtix.warbox.utils.ItemBuilder;

public class ProfilGui extends Gui {

	private WarPlayer wp;
	
	public ProfilGui(Player player) {
		super("§8Profil", 6, player);
		
		wp = WarBox.getInstance().getUser(player);
	}

	@Override
	public void drawScreen() {
		
		for(int i = 0; i <= 53; i++) {
			if((i >=0 && i<=8) || (i%9 == 0) || ((i+1) %9 == 0) || (i >= 44 && i <= 53)) {
				setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(9).build());
			}
		}

		setItem(49, new ItemBuilder(Material.ARROW).setName("§cFermer").build());
		
		setItem(20, new ItemBuilder(Material.NAME_TAG).setName("§dLes Tags").setLore(new String[] {
				"§7Choisissez un Tag et il sera affiché lorsque",
				"§7que vous écrivez dans le chat!",
				"§7",
				"§7Tag actif ? " + (wp.getStats().getActive_tag().equals(Tags.NONE) ? "§cNon" : "§aOui"),
				"§7",
				"§8»§7»§8» §aCliquez ici pour ouvrir le menu des tags"
		}).build());
		
		if(!wp.getGroup().isHigherOrEquals(Groups.VIP) || WarBox.getInstance().getDailyDropsManager().hasDailyDrop(wp)) {
			setItem(22, new ItemBuilder(Material.STORAGE_MINECART).setName("§2Arrivage quotidien").setLore(new String[] {
					"§7Chaque jour récupérez une clef que vous pourrez",
					"§7utiliser dans la zone des maitres des caisses!",
					"§7",
					"§6✰ §e§lVotre arrivage est arrivé! §6✰",
					"§8»§7»§8» §aCliquez ici pour le récupérer!"
			}).build());
		} else {
			if(!WarBox.getInstance().getDailyDropsManager().hasDailyDrop(wp)) {
				setItem(22, new ItemBuilder(Material.MINECART).setName("§2Arrivage quotidien").setLore(new String[] {
						"§7Chaque jour récupérez une clef que vous pourrez",
						"§7utiliser dans la zone des maitres des caisses!",
						"§7",
						"§3✰ §bRécupérez votre arrivage dans §e"+(DailyDropsManager.timeForPick - WarBox.getInstance().getDailyDropsManager().getDiffHour(wp)) + " heure(s) §3✰",
						"§8»§7»§8» §cVous ne pouvez pas récupérer votre arrivage"
				}).build());
			}
		}

		setItem(24, new ItemBuilder(Material.BOW).setName("§6Particules d'arc").setLore(new String[] {
				"§7Choisissez une particule pour vos arcs !",
				"§7",
				"§7Particule active ? " + (wp.getStats().getTrail().equals(BowTrail.NONE) ? "§cNon" : "§aOui"),
				"§7",
				"§8»§7»§8» §aCliquez ici pour ouvrir le menu des tags"
		}).build());
		
		int level = wp.getLevel();
		setItem(30, new ItemBuilder(Material.EXP_BOTTLE).setName("§3Votre rang").setLore(new String[] {
				"§7Le rang vous permet de montrer votre supériorité",
				"§7et de débloquer des kits etc...",
				"§7",
				"§7Votre niveau: " + WarBox.getInstance().getLevelManager().getPrefix(wp.getLevel()),
				"§7Il faut §b"+WarBox.getInstance().getLevelManager().getExpForLevel(level+1)+" exp §7pour rankup",
				"§7",
				"§8»§7»§8» §aCliquez ici pour rankup"
		}).build());
		
		setItem(32, new HeadBuilder(wp.getName()).setName("§fVotre profil").setLore(new String[] {
				"§7Coins: §e" +wp.getCoins()+" coins",
				"§7Expérience: §b" + wp.getExp() + " exp",
				"§7",
				"§7Niveau: " + WarBox.getInstance().getLevelManager().getPrefix(wp.getLevel())
		}).build());
		
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		
		if(position == 49) {
			close();
		}
		
		if(position == 20) {
			GuiManager.closePlayer(getPlayer());
			GuiManager.openGui(new TagsGui(getPlayer()));
			
		} else if(position == 22) {
			GuiManager.closePlayer(getPlayer());
			if(!wp.getGroup().isHigherOrEquals(Groups.VIP)) {
				getPlayer().sendMessage("§f▎ §cCette fonctionalité est réservée aux " + Groups.VIP.getPrefix() + " §c!");
				getPlayer().sendMessage("§f▎ §eNotre boutique: §6www.http://pvp-warcraft.net/shop");
			} else {
				WarBox.getInstance().getDailyDropsManager().pickup(wp);
			}
		} else if(position == 24) {
			GuiManager.closePlayer(getPlayer());
			GuiManager.openGui(new TrailsGui(getPlayer()));
			
		} else if(position == 30) {
			GuiManager.closePlayer(getPlayer());
			WarBox.getInstance().getLevelManager().rankup(wp);
			
		}
		
		
		
		
	}

}
