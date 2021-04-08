package fr.cmuagab.sheepwars.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import fr.cmuagab.sheepwars.SheepWarsPlugin;

import org.bukkit.event.Listener;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SheepListener implements Listener {
    protected SheepWarsPlugin plugin;
}
