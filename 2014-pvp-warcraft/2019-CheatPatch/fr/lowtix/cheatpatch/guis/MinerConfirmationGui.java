package fr.lowtix.cheatpatch.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.cheatpatch.CheatPatch;
import fr.lowtix.cheatpatch.utils.Gui;
import fr.lowtix.cheatpatch.utils.GuiManager;
import fr.lowtix.cheatpatch.utils.ItemBuilder;

public class MinerConfirmationGui extends Gui {
	
	public MinerConfirmationGui(Player player) {
		super("§f» §6WarMiner", 4, player);
	}
	
	@Override
	public void drawScreen() {
		
		if(CheatPatch.getInstance().getMinerManager().hasMiner(player)) {
			System.out.println("1");
			GuiManager.closePlayer(player);
			GuiManager.openGui(new MinerGui(player, CheatPatch.getInstance().getMinerManager().getMiner(player)));
		}
		
		setItem(13, new ItemBuilder(Material.PAPER).setName("§eChoisissez votre Miner").setLore(new String[] {
				"§7Le §eMiner §7est un outil qui vous permet lorsque vous êtes",
				"§7AFK, de pouvoir continuer a farmer. Automatiquement, vous",
				"§7recevrez de l'argent, des clefs etc.."
		}).build());
		
		setItem(20, new ItemBuilder(Material.FURNACE).setName("§6Miner §eniveau 1").setLore(new String[] {
				"§7Toutes les §e10 minutes§7:",
				"  §f- §a§l+ §b"+CheatPatch.getInstance().getMinerManager().moneyPerTick(1) + "$",
				"  §f- §23 émeraudes",
				"  §f- §d5% §7d'avoir une clef §6Vote",
				"  ",
				"§cLimité à §e6 heures§c, si vous déconnectez/crash",
				"§cvous récupérerez votre miner !",
				"  ",
				"§7§lPrix: §e§l15'000$",
		}).build());
	
		setItem(21, new ItemBuilder(Material.FURNACE).setName("§6Miner §eniveau 2").setAmount(2).setLore(new String[] {
				"§7Toutes les §e10 minutes§7:",
				"  §f- §a§l+ §b"+CheatPatch.getInstance().getMinerManager().moneyPerTick(2) + "$",
				"  §f- §24 émeraudes",
				"  §f- §d10% §7d'avoir une clef §6Vote",
				"  ",
				"§cLimité à §e6 heures§c, si vous déconnectez/crash",
				"§cvous récupérerez votre miner !",
				"  ",
				"§7§lPrix: §e§l100'000$",
		}).build());
		
		setItem(22, new ItemBuilder(Material.FURNACE).setName("§6Miner §eniveau 3").setAmount(3).setLore(new String[] {
				"§7Toutes les §e10 minutes§7:",
				"  §f- §a§l+ §b"+CheatPatch.getInstance().getMinerManager().moneyPerTick(3) + "$",
				"  §f- §25 émeraudes",
				"  §f- §d15% §7d'avoir une clef §6Vote",
				"  ",
				"§cLimité à §e6 heures§c, si vous déconnectez/crash",
				"§cvous récupérerez votre miner !",
				"  ",
				"§7§lPrix: §e§l200'000$",
		}).build());
		
		setItem(23, new ItemBuilder(Material.FURNACE).setName("§6Miner §eniveau 4").setAmount(4).setLore(new String[] {
				"§7Toutes les §e10 minutes§7:",
				"  §f- §a§l+ §b"+CheatPatch.getInstance().getMinerManager().moneyPerTick(4) + "$",
				"  §f- §26 émeraudes",
				"  §f- §d20% §7d'avoir une clef §6Vote",
				"  ",
				"§cLimité à §e6 heures§c, si vous déconnectez/crash",
				"§cvous récupérerez votre miner !",
				"  ",
				"§7§lPrix: §e§l500'000$",
		}).build());
		
		setItem(24, new ItemBuilder(Material.FURNACE).setName("§6Miner §eniveau 5").setAmount(5).setLore(new String[] {
				"§7Toutes les §e10 minutes§7:",
				"  §f- §a§l+ §b"+CheatPatch.getInstance().getMinerManager().moneyPerTick(5) + "$",
				"  §f- §27 émeraudes",
				"  §f- §d25% §7d'avoir une clef §6Vote",
				"  ",
				"§cLimité à §e6 heures§c, si vous déconnectez/crash",
				"§cvous récupérerez votre miner !",
				"  ",
				"§7§lPrix: §e§l1'200'000$",
		}).build());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		
		double money = CheatPatch.getInstance().getEconomy().getBalance(player.getName());
		if(position == 20) {
			
			if(money < 15000) {
				player.sendMessage("§8[§6WarMiner§8] §cVous n'avez pas les fonds nécéssaires.");
			} else {
				System.out.println("1");
				CheatPatch.getInstance().getEconomy().withdrawPlayer(player.getName(), 15000);
				System.out.println("2");
				player.sendMessage("§8[§6WarMiner§8] §aAchat réussi!");
				System.out.println("3");
				player.closeInventory();
				GuiManager.closePlayer(player);
				System.out.println("1");
				GuiManager.openGui(new MinerGui(player, CheatPatch.getInstance().getMinerManager().createMiner(player, 1)));
			}
			
		} else if(position == 21) {
			if(money < 100000) {
				player.sendMessage("§8[§6WarMiner§8] §cVous n'avez pas les fonds nécéssaires.");
			} else {
				CheatPatch.getInstance().getEconomy().withdrawPlayer(player.getName(), 100000);
				player.sendMessage("§8[§6WarMiner§8] §aAchat réussi!");
				GuiManager.closePlayer(player);
				GuiManager.openGui(new MinerGui(player, CheatPatch.getInstance().getMinerManager().createMiner(player, 2)));
			}
		} else if(position == 22) {
			if(money < 200000) {
				player.sendMessage("§8[§6WarMiner§8] §cVous n'avez pas les fonds nécéssaires.");
			} else {
				CheatPatch.getInstance().getEconomy().withdrawPlayer(player.getName(), 200000);
				player.sendMessage("§8[§6WarMiner§8] §aAchat réussi!");
				GuiManager.closePlayer(player);
				GuiManager.openGui(new MinerGui(player, CheatPatch.getInstance().getMinerManager().createMiner(player, 3)));
			}
		} else if(position == 23) {
			if(money < 500000) {
				player.sendMessage("§8[§6WarMiner§8] §cVous n'avez pas les fonds nécéssaires.");
			} else {
				CheatPatch.getInstance().getEconomy().withdrawPlayer(player.getName(), 500000);
				player.sendMessage("§8[§6WarMiner§8] §aAchat réussi!");
				GuiManager.closePlayer(player);
				GuiManager.openGui(new MinerGui(player, CheatPatch.getInstance().getMinerManager().createMiner(player, 4)));
			}
		} else if(position == 24) {
			if(money < 1200000) {
				player.sendMessage("§8[§6WarMiner§8] §cVous n'avez pas les fonds nécéssaires.");
			} else {
				CheatPatch.getInstance().getEconomy().withdrawPlayer(player.getName(), 1200000);
				player.sendMessage("§8[§6WarMiner§8] §aAchat réussi!");
				GuiManager.closePlayer(player);
				GuiManager.openGui(new MinerGui(player, CheatPatch.getInstance().getMinerManager().createMiner(player, 5)));
			}
		}
		
	}

}
