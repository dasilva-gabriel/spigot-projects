package eu.pvpwarcraft.warfight.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.warfight.PlayerWrapper;
import eu.pvpwarcraft.warfight.PlayerWrapper.PlayerStates;
import eu.pvpwarcraft.warfight.WarFight;
import eu.pvpwarcraft.warfight.games.Game;
import eu.pvpwarcraft.warfight.games.GamesManager;
import eu.pvpwarcraft.warfight.managers.Permissions;
import eu.pvpwarcraft.warfight.managers.arenas.ArenasManager;
import eu.pvpwarcraft.warfight.managers.guis.AdminGui;
import eu.pvpwarcraft.warfight.managers.guis.ArenasGui;
import eu.pvpwarcraft.warfight.managers.guis.GuiManager;
import eu.pvpwarcraft.warfight.managers.kits.Kits;
import eu.pvpwarcraft.warfight.managers.kits.KitsManager;

public class WarArenaCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (!player.hasPermission(Permissions.COMMANDS_WARARENA.getPermissions())) {
				sender.sendMessage("§cErreur §8» §7Cette commande est réservée aux Membres de l'administration!");
				return true;
			}

			if (args.length == 0) {
				GuiManager.openGui(new AdminGui(player));
				return true;
			}

			if (args.length > 0) {
				String subCommand = args[0];
				if (subCommand.equalsIgnoreCase("help")) {
					player.sendMessage("§8§m--+------------------------------------------------+--");
					player.sendMessage("");
					player.sendMessage(" §b/waradmin §8» §7Ouvrir le menu.");
					player.sendMessage(" §b/waradmin help §8» §7Obtenir l'aide.");
					player.sendMessage(" §b/waradmin addarena <name> §8» §7Créer une arène.");
					player.sendMessage(" §b/waradmin kitsave <name> §8» §7Save un kit.");
					player.sendMessage(" §b/waradmin kitgive <name> §8» §7Give un kit.");
					player.sendMessage(" §b/waradmin gameinfo <pseudo> §8» §7Info sur la game d'un joueur.");
					player.sendMessage("");
					player.sendMessage("§8§m--+------------------------------------------------+--");
					return true;
				}

				if (args.length == 2) {
					if (subCommand.equalsIgnoreCase("gameinfo")) {
						String name = args[1];
						if(Bukkit.getPlayer(name) != null && Bukkit.getPlayer(name).isOnline()){
							Player target = Bukkit.getPlayer(name);
							PlayerWrapper pw = WarFight.getPlayer(target);
							if(pw.getState().name().equalsIgnoreCase(PlayerStates.IN_GAME.name())){
								
								if(pw.getGame() != null){
									Game game = pw.getGame();
									player.sendMessage("§8§m--+------------------------------------------------+--");
									player.sendMessage("");
									player.sendMessage("  §8» §7Information de la game: §e"+game.getID()+"§7. §8(§c§l"+GamesManager.games_1vs1.size()+"§8)");
									player.sendMessage("");
									player.sendMessage("     §8▪ §7Joueur 1: §b"+game.getFirst_player().getName());
									player.sendMessage("     §8▪ §7Joueur 2: §b"+game.getSecond_player().getName());
									player.sendMessage("     §8▪ §7Temps (secs): §b"+game.getTime());
									player.sendMessage("     §8▪ §7Arène: §b"+game.getArena().getName());
									player.sendMessage("");
									player.sendMessage("§8§m--+------------------------------------------------+--");
									return true;
								}
								
							}else{
								player.sendMessage("§cErreur §8» §7Le joueur n'est dans aucunes games.");
								return true;
							}
						}
					}
					if (subCommand.equalsIgnoreCase("kitgive")) {
						String name = args[1];
						Kits kit = Kits.getKitsFromString(name);
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
						player.sendMessage("§aLoad "+kit.getName());
						return true;
					}
					if (subCommand.equalsIgnoreCase("kitsave")) {
						String name = args[1];
						Kits kit = Kits.getKitsFromString(name);
						KitsManager.setKitFromPlayer(kit, player, 20);
						player.sendMessage("§aSave "+kit.getName());
						return true;
					}
					if (subCommand.equalsIgnoreCase("addarena")) {
						String name = args[1];
						if (ArenasManager.containsArena(name)) {
							player.sendMessage("§cErreur §8» §7L'arène §e" + name + " §7existe déjà.");
							return true;
						} else {
							if(ArenasManager.containsSavedArena(name)){
								player.sendMessage("§cErreur §8» §7L'arène §e" + name + " §7existe déjà.");
								return true;
							}
							player.sendMessage("§6Arènes §8» §7Création de l'arène §b" + name + "§7...");
							try {
								ArenasManager.createArena(name, player);
								player.sendMessage("§6Arènes §8» §7L'arène §a" + name + " §7est désormais créée.");
								ArenasManager.initArenas();
								GuiManager.closePlayer(player);
								GuiManager.openGui(new ArenasGui(player));
							} catch (Exception e) {
								e.printStackTrace();
								player.sendMessage("§6Arènes §8» §7Erreur lors de la création de l'arène.");
							}
						}
					}
				}
			}

		}
		return true;
	}

}
