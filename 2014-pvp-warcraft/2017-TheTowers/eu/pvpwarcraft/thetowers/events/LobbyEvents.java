package eu.pvpwarcraft.thetowers.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import eu.pvpwarcraft.thetowers.TheTowers;
import eu.pvpwarcraft.thetowers.handler.Step;
import eu.pvpwarcraft.thetowers.handler.Team.Teams;
import eu.pvpwarcraft.thetowers.handler.TowerPlayer;
import eu.pvpwarcraft.thetowers.managers.TeamManager;
import eu.pvpwarcraft.thetowers.utils.PlayerUtils;

public class LobbyEvents implements Listener {

	@EventHandler
	public void onFoodLoss(FoodLevelChangeEvent event) {
		if (Step.isStep(Step.LOBBY)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (Step.isStep(Step.LOBBY)) {
			event.setCancelled(true);
			if (event.getEntity() instanceof Player) {
				if (event.getEntity().getLocation().getY() < 2) {
					TowerPlayer towerpl = TheTowers.getPlayer((Player) event.getEntity());
					towerpl.getPlayer().setFallDistance(0);
					towerpl.getPlayer().setNoDamageTicks(20);
					towerpl.getPlayer().teleport(TheTowers.getTeam(Teams.AUCUNE).getSpawn());
					PlayerUtils.playSound(towerpl.getPlayer(), Sound.ENTITY_WITHER_AMBIENT);
					towerpl.getPlayer().sendMessage("§6TheTowers §8» §7Vous ne pouvez pas sortir de la zone d'attente...");
				}
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (Step.isStep(Step.LOBBY)) {
			if (event.getItem() == null) {
				return;
			}
			if (event.getAction() == null) {
				return;
			}

			event.setCancelled(true);

			Player player = event.getPlayer();
			TowerPlayer towerpl = TheTowers.getPlayer(player);

			if (event.getItem().getType().equals(Material.SKULL_ITEM)) {
				Teams rteam = TeamManager.randomTeam();
				TeamManager.removeTeam(towerpl, towerpl.getTeam());
				TeamManager.addTeam(towerpl, rteam);
				PlayerUtils.playSound(player, Sound.ENTITY_VILLAGER_TRADING);
				player.sendMessage("§6TheTowers §8» §7Vous rejoignez aléatoirement l'équipe "
						+ TheTowers.getTeam(towerpl.getTeam()).getColor()
						+ TheTowers.getTeam(towerpl.getTeam()).getName() + ".");
				return;
			}

			if (event.getItem().getItemMeta().getDisplayName().contains("Orange")
					&& event.getItem().getType().equals(Material.STAINED_CLAY)) {

				if (TeamManager.isInTeam(towerpl, Teams.ORANGE)) {

					PlayerUtils.playSound(player, Sound.ENTITY_VILLAGER_NO);
					player.sendMessage("§6TheTowers §8» §cVous êtes déjà dans cette equipe.");
					return;

				} else if (TeamManager.canJoinTeam(towerpl, Teams.ORANGE)) {

					PlayerUtils.playSound(player, Sound.ENTITY_VILLAGER_NO);
					player.sendMessage("§6TheTowers §8» §cCette équipe contient déjà beaucoup de joueurs.");
					return;

				} else {

					TeamManager.removeTeam(towerpl, towerpl.getTeam());
					TeamManager.addTeam(towerpl, Teams.ORANGE);
					PlayerUtils.playSound(player, Sound.ENTITY_VILLAGER_YES);
					player.sendMessage("§6TheTowers §8» §7Vous rejoignez l'équipe "
							+ TheTowers.getTeam(towerpl.getTeam()).getColor()
							+ TheTowers.getTeam(towerpl.getTeam()).getName() + ".");
					return;

				}

			}

			if (event.getItem().getItemMeta().getDisplayName().contains("Cyan")
					&& event.getItem().getType().equals(Material.STAINED_CLAY)) {

				if (TeamManager.isInTeam(towerpl, Teams.CYAN)) {

					PlayerUtils.playSound(player, Sound.ENTITY_VILLAGER_NO);
					player.sendMessage("§6TheTowers §8» §cVous êtes déjà dans cette equipe.");
					return;

				} else if (TeamManager.canJoinTeam(towerpl, Teams.CYAN)) {

					PlayerUtils.playSound(player, Sound.ENTITY_VILLAGER_NO);
					player.sendMessage("§6TheTowers §8» §cCette équipe contient déjà beaucoup de joueurs.");
					return;

				} else {

					TeamManager.removeTeam(towerpl, towerpl.getTeam());
					TeamManager.addTeam(towerpl, Teams.CYAN);
					PlayerUtils.playSound(player, Sound.ENTITY_VILLAGER_YES);
					player.sendMessage("§6TheTowers §8» §7Vous rejoignez l'équipe "
							+ TheTowers.getTeam(towerpl.getTeam()).getColor()
							+ TheTowers.getTeam(towerpl.getTeam()).getName() + ".");
					return;
				}

			}

		}
	}

}
