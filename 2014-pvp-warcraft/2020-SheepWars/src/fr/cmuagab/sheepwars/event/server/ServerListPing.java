package fr.cmuagab.sheepwars.event.server;

import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.event.SheepListener;
import fr.cmuagab.sheepwars.handler.Step;

import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPing extends SheepListener {
    public ServerListPing(final SheepWarsPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onServerListPing(final ServerListPingEvent event) {
        event.setMotd(Step.getMOTD());
    }
}
