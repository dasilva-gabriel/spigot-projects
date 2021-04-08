package eu.pvpwarcraft.skypvp.utils.skybox;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import eu.pvpwarcraft.skypvp.SkyPvP;
import eu.pvpwarcraft.skypvp.managers.EnumLocations;
import eu.pvpwarcraft.skypvp.managers.Locations;
import eu.pvpwarcraft.skypvp.utils.ItemBuilder;

public class SkyBoxGive implements Listener {

	public static Map<EnumLocations, Integer> canpichkup = new HashMap<EnumLocations, Integer>();

	private void spawnASCooldown(final Location loc, int time) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (all.getLocation().distance(loc) < 10) {
				all.playSound(loc, Sound.NOTE_SNARE_DRUM, 10.0F, 10.0F);
			}
		}
		final ArmorStand as2 = (ArmorStand) loc.getWorld().spawnEntity(loc.add(0.5, 0.3, 0.5), EntityType.ARMOR_STAND);
		as2.setSmall(true);
		as2.setVisible(false);
		as2.setBasePlate(false);
		as2.setGravity(false);
		as2.setNoDamageTicks(100);
		String bar = "";
		bar += "§3";
        for(int i = 1; i <= (30-time); i++){
            bar += "|";
        }
        bar += "§7";
        for(int i = 1; i <= time; i++){
            bar += "|";
        }
        as2.setCustomName(bar);
		as2.setCustomNameVisible(true);
		new BukkitRunnable() {

			@Override
			public void run() {
				as2.remove();

			}
		}.runTaskLater(SkyPvP.getInstance(), 22);
	}

	@EventHandler
	public void onClick(final PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() != null || e.getItem() != null) {
			if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
				if (e.getClickedBlock().getType() == Material.STAINED_GLASS) {
					e.setCancelled(true);
					if (e.getClickedBlock().getLocation().distance(Locations.getLocation(EnumLocations.SKYBOX1)) < 1) {
						e.setCancelled(true);
						if (canpichkup.get(EnumLocations.SKYBOX1) > 0) {
							e.getClickedBlock().getLocation().getWorld().playEffect(
									e.getClickedBlock().getLocation().add(0.5, 0.1, 0.5), Effect.VILLAGER_THUNDERCLOUD,
									500);
							p.playSound(p.getLocation(), Sound.VILLAGER_NO, 10.0F, 1.0F);
							p.setVelocity(p.getLocation().getDirection().multiply(-0.8).setY(0.3));
							p.sendMessage("§b§lSky§3§lPvP §8▶ §7La §6SkyBox §7se regénère... Patientez §e"
									+ SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX1) + " seconde(s)§7.");
							e.setCancelled(true);
							return;
						}
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (all.getLocation().distance(e.getClickedBlock().getLocation()) < 5) {
								all.playSound(all.getLocation(), Sound.CHICKEN_EGG_POP, 10.0F, 10.0F);
							}
						}
						p.getInventory().addItem(new ItemBuilder(Material.GOLDEN_APPLE).setAmount(3).build());
						p.sendMessage("§b§lSky§3§lPvP §8▶ §7Reçu §e+3 Pommes dorées");
						SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX1, 30);
						new BukkitRunnable() {

							@Override
							public void run() {
								spawnASCooldown(e.getClickedBlock().getLocation(),
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX1)));
								SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX1,
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX1) - 1));
								if (canpichkup.get(EnumLocations.SKYBOX1) < 1) {
									cancel();
									return;
								}

							}
						}.runTaskTimer(SkyPvP.getInstance(), 21, 21);
					}

					// SKYBOX2
					if (e.getClickedBlock().getLocation().distance(Locations.getLocation(EnumLocations.SKYBOX2)) < 1) {
						e.setCancelled(true);
						if (canpichkup.get(EnumLocations.SKYBOX2) > 0) {
							e.getClickedBlock().getLocation().getWorld().playEffect(
									e.getClickedBlock().getLocation().add(0.5, 0.1, 0.5), Effect.VILLAGER_THUNDERCLOUD,
									500);
							p.playSound(p.getLocation(), Sound.VILLAGER_NO, 10.0F, 1.0F);
							p.setVelocity(p.getLocation().getDirection().multiply(-0.8).setY(0.3));
							p.sendMessage("§b§lSky§3§lPvP §8▶ §7La §6SkyBox §7se regénère... Patientez §e"
									+ SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX2) + " seconde(s)§7.");
							e.setCancelled(true);
							return;
						}
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (all.getLocation().distance(e.getClickedBlock().getLocation()) < 5) {
								all.playSound(all.getLocation(), Sound.CHICKEN_EGG_POP, 10.0F, 10.0F);
							}
						}
						p.getInventory()
								.addItem(new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1).build());
						p.sendMessage("§b§lSky§3§lPvP §8▶ §7Reçu §e+1 Arc enchanté");
						SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX2, 30);
						new BukkitRunnable() {

							@Override
							public void run() {
								spawnASCooldown(e.getClickedBlock().getLocation(),
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX2)));
								SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX2,
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX2) - 1));
								if (canpichkup.get(EnumLocations.SKYBOX2) < 1) {
									cancel();
									return;
								}

							}
						}.runTaskTimer(SkyPvP.getInstance(), 21, 21);
					}

					// SKYBOX3
					if (e.getClickedBlock().getLocation().distance(Locations.getLocation(EnumLocations.SKYBOX3)) < 1) {
						e.setCancelled(true);
						if (canpichkup.get(EnumLocations.SKYBOX3) > 0) {
							e.getClickedBlock().getLocation().getWorld().playEffect(
									e.getClickedBlock().getLocation().add(0.5, 0.1, 0.5), Effect.VILLAGER_THUNDERCLOUD,
									500);
							p.playSound(p.getLocation(), Sound.VILLAGER_NO, 10.0F, 1.0F);
							p.setVelocity(p.getLocation().getDirection().multiply(-0.8).setY(0.3));
							p.sendMessage("§b§lSky§3§lPvP §8▶ §7La §6SkyBox §7se regénère... Patientez §e"
									+ SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX3) + " seconde(s)§7.");
							e.setCancelled(true);
							return;
						}
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (all.getLocation().distance(e.getClickedBlock().getLocation()) < 5) {
								all.playSound(all.getLocation(), Sound.CHICKEN_EGG_POP, 10.0F, 10.0F);
							}
						}
						p.getInventory().addItem(new ItemBuilder(Material.DIAMOND_HELMET).build());
						SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX3, 30);
						p.sendMessage("§b§lSky§3§lPvP §8▶ §7Reçu §e+1 Casque");
						new BukkitRunnable() {

							@Override
							public void run() {
								spawnASCooldown(e.getClickedBlock().getLocation(),
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX3)));
								SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX3,
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX3) - 1));
								if (canpichkup.get(EnumLocations.SKYBOX3) < 1) {
									cancel();
									return;
								}

							}
						}.runTaskTimer(SkyPvP.getInstance(), 21, 21);
					}

					// SKYBOX4
					if (e.getClickedBlock().getLocation().distance(Locations.getLocation(EnumLocations.SKYBOX4)) < 1) {
						e.setCancelled(true);
						if (canpichkup.get(EnumLocations.SKYBOX4) > 0) {
							e.getClickedBlock().getLocation().getWorld().playEffect(
									e.getClickedBlock().getLocation().add(0.5, 0.1, 0.5), Effect.VILLAGER_THUNDERCLOUD,
									500);
							p.playSound(p.getLocation(), Sound.VILLAGER_NO, 10.0F, 1.0F);
							p.setVelocity(p.getLocation().getDirection().multiply(-0.8).setY(0.3));
							p.sendMessage("§b§lSky§3§lPvP §8▶ §7La §6SkyBox §7se regénère... Patientez §e"
									+ SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX4) + " seconde(s)§7.");
							e.setCancelled(true);
							return;
						}
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (all.getLocation().distance(e.getClickedBlock().getLocation()) < 5) {
								all.playSound(all.getLocation(), Sound.CHICKEN_EGG_POP, 10.0F, 10.0F);
							}
						}
						p.getInventory().addItem(new ItemBuilder(Material.DIAMOND_BOOTS).build());
						p.sendMessage("§b§lSky§3§lPvP §8▶ §7Reçu §e+1 Bottes");
						SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX4, 30);
						new BukkitRunnable() {

							@Override
							public void run() {
								spawnASCooldown(e.getClickedBlock().getLocation(),
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX4)));
								SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX4,
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX4) - 1));
								if (canpichkup.get(EnumLocations.SKYBOX4) < 1) {
									cancel();
									return;
								}

							}
						}.runTaskTimer(SkyPvP.getInstance(), 21, 21);
					}

					// SKYBOX5
					if (e.getClickedBlock().getLocation().distance(Locations.getLocation(EnumLocations.SKYBOX5)) < 1) {
						e.setCancelled(true);
						if (canpichkup.get(EnumLocations.SKYBOX5) > 0) {
							e.getClickedBlock().getLocation().getWorld().playEffect(
									e.getClickedBlock().getLocation().add(0.5, 0.1, 0.5), Effect.VILLAGER_THUNDERCLOUD,
									500);
							p.playSound(p.getLocation(), Sound.VILLAGER_NO, 10.0F, 1.0F);
							p.setVelocity(p.getLocation().getDirection().multiply(-0.8).setY(0.3));
							p.sendMessage("§b§lSky§3§lPvP §8▶ §7La §6SkyBox §7se regénère... Patientez §e"
									+ SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX5) + " seconde(s)§7.");
							e.setCancelled(true);
							return;
						}
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (all.getLocation().distance(e.getClickedBlock().getLocation()) < 5) {
								all.playSound(all.getLocation(), Sound.CHICKEN_EGG_POP, 10.0F, 10.0F);
							}
						}
						p.getInventory().addItem(new ItemBuilder(Material.DIAMOND_LEGGINGS).build());
						p.sendMessage("§b§lSky§3§lPvP §8▶ §7Reçu §e+1 Pantalon");
						SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX5, 30);
						new BukkitRunnable() {

							@Override
							public void run() {
								spawnASCooldown(e.getClickedBlock().getLocation(),
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX5)));
								SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX5,
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX5) - 1));
								if (canpichkup.get(EnumLocations.SKYBOX5) < 1) {
									cancel();
									return;
								}

							}
						}.runTaskTimer(SkyPvP.getInstance(), 21, 21);
					}

					// SKYBOX6
					if (e.getClickedBlock().getLocation().distance(Locations.getLocation(EnumLocations.SKYBOX6)) < 1) {
						e.setCancelled(true);
						if (canpichkup.get(EnumLocations.SKYBOX6) > 0) {
							e.getClickedBlock().getLocation().getWorld().playEffect(
									e.getClickedBlock().getLocation().add(0.5, 0.1, 0.5), Effect.VILLAGER_THUNDERCLOUD,
									500);
							p.playSound(p.getLocation(), Sound.VILLAGER_NO, 10.0F, 1.0F);
							p.setVelocity(p.getLocation().getDirection().multiply(-0.8).setY(0.3));
							p.sendMessage("§b§lSky§3§lPvP §8▶ §7La §6SkyBox §7se regénère... Patientez §e"
									+ SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX6) + " seconde(s)§7.");
							e.setCancelled(true);
							return;
						}
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (all.getLocation().distance(e.getClickedBlock().getLocation()) < 5) {
								all.playSound(all.getLocation(), Sound.CHICKEN_EGG_POP, 10.0F, 10.0F);
							}
						}
						p.getInventory().addItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE).build());
						p.sendMessage("§b§lSky§3§lPvP §8▶ §7Reçu §e+1 Plastron");
						SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX6, 30);
						new BukkitRunnable() {

							@Override
							public void run() {
								spawnASCooldown(e.getClickedBlock().getLocation(),
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX6)));
								SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX6,
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX6) - 1));
								if (canpichkup.get(EnumLocations.SKYBOX6) < 1) {
									cancel();
									return;
								}

							}
						}.runTaskTimer(SkyPvP.getInstance(), 21, 21);
					}

					// SKYBOX7
					if (e.getClickedBlock().getLocation().distance(Locations.getLocation(EnumLocations.SKYBOX7)) < 1) {
						e.setCancelled(true);
						if (canpichkup.get(EnumLocations.SKYBOX7) > 0) {
							e.getClickedBlock().getLocation().getWorld().playEffect(
									e.getClickedBlock().getLocation().add(0.5, 0.1, 0.5), Effect.VILLAGER_THUNDERCLOUD,
									500);
							p.playSound(p.getLocation(), Sound.VILLAGER_NO, 10.0F, 1.0F);
							p.setVelocity(p.getLocation().getDirection().multiply(-0.8).setY(0.3));
							p.sendMessage("§b§lSky§3§lPvP §8▶ §7La §6SkyBox §7se regénère... Patientez §e"
									+ SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX7) + " seconde(s)§7.");
							e.setCancelled(true);
							return;
						}
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (all.getLocation().distance(e.getClickedBlock().getLocation()) < 5) {
								all.playSound(all.getLocation(), Sound.CHICKEN_EGG_POP, 10.0F, 10.0F);
							}
						}
						p.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).build());
						p.sendMessage("§b§lSky§3§lPvP §8▶ §7Reçu §e+1 Epée");
						SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX7, 30);
						new BukkitRunnable() {

							@Override
							public void run() {
								spawnASCooldown(e.getClickedBlock().getLocation(),
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX7)));
								SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX7,
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX7) - 1));
								if (canpichkup.get(EnumLocations.SKYBOX7) < 1) {
									cancel();
									return;
								}

							}
						}.runTaskTimer(SkyPvP.getInstance(), 21, 21);
					}

					// SKYBOX8
					if (e.getClickedBlock().getLocation().distance(Locations.getLocation(EnumLocations.SKYBOX8)) < 1) {
						e.setCancelled(true);
						if (canpichkup.get(EnumLocations.SKYBOX8) > 0) {
							e.getClickedBlock().getLocation().getWorld().playEffect(
									e.getClickedBlock().getLocation().add(0.5, 0.1, 0.5), Effect.VILLAGER_THUNDERCLOUD,
									500);
							p.playSound(p.getLocation(), Sound.VILLAGER_NO, 10.0F, 1.0F);
							p.setVelocity(p.getLocation().getDirection().multiply(-0.8).setY(0.3));
							p.sendMessage("§b§lSky§3§lPvP §8▶ §7La §6SkyBox §7se regénère... Patientez §e"
									+ SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX8) + " seconde(s)§7.");
							e.setCancelled(true);
							return;
						}
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (all.getLocation().distance(e.getClickedBlock().getLocation()) < 5) {
								all.playSound(all.getLocation(), Sound.CHICKEN_EGG_POP, 10.0F, 10.0F);
							}
						}
						p.getInventory().addItem(new ItemBuilder(Material.ARROW).setAmount(15).build());
						p.sendMessage("§b§lSky§3§lPvP §8▶ §7Reçu §e+15 Flèches");
						SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX8, 30);
						new BukkitRunnable() {

							@Override
							public void run() {
								spawnASCooldown(e.getClickedBlock().getLocation(),
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX8)));
								SkyBoxGive.canpichkup.put(EnumLocations.SKYBOX8,
										Integer.valueOf(SkyBoxGive.canpichkup.get(EnumLocations.SKYBOX8) - 1));
								if (canpichkup.get(EnumLocations.SKYBOX8) < 1) {
									cancel();
									return;
								}

							}
						}.runTaskTimer(SkyPvP.getInstance(), 21, 21);
					}

				}
			}
		}
	}

}
