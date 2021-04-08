package eu.pvpwarcraft.warfight.handlers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class InventoryHandler {

	public static String toBase64(Inventory paramInventory) {
		try {
			ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream localBukkitObjectOutputStream = new BukkitObjectOutputStream(
					localByteArrayOutputStream);

			localBukkitObjectOutputStream.writeInt(paramInventory.getSize());

			for (int i = 0; i < paramInventory.getSize(); i++) {
				localBukkitObjectOutputStream.writeObject(paramInventory.getItem(i));
			}

			localBukkitObjectOutputStream.close();
			return Base64Coder.encodeLines(localByteArrayOutputStream.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Inventory fromBase64(String paramString) {
		try {
			ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(
					Base64Coder.decodeLines(paramString));
			BukkitObjectInputStream localBukkitObjectInputStream = new BukkitObjectInputStream(
					localByteArrayInputStream);
			Inventory localInventory = Bukkit.getServer().createInventory(null, localBukkitObjectInputStream.readInt());

			for (int i = 0; i < localInventory.getSize(); i++) {
				localInventory.setItem(i, (ItemStack) localBukkitObjectInputStream.readObject());
			}
			localBukkitObjectInputStream.close();
			return localInventory;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String itemToBase64(ItemStack[] paramArrayOfItemStack) {
		try {
			ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream localBukkitObjectOutputStream = new BukkitObjectOutputStream(
					localByteArrayOutputStream);
			localBukkitObjectOutputStream.writeInt(paramArrayOfItemStack.length);
			for (int i = 0; i < paramArrayOfItemStack.length; i++) {
				localBukkitObjectOutputStream.writeObject(paramArrayOfItemStack[i]);
			}
			localBukkitObjectOutputStream.close();
			return Base64Coder.encodeLines(localByteArrayOutputStream.toByteArray());
		} catch (Exception localException) {
			throw new IllegalStateException("Unable to save item stacks.", localException);
		}
	}

	public static ItemStack[] itemFromBase64(String paramString) {
		try {
			ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(
					Base64Coder.decodeLines(paramString));
			BukkitObjectInputStream localBukkitObjectInputStream = new BukkitObjectInputStream(
					localByteArrayInputStream);
			ItemStack[] arrayOfItemStack = new ItemStack[localBukkitObjectInputStream.readInt()];
			for (int i = 0; i < arrayOfItemStack.length; i++) {
				arrayOfItemStack[i] = ((ItemStack) localBukkitObjectInputStream.readObject());
			}

			localBukkitObjectInputStream.close();
			return arrayOfItemStack;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
