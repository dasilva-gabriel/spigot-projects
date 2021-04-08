package fr.lowtix.cheatpatch.guis;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.cheatpatch.CheatPatch;
import fr.lowtix.cheatpatch.managers.miner.PlayerMiner;
import fr.lowtix.cheatpatch.utils.Gui;
import fr.lowtix.cheatpatch.utils.HeadBuilder;
import fr.lowtix.cheatpatch.utils.ItemBuilder;

public class MinerGui extends Gui {

	public static ArrayList<MinerGui> openGuis = new ArrayList<MinerGui>();
	
	private PlayerMiner miner;
	private int dmg = 0;
	
	public MinerGui(Player player, PlayerMiner miner) {
		super("§f» §6WarMiner", 4, player);
		this.miner = miner;
		if(miner == null) {
			CheatPatch.getInstance().getMinerManager().getMiner(player);
		}
	}
	
	@Override
	public void onOpen() {
		if(!openGuis.contains(this)) {
			openGuis.add(this);
		}
	}
	
	@Override
	public void onClose() {
		if(openGuis.contains(this)) {
			openGuis.remove(this);
		}
	}
	
	@Override
	public void drawScreen() {
		
		if(miner.getAliveSeconds() <= 0 || getPlayer().hasPermission("warcraft.miner")) {
			player.sendMessage("§8[§6WarMiner§8] §cVotre miner est épuisé, cela fais 6 heures que vous le faites.");
			give();
			CheatPatch.getInstance().getMinerManager().removeMiner(player, true);
			
		}
		
		setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(dmg).build());
		setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(dmg).build());
		
		setItem(2, new ItemBuilder(Material.PAPER).setName("§6Informations sur le Miner").setLore(new String[] {
				"§7Niveau: §b" + miner.getLevel(),
				"§7Temps restants: §e"+ (miner.getAliveSeconds() < 60 ? "§e"+miner.getAliveSeconds()+" seconde(s)" : "§e"+((int) miner.getAliveSeconds()/60)+" minute(s)")
		}).build());
		
		setItem(4, new HeadBuilder(player.getName()).setName("§6WarMiner de §e"+player.getName()).build());
		
		setItem(6, new ItemBuilder(Material.PAPER).setName("§6Informations générale").setLore(new String[] {
				"§7Le §eMiner §7est un outil qui vous permet lorsque vous êtes",
				"§7AFK, de pouvoir continuer a farmer. Automatiquement, vous",
				"§7recevrez de l'argent, des clefs etc..."
		}).build());
		
		for(int i = 9; i <= 17; i++) {
			setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(dmg).build());
		}
		
		setItem(20, new ItemBuilder(Material.EMERALD).setName("§aEmeraudes générées §a"+miner.getEmerald_earned()).build());
		setItem(22, new ItemBuilder(Material.GOLD_NUGGET).setName("§eArgent généré §6"+miner.getMoney_earned()).build());
		setItem(24, new ItemBuilder(Material.TRIPWIRE_HOOK).setName("§dClés générés §5"+miner.getKeys_earned()).build());
		
		setItem(29, new ItemBuilder(Material.FLOWER_POT_ITEM).setName("§aRécupérer ses gains").build());
		setItem(31, new ItemBuilder(Material.SLIME_BALL).setName("§aActualiser l'interface").build());
		setItem(33, new ItemBuilder(Material.BARRIER).setName("§cArrêter de miner").build());
		
		dmg++;
		if(dmg == 15) {
			dmg = 1;
		}
	}

	@Override
	public void onClick(int position, ItemStack item, ClickType click) {
		
		if(position == 33) {
			
			player.sendMessage("§8[§6WarMiner§8] §cVous ne minez plus.");
			give();
			CheatPatch.getInstance().getMinerManager().removeMiner(player, true);
			close();
		
		} else if(position == 31) {
			
			drawScreen();
			
		} else if(position == 29) {
			
			give();
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private void give() {
		
		player.sendMessage("§8[§6WarMiner§8] §aVous avez récupéré vos gains.");
		
		for(int i = 0; i < miner.getEmerald_earned(); i++) {
			player.getInventory().addItem(new ItemBuilder(Material.EMERALD).build());
		}
		
		if(miner.getKeys_earned() < 1) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate givekey " + player.getName() + " vote " + miner.getKeys_earned());
		}
		
		CheatPatch.getInstance().getEconomy().depositPlayer(player.getName(), miner.getMoney_earned());
		
		miner.setEmerald_earned(0);
		miner.setKeys_earned(0);
		miner.setMoney_earned(0);
	}

}
