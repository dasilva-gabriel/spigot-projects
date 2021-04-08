package fr.lowtix.warcore.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warcore.utils.Gui;
import fr.lowtix.warcore.utils.GuiManager;
import fr.lowtix.wartracker.utils.ItemBuilder;

public class RanksGui extends Gui{

	public RanksGui(Player player) {
		super("§6✧ §eNotre boutique §6✧", 4, player);
	}

	@Override
	public void drawScreen() {
		
		setItem(4, new ItemBuilder(Material.BOOK).setName("§e§nEngagements PvP-Warcraft").setLore(new String[] {
				" ",
				"§7Nous nous engageons a repecter les règles",
				"§7appliquées par Mojang. Le respect de ces",
				"§7règles nommées §c§lEULA §7nous obligent à respecter",
				"§7les règles liées aux achats (boutique). C'est pour cela",
				"§7que vous ne pourrez pas trouver des grades qui",
				"§7vous avantagent par apport au Gameplay des autres. Comme",
				"§7par exemples des Kits exclu...",
				"§bNous remercions tous nos joueurs pour l'attention",
				"§bportée au serveur. §7Ainsi nous pouvons collaborer",
				"§7(vous, nous et Mojang) dans de parfaites conditions.",
		}).build());
		
		setItem(11, new ItemBuilder(Material.DIAMOND).setName("§7§oGrade boutique §b§lDIAMOND").setLore(new String[] {
				" ",
				"§e§nAtouts du grade:",
				"  §f- §7Préfixe dans le chat",
				"  §f- §7Mise en avant dans la Tabulation",
				"  §f- §7Pouvoir d'écrire en blanc",
				"  §f- §7Anti Spam/Flood §b1 seconde",
				"  §f- §7Accès au §eserveur créatif",
				"  §f- §7Accès au §d/afk",
				"  §f- §e§ko§r §7Customisation du Tab et du NameTag §e§ko",
				"  §f- §7Accès à l'§2auto remerciement",
				"  §f- §7Accès aux §fparamètres avancés",
				"  §f- §310 tags §7exclusifs",
				"  §f- §bGrade sur le TeamSpeak",
				" ",
				"§e§nAutre:",
				"  §f- §7KillBoost §b§l+10%§b/joueur",
				"  §f- §7Boost de booster §a§l+10%§a/joueur",
				"  §f- §7Soutient du serveur: §4❤❤❤",
				" ",
				"   §2✔ §aAchat en Boutique disponible §8(§7§oCliquez§8)",
		}).build());
		setItem(21, new ItemBuilder(Material.GOLD_INGOT).setName("§7§oGrade boutique §e§lGOLD").setLore(new String[] {
				" ",
				"§e§nAtouts du grade:",
				"  §f- §7Préfixe dans le chat",
				"  §f- §7Mise en avant dans la Tabulation",
				"  §f- §7Pouvoir d'écrire en blanc",
				"  §f- §7Anti Spam/Flood §b2 secondes",
				"  §f- §7Accès au §eserveur créatif",
				"  §f- §7Accès au §6/logo",
				"  §f- §7Accès aux §fparamètres avancés",
				"  §f- §35 tags §7exclusifs",
				"  §f- §bGrade sur le TeamSpeak",
				" ",
				"§e§nAutre:",
				"  §f- §7KillBoost §b§l+5%§b/joueur",
				"  §f- §7Boost de booster §a§l+5%§a/joueur",
				"  §f- §7Soutient du serveur: §4❤❤§8❤",
				" ",
				"   §2✔ §aAchat en Boutique disponible §8(§7§oCliquez§8)",
		}).build());
		setItem(19, new ItemBuilder(Material.IRON_INGOT).setName("§7§oGrade boutique §f§lSILVER").setLore(new String[] {
				" ",
				"§e§nAtouts du grade:",
				"  §f- §7Préfixe dans le chat",
				"  §f- §7Mise en avant dans la Tabulation",
				"  §f- §7Pouvoir d'écrire en blanc",
				"  §f- §7Anti Spam/Flood §b3 secondes",
				"  §f- §7Accès aux §fparamètres avancés",
				"  §f- §32 tags §7exclusifs",
				"  §f- §bGrade sur le TeamSpeak",
				" ",
				"§e§nAutre:",
				"  §f- §7KillBoost §b§l+2%§b/joueur",
				"  §f- §7Boost de booster §a§l+2%§a/joueur",
				"  §f- §7Soutient du serveur: §4❤§8❤❤",
				" ",
				"   §2✔ §aAchat en Boutique disponible §8(§7§oCliquez§8)",
		}).build());
		
		setItem(16, new ItemBuilder(Material.ENCHANTMENT_TABLE).setName("§e§nNos boosters").build());
		setItem(25, new ItemBuilder(Material.PAPER).setName("§e§nTags du chat").build());
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		if(position == 16) {
			GuiManager.closePlayer(player);
			GuiManager.openGui(new BoostersGui(player));
		}
		
		if(position == 19) {
			GuiManager.closePlayer(player);
			getPlayer().sendMessage("§8[§e§l?§8] §7Lien: §6http://pvp-warcraft.fr/shop");
		} else if(position == 31) {
			GuiManager.closePlayer(player);
			getPlayer().sendMessage("§8[§e§l?§8] §7Lien: §6http://pvp-warcraft.fr/shop");
		} else if(position == 11) {
			GuiManager.closePlayer(player);
			getPlayer().sendMessage("§8[§e§l?§8] §7Lien: §6http://pvp-warcraft.fr/shop");
		}
	}
	

}
