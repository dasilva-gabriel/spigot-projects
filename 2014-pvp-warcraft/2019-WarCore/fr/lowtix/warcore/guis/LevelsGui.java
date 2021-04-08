package fr.lowtix.warcore.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Levels;
import fr.lowtix.warcore.utils.Gui;
import fr.lowtix.wartracker.utils.ItemBuilder;

public class LevelsGui extends Gui{

	private WarPlayer wPlayer;
	
	public LevelsGui(Player player) {
		super("§8» §eLes niveaux", 4, player);
		this.wPlayer = WarCore.getInstance().getUser(player);
	}

	@Override
	public void drawScreen() {
		
		for(int i = 0; i < 36; i++) {
			setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName(" ").build());
		}
		
		setItem(4, new ItemBuilder(Material.SIGN).setName("§e§nInformations sur les niveaux").setLore(new String[] {
				" ",
				"§7Les niveaux (ou levels) sont des sortes de grades",
				"§7vous permettant de gagner en puissance et/ou",
				"§7gagner du stuff sans dépenser de l'argent sur",
				"§7le serveur (§f§ocf EULA ou engagement PvP-Warcraft§7).",
				"§7Ainsi vous pourrez jouer comme tout le monde",
				"§7sans être désaventagé par apport aux gradés.",
		}).build());
		
		int i = 18;
		for(Levels level : Levels.values()) {
			ItemBuilder builder =  new ItemBuilder(Material.INK_SACK);
			if(level.getId() < wPlayer.getLevel().getId()) {
				builder.setDamage(10);
				builder.setName("§8[§2✔§8] §7§oNiveau §e§l"+level.getDisplayName());
			} else if(level.getId() > wPlayer.getLevel().getId()) {
				builder.setDamage(8);
				builder.setName("§7§oNiveau §e§l"+level.getDisplayName());
				builder.setName("§8[§4✕§8] §7§oNiveau §e§l"+level.getDisplayName()+" §8("+"§7Prix: §e"+level.getMoneyForUp()+" ⛃ §7et §f"+level.getPointsForUp()+" ✴§8)");
			} else if(level.getId() == wPlayer.getLevel().getId()) {
				builder.setDamage(14);
				builder.setName("§8[§6✛§8] §7§oNiveau §e§l"+level.getDisplayName());
			}
			
			builder.setLore(level.getDesc());
			setItem(i, builder.build());
			i++;
		}
	}
	
	

}
