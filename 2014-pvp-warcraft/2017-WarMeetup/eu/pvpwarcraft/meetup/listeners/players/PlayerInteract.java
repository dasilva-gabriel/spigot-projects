package eu.pvpwarcraft.meetup.listeners.players;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.meetup.Meetup;
import eu.pvpwarcraft.meetup.handler.Step;
import eu.pvpwarcraft.meetup.managers.types.Types;
import eu.pvpwarcraft.meetup.managers.types.TypesManager;
import eu.pvpwarcraft.meetup.utils.ItemBuilder;
import eu.pvpwarcraft.meetup.utils.guis.GuiManager;
import eu.pvpwarcraft.meetup.utils.guis.HubGui;
import eu.pvpwarcraft.meetup.utils.guis.TypesGui;
import eu.pvpwarcraft.warfightapi.WarFightAPI;
import eu.pvpwarcraft.warfightapi.managers.User;

public class PlayerInteract implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(event.getItem() == null) return;
		if(event.getAction() == null) return;
		if(Step.isStep(Step.PRE_GAME)){
			if(event.getItem().getType().equals(Material.BOW) || event.getItem().getType().equals(Material.FISHING_ROD)){
				event.setCancelled(true);
			}
			player.updateInventory();
		}
		if(Step.isStep(Step.LOBBY)){
			if(event.getItem().getType().equals(Material.BED)){
				GuiManager.openGui(new HubGui(player));
			}
			if(event.getItem().getType().equals(Material.BOOK) && !event.getItem().getItemMeta().getDisplayName().contains("Attend")){
				User user = WarFightAPI.getInstance().getUsers().get(player.getName());
				player.sendMessage("§8§m---+---------------------------------------------+---");
				player.sendMessage("   §8» §7Profil: §3"+player.getName()+"§7. §8[§2"+user.getGemmes()+" gemmes§8]");
				player.sendMessage("   §8» §7Kills: §a§l"+user.getStats().getMeetUpKills()+"§7.");
				player.sendMessage("   §8» §7Morts: §c§l"+user.getStats().getMeetUpDeaths()+"§7.");
				player.sendMessage("");
				player.sendMessage("   §8» §7Ping: §3"+((CraftPlayer)player).getHandle().ping+"§7 MS.");
				player.sendMessage("§8§m---+---------------------------------------------+---");
				player.getInventory().setItem(0, new ItemBuilder(Material.BOOK).setName("§8• §bProfil UHCMeetup §8(§cAttendez avant de réutiliser§8) §8•").build());
				new BukkitRunnable() {
					
					@Override
					public void run() {
						if(Step.isStep(Step.LOBBY)){
							player.getInventory().setItem(0, new ItemBuilder(Material.BOOK).setName("§8• §bProfil UHCMeetup §8•").build());
						}
						
					}
				}.runTaskLater(Meetup.getInstance(), 80);
			}else if( event.getItem().getType().equals(Material.BOOK) && event.getItem().getItemMeta().getDisplayName().contains("Attend")){
				player.sendMessage("§cErreur §8» §7Patientez avant de réutiliser...");
			}
			if(event.getItem().getType().equals(Material.ANVIL)){
				GuiManager.openGui(new TypesGui(player));
			}
		}else{
			if(TypesManager.getTypesWinner().equals(Types.ANVIL_LESS)){
				if(event.getItem().getType().equals(Material.ANVIL)){
					player.sendMessage("§cErreur §8» §7Cet item est interdit dans ce type de jeu.");
					event.setCancelled(true);
					player.updateInventory();
				}
			}
			if(TypesManager.getTypesWinner().equals(Types.APPLE_LESS)){
				if(event.getItem().getType().equals(Material.GOLDEN_APPLE)){
					player.sendMessage("§cErreur §8» §7Cet item est interdit dans ce type de jeu.");
					event.setCancelled(true);
					player.updateInventory();
				}
			}
			if(TypesManager.getTypesWinner().equals(Types.ROD_LESS)){
				if(event.getItem().getType().equals(Material.FISHING_ROD)){
					player.sendMessage("§cErreur §8» §7Cet item est interdit dans ce type de jeu.");
					event.setCancelled(true);
					player.updateInventory();
				}
			}
			if(TypesManager.getTypesWinner().equals(Types.BOW_LESS)){
				if(event.getItem().getType().equals(Material.BOW)){
					player.sendMessage("§cErreur §8» §7Cet item est interdit dans ce type de jeu.");
					event.setCancelled(true);
					player.updateInventory();
				}
			}
		}
	}

}
