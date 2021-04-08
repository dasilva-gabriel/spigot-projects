package fr.lowtix.palatraining.guis;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.handlers.GamePlayer;
import fr.lowtix.palatraining.utils.Gui;
import fr.lowtix.palatraining.utils.GuiManager;
import fr.lowtix.palatraining.utils.ItemBuilder;

public class MainGui extends Gui {

	public static HashMap<String, MainGui> guis = new HashMap<String, MainGui>();
	public static int[] caseSlots = {0, 1, 7, 8, 9, 17, 27, 35, 36, 37, 43, 44};
	
	public GamePlayer gPlayer;
	
	@Override
	public void onOpen() {
		guis.put(getPlayer().getName(), this);
	}
	
	@Override
	public void onClose() {
		guis.remove(getPlayer().getName());
	}
	
	public MainGui(Player player) {
		super("§8PalaTraining - 1vs1", 5, player);
		gPlayer = PalaTraining.getInstance().getGamePlayer(player);
	}

	@Override
	public void drawScreen() {
		
		for(int slots : caseSlots) {
			setItem(slots, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(9).build());
		}
		
		setItem(3, new ItemBuilder(Material.DIAMOND_SWORD).setName("§6Match §e1vs1").build());
		setItem(4, new ItemBuilder(Material.WRITTEN_BOOK).setName("§6Profil de §e"+getPlayer().getName()).build());
		setItem(5, new ItemBuilder(Material.LEASH).setName("§6Match de §eTeam").build());
		
		
		if(PalaTraining.getInstance().getQueueManager().getQueue(player) != null) {
			setItem(22, new ItemBuilder(Material.EMPTY_MAP).setName("§4Quitter la File actuelle").setLore(new String[] {
					"§7Cliquez ici pour quitter la file d'attente..."
			}).build());
		} else {
			setItem(22, new ItemBuilder(Material.BOOK).setName("§6Bienvenue sur le §e§nPalaTraining§r §6!").setLore(new String[] {
					"§7",
					"§7Pour débuter un match, cliquez sur l'un des items situés",
					"§7sur la première ligne de ce menu."
			}).build());
		}
		
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		
		switch(position) {
		case 3:
			GuiManager.openGui(new OneVSOneGui(player));
			break;
		case 4:
			GuiManager.openGui(new ProfileGui(player));
			break;
		case 5:
			GuiManager.openGui(new TeamGui(player));
			break;
		case 22:
			if(PalaTraining.getInstance().getQueueManager().getQueue(player) != null) {
				GuiManager.closePlayer(player);
				PalaTraining.getInstance().getQueueManager().removeFromQueue(gPlayer, true);
			}
			break;
		}
		
	}

}
