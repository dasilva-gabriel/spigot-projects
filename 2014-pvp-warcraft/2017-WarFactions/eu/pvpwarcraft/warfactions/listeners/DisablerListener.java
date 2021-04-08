package eu.pvpwarcraft.warfactions.listeners;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.warfactions.PlayerUtils;

public class DisablerListener implements Listener {

	private ItemStack[] potionsList = { new ItemStack(Material.POTION, 1, (short) 8201),
			new ItemStack(Material.POTION, 1, (short) 16393), new ItemStack(Material.POTION, 1, (short) 8265),
			new ItemStack(Material.POTION, 1, (short) 16457) };
	private List<ItemStack> potions = Arrays.asList(this.potionsList);
	private List<ItemStack> banned = Arrays.asList(new ItemStack[] { new ItemStack(Material.POTION, 1, (short) 8233),
			new ItemStack(Material.POTION, 1, (short) 16425) });

	@EventHandler
	public void onBrew(BrewEvent event) {
		ItemStack[] itemsIn = event.getContents().getContents();
		if (itemsIn[3].getType().equals(Material.GLOWSTONE_DUST))
			for (int i = 0; i < 2; i++)
				if (this.potions.contains(itemsIn[i]))
					event.setCancelled(true);
	}

	@EventHandler
	public void onClick(PlayerItemConsumeEvent event) {
		if (this.banned.contains(event.getItem())) {
			event.setCancelled(true);
			Player player = event.getPlayer();
			PlayerUtils.playSound(player, Sound.VILLAGER_NO);
			PlayerUtils.sendTitle(player, "§4✖", "§cCette potion est interdite", 0, 10, 0);
			player.sendMessage("§8§m--+------------------------------------------------+--");
			player.sendMessage("");
			player.sendMessage("  §8» §c§lAttention: §fPotion prohibée.");
			player.sendMessage("     §7■ §eInfo: §7Les potions de Force II sont interdites. Vous ne pouvez pas les utilisées !");
			player.sendMessage("");
			player.sendMessage("  §8[§3?§8] §bVous ne pourrez pas être remboursé de cette potion.");
			player.sendMessage("");
			player.sendMessage("§8§m--+------------------------------------------------+--");
			event.getItem().setType(Material.GLASS_BOTTLE);
			player.updateInventory();
		}
	}

	@EventHandler
	public void splash(PotionSplashEvent event) {
		if (this.banned.contains(event.getPotion().getItem())) {
			event.setCancelled(true);
			if ((event.getPotion().getShooter() instanceof Player)) {
				Player player = (Player) event.getPotion().getShooter();
				PlayerUtils.playSound(player, Sound.VILLAGER_NO);
				PlayerUtils.sendTitle(player, "§4✖", "§cCette potion est interdite", 0, 10, 0);
				player.sendMessage("§8§m--+------------------------------------------------+--");
				player.sendMessage("");
				player.sendMessage("  §8» §c§lAttention:");
				player.sendMessage(
						"     §8• §7Les potions de Force II sont interdites. Vous ne pouvez pas les utilisées !");
				player.sendMessage("");
				player.sendMessage("  §8[§3?§8] §bVous ne pourrez pas être remboursé de cette potion.");
				player.sendMessage("");
				player.sendMessage("§8§m--+------------------------------------------------+--");
			}
		}
	}

}
