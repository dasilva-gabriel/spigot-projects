package eu.pvpwarcraft.warfight.managers.guis;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.warfight.managers.arenas.Arena;
import eu.pvpwarcraft.warfight.managers.arenas.ArenasManager;
import eu.pvpwarcraft.warfight.utils.ItemBuilder;

public class ArenasGui extends Gui {
	
	private ArrayList<Arena> arenas = new ArrayList<>();

	public ArenasGui(Player player) {
		super("§8■ §cAdministration §8■", 6, player);
	}

	@Override
	public void drawScreen() {

		setItem(0, new ItemBuilder(Material.BARRIER).setAmount(0).setName("§4Aucunes arènes").build());
		
		int i = -1;
		
		for(Entry<String, Arena> entry : ArenasManager.arenas.entrySet()) {
			
			i++;
			
			String cle = entry.getKey();
			Arena valeur = entry.getValue();
		    
			arenas.add(valeur);
			if(ArenasManager.isReady(cle)){
				setItem(i, new ItemBuilder(Material.STAINED_CLAY).setDamage(5).setName("§8» §7Configurer §e"+valeur.getName()+" §8(§2✔ §aTerminée§8)").build());
				if(valeur.isInUse()){
					setItem(i, new ItemBuilder(Material.STAINED_CLAY).setDamage(1).setName("§8» §7Configurer §e"+valeur.getName()+" §8(§3♽ §bMatch en cours§8)").build());
				}
			}else{
				setItem(i, new ItemBuilder(Material.STAINED_CLAY).setDamage(14).setName("§8» §7Configurer §e"+valeur.getName()+" §8(§4✗ §cNon Terminée§8)").build());
			}
			
		}

	}

	@Override
	public void onClick(int position, ItemStack item, ClickType click) {

		Player player = getPlayer();

		if (item.getAmount() == 0) {
			return;
		}else{
			Arena arena = arenas.get(position);
			GuiManager.closePlayer(player);
			try {
				GuiManager.openGui(new ConfigArenaGui(player, arena));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
