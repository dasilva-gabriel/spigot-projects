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

public class PunishGameplay extends Gui {

	private String target;
	private PunishManager punish;

	public PunishGameplay(Player player, String target) {
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
			setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f#§6WarCraftPower").setDamage(1).build());
		}

		setItem(2, new ItemBuilder(Material.DIAMOND_SWORD).setName("§8» §6Infraction au niveau du Gameplay").build());
		setItem(4, new ItemBuilder(Material.BOOK_AND_QUILL).setName("§8» §6Infraction au niveau du Chat").build());
		setItem(6, new ItemBuilder(Material.NAME_TAG).setName("§8» §6Infraction d'une régle particulière").build());

		setItem(11, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f§l¬‡").setDamage(0).build());

		setItem(18, new ItemBuilder(Material.IRON_SWORD).setName("§7Sanction: §e§oKillAura").build());
		setItem(19, new ItemBuilder(Material.GOLD_SWORD).setName("§7Sanction: §e§oForceField").build());
		setItem(20, new ItemBuilder(Material.COMPASS).setName("§7Sanction: §e§oAimbot").build());
		setItem(21, new ItemBuilder(Material.ACTIVATOR_RAIL).setName("§7Sanction: §e§oReach").build());
		setItem(22, new ItemBuilder(Material.RABBIT_FOOT).setName("§7Sanction: §e§oSpeedHack").build());
		setItem(23, new ItemBuilder(Material.SADDLE).setName("§7Sanction: §e§oFlyHack").build());
		setItem(24, new ItemBuilder(Material.ANVIL).setName("§7Sanction: §e§oNoKnockback").build());
		setItem(25, new ItemBuilder(Material.BOW).setName("§7Sanction: §e§oFastBow").build());
		setItem(26, new ItemBuilder(Material.COOKED_BEEF).setName("§7Sanction: §e§oFastConsume").build());
		setItem(27, new ItemBuilder(Material.RED_ROSE).setName("§7Sanction: §e§oRegenHack").build());
		setItem(28, new ItemBuilder(Material.WATCH).setName("§7Sanction: §e§oAutoClicker").build());
		setItem(29, new ItemBuilder(Material.WOOD).setName("§7Sanction: §e§oFastPlace/FastBreak").build());
		setItem(30, new ItemBuilder(Material.BARRIER).setName("§7Sanction: §e§oAutre").build());
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
			punish.createSanction(p, target, 1, "7d", "Triche (KillAura)");
			return;
		}
		if (position == 19) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "7d", "Triche (ForceField)");
			return;
		}
		if (position == 20) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "7d", "Triche (Aimbot)");
			return;
		}
		if (position == 21) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "7d", "Triche (Reach)");
			return;
		}
		if (position == 22) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "7d", "Triche (SpeedHack)");
			return;
		}
		if (position == 23) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "7d", "Triche (FlyHack)");
			return;
		}
		if (position == 24) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "7d", "Triche (NoVelocity)");
			return;
		}
		if (position == 25) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "7d", "Triche (FastBow)");
			return;
		}
		if (position == 26) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "7d", "Triche (FastConsume)");
			return;
		}
		if (position == 27) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "7d", "Triche (RegenHack)");
			return;
		}
		if (position == 28) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "7d", "Triche (Autoclicker)");
			return;
		}
		if (position == 29) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "7d", "Triche (FastPlace/FastBreak)");
			return;
		}
		if (position == 30) {
			player.getOpenInventory().close();
			punish.createSanction(p, target, 1, "7d", "Triche");
			return;
		}
	}

}
