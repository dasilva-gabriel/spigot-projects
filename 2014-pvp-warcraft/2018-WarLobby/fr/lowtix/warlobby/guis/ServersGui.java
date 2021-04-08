package fr.lowtix.warlobby.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.lowtix.warlobby.WarLobby;
import fr.lowtix.warlobby.utils.Gui;
import fr.lowtix.warlobby.utils.ItemBuilder;

public class ServersGui extends Gui {
	
	public ServersGui(Player player) {
		super("PvP-Warcraft ▎ Nos serveurs", 3, player);
	}
	
	@Override
	public void drawScreen() {
		
		setItem(11, new ItemBuilder(Material.DIAMOND_SWORD).setName("§6PvP-Cheat §7§lV2 §f▎ §d§lNOUVELLE VERSION").setLore(new String[] {
				"§8Type: Aventure, Combat",
				"",
				"§7Construisez un empire dans un monde",
				"§7où le combat est d'ordre, Des stuffs",
				"§7boostés en enchantement et bien plus encore...",
				"",
				"§7Version: §f1.8.8",
				"§7Joueurs: §a" + WarLobby.getInstance().getServersManager().factionsCount + "§8/150",
				"",
				"§eCliquez pour rejoindre le serveur"
		}).build());
		
		setItem(12, new ItemBuilder(Material.IRON_HELMET).setName("§6PvP-Box §7§lV2 §f▎  §e§lNOUVEAUTE").setLore(new String[] {
				"§8Type: Combat, Compétition",
				"",
				"§7Entrez dans une arène où personne ne pourra",
				"§7en sortir vivant. Grimpez au sommet en gagnant",
				"§7de l'expérience grâce a votre puissance!",
				"",
				"§7Version: §f1.8.8",
				"§7Joueurs: §a"+ WarLobby.getInstance().getServersManager().pvpboxCount +"§8/75",
				"",
				"§eCliquez pour rejoindre le serveur"
		}).build());
		
		setItem(13, new ItemBuilder(Material.STONE).setDamage(2).setName("§6Skyblock").setLore(new String[] {
				"§8Type: Survie, Compétition, Construction",
				"",
				"§7Commencez votre aventure sur une île avec des",
				"§7ressources limitées. Créez votre équipe et batissez",
				"§7un empire pour que votre ville devienne la",
				"§7première puissance du serveur!",
				"",
				"§7Version: §f1.13.2",
				"§7Joueurs: §a?§8/150",
				"",
				"§r  §8§m--------------------§r  §e",
				" §cLe serveur est en construction! Restez",
				" §cinformés de son ouverture prochaine via",
				" §cnotre serveur Discord!",
				"§r  §8§m--------------------§r  §e"
		}).build());
		
		setItem(15, new ItemBuilder(Material.EYE_OF_ENDER).setName("§6Lobby").setLore(new String[] {
				"",
				"§eCliquez pour vous téléportez au spawn du Lobby"
		}).build());
	
	}

	@Override
	public void onClick(int position, ItemStack item, ClickType click) {

		if (position == 11) {

			player.sendMessage("§8[§3§lW§fInfo§8] §6Vous allez être redirigé vers le serveur §fPvPCheat§6...");

			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("pvpcheat");
			player.sendPluginMessage(WarLobby.getInstance(), "BungeeCord", out.toByteArray());

		} else if (position == 12) {

			player.sendMessage("§8[§3§lW§fInfo§8] §6Vous allez être redirigé vers le serveur §fPvPBox§6...");

			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("pvpbox");
			player.sendPluginMessage(WarLobby.getInstance(), "BungeeCord", out.toByteArray());

		} else if (position == 15) {

			player.teleport(player.getWorld().getSpawnLocation());

		}
	}

}


