package eu.pvpwarcraft.skypvp.listeners.sort;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import eu.pvpwarcraft.skypvp.utils.ItemBuilder;

public class PoisonSort implements Listener {

	public static void Give(Player player, int utilisations) {
		player.getInventory().addItem(new ItemBuilder(Material.STICK)
				.setName("§8▶ §2Sort de Poison §8(§3" + utilisations + " §7utilisations§8)").build());
	}

	@EventHandler
	public static void onUse(PlayerInteractAtEntityEvent event) {
		if (!(event.getRightClicked() instanceof Player))
			return;
		Player user = event.getPlayer();
		Player rightclicked = (Player) event.getRightClicked();
		if (user.getItemInHand() == null)
			return;
		if (rightclicked.isDead())
			return;
		if (user.isDead())
			return;
		if (!user.getItemInHand().getType().equals(Material.STICK))
			return;
		if (!user.getItemInHand().getItemMeta().getDisplayName().contains("§8▶ §2Sort de Poison"))
			return;
		String name = user.getItemInHand().getItemMeta().getDisplayName().replace("§8▶ §2Sort de Poison §8(§3", "")
				.replace(" §7utilisations§8)", "");
		int use = Integer.parseInt(name);
		if (use <= 1) {
			user.playSound(user.getLocation(), Sound.ITEM_BREAK, 10.0F, 1.0F);
			user.getItemInHand().getItemMeta().setDisplayName("§8▶ §2Sort de Poison §8(§cAucune §7utilisation§8)");
		}else{
			user.playSound(user.getLocation(), Sound.BLAZE_BREATH, 10.0F, 1.0F);
			user.getItemInHand().getItemMeta().setDisplayName("§8▶ §2Sort de Poison §8(§3" + use + " §7utilisations§8)");
		}
		rightclicked.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 3, 2));
		rightclicked.sendMessage("§8▶ §7Aïe ! §b"+user.getName()+" §7vous a mordu(e), vous subissez son Poison.");
		return;
	}

}
