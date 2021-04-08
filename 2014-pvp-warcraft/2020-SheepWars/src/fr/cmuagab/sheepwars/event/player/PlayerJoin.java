package fr.cmuagab.sheepwars.event.player;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Step;
import fr.cmuagab.sheepwars.handler.Team;
import fr.cmuagab.sheepwars.handler.Title;
import fr.cmuagab.sheepwars.packet.PacketPlayOutActionBar;
import fr.cmuagab.sheepwars.scheduler.BeginCountdown;
import fr.cmuagab.sheepwars.util.ItemBuilder;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PlayerConnection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.spigotmc.ProtocolInjector;

public class PlayerJoin extends SheepListener {
    public PlayerJoin(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        player.getInventory().clear();
        if (!Step.isStep(Step.LOBBY) && player.hasPermission("games.join")) {
            event.setJoinMessage(null);
            this.plugin.setSpectator(player, false);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
            final Location spawn = Team.SPEC.getNextSpawn();
            player.teleport(spawn == null ? this.plugin.lobbyLocation : spawn);
            player.setFlying(true);
        } else if (Step.isStep(Step.LOBBY)) {
            event.setJoinMessage(SheepWarsPlugin.prefix + ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " a rejoint la partie " + ChatColor.GREEN + "(" + Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers() + ")");
            for (final Team team : Team.values()) {
                if (team.getSpawns() != null && !team.getSpawns().isEmpty() && team != Team.SPEC) {
                    player.getInventory().addItem(team.getIcon());
                }
            }
            this.plugin.loadData(player);
            player.getInventory().setItem(8, new ItemBuilder(Material.NAME_TAG).setTitle(ChatColor.GOLD + "Kits " + ChatColor.GRAY + "(Clic-droit)").build());
            player.setGameMode(GameMode.ADVENTURE);
            player.teleport(this.plugin.lobbyLocation);
            if (Bukkit.getOnlinePlayers().length >= 1 && !BeginCountdown.started && !SheepWarsPlugin.i.boosters.isEmpty()) {
                for (final Team team : Team.values()) {
                    if (team != Team.SPEC && (team.getSpawns() == null || team.getSpawns().isEmpty())) {
                        BeginCountdown.started = true;
                        return;
                    }
                }
                new BeginCountdown(this.plugin);
            }
            if (Title.isPlayerRightVersion(player)) {
                final PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
                final IChatBaseComponent header = ChatSerializer.a("{\"color\": \"" + ChatColor.DARK_AQUA.name().toLowerCase() + "\", \"text\": \"" + ChatColor.GOLD + ChatColor.BOLD + "War Of Sheeps\"}");
                final IChatBaseComponent footer = ChatSerializer.a("{\"color\": \"" + ChatColor.GRAY.name().toLowerCase() + "\", \"text\": \"" + ChatColor.GRAY + "Equipe séléctionné : " + ChatColor.GOLD + "Aucune\"}");
                connection.sendPacket(new ProtocolInjector.PacketTabHeader(header, footer));
                final Title title = new Title(ChatColor.BOLD + "" + ChatColor.YELLOW + ChatColor.MAGIC + "|" + ChatColor.AQUA + ChatColor.MAGIC + "|" + ChatColor.GREEN + ChatColor.MAGIC + "|" + ChatColor.RED + ChatColor.MAGIC + "|" + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "| " + ChatColor.AQUA + ChatColor.BOLD + "Bienvenue " + ChatColor.GREEN + ChatColor.BOLD + player.getName() + ChatColor.YELLOW + ChatColor.MAGIC + " |" + ChatColor.AQUA + ChatColor.MAGIC + "|" + ChatColor.GREEN + ChatColor.MAGIC + "|" + ChatColor.RED + ChatColor.MAGIC + "|" + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "|");
                title.setSubtitle(ChatColor.GRAY + "Choisissez votre équipe " + ChatColor.BLUE + "Bleue" + ChatColor.GRAY + ChatColor.BOLD + "/" + ChatColor.RED + "Rouge");
                title.send(player);
                new PacketPlayOutActionBar(ChatColor.GRAY + "Bienvenue " + ChatColor.GOLD + player.getName()).send(player);
            }
        }
    }
}
