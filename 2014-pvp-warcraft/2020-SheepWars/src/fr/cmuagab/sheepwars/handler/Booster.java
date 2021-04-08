package fr.cmuagab.sheepwars.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import fr.cmuagab.sheepwars.booster.ArrowBackBooster;
import fr.cmuagab.sheepwars.booster.ArrowFireBooster;
import fr.cmuagab.sheepwars.booster.MoreSheepBooster;
import fr.cmuagab.sheepwars.booster.NauseaBooster;
import fr.cmuagab.sheepwars.booster.PoisonBooster;
import fr.cmuagab.sheepwars.booster.RegenerationBooster;
import fr.cmuagab.sheepwars.booster.ResistanceBooster;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public enum Booster {
    // @formatter:off
    POISON(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Poison " + ChatColor.YELLOW + "(4 secondes)", new PoisonBooster(), new ArrayList<TriggerAction>()),
    NAUSEA(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Nausée " + ChatColor.YELLOW + "(10 secondes)", new NauseaBooster(), new ArrayList<TriggerAction>()),
    MORE_SHEEP(ChatColor.AQUA + "" + ChatColor.BOLD + "Plus de moutons " + ChatColor.YELLOW + "(+1 mouton)", new MoreSheepBooster(), new ArrayList<TriggerAction>()),
    REGENERATION(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Régénération " + ChatColor.YELLOW + "(6 secondes)", new RegenerationBooster(), new ArrayList<TriggerAction>()),
    RESISTANCE(ChatColor.WHITE + "" + ChatColor.BOLD + "Résistance " + ChatColor.YELLOW + "(30 secondes)", new ResistanceBooster(), new ArrayList<TriggerAction>()),
    ARROW_FIRE(ChatColor.GOLD + "" + ChatColor.BOLD + "Flèches de feu " + ChatColor.YELLOW + "(15 secondes)", new ArrowFireBooster(), Arrays.asList(TriggerAction.ARROW_LAUNCH)),
    ARROW_BACK(ChatColor.GRAY + "" + ChatColor.BOLD + "Flèches de recul " + ChatColor.YELLOW + "(10 secondes)", new ArrowBackBooster(), Arrays.asList(TriggerAction.ARROW_LAUNCH));
    // @formatter:on

    public static interface BoosterAction {
        public boolean onStart(final Player player, final Team team, final Booster booster);

        public boolean onEvent(final Player player, final Event event, final TriggerAction trigger);
    }

    public static enum TriggerAction {
        ARROW_LAUNCH
    }

    @Getter
    private String name;
    @Getter
    private BoosterAction action;
    @Getter
    private List<TriggerAction> triggers;

    private Booster(final String name, final BoosterAction action, final List<TriggerAction> triggers) {
        this.name = name;
        this.action = action;
        this.triggers = triggers;
    }
}
