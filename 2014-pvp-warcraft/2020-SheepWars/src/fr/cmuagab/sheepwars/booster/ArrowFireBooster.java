package fr.cmuagab.sheepwars.booster;

import java.util.HashMap;
import java.util.Map;

import fr.cmuagab.sheepwars.handler.Booster;
import fr.cmuagab.sheepwars.handler.Booster.BoosterAction;
import fr.cmuagab.sheepwars.handler.Booster.TriggerAction;
import fr.cmuagab.sheepwars.handler.Team;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ArrowFireBooster implements BoosterAction {
    private final Map<Team, Long> teams = new HashMap<>();

    @Override
    public boolean onStart(final Player player, final Team team, final Booster booster) {
        this.teams.put(team, System.currentTimeMillis());
        return true;
    }

    @Override
    public boolean onEvent(final Player player, final Event event, final TriggerAction trigger) {
        if (trigger == TriggerAction.ARROW_LAUNCH) {
            final ProjectileLaunchEvent launchEvent = (ProjectileLaunchEvent) event;
            final Team team = Team.getPlayerTeam(player);
            if (team != null && this.teams.containsKey(team)) {
                final long time = this.teams.get(team);
                if (System.currentTimeMillis() - time > 15000) {
                    this.teams.remove(team);
                } else {
                    ((Arrow) launchEvent.getEntity()).setFireTicks(Integer.MAX_VALUE);
                    return true;
                }
            }
        }
        return false;
    }
}
