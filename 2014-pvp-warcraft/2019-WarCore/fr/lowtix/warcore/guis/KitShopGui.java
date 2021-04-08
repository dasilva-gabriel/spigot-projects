package fr.lowtix.warcore.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.utils.Gui;
import fr.lowtix.warcore.utils.GuiManager;
import fr.lowtix.wartracker.utils.ItemBuilder;

public class KitShopGui extends Gui{

	private WarPlayer wPlayer;
	
	public KitShopGui(Player player) {
		super("§8» §eBoutique de Kits", 4, player);
		this.wPlayer = WarCore.getInstance().getUser(player);
	}

	@Override
	public void drawScreen() {
		
		setItem(4, new ItemBuilder(Material.SIGN).setName("§e§nInformation et légende").setLore(new String[] {
				"§7L'achat n'est pas a vie, il concerne",
				"§7que l'achat d'un Kit. Aucun remboursement",
				"§7ne sera effectué même si erreur de votre part.",
				" ",
				"  §f- §7Clique droit: §a§oAchat en §e⛃",
				"  §f- §7Clique gauche: §a§oAchat en §f✴"
		}).build());
		
		setItem(19, new ItemBuilder(Material.DIAMOND_ORE).setName("§7Achat du kit §3Mineur").setLore(new String[] {
				"§7Prix: §e1'000 ⛃ §7ou §f3'500 ✴",
				"§7§oUne seule utilisation"
		}).build());
		setItem(20, new ItemBuilder(Material.TNT).setName("§7Achat du kit §3Pilleur").setLore(new String[] {
				"§7Prix: §e2'500 ⛃ §7ou §f6'000 ✴",
				"§7§oUne seule utilisation"
		}).build());
		setItem(21, new ItemBuilder(Material.SEEDS).setName("§7Achat du kit §3Fermier").setLore(new String[] {
				"§7Prix: §e750 ⛃ §7ou §f1'000 ✴",
				"§7§oUne seule utilisation"
		}).build());
		setItem(22, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§7Achat du kit §3Guerrier niveau 1").setLore(new String[] {
				"§7Prix: §e2'000 ⛃ §7ou §f5'000 ✴",
				"§7§oUne seule utilisation"
		}).build());
		setItem(23, new ItemBuilder(Material.BOW).setName("§7Achat du kit §3Archer").setLore(new String[] {
				"§7Prix: §e1'000 ⛃ §7ou §f3'500 ✴",
				"§7§oUne seule utilisation"
		}).build());
		setItem(24, new ItemBuilder(Material.ENDER_PEARL).setName("§7Achat du kit §3Explorateur").setLore(new String[] {
				"§7Prix: §e1'500 ⛃ §7ou §f4'500 ✴",
				"§7§oUne seule utilisation"
		}).build());
		setItem(25, new ItemBuilder(Material.REDSTONE_BLOCK).setName("§7Achat du kit §3Electricien").setLore(new String[] {
				"§7Prix: §e2'500 ⛃ §7ou §f6'000 ✴",
				"§7§oUne seule utilisation"
		}).build());
		setItem(31, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§7Achat du kit §3Guerrier niveau 2").setLore(new String[] {
				"§7Prix: §e4'000 ⛃ §7ou §f10'000 ✴",
				"§7§oUne seule utilisation"
		}).build());

	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		if(click.equals(ClickType.RIGHT)) {
			switch (position) {
			case 19:
				GuiManager.closePlayer(player);
				if(wPlayer.hasCoins(1000)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eMineur§7. §8(§c-1000⛃§8)");
					wPlayer.removeCoins(1000);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Mineur "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de coins.");
					break;
				}
				
			case 20:
				GuiManager.closePlayer(player);
				if(wPlayer.hasCoins(2500)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §ePilleur§7. §8(§c-2500⛃§8)");
					wPlayer.removeCoins(2500);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Pilleur "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de coins.");
					break;
				}
				
			case 21:
				GuiManager.closePlayer(player);
				if(wPlayer.hasCoins(750)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eFermier§7. §8(§c-750⛃§8)");
					wPlayer.removeCoins(750);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Fermier "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de coins.");
					break;
				}
				
			case 22:
				GuiManager.closePlayer(player);
				if(wPlayer.hasCoins(2000)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eGuerrier niveau 1§7. §8(§c-2000⛃§8)");
					wPlayer.removeCoins(2000);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Guerrier_1 "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de coins.");
					break;
				}
				
			case 23:
				GuiManager.closePlayer(player);
				if(wPlayer.hasCoins(1000)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eArcher§7. §8(§c-1000⛃§8)");
					wPlayer.removeCoins(1000);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Archer "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de coins.");
					break;
				}
				
			case 24:
				GuiManager.closePlayer(player);
				if(wPlayer.hasCoins(1500)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eExplorateur§7. §8(§c-1500⛃§8)");
					wPlayer.removeCoins(1500);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Explorateur "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de coins.");
					break;
				}
				
			case 25:
				GuiManager.closePlayer(player);
				if(wPlayer.hasCoins(2500)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eElectricien§7. §8(§c-2500⛃§8)");
					wPlayer.removeCoins(2500);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Electricien "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de coins.");
					break;
				}
				
			case 31:
				GuiManager.closePlayer(player);
				if(wPlayer.hasCoins(4000)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eGuerrier niveau 2§7. §8(§c-4000⛃§8)");
					wPlayer.removeCoins(4000);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Guerrier_2 "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de coins.");
					break;
				}
			default:
				break;
			}
		} else if(click.equals(ClickType.LEFT)) {
			switch (position) {
			case 19:
				GuiManager.closePlayer(player);
				if(wPlayer.hasPoints(3500)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eMineur§7. §8(§c-3500✴§8)");
					wPlayer.removePoints(3500);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Mineur "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de points.");
					break;
				}
				
			case 20:
				GuiManager.closePlayer(player);
				if(wPlayer.hasPoints(6000)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §ePilleur§7. §8(§c-6000✴§8)");
					wPlayer.removePoints(6000);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Pilleur "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de points.");
					break;
				}
				
			case 21:
				GuiManager.closePlayer(player);
				if(wPlayer.hasPoints(1000)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eFermier§7. §8(§c-1000✴§8)");
					wPlayer.removePoints(1000);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Fermier "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de points.");
					break;
				}
				
			case 22:
				GuiManager.closePlayer(player);
				if(wPlayer.hasPoints(5000)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eGuerrier niveau 1§7. §8(§c-5000✴§8)");
					wPlayer.removePoints(5000);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Guerrier_1 "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de points.");
					break;
				}
				
			case 23:
				GuiManager.closePlayer(player);
				if(wPlayer.hasPoints(3500)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eArcher§7. §8(§c-3500✴§8)");
					wPlayer.removePoints(3500);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Archer "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de points.");
					break;
				}
				
			case 24:
				GuiManager.closePlayer(player);
				if(wPlayer.hasPoints(4500)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eExplorateur§7. §8(§c-4500✴§8)");
					wPlayer.removePoints(4500);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Explorateur "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de points.");
					break;
				}
				
			case 25:
				GuiManager.closePlayer(player);
				if(wPlayer.hasPoints(6000)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eElectricien§7. §8(§c-6000✴§8)");
					wPlayer.removePoints(6000);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Electricien "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de points.");
					break;
				}
				
			case 31:
				GuiManager.closePlayer(player);
				if(wPlayer.hasPoints(10000)) {
					player.playSound(player.getLocation(), Sound.SLIME_ATTACK, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §7Vous avez §aacheté §7le kit §eGuerrier niveau 2§7. §8(§c-10000✴§8)");
					wPlayer.removePoints(10000);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit Guerrier_2 "+player.getName());
					break;
				} else {
					player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 12.0F);
					player.sendMessage("§3Boutique §8» §cVous n'avez pas assez de points.");
					break;
				}
			default:
				break;
			}
		}
	}
	
	

}
