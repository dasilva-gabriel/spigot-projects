package eu.pvpwarcraft.meetup.utils.guis;


import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.meetup.managers.types.Types;
import eu.pvpwarcraft.meetup.managers.types.TypesManager;
import eu.pvpwarcraft.meetup.utils.ItemBuilder;
import eu.pvpwarcraft.meetup.utils.PlayerUtils;
import eu.pvpwarcraft.warfightapi.WarFightAPI;
import eu.pvpwarcraft.warfightapi.managers.User;

public class TypesGui extends Gui{
	
	private static Map<Integer, Types> slotTypes = new HashMap<Integer, Types>();
	
	public TypesGui(Player player) {
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
		
		setItem(13, new ItemBuilder(Material.PAPER).setName("§2✔ §aChoisissez un type de jeu").build());
		
		int iType = 0;
		for(int i=18; i < (Types.types.size()+18); i++){
			Types type = Types.types.get(iType);
			ItemBuilder item = Types.getItem(type);
			setItem(i, item.setName("§7Type: §e§l"+Types.getName(type)).build());
			slotTypes.put(i, type);
			iType++;
		}
		
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
		if(slotTypes.containsKey(position)){
			Types type = slotTypes.get(position);
			if(TypesManager.hasVoted(p, type)){
				p.sendMessage("§6WarFight §8» §7Vous avez déjà voter ce type de jeu.");
				p.getOpenInventory().close();
				return;
			}
			if(user.getGemmes() < 15){
				p.sendMessage("§6WarFight §8» §7Il vous faut encore §a"+(15-(user.getGemmes()))+" gemmes §7pour pouvoir voter.");
				p.getOpenInventory().close();
				return;
			}
			user.removeGemmes(15);
			TypesManager.addVotes(p, type);
			p.getOpenInventory().close();
			p.sendMessage("§2Gemme §8» §7Transaction éffectuée §c-15 gemmes §8(§bVote§8)");
			Bukkit.broadcastMessage("§6WarFight §8» §b"+p.getName()+" §7 à voté §e"+Types.getName(type)+" §8(§a"+TypesManager.getVotes(type)+" vote(s)§8)");
		}
	}

	
	
}
