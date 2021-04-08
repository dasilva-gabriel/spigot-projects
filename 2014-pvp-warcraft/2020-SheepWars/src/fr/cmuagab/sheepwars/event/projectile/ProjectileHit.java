package fr.cmuagab.sheepwars.event.projectile;

import java.lang.reflect.Field;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Booster;
import fr.cmuagab.sheepwars.handler.Team;
import fr.cmuagab.sheepwars.packet.PacketPlayOutActionBar;
import fr.cmuagab.sheepwars.util.MathUtils;
import net.minecraft.server.v1_7_R4.EntityArrow;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ProjectileHit extends SheepListener {
    public ProjectileHit(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onProjectileHit(final ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            final Arrow arrow = (Arrow) event.getEntity();
            arrow.remove();
            if (arrow.getShooter() instanceof Player) {
                final Player player = (Player) arrow.getShooter();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        try {
                            final EntityArrow entityArrow = ((CraftArrow) arrow).getHandle();
                            final Field fieldX = EntityArrow.class.getDeclaredField("d");
                            final Field fieldY = EntityArrow.class.getDeclaredField("e");
                            final Field fieldZ = EntityArrow.class.getDeclaredField("f");
                            fieldX.setAccessible(true);
                            fieldY.setAccessible(true);
                            fieldZ.setAccessible(true);
                            final int x = fieldX.getInt(entityArrow);
                            final int y = fieldY.getInt(entityArrow);
                            final int z = fieldZ.getInt(entityArrow);
                            final Block block = arrow.getWorld().getBlockAt(x, y, z);
                            if (block.getType() == Material.WOOL && ProjectileHit.this.plugin.isBooster(block.getLocation())) {
                                final Team team = Team.getPlayerTeam(player);
                                if (team != null) {
                                    block.setType(Material.AIR);
                                    block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getTypeId());
                                    final Booster booster = Booster.values()[MathUtils.random.nextInt(Booster.values().length)];
                                    Bukkit.broadcastMessage(SheepWarsPlugin.prefix + team.getColor() + player.getName() + " active " + booster.getName());
                                    booster.getAction().onStart(player, team, booster);
                                    new PacketPlayOutActionBar(team.getColor() + player.getName() + " active " + booster.getName()).broadcast();
                                    ;
                                }
                            }
                        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }.runTaskLater(this.plugin, 1);
            }
        }
    }
}
