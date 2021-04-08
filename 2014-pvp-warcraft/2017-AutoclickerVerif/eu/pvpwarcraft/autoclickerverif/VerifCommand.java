package eu.pvpwarcraft.autoclickerverif;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class VerifCommand implements CommandExecutor {
	
	public static HashMap<Player, PlayerWrapper> verifiers = new HashMap<>();
	private String perm;

	public VerifCommand(String perm) {
		this.perm = perm;
	}

	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§4Warrior §8§l» §7Seul les joueurs peuvent faire cela.");
			return true;
		}
		if (!sender.hasPermission(this.perm)) {
			sender.sendMessage("§4Warrior §8§l» §7Cette commande est réservée aux membres de la modération.");
			return true;
		}
		if (args.length != 1) {
			sender.sendMessage("§4Warrior §8§l» §7Faites §b/verif <joueur>§7.");
			return true;
		}

		String pseudo = args[0];
		if (Bukkit.getPlayer(pseudo) != null) {
			Inventory i = Bukkit.createInventory(null, 45, "§8[§4»§8] §c" + pseudo);
			verifiers.put((Player) sender, PlayerWrapper.getByPlayer(Bukkit.getPlayer(pseudo)));
			((Player) sender).openInventory(i);
		} else {
			((Player) sender).sendMessage("§4Warrior §8§l» §7Joueur actuellement hors-ligne.");
		}

		return true;
	}
}