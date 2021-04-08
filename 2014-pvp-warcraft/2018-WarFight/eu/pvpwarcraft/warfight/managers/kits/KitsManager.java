package eu.pvpwarcraft.warfight.managers.kits;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.warfight.WarFight;
import eu.pvpwarcraft.warfight.handlers.InventoryHandler;

public class KitsManager {

	public static Inventory getContents(Kits kit) {
		try {
			File f = new File(WarFight.getInstance().getDataFolder() + "/kits/", kit.getName() + ".yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(f);
			String contents = config.getString("contents");
			return InventoryHandler.fromBase64(contents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setKitFromPlayer(Kits kit, Player player, int hit) {
		String inv = InventoryHandler.toBase64(player.getInventory());
		String armor = InventoryHandler.itemToBase64(player.getInventory().getArmorContents());
		File f = new File(WarFight.getInstance().getDataFolder() + "/kits/", kit.getName() + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		try {
			config.set("contents", inv);
			config.set("armor", armor);
			config.set("hit", hit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			((FileConfiguration) config).save(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ItemStack[] getArmor(Kits kit) {
		try {
			File f = new File(WarFight.getInstance().getDataFolder() + "/kits/", kit.getName() + ".yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(f);
			String armor = config.getString("armor");
			return InventoryHandler.itemFromBase64(armor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getHit(Kits kit) {
		try {
			File f = new File(WarFight.getInstance().getDataFolder() + "/kits/", kit.getName() + ".yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(f);
			int hit = config.getInt("hit");
			return hit;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 20;
	}
	
	public static void giveKit(Kits kit, Player player){
		Inventory localInventory = null;
		ItemStack[] arrayOfItemStack = null;
		try {
			localInventory = KitsManager.getContents(kit);
			arrayOfItemStack = KitsManager.getArmor(kit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		player.getInventory().setContents(localInventory.getContents());
		player.getInventory().setArmorContents(arrayOfItemStack);
		player.updateInventory();
		player.sendMessage("§6WarFight §8» §aLe kit §e"+kit.getName()+" §aà été chargé !");
		player.setMaxHealth(20);
		player.setHealth(player.getMaxHealth());
	}

}
