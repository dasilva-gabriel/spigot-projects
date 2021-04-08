package eu.pvpwarcraft.thetowers.managers;

import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;

import eu.pvpwarcraft.thetowers.TheTowers;
import eu.pvpwarcraft.thetowers.handler.TowerPlayer;

public class PlayerManager {

	private static ItemStack buildLeatherItem(ItemStack item, Color color) {
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(color);
		ItemStack newItem = item;
		item.setItemMeta(meta);
		return newItem;
	}

	public static void respawnPlayer(TowerPlayer player) {
		player.getPlayer().setNoDamageTicks(100);

		player.getPlayer().teleport(TheTowers.getTeam(player.getTeam()).getSpawn());
		player.getPlayer().setGameMode(GameMode.SURVIVAL);
		player.getPlayer().getInventory().clear();
		player.getPlayer().getInventory().setArmorContents(null);
		for (PotionEffect effect : player.getPlayer().getActivePotionEffects()) {
			player.getPlayer().removePotionEffect(effect.getType());
		}
		player.getPlayer().setMaxHealth(20);
		player.getPlayer().setHealth(player.getPlayer().getMaxHealth());
		player.getPlayer().setFoodLevel(20);
		player.getPlayer().getInventory().setHelmet(
				buildLeatherItem(new ItemStack(Material.LEATHER_HELMET), player.getTeam().getLeatherColor()));
		player.getPlayer().getInventory().setChestplate(
				buildLeatherItem(new ItemStack(Material.LEATHER_CHESTPLATE), player.getTeam().getLeatherColor()));
		player.getPlayer().getInventory().setLeggings(
				buildLeatherItem(new ItemStack(Material.LEATHER_LEGGINGS), player.getTeam().getLeatherColor()));
		player.getPlayer().getInventory()
				.setBoots(buildLeatherItem(new ItemStack(Material.LEATHER_BOOTS), player.getTeam().getLeatherColor()));
		player.getPlayer().getInventory().addItem(new ItemStack(Material.BAKED_POTATO, 8));
		player.getPlayer().sendMessage("§6TheTowers §8» §aVous êtes invulnérable pendant §e10 secondes§a.");
	}

}
