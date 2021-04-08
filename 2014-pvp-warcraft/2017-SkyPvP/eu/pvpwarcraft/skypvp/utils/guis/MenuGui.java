package eu.pvpwarcraft.skypvp.utils.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.skypvp.listeners.hitman.HitmanListener;
import eu.pvpwarcraft.skypvp.managers.EnumLocations;
import eu.pvpwarcraft.skypvp.managers.Locations;
import eu.pvpwarcraft.skypvp.managers.players.PlayersStats;
import eu.pvpwarcraft.skypvp.utils.HeadBuilder;
import eu.pvpwarcraft.skypvp.utils.ItemBuilder;

public class MenuGui extends Gui {

	public MenuGui(Player player) {
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
				"  §e§l> §7Ratio: §6" + ratio, "  §e§l> §7Coins: §6" + coins + "§6✪", " " };

		getPlayer().playSound(getPlayer().getLocation(), Sound.NOTE_SNARE_DRUM, 10.0F, 10.0F);
		setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(9).build());
		setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(9).build());
		setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());
		setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).build());

		setItem(4, new HeadBuilder(p.getName()).setName("§8▶ §7Compte: §a" + p.getName()).setLore(profile).build());
		setItem(8, new ItemBuilder(Material.GOLD_INGOT).setName("§8▶ §7Boutique").build());
		setItem(20, new ItemBuilder(Material.GOLD_HELMET).setName("§8▶ §7Kit §eBasique §8(§aGratuit§8)").build());
		setItem(22, new ItemBuilder(Material.IRON_HELMET).setName("§8▶ §7Kit §6Rare §8(§aToutes les 10 minutes§8)")
				.build());
		setItem(24, new ItemBuilder(Material.DIAMOND_HELMET).setName("§8▶ §7Kit §cEpique §8(§aToutes les 30 minutes§8)")
				.build());
		setItem(30, new ItemBuilder(Material.SNOW_BALL).setName("§8▶ §7Kit §bSkyKnight §8(§aToutes les 10 minutes§8)")
				.build());
		setItem(32, new ItemBuilder(Material.SNOW_BALL).addEnchant(Enchantment.DAMAGE_ALL, 1)
				.addFlag(ItemFlag.HIDE_ENCHANTS).setName("§8▶ §7Kit §3SkyKing §8(§aToutes les 10 minutes§8)").build());
		if(p.isOp()){
			setItem(3, new ItemBuilder(Material.SNOW_BALL).setName("§4§lTEST MISSION POUR LES OP").build());
		}

	}

	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		if(getPlayer().isOp()){
			if(position == 3){
				getPlayer().getOpenInventory().close();
				Bukkit.broadcastMessage(
						"§b§lSky§3§lPvP §8▶ §e" + getPlayer().getName() + " §7lance un Event §6§lHitman §7!");
				for(Player all : Bukkit.getOnlinePlayers()){
					HitmanListener.startHitman(all);
				}
			}
		}
		if(position == 8){
			getPlayer().getOpenInventory().close();
			GuiManager.openGui(new BoutiqueGui(getPlayer()));
		}
		if (position == 20) {
			if (getPlayer().getLocation().distance(Locations.getLocation(EnumLocations.SPAWN)) > 25) {
				getPlayer().playSound(getPlayer().getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
				getPlayer().sendMessage("§b§lSky§3§lPvP §8▶ §7Rapprochez vous du spawn pour faire cela.");
				return;
			}
			getPlayer().getOpenInventory().close();
			getPlayer().performCommand("kit Basique");
		}
		if (position == 22) {
			if (getPlayer().getLocation().distance(Locations.getLocation(EnumLocations.SPAWN)) > 25) {
				getPlayer().playSound(getPlayer().getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
				getPlayer().sendMessage("§b§lSky§3§lPvP §8▶ §7Rapprochez vous du spawn pour faire cela.");
				return;
			}
			getPlayer().getOpenInventory().close();
			getPlayer().performCommand("kit rare");
		}
		if (position == 24) {
			if (getPlayer().getLocation().distance(Locations.getLocation(EnumLocations.SPAWN)) > 25) {
				getPlayer().playSound(getPlayer().getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
				getPlayer().sendMessage("§b§lSky§3§lPvP §8▶ §7Rapprochez vous du spawn pour faire cela.");
				return;
			}
			getPlayer().getOpenInventory().close();
			getPlayer().performCommand("kit epique");
		}
		if (position == 30) {
			if (getPlayer().getLocation().distance(Locations.getLocation(EnumLocations.SPAWN)) > 25) {
				getPlayer().playSound(getPlayer().getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
				getPlayer().sendMessage("§b§lSky§3§lPvP §8▶ §7Rapprochez vous du spawn pour faire cela.");
				return;
			}
			getPlayer().getOpenInventory().close();
			getPlayer().performCommand("kit skyknight");
		}
		if (position == 32) {
			if (getPlayer().getLocation().distance(Locations.getLocation(EnumLocations.SPAWN)) > 25) {
				getPlayer().playSound(getPlayer().getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
				getPlayer().sendMessage("§b§lSky§3§lPvP §8▶ §7Rapprochez vous du spawn pour faire cela.");
				return;
			}
			getPlayer().getOpenInventory().close();
			getPlayer().performCommand("kit skyking");
		}
	}

}
