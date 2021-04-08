package fr.lowtix.warcore.guis.punish;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;
import fr.lowtix.warcore.managers.PunishManager;
import fr.lowtix.warcore.utils.Gui;
import fr.lowtix.warcore.utils.GuiManager;
import fr.lowtix.warcore.utils.HeadBuilder;
import fr.lowtix.wartracker.utils.ItemBuilder;

public class PunishOther extends Gui {

	private String target;
	private PunishManager punish;
	private WarPlayer user;

	public PunishOther(Player player, String target) {
		super("§8» §6Sanction", 6, player);
		this.target = target;
		this.punish = new PunishManager();
		this.user = WarCore.getInstance().getUser(player);
	}

	@Override
	public void drawScreen() {
		getPlayer().playSound(getPlayer().getLocation(), Sound.NOTE_SNARE_DRUM, 10.0F, 10.0F);

		setItem(0, new HeadBuilder(target).setName("§8» §7Santion à appliquée sur §b§o" + target).build());
		setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f#§6WarCraftPower").setDamage(1).build());
		setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f#§6WarCraftPower").setDamage(1).build());
		setItem(8, new HeadBuilder(target).setName("§8» §7Santion à appliquée sur §b§o" + target).build());

		for (int i = 9; i <= 17; i++) {
			setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f#§6WarCraftPower").setDamage(1).build());
		}

		setItem(2, new ItemBuilder(Material.DIAMOND_SWORD).setName("§8» §6Infraction au niveau du Gameplay").build());
		setItem(4, new ItemBuilder(Material.BOOK_AND_QUILL).setName("§8» §6Infraction au niveau du Chat").build());
		setItem(6, new ItemBuilder(Material.NAME_TAG).setName("§8» §6Infraction d'une régle particulière").build());

		setItem(15, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f§l-").setDamage(0).build());

		setItem(18, new ItemBuilder(Material.FEATHER).setName("§7Sanction: §e§oCamp").build());
		setItem(19, new ItemBuilder(Material.ICE).setName("§7Sanction: §e§oUseBug").build());
		setItem(20, new ItemBuilder(Material.ANVIL).setName("§7Sanction: §e§oNon respect des règles de l'évènement").build());
		
		if(user.getRank().isHigher(Ranks.MOD_P)) {
			setItem(21, new ItemBuilder(Material.GHAST_TEAR).setName("§7Sanction: §e§oTroll").setLore(new String[] {" ", "§cRéservé aux MOD+"}).build());
			setItem(22, new ItemBuilder(Material.BAKED_POTATO).setName("§7Sanction: §e§oTroll+").setLore(new String[] {" ", "§cRéservé aux MOD+"}).build());
			setItem(23, new ItemBuilder(Material.NAME_TAG).setName("§7Sanction: §e§oInsultes Staff/Serveur").setLore(new String[] {" ", "§cRéservé aux MOD+"}).build());
		}
	}

	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		Player p = getPlayer();
		if (item.getType() == Material.STAINED_GLASS_PANE) {
			return;
		}
		if(position == 2){
			p.getOpenInventory().close();
			GuiManager.openGui(new PunishGameplay(p, target));
		}
		if(position == 4){
			p.getOpenInventory().close();
			GuiManager.openGui(new PunishChat(p, target));
		}
		if(position == 6){
			p.getOpenInventory().close();
			GuiManager.openGui(new PunishOther(p, target));
		}
		if (position == 18) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "3h", "Camp");
			return;
		}
		if (position == 19) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "3d", "UseBug");
			return;
		}
		if (position == 20) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "7d", "Non respect des règles de l'évènement");
			return;
		}
		if(user.getRank().isHigher(Ranks.MOD_P)) {
			if (position == 21) {
				player.getOpenInventory().close();
				punish.createSanction(p, target, 2, "5d", "Troll");
				return;
			}
			if (position == 22) {
				player.getOpenInventory().close();
				punish.createSanction(p, target, 1, "10d", "Troll+");
				return;
			}
			if (position == 23) {
				player.getOpenInventory().close();
				punish.createSanction(p, target, 2, "10d", "Insultes Staff/Serveur");
				return;
			}
		}
		
	}

}
