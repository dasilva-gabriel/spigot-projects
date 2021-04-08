package fr.cmuagab.sheepwars.event.player;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Team;
import fr.cmuagab.sheepwars.handler.Title;
import fr.cmuagab.sheepwars.packet.PacketWorldBorder;

import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDamage extends SheepListener {
    public PlayerDamage(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerDamage(final EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            final Team playerTeam = Team.getPlayerTeam(player);
            if (Step.isStep(Step.LOBBY) || playerTeam == Team.SPEC || (event.getCause() == DamageCause.FALL && player.getVehicle() instanceof Sheep)) {
                event.setCancelled(true);
            } else if (Title.isPlayerRightVersion(player)) {
                final PacketWorldBorder worldBorder = new PacketWorldBorder(player.getLocation().getX(), player.getLocation().getZ(), 60000000, 0, 60000000, 0);
                worldBorder.send(player);
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if (player.isOnline()) {
                            worldBorder.setAction(PacketWorldBorder.Action.SET_WARNING_BLOCKS);
                            worldBorder.setWarningBlocks(0);
                            worldBorder.send(player);
                        }
                    }
                }.runTaskLater(this.plugin, 10);
            }
        }
    }
}
