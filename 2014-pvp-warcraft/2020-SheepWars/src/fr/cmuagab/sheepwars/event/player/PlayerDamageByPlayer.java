package fr.cmuagab.sheepwars.event.player;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Kit;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Team;
import fr.cmuagab.sheepwars.util.MathUtils;
import fr.cmuagab.sheepwars.util.ParticleEffect;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class PlayerDamageByPlayer extends SheepListener {
    public PlayerDamageByPlayer(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onEntityDamageByPlayer(final EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            Entity damagerEntity = event.getDamager();
            if (damagerEntity instanceof Projectile) {
                damagerEntity = ((Projectile) damagerEntity).getShooter();
            }
            if (damagerEntity instanceof Player) {
                final Player damager = (Player) damagerEntity;
                if (Step.isStep(Step.LOBBY) || Team.getPlayerTeam(player) == Team.SPEC) {
                    event.setCancelled(true);
                    if (!Step.isStep(Step.LOBBY) && event.getCause() == DamageCause.PROJECTILE) {
                        player.teleport(player.getLocation().add(0, 5, 0));
                        player.setFlying(true);
                        final Arrow arrow = damager.launchProjectile(Arrow.class);
                        arrow.setVelocity(event.getDamager().getVelocity());
                    }
                } else {
                    final Kit kit = Kit.getPlayerKit(damager);
                    final ItemStack item = damager.getItemInHand();
                    if (((kit == Kit.BETTER_BOW && event.getCause() == DamageCause.PROJECTILE) || (kit == Kit.BETTER_SWORD && item != null && item.getType() == Material.WOOD_SWORD)) && MathUtils.randomBoolean(0.05F)) {
                        float random = MathUtils.random();
                        if (random < 0.37F) {
                            random = 0.45F;
                        }
                        ParticleEffect.CRIT.display(event.getEntity().getLocation(), random, 1, random, 0, 50);
                    }
                }
            }
        }
    }
}
