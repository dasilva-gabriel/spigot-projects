package fr.lowtix.warcore.guis.punish;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warcore.managers.PunishManager;
import fr.lowtix.warcore.utils.Gui;
import fr.lowtix.warcore.utils.GuiManager;
import fr.lowtix.warcore.utils.HeadBuilder;
import fr.lowtix.wartracker.utils.ItemBuilder;

public class PunishChat extends Gui {

	private String target;
	private PunishManager punish;

	public PunishChat(Player player, String target) {
		super("§8» §6Sanction", 6, player);
		this.target = target;
		this.punish = new PunishManager();
	}

	@Override
	public void drawScreen() {
		getPlayer().playSound(getPlayer().getLocation(), Sound.NOTE_SNARE_DRUM, 10.0F, 10.0F);

		setItem(0, new HeadBuilder(target).setName("§8» §7Santion à appliquée sur §b§o" + target).build());
		setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f#§6WarCraftPower").setDamage(1).build());
		setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f#§6WarCraftPower").setDamage(1).build());
		setItem(8, new HeadBuilder(target).setName("§8» §7Santion à appliquée sur §b§o" + target).build());

		for (int i = 9; i <= 17; i++) {
			setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f§l¬‡").setDamage(1).build());
		}

		setItem(2, new ItemBuilder(Material.DIAMOND_SWORD).setName("§8» §6Infraction au niveau du Gameplay").build());
		setItem(4, new ItemBuilder(Material.BOOK_AND_QUILL).setName("§8» §6Infraction au niveau du Chat").build());
		setItem(6, new ItemBuilder(Material.NAME_TAG).setName("§8» §6Infraction d'une régle particulière").build());

		setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f#§6WarCraftPower").setDamage(0).build());

		setItem(18, new ItemBuilder(Material.RECORD_11).setName("§7Sanction: §e§oSpam").build());
		setItem(19, new ItemBuilder(Material.RECORD_12).setName("§7Sanction: §e§oFlood").build());
		setItem(20, new ItemBuilder(Material.RECORD_3).setName("§7Sanction: §e§oMajuscules").build());
		setItem(21, new ItemBuilder(Material.RECORD_4).setName("§7Sanction: §e§oProvocation").build());
		setItem(22, new ItemBuilder(Material.RECORD_10).setName("§7Sanction: §e§oInsulte").build());
		setItem(23, new ItemBuilder(Material.RECORD_5).setName("§7Sanction: §e§oLangage incorrect").build());
		setItem(24, new ItemBuilder(Material.RECORD_6).setName("§7Sanction: §e§oLangage obscène").build());
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
			punish.createSanction(p, target, 2, "10m", "Spam");
			return;
		}
		if (position == 19) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 2, "10m", "Flood");
			return;
		}
		if (position == 20) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 2, "15m", "Majuscules");
			return;
		}
		if (position == 21) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 2, "30m", "Provocation");
			return;
		}
		if (position == 22) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 2, "1h", "Insulte(s)");
			return;
		}
		if (position == 23) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 2, "30m", "Langage incorrect");
			return;
		}
		if (position == 24) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 2, "1h", "Langage obscène");
			return;
		}
	}

}
