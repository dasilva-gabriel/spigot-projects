package eu.pvpwarcraft.warcore.guis;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.warcore.manager.SancList;
import eu.pvpwarcraft.warcore.manager.SanctionManager;
import eu.pvpwarcraft.warcore.utils.Gui;
import eu.pvpwarcraft.warcore.utils.GuiManager;
import eu.pvpwarcraft.warcore.utils.HeadBuilder;
import eu.pvpwarcraft.warcore.utils.ItemBuilder;

public class SanctionGui_Admin extends Gui{
	
	public SanctionGui_Admin(Player player, Player target) {
		super("§e"+target.getName(), 6, player);
	}

	@Override
	public void drawScreen() {
		
		String name = getPlayer().getOpenInventory().getTitle().replace("§e", "");
		
		if(Bukkit.getPlayer(name) == null || !Bukkit.getPlayer(name).isOnline()){
			getPlayer().getOpenInventory().close();
			player.sendMessage("§8[§4»§8] §cLe joueur est déconnecté. La sanction doit être appliquée à la main.");
			return;
		}
		
		Player target = Bukkit.getPlayer(name);
		
		for(int i = 9; i <= 17; i++){
			setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").setDamage(0).build());
		}
		
		setItem(0, new HeadBuilder(target.getName()).setName("§7Joueur §8» §e"+target.getName()).setDamage(0).build());
		setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").setDamage(0).build());
		setItem(2, new ItemBuilder(Material.NAME_TAG).setName("§8[§3»§8] §bInfraction au niveau du Chat.").build());
		setItem(4, new ItemBuilder(Material.MAP).setName("§8[§3»§8] §bInfraction au niveau de règles particulères.").build());
		setItem(6, new ItemBuilder(Material.DIAMOND_SWORD).setName("§8[§3»§8] §bInfraction de triche.").build());
		setItem(8, new ItemBuilder(Material.BLAZE_POWDER).setName("§8[§3»§8] §bInfraction administratives. §8(§cRéservé aux AdminG et Fondateurs§8)").build());
		
		setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setName(" ").setDamage(1).build());
		setItem(18, new ItemBuilder(Material.GOLD_BLOCK).setName("§8[§2»§8] §aAdministation > Fraude boutique").build());
		setItem(19, new ItemBuilder(Material.BARRIER).setName("§8[§2»§8] §aAdministation > Insolent").build());
	}
	
	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		Player p = getPlayer();
		String name = getPlayer().getOpenInventory().getTitle().replace("§e", "");
		
		if(Bukkit.getPlayer(name) == null || !Bukkit.getPlayer(name).isOnline()){
			getPlayer().getOpenInventory().close();
			player.sendMessage("§8[§4»§8] §cLe joueur est déconnecté. La sanction doit être appliquée à la main.");
			return;
		}
		
		Player target = Bukkit.getPlayer(name);
		
		if(position == 2){
			p.getOpenInventory().close();
			GuiManager.openGui(new SanctionGui_Chat(p, target));
		}
		
		if(position == 4){
			p.getOpenInventory().close();
			GuiManager.openGui(new SanctionGui_Rules(p, target));
		}
		
		if(position == 6){
			p.getOpenInventory().close();
			GuiManager.openGui(new SanctionGui_Cheat(p, target));
		}
		
		if(position == 8){
			p.getOpenInventory().close();
			if(p.getName().equalsIgnoreCase("lowtix_") || p.getName().equalsIgnoreCase("xtempete") || p.getName().equalsIgnoreCase("dragoh")){
				GuiManager.openGui(new SanctionGui_Admin(p, target));
			}else{
				p.sendMessage("§8[§4»§8] §cNon mais tu es qui ? C'est écrit §f'&eAdminG ou Fondateur§f'§c... Hop hop hop au boulot ! c:");
			}
		}
		
		if(position == 18){
			p.getOpenInventory().close();
			p.sendMessage("§8[§2✔§8] §aEnvoi de la sanction...");
			SanctionManager.sancPlayer(p, target, SancList.ADMIN_FRAUDE);
		}
		if(position == 19){
			p.getOpenInventory().close();
			p.sendMessage("§8[§2✔§8] §aEnvoi de la sanction...");
			SanctionManager.sancPlayer(p, target, SancList.ADMIN_INSOLENT);
		}
	}

	
	
}
