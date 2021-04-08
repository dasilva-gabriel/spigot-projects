package eu.pvpwarcraft.meetup.utils.guis;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.meetup.managers.kits.KitsManager;
import eu.pvpwarcraft.meetup.utils.ItemBuilder;
import eu.pvpwarcraft.meetup.utils.PlayerUtils;
import eu.pvpwarcraft.warfightapi.WarFightAPI;
import eu.pvpwarcraft.warfightapi.managers.User;

public class RetryGui extends Gui{
	
	public RetryGui(Player player) {
		super("§6§lW§e§lar§6§lF§e§light", 6, player);
	}

	@Override
	public void drawScreen() {
		PlayerUtils.playSound(getPlayer(), Sound.NOTE_BASS_DRUM);
		setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(2).build());
		setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(2).build());
		setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(2).build());
		setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(2).build());
		setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(2).build());
		
		setItem(29, new ItemBuilder(Material.STAINED_CLAY).setDamage(5).setName("§2✔ §aReprendre un Stuff §8[§cPrix: 10 Gemmes§8]").build());
		setItem(33, new ItemBuilder(Material.STAINED_CLAY).setDamage(14).setName("§4✘ §cGarder l'actuel").build());
		setItem(22, new ItemBuilder(Material.PAPER).setName("§8» §6Voulez vous continuer ?").build());
		
		setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(2).build());
		setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(47, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(2).build());
		setItem(48, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(49, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(2).build());
		setItem(50, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(51, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(2).build());
		setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(53, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(2).build());
		
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		Player p = getPlayer();
		User user = WarFightAPI.getInstance().getUsers().get(p.getName());
		if(item.getType() == Material.STAINED_GLASS_PANE){
			return;
		}
		if(position == 29){
			p.getOpenInventory().close();
			if(user.getGemmes() < 10){
				p.sendMessage("§6WarFight §8» §7Il vous faut encore §a"+(10-(user.getGemmes()))+" gemmes §7pour pouvoir voter.");
				p.getOpenInventory().close();
				return;
			}
			user.removeGemmes(10);
			p.getOpenInventory().close();
			p.sendMessage("§2Gemme §8» §7Transaction éffectuée §c-10 gemmes §8(§bRetry Stuff§8)");
			Bukkit.broadcastMessage("§6WarFight §8» §b"+p.getName()+" §7a retenté sa chance !");
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			KitsManager.giveItems(p);
		}
		if(position == 33){
			p.getOpenInventory().close();
		}
	}

	
	
}
