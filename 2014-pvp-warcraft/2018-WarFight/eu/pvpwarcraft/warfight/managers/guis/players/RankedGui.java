package eu.pvpwarcraft.warfight.managers.guis.players;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.warfight.managers.guis.Gui;
import eu.pvpwarcraft.warfight.managers.guis.GuiManager;
import eu.pvpwarcraft.warfight.managers.queues.Queues;
import eu.pvpwarcraft.warfight.managers.queues.QueuesManager;
import eu.pvpwarcraft.warfight.utils.ItemBuilder;

public class RankedGui extends Gui {
	
	public RankedGui(Player player) {
		super("§c§lRanked §8■ §a1vs1", 5, player);
	}

	@Override
	public void drawScreen() {
		
		setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).setName("  ").build());
		setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).setName("  ").build());
		setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).setName("  ").build());
		setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).setName("  ").build());
		setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).setName("  ").build());
		setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).setName("  ").build());
		setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).setName("  ").build());
		setItem(27, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).setName("  ").build());
		setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(38, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).setName("  ").build());
		setItem(39, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(40, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).setName("  ").build());
		setItem(41, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(42, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).setName("  ").build());
		setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName("  ").build());
		setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(14).setName("  ").build());
		setItem(44, new ItemBuilder(Material.TRIPWIRE_HOOK).setName("§7➡ §6Menu principal").build());
		
		setItem(11, new ItemBuilder(Material.LAVA_BUCKET).setName("§8▪ §aBuildUHC").build());
		setItem(13, new ItemBuilder(Material.BOW).setName("§8▪ §aArcher").build());
		setItem(15, new ItemBuilder(Material.IRON_AXE).setName("§8▪ §aAxePvP").build());
		setItem(25, new ItemBuilder(Material.POTION).setDamage(16421).setName("§8▪ §aNoDebuff").build());
		setItem(19, new ItemBuilder(Material.POTION).setDamage(16452).setName("§8▪ §aDebuff").build());
		setItem(29, new ItemBuilder(Material.GOLDEN_APPLE).setDamage(1).setName("§8▪ §aGApple").build());
		setItem(33, new ItemBuilder(Material.RAW_FISH).setDamage(3).setName("§8▪ §aCombo").build());

	}

	@Override
	public void onClick(int position, ItemStack item, ClickType click) {

		Player player = getPlayer();
		
		if(position == 44){
			GuiManager.closePlayer(player);
			GuiManager.openGui(new MenuGui(player));
		}
		
		if(position == 11){
			GuiManager.closePlayer(player);
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 6.0F);
			player.sendMessage("§dFiles §8» §7Vous avez été ajouté à la file §b"+Queues.RANKED_BUILDUHC.getName());
			QueuesManager.addQueue(player, Queues.RANKED_BUILDUHC);
		}
		

	}

}
