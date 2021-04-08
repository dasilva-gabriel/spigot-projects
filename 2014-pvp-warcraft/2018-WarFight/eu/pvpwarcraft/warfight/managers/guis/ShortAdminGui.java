package eu.pvpwarcraft.warfight.managers.guis;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.warfight.managers.guis.players.RankedGui;
import eu.pvpwarcraft.warfight.utils.ItemBuilder;

public class ShortAdminGui extends Gui {

	public ShortAdminGui(Player player) {
		super("§8■ §cAdministration §8■", 1, player);
	}

	@Override
	public void drawScreen() {

		setItem(0, new ItemBuilder(Material.IRON_SWORD).setName("§8» §7Créer une arène").build());
		setItem(1, new ItemBuilder(Material.GOLD_CHESTPLATE).setName("§8» §7Menu Ranked").build());

	}

	@Override
	public void onClick(int position, ItemStack item, ClickType click) {

		Player player = getPlayer();

		if (position == 0) {
			
			Random random1 = new Random();		
			int numb1 = 1 + random1.nextInt(8);
			Random random2 = new Random();		
			int numb2 = 1 + random2.nextInt(8);
			Random random3 = new Random();		
			int numb3 = 1 + random3.nextInt(8);
			
	        String ra_name = RandomStringUtils.randomAlphabetic(7);
	        ra_name += "#"+numb1;
	        ra_name += ""+numb2;
	        ra_name += ""+numb3;
			
			player.performCommand("wararena addarena "+ra_name);
			return;
		}
		
		if (position == 1) {
			GuiManager.closePlayer(player);
			GuiManager.openGui(new RankedGui(player));
		}

	}

}
