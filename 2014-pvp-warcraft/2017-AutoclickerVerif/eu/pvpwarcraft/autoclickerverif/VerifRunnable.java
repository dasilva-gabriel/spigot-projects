package eu.pvpwarcraft.autoclickerverif;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class VerifRunnable extends BukkitRunnable {
	
	public void run() {
		for (Player verifier : VerifCommand.verifiers.keySet())
			if ((verifier.getOpenInventory().getTopInventory() != null)
					&& (verifier.getOpenInventory().getTopInventory().getTitle().startsWith("§8[§4»§8] §c"))) {
				String o = verifier.getOpenInventory().getTopInventory().getName().replace("§8[§4»§8] §c", "");
				if (Bukkit.getPlayer(o) != null) {
					Player player = Bukkit.getPlayer(o);
					verifier.getOpenInventory().getTopInventory().setItem(0, player.getInventory().getHelmet());
					verifier.getOpenInventory().getTopInventory().setItem(1, player.getInventory().getChestplate());
					verifier.getOpenInventory().getTopInventory().setItem(2, player.getInventory().getLeggings());
					verifier.getOpenInventory().getTopInventory().setItem(3, player.getInventory().getBoots());
					
					PlayerWrapper wp = (PlayerWrapper) VerifCommand.verifiers.get(verifier);

					ItemStack it = new ItemStack(Material.DIAMOND_BLOCK, wp.maxClicks > 64 ? 64 : wp.maxClicks);
					ItemMeta im = it.getItemMeta();
					im.setDisplayName("§8» §7Maximum de clicks: §6"+ wp.maxClicks);
					it.setItemMeta(im);

					ItemStack it1 = new ItemStack(Material.GOLD_BLOCK, wp.clicks > 64 ? 64 : wp.clicks);
					ItemMeta im1 = it1.getItemMeta();
					im1.setDisplayName(" ");
					im1.setDisplayName("§8» §7Clicks actuellement: §d§l" + wp.clicks);
					//im.setLore(Arrays.asList(new String[] { "Â§9Clics", "Â§9actuellement: Â§f" + wp.maxClicks }));
					it1.setItemMeta(im1);

					int ping = wp.getPing();
					ItemStack it2 = new ItemStack(Material.IRON_BLOCK, ping > 64 ? 64 : ping);
					ItemMeta im2 = it2.getItemMeta();
					im2.setDisplayName("§8» §7Ping: §e" + ping);

					Long l = Long.valueOf(wp.Connexion);
					Long l2 = Long.valueOf(System.currentTimeMillis());
					long diffMs = l2.longValue() - l.longValue();
					long diffHours = diffMs / 3600000L;
					if (diffHours != 0L) {
						long diffMins = diffMs / 3600000L % 60L;
						im2.setLore(Arrays.asList(new String[] {"  §8• §7Connecté depuis §b" + diffHours + " heures et " + diffMins + " minutes" }));
					} else {
						long diffSec = diffMs / 1000L;
						long min = diffSec / 60L;
						im2.setLore(Arrays.asList(new String[] { "  §8• §7Connecté depuis §b" + min + " minutes" }));
					}
					it2.setItemMeta(im2);
					int nombrealert = 1;
					if (wp.nombreAlertesAutoClick > 0) {
						nombrealert = wp.nombreAlertesAutoClick;
					}

					ItemStack it3 = new ItemStack(Material.REDSTONE_BLOCK, nombrealert > 64 ? 64 : nombrealert);
					ItemMeta im3 = it3.getItemMeta();
					im3.setDisplayName("§8» §7Alertes d'autoclick: §2" + wp.nombreAlertesAutoClick);
					it3.setItemMeta(im3);

					ItemStack it4 = new ItemStack(Material.GOLD_BLOCK, wp.clicks2 > 64 ? 64 : wp.clicks2);
					ItemMeta im4 = it4.getItemMeta();
					im4.setDisplayName("§8» §75 dernières secondes:");
					im4.setLore(Arrays.asList(new String[] { "  §8• §a§l" + wp.clicks2, "  §8• §a§l" + wp.clicks3, "  §8• §a§l" + wp.clicks4,
							"  §8• §a§l" + wp.clicks5, "  §8• §a§l" + wp.clicks6 }));
					it4.setItemMeta(im4);

					verifier.getOpenInventory().getTopInventory().setItem(5, it3);
					verifier.getOpenInventory().getTopInventory().setItem(6, it2);
					verifier.getOpenInventory().getTopInventory().setItem(7, it1);
					verifier.getOpenInventory().getTopInventory().setItem(8, it);
					verifier.getOpenInventory().getTopInventory().setItem(4, it4);

					int slot = 8;
					for (ItemStack itemStack : player.getInventory().getContents()) {
						slot++;
						verifier.getOpenInventory().getTopInventory().setItem(slot, itemStack);
					}
				}

			} else {
				VerifCommand.verifiers.remove(verifier);
			}
	}
}
