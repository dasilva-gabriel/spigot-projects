package eu.pvpwarcraft.skypvp.utils.guis;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import eu.pvpwarcraft.skypvp.managers.configuration.ConfigManager;
import eu.pvpwarcraft.skypvp.managers.players.PlayersStats;
import eu.pvpwarcraft.skypvp.utils.HeadBuilder;
import eu.pvpwarcraft.skypvp.utils.ItemBuilder;

public class BoutiqueGui extends Gui {

	public BoutiqueGui(Player player) {
		super("§b§lSky§3§lPvP §8| §eMenu", 5, player);
	}

	@Override
	public void drawScreen() {
		Player p = getPlayer();
		int kills = PlayersStats.getKills(p);
		int deaths = PlayersStats.getDeaths(p);
		int coins = PlayersStats.getCoins(p);
		double ratio = PlayersStats.getRatio(p);

		String profile[] = { " ", "  §e§l> §7Tué(s): §6" + kills, "  §e§l> §7Mort(s): §6" + deaths,
				"  §e§l> §7Ratio: §6" + ratio, "  §e§l> §7Coins: §6" + coins+"§6✪"," " };

		getPlayer().playSound(getPlayer().getLocation(), Sound.NOTE_SNARE_DRUM, 10.0F, 10.0F);
		setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(9).build());
		setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(9).build());
		setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());

		setItem(4, new HeadBuilder(p.getName()).setName("§8▶ §7Compte: §a" + p.getName()).setLore(profile).build());
		setItem(20, new ItemBuilder(Material.ENCHANTED_BOOK).addEnchant(Enchantment.DAMAGE_ALL, 1).setName("§8▶ §aSharpness I §8(§e75 Coins§8)").build());
		setItem(21, new ItemBuilder(Material.ENCHANTED_BOOK).addEnchant(Enchantment.DURABILITY, 1).setName("§8▶ §aUnbreaking I §8(§e10 Coins§8)").build());
		setItem(22, new ItemBuilder(Material.ENCHANTED_BOOK).addEnchant(Enchantment.ARROW_DAMAGE, 1).setName("§8▶ §aPower I §8(§e50 Coins§8)").build());
		setItem(23, new ItemBuilder(Material.ENCHANTED_BOOK).addEnchant(Enchantment.ARROW_KNOCKBACK, 1).setName("§8▶ §aPunch I §8(§e50 Coins§8)").build());
		setItem(24, new ItemBuilder(Material.ENCHANTED_BOOK).addEnchant(Enchantment.ARROW_FIRE, 1).setName("§8▶ §aFire I §8(§e150 Coins§8)").build());
		setItem(29, new ItemBuilder(Material.ARROW).setAmount(5).setName("§8▶ §ax5 Flèche §8(§e1 Coins§8)").build());
		setItem(30, new ItemBuilder(Material.GOLDEN_APPLE).setAmount(2).setName("§8▶ §ax2 Pomme en Or §8(§e3 Coins§8)").build());
		setItem(31, new ItemBuilder(Material.EXP_BOTTLE).setName("§8▶ §ax1 Bouteille d'Exp §8(§e3 Coins§8)").build());
		setItem(32, new ItemBuilder(Material.POTION).setDamage(16389).setName("§8▶ §ax1 Splash Heal §8(§e5 Coins§8)").build());
		setItem(33, new ItemBuilder(Material.INK_SACK).setDamage(4).setAmount(3).setName("§8▶ §ax3 Lapis §8(§e5 Coins§8)").build());
		setItem(13, new ItemBuilder(Material.PRISMARINE_SHARD).addEnchant(Enchantment.ARROW_KNOCKBACK, 1).setName("§5§l-75% §8▶ §ax1 CLefs de Lottery §8(§e30 Coins§8)").addFlag(ItemFlag.HIDE_ENCHANTS).build());
	}

	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		ConfigManager.savePlayersConfig();
		Player p = getPlayer();
		int coins = PlayersStats.getCoins(p);
		if(position == 13){
			if(coins < 30){
				p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous n'avez pas les fonds nécéssaires.");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
				return;
			}
			p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
			p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous avez acheté §eune Clef §7pour §a30 Coins§7.");
			p.getServer().dispatchCommand(p.getServer().getConsoleSender(), "crate givekey "+p.getName()+" Roulette 1");
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", coins-30);
			ConfigManager.savePlayersConfig();
			return;
		}
		if(position == 33){
			if(coins < 5){
				p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous n'avez pas les fonds nécéssaires.");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
				return;
			}
			p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
			p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous avez acheté §eun Lapis §7pour §a5 Coins§7.");
			p.getInventory().addItem(new ItemBuilder(Material.INK_SACK).setDamage(4).setAmount(3).build());
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", coins-5);
			ConfigManager.savePlayersConfig();
			return;
		}
		if(position == 32){
			if(coins < 5){
				p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous n'avez pas les fonds nécéssaires.");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
				return;
			}
			p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
			p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous avez acheté §eune Splash Heal §7pour §a5 Coins§7.");
			p.getInventory().addItem(new ItemBuilder(Material.POTION).setDamage(16389).build());
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", coins-5);
			ConfigManager.savePlayersConfig();
			return;
		}
		if(position == 31){
			if(coins < 3){
				p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous n'avez pas les fonds nécéssaires.");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
				return;
			}
			p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
			p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous avez acheté §eune bouteille d'Exp §7pour §a3 Coins§7.");
			p.getInventory().addItem(new ItemBuilder(Material.EXP_BOTTLE).build());
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", coins-3);
			ConfigManager.savePlayersConfig();
			return;
		}
		if(position == 30){
			if(coins < 3){
				p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous n'avez pas les fonds nécéssaires.");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
				return;
			}
			p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
			p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous avez acheté §edes Pommes d'or §7pour §a3 Coins§7.");
			p.getInventory().addItem(new ItemBuilder(Material.GOLDEN_APPLE).setAmount(3).build());
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", coins-3);
			ConfigManager.savePlayersConfig();
			return;
		}
		if(position == 29){
			if(coins < 1){
				p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous n'avez pas les fonds nécéssaires.");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
				return;
			}
			p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
			p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous avez acheté §edes Flèches §7pour §a1 Coins§7.");
			p.getInventory().addItem(new ItemBuilder(Material.ARROW).setAmount(5).build());
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", coins-1);
			ConfigManager.savePlayersConfig();
			return;
		}
		if(position == 24){
			if(coins < 150){
				p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous n'avez pas les fonds nécéssaires.");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
				return;
			}
			p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
			p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous avez acheté §eun livre Fire I §7pour §a150 Coins§7.");
			ItemStack book = new ItemBuilder(Material.ENCHANTED_BOOK).build();
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
            meta.addStoredEnchant(Enchantment.ARROW_FIRE, 1, true);
            book.setItemMeta(meta);
			p.getInventory().addItem(book);
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", coins-150);
			ConfigManager.savePlayersConfig();
			return;
		}
		if(position == 23){
			if(coins < 50){
				p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous n'avez pas les fonds nécéssaires.");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
				return;
			}
			p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
			p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous avez acheté §eun livre Punch I §7pour §a50 Coins§7.");
			ItemStack book = new ItemBuilder(Material.ENCHANTED_BOOK).build();
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
            meta.addStoredEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
            book.setItemMeta(meta);
			p.getInventory().addItem(book);
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", coins-50);
			ConfigManager.savePlayersConfig();
			return;
		}
		if(position == 20){
			if(coins < 75){
				p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous n'avez pas les fonds nécéssaires.");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
				return;
			}
			p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
			p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous avez acheté §eun livre Sharpness I §7pour §a75 Coins§7.");
			ItemStack book = new ItemBuilder(Material.ENCHANTED_BOOK).build();
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
            meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 1, true);
            book.setItemMeta(meta);
			p.getInventory().addItem(book);
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", coins-75);
			ConfigManager.savePlayersConfig();
			return;
		}
		if(position == 21){
			if(coins < 10){
				p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous n'avez pas les fonds nécéssaires.");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
				return;
			}
			p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
			p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous avez acheté §eun livre Unbreaking I §7pour §a10 Coins§7.");
			ItemStack book = new ItemBuilder(Material.ENCHANTED_BOOK).build();
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
            meta.addStoredEnchant(Enchantment.DURABILITY, 1, true);
            book.setItemMeta(meta);
			p.getInventory().addItem(book);
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", coins-10);
			ConfigManager.savePlayersConfig();
			return;
		}
		if(position == 22){
			if(coins < 50){
				p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous n'avez pas les fonds nécéssaires.");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
				return;
			}
			p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
			p.sendMessage("§b§lSky§3§lPvP §8▶ §7Vous avez acheté §eun livre Power I §7pour §a50 Coins§7.");
			ItemStack book = new ItemBuilder(Material.ENCHANTED_BOOK).build();
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
            meta.addStoredEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            book.setItemMeta(meta);
			p.getInventory().addItem(book);
			ConfigManager.getPlayersConfig().set("Players. " + p.getName() + " .Coins", coins-50);
			ConfigManager.savePlayersConfig();
			return;
		}
	}

}
