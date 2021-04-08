package fr.lowtix.warcore.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Boosters;
import fr.lowtix.warcore.utils.Gui;
import fr.lowtix.warcore.utils.HeadBuilder;
import fr.lowtix.wartracker.utils.ItemBuilder;

public class BoostersGui extends Gui{

	private WarPlayer wPlayer;
	
	public BoostersGui(Player player) {
		super("§8» §eLes boosters", 4, player);
		this.wPlayer = WarCore.getInstance().getUser(player);
	}

	@Override
	public void drawScreen() {
		
		for(int i = 0; i < 36; i++) {
			setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName(" ").build());
		}
		
		setItem(4, new ItemBuilder(Material.SIGN).setName("§e§nInformations sur les boosters").setLore(new String[] {
				" ",
				"§7Les boosters sont des multiplicateurs de gains,",
				"§7ils vous permettent à un moment donné de",
				"§7booster tous les joueurs qui sont connectés au",
				"§7serveur pendant §b1 heure§7. Ils pourront vous",
				"§7remercier et vous faire gagner des Coins et des Points.",
				"§7(Ils gagneront aussi leur part :p )",
		}).build());
		
		setItem(8, new HeadBuilder(player.getName()).setName("§7Vous avez §2"+wPlayer.getGemmes()).build());
		
		setItem(20, new ItemBuilder(Material.MINECART).setName("§e§lBOOSTER DE JOBS (POINTS)").setLore(new String[] {
				"§7Prix: §21 gemme",
				" ",
				"§7Doubler les points gagnés dans les métiers",
				"§7des joueurs.",
		}).build());
		
		setItem(22, new ItemBuilder(Material.DIAMOND_SWORD).setName("§e§lBOOSTER DE KILLS").setLore(new String[] {
				"§7Prix: §21 gemme",
				" ",
				"§7Doubler les gains des joueurs lors d'un Kill,",
				"§7si le KillBoost est à 200% alors le gain",
				"§7sera au total de §d400%§7.",
		}).build());
		setItem(24, new ItemBuilder(Material.BOAT).setName("§e§lBOOSTER DE JOBS (COINS)").setLore(new String[] {
				"§7Prix: §22 gemmes",
				" ",
				"§7Doubler les coins gagnés dans les métiers",
				"§7des joueurs.",
		}).build());
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		if(position == 20) {
			WarCore.getInstance().booster.requestBooster(player, Boosters.JOBS_POINTS);
		}
		if(position == 22) {
			WarCore.getInstance().booster.requestBooster(player, Boosters.KILLS);
		}
		if(position == 24) {
			WarCore.getInstance().booster.requestBooster(player, Boosters.JOBS_COINS);
		}
	}

}
