package eu.pvpwarcraft.warfight.managers.guis;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.warfight.managers.Locations;
import eu.pvpwarcraft.warfight.managers.LocationsManager;
import eu.pvpwarcraft.warfight.utils.ItemBuilder;

public class AdminGui extends Gui {

	public AdminGui(Player player) {
		super("§8■ §cAdministration §8■", 1, player);
	}

	@Override
	public void drawScreen() {

		setItem(1, new ItemBuilder(Material.GOLD_SWORD).setName("§b§lArènes §8■ §7Gérer les arènes").build());
		setItem(3, new ItemBuilder(Material.BED).setName("§a§lSpawn §8■ §7Définir la position du spawn").build());
		setItem(4, new ItemBuilder(Material.ANVIL).setName("§f§lRaccourcis §8■ §7Quelques raccourcis...").build());
		setItem(5, new ItemBuilder(Material.BOOK).setName("§d§lEditeur §8■ §7Définir la position de l'éditeur").build());
		setItem(7, new ItemBuilder(Material.BEACON).setName("§c§lReload §8■ §7Reload les configurations").build());

	}

	@Override
	public void onClick(int position, ItemStack item, ClickType click) {

		Player player = getPlayer();

		if (item.getType() == Material.STAINED_GLASS_PANE) {
			return;
		}

		if (position == 4) {

			GuiManager.closePlayer(player);

			GuiManager.openGui(new ShortAdminGui(player));
			return;

		}

		if (position == 1) {

			GuiManager.closePlayer(player);

			GuiManager.openGui(new ArenasGui(player));
			return;

		}

		if (position == 3) {

			GuiManager.closePlayer(player);

			LocationsManager.saveLocation(player.getLocation(), Locations.SPAWN.getPath(), true);
			player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 10.0F);
			player.sendMessage("§6Settings §8» §7Vous avez placé la position §a"+Locations.SPAWN.getName()+"§7!");
			return;
		}

		if (position == 5) {

			GuiManager.closePlayer(player);

			LocationsManager.saveLocation(player.getLocation(), Locations.EDIT.getPath(), true);
			player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 10.0F);
			player.sendMessage("§6Settings §8» §7Vous avez placé la position §a"+Locations.EDIT.getName()+"§7!");
			return;
		}

	}

}
