package fr.cmuagab.sheepwars.handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.util.ItemBuilder;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

@SuppressWarnings("deprecation")
public enum Team {
    // @formatter:off
    BLUE("blue", "Bleue", Material.INK_SACK, DyeColor.BLUE.getDyeData(), ChatColor.BLUE, Color.BLUE),
    RED("red", "Rouge", Material.INK_SACK, DyeColor.RED.getDyeData(), ChatColor.RED, Color.RED),
    SPEC("spec", "Spec", null, (short) 0, ChatColor.GRAY, null);
    // @formatter:on

    public static Team getPlayerTeam(final Player player) {
        if (player == null) {
            return Team.SPEC;
        } else if (!player.hasMetadata("team")) {
            for (final Team team : Team.values()) {
                if (team.craftTeam.getPlayers().contains(player)) { return team; }
            }
        } else {
            final String teamName = player.getMetadata("team").get(0).asString();
            for (final Team team : Team.values()) {
                if (team.name.equals(teamName)) { return team; }
            }
        }
        return Team.SPEC;
    }

    public static Team getRandomTeam() {
        return Team.BLUE.getOnlinePlayers().size() < Team.RED.getOnlinePlayers().size() ? Team.BLUE : Team.RED;
    }

    public static Team getTeam(final String name) {
        for (final Team team : Team.values()) {
            if (team.craftTeam != null && team.craftTeam.getName().equalsIgnoreCase(name)) { return team; }
        }
        return null;
    }

    public static Team getTeam(final ChatColor color) {
        for (final Team team : Team.values()) {
            if (team.color == color) { return team; }
        }
        return null;
    }

    private String name;
    @Getter
    private final String displayName;
    @Getter
    private ItemStack icon;
    @Getter
    private final ChatColor color;
    @Getter
    private final Color leatherColor;
    @Getter
    private org.bukkit.scoreboard.Team craftTeam;
    @Getter
    private List<Location> spawns;
    private int lastSpawn;

    private Team(final String name, final String displayName, final Material material, final short durability, final ChatColor color, final Color leatherColor) {
        this.name = name;
        this.displayName = displayName;
        if (material != null) {
            this.icon = new ItemBuilder(material, 1, durability).setTitle(color + "Rejoindre l'équipe " + displayName).build();
        }
        this.color = color;
        this.leatherColor = leatherColor;
        this.lastSpawn = 0;
        this.createTeam(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    public void addPlayer(final Player player) {
        player.setMetadata("team", new FixedMetadataValue(SheepWarsPlugin.i, this.name));
        player.setPlayerListName(this.color + (player.getName().length() > 14 ? player.getName().substring(0, 14) : player.getName()));
        this.craftTeam.addPlayer(player);
        if (this != Team.SPEC) {
            final Score score = this.getScore();
            score.setScore(score.getScore() + 1);
        }
    }

    public void removePlayer(final Player player) {
        player.removeMetadata("team", SheepWarsPlugin.i);
        this.craftTeam.removePlayer(player);
        if (this != Team.SPEC) {
            final Score score = this.getScore();
            score.setScore(score.getScore() - 1);
        }
    }

    public Score getScore() {
        final Score objScore = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("teams").getScore(this.color + "Equipe " + this.displayName);
        return objScore;
    }

    public void setScore(final int score) {
        final Score objScore = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("teams").getScore(this.color + "Equipe " + this.displayName);
        if (score == 0) {
            objScore.setScore(1);
        }
        objScore.setScore(score);
    }

    public Set<Player> getOnlinePlayers() {
        final Set<Player> players = new HashSet<>();
        for (final OfflinePlayer offline : this.craftTeam.getPlayers()) {
            if (offline instanceof Player) {
                players.add((Player) offline);
            }
        }
        return players;
    }

    public void broadcastMessage(final String msg) {
        for (final Player player : this.getOnlinePlayers()) {
            player.sendMessage(msg);
        }
    }

    public Location getNextSpawn() {
        if (this.spawns.isEmpty()) {
            return null;
        } else if (this.spawns.size() == this.lastSpawn) {
            this.lastSpawn = 0;
        }
        return this.spawns.get(this.lastSpawn++);
    }

    public void createTeam(final Scoreboard scoreboard) {
        this.craftTeam = scoreboard.getTeam(this.name);
        if (this.craftTeam == null) {
            this.craftTeam = scoreboard.registerNewTeam(this.name);
        }
        this.craftTeam.setPrefix(this.color.toString());
        this.craftTeam.setDisplayName(this.name);
        this.craftTeam.setAllowFriendlyFire(false);
        this.craftTeam.setCanSeeFriendlyInvisibles(true);
        this.spawns = new ArrayList<>();
    }
}
