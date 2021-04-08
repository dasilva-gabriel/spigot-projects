package eu.pvpwarcraft.dayz.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.dayz.DayZ;
import eu.pvpwarcraft.dayz.configuration.LocationsList;
import eu.pvpwarcraft.dayz.configuration.LocationsManager;
import eu.pvpwarcraft.dayz.users.DayzPlayer;

public class AdministrationCommand implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (args.length == 0 || (args.length >= 1 && args[0].equalsIgnoreCase("help"))) {
				sendHelpMessages(player);
				return true;
			}

			if (args.length >= 1 && args[0].equalsIgnoreCase("setspawn")) {
				Location loc = player.getLocation();
				LocationsManager.saveLocation(LocationsList.SPAWN, loc);
				;
				DayZ.getInstance().essentialsSpawn.setSpawn(loc, "default");
				player.sendMessage(DayZ.getInstance().prefix + "§7Vous avez placé la position §f'§eSpawn§f' §7en §ax:§e"
						+ loc.getBlockX() + " §ay:§e" + loc.getBlockY() + " §az:§e" + loc.getBlockZ());
				return true;
			}

			if (args.length >= 1 && args[0].equalsIgnoreCase("spawn")) {
				if (args.length >= 2 && (Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline())) {
					Player target = (Player) Bukkit.getPlayer(args[1]);
					target.teleport(LocationsManager.getLocation(LocationsList.SPAWN, true));
					player.sendMessage(DayZ.getInstance().prefix + "§7Vous avez téléporter §b" + target.getName()
							+ " §7à la position du §f'§eSpawn§f'");
					target.sendMessage(
							DayZ.getInstance().prefix + "§7Vous avez été téléporter par §b" + player.getName() + "§7.");
					return true;
				}
				player.teleport(LocationsManager.getLocation(LocationsList.SPAWN, true));
				player.sendMessage(
						DayZ.getInstance().prefix + "§7Vous avez été téléporter §7à la position du §f'§eSpawn§f'");
				return true;
			}

			if (args.length >= 2 && args[0].equalsIgnoreCase("setwarp")) {
				Location loc = player.getLocation();
				boolean conclue = false;
				String warpName = args[1];
				for (LocationsList locations : LocationsList.values()) {
					if (warpName.equalsIgnoreCase(locations.getName())) {
						conclue = true;
						LocationsManager.saveLocation(locations, loc);
						player.sendMessage(DayZ.getInstance().prefix + "§7Vous avez placé la position §f'§e"
								+ locations.getName() + "§f' §7en §ax:§e" + loc.getBlockX() + " §ay:§e"
								+ loc.getBlockY() + " §az:§e" + loc.getBlockZ());
					}
				}

				if (!conclue) {
					player.performCommand("admin warps");
				}

				return true;
			}

			if (args.length >= 2 && args[0].equalsIgnoreCase("goto")) {
				Location loc = player.getLocation();
				String warpName = args[1];
				for (LocationsList locations : LocationsList.values()) {
					if (warpName.equalsIgnoreCase(locations.getName())
							&& LocationsManager.hasLocation(locations.getName())) {
						loc = LocationsManager.getLocation(locations, true);
						player.sendMessage(DayZ.getInstance().prefix
								+ "§7Vous avez été téléporter §7à la position du §f'§e" + locations.getName() + "§f'");
					} else {
						player.sendMessage(DayZ.getInstance().prefix + "§7Le warp §f'§e" + locations.getName()
								+ "§f' §7n'est pas défini.");
					}
				}
				player.teleport(loc);
				return true;
			} else if (args.length < 2 && args[0].equalsIgnoreCase("goto")) {
				sendHelpMessages(player);
				return true;
			}

			if (args.length >= 1 && args[0].equalsIgnoreCase("warps")) {
				String warps = "";
				for (LocationsList locations : LocationsList.values()) {
					if (!LocationsManager.hasLocation(locations.getName())) {
						warps += "§7, §c" + locations.getName();
					} else {
						warps += "§7, §a" + locations.getName();
					}
				}
				if (warps == "") {
					warps = "§cAucun";
				}
				player.sendMessage(DayZ.getInstance().prefix + "§7Liste des warps: " + warps.replaceFirst(",", ""));
			}

			if (args.length >= 1 && args[0].equalsIgnoreCase("getinfo")) {
				if (args.length >= 2 && (Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline())) {
					Player target = (Player) Bukkit.getPlayer(args[1]);
					sendInfo(player, target);
					return true;
				}
				sendInfo(player, player);
				return true;
			}

			if (args.length >= 1 && args[0].equalsIgnoreCase("spawn")) {
				if (args.length >= 2 && (Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline())) {
					Player target = (Player) Bukkit.getPlayer(args[1]);
					target.teleport(LocationsManager.getLocation(LocationsList.SPAWN, true));
					player.sendMessage(DayZ.getInstance().prefix + "§7Vous avez téléporter §b" + target.getName()
							+ " §7à la position du §f'§eSpawn§f'");
					target.sendMessage(
							DayZ.getInstance().prefix + "§7Vous avez été téléporter par §b" + player.getName() + "§7.");
					return true;
				}
				player.teleport(LocationsManager.getLocation(LocationsList.SPAWN, true));
				player.sendMessage(
						DayZ.getInstance().prefix + "§7Vous avez été téléporter §7à la position du §f'§eSpawn§f'");
				return true;
			}
			
			if (args.length >= 1 && args[0].equalsIgnoreCase("rankup")) {
				if (args.length >= 2 && (Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline())) {
					Player target = (Player) Bukkit.getPlayer(args[1]);
					DayzPlayer dzt = DayZ.getPlayer(target);
					if(dzt.getIgranks().getPosition() == 24){
						player.sendMessage(DayZ.getInstance().prefix + "§cErreur de rang: "+target.getName()+" à atteint le rang maximal. ("+dzt.getIgranks().getName()+")");
						return true;
					}
					player.sendMessage(DayZ.getInstance().prefix + "§7Vous avez fais passer §b"+target.getName()+" §7de rang. §e"+dzt.getIgranks().getName()+" §6➟ §e"+dzt.getIgranks().upRank().getName());
					target.sendMessage(DayZ.getInstance().prefix + "§7Le membre du Staff §b"+player.getName()+" §7vous à fais monter de rang. §e"+dzt.getIgranks().getName()+" §6➟ §e"+dzt.getIgranks().upRank().getName());
					dzt.setIgranks(dzt.getIgranks().upRank());
				}
				return true;
			}
			
			if (args.length >= 1 && args[0].equalsIgnoreCase("rankdown")) {
				if (args.length >= 2 && (Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline())) {
					Player target = (Player) Bukkit.getPlayer(args[1]);
					DayzPlayer dzt = DayZ.getPlayer(target);
					if(dzt.getIgranks().getPosition() == 1){
						player.sendMessage(DayZ.getInstance().prefix + "§cErreur de rang: "+target.getName()+" est rang le plus bas. ("+dzt.getIgranks().getName()+")");
						return true;
					}
					player.sendMessage(DayZ.getInstance().prefix + "§7Vous avez fais descendre §b"+target.getName()+" §7de rang. §e"+dzt.getIgranks().getName()+" §6➟ §e"+dzt.getIgranks().downRank().getName());
					target.sendMessage(DayZ.getInstance().prefix + "§7Le membre du Staff §b"+player.getName()+" §7vous à fais descendre de rang. §e"+dzt.getIgranks().getName()+" §6➟ §e"+dzt.getIgranks().downRank().getName());
					dzt.setIgranks(dzt.getIgranks().downRank());
				}
				return true;
			}

		} else {
			sender.sendMessage("Players only...");
		}
		return true;
	}

	private static void sendHelpMessages(Player player) {

		player.sendMessage(DayZ.getInstance().ligne);
		player.sendMessage(" §8» §7Légende: §b<obligatoire> §8| §b(option)");
		player.sendMessage(" §8» §2Administration:");
		player.sendMessage("     §8• §a/admin help §7montre cette page.");
		player.sendMessage("     §8• §a/admin setspawn §7permet de placer la position du spawn.");
		player.sendMessage("     §8• §a/admin spawn (joueur) §7permet se téléporter à la position du spawn.");
		player.sendMessage("     §8• §a/admin getinfo (joueur) §7permet se téléporter à la position du spawn.");
		player.sendMessage("     §8• §a/admin setwarp <nom> §7permet de placer un warp.");
		player.sendMessage("     §8• §a/admin goto <nom> §7permet se téléporter à la position d'un warp.");
		player.sendMessage("     §8• §a/admin warps §7permet d'obtenir la liste des warps.");
		player.sendMessage(DayZ.getInstance().ligne);

	}

	private static void sendInfo(Player sender, Player target) {
		DayzPlayer dpl = DayZ.getPlayer(target);
		sender.sendMessage(DayZ.getInstance().ligne);
		sender.sendMessage(" §8» §7Pseudo: §b" + target.getName());
		sender.sendMessage(" §8» §7Location: §bx§8:§a" + target.getLocation().getBlockX() + " §by§8:§a"
				+ target.getLocation().getBlockY() + " §bz§8:§a" + target.getLocation().getBlockZ());
		sender.sendMessage(" §8» §7Soif: §a" + dpl.getThirst() + "§8/§a200.0");
		sender.sendMessage(DayZ.getInstance().ligne);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String arg, String[] args) {
		if (sender instanceof Player && sender.isOp()) {
			if (args.length <= 1) {
				return Arrays
						.asList(new String[] { "help", "setspawn", "spawn", "getinfo", "setwarp", "goto", "warps" });
			}
		}
		return null;
	}

}
