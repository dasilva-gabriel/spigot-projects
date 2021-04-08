package fr.lowtix.palatraining.guis;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.enums.UnrankedQueueType;
import fr.lowtix.palatraining.handlers.GamePlayer;
import fr.lowtix.palatraining.manager.QueueManager;
import fr.lowtix.palatraining.utils.Gui;
import fr.lowtix.palatraining.utils.GuiManager;
import fr.lowtix.palatraining.utils.ItemBuilder;

public class OneVSOneGui extends Gui {

	public static HashMap<String, OneVSOneGui> guis = new HashMap<String, OneVSOneGui>();
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
	
	public OneVSOneGui(Player player) {
		super("§8PalaTraining - 1vs1", 5, player);
		gPlayer = PalaTraining.getInstance().getGamePlayer(player);
	}

	@Override
	public void drawScreen() {
		
		for(int slots : caseSlots) {
			setItem(slots, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(9).build());
		}
		// ✔ ✕ ✖
		setItem(3, new ItemBuilder(Material.DIAMOND_SWORD).setName("§6Match §e1vs1").build());
		setItem(4, new ItemBuilder(Material.WRITTEN_BOOK).setName("§6Profil de §e"+getPlayer().getName()).build());
		setItem(5, new ItemBuilder(Material.LEASH).setName("§6Match de §eTeam").build());
		
		
		setItem(20, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§6File: §f1vs1 Classé").setLore(new String[] {
				"  §8» §7Type: §e1 contre 1",
				"  §8» §7Action sur l'Elo: §a✔",
				"  §8» §7Perte de Stuff: §a✔",
				"  §8» §7Perte de Money: §4✕",
				"§7",
				"§a§nCliquez pour rejoindre cette file"
		}).build());
		setItem(22, new ItemBuilder(Material.GOLD_CHESTPLATE).setName("§6File: §f1vs1 Non-Classé w/ Stuff").setLore(new String[] {
				"  §8» §7Type: §e1 contre 1",
				"  §8» §7Action sur l'Elo: §4✕",
				"  §8» §7Perte de Stuff: §a✔",
				"  §8» §7Perte de Money: §4✕",
				"§7",
				"§a§nCliquez pour rejoindre cette file"
		}).build());
		
		setItem(24, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§6File: §f1vs1 Non-Classé w/ Money").setLore(new String[] {
				"  §8» §7Type: §e1 contre 1",
				"  §8» §7Action sur l'Elo: §4✕",
				"  §8» §7Perte de Stuff: §4✕",
				"  §8» §7Perte de Money: §a✔",
				"§7",
				"§a§nCliquez pour rejoindre cette file"
		}).build());
		
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		QueueManager qu = PalaTraining.getInstance().getQueueManager();
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
		case 20:
			GuiManager.closePlayer(player);
			qu.addInQueue(gPlayer, qu.getQueue(true, UnrankedQueueType.NONE));
			break;
		case 22:
			GuiManager.openGui(new TeamGui(player));
			qu.addInQueue(gPlayer, qu.getQueue(false, UnrankedQueueType.STUFF));
			break;
		case 24:
			GuiManager.openGui(new TeamGui(player));
			qu.addInQueue(gPlayer, qu.getQueue(false, UnrankedQueueType.MONEY));
			break;
		}
		
	}

}
