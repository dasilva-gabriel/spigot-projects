package fr.lowtix.warbox.managers;

import java.util.HashMap;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class BowTrailsManager {
	
	public HashMap<Entity, Effect> arrows = new HashMap<Entity, Effect>();
	
	public void addArrow(Entity ent, Effect effect) {
		if(!arrows.containsKey(ent)) {
			arrows.put(ent, effect);
		}
	}
	
	public void removeArrow(Entity ent) {
		if(arrows.containsKey(ent)) {
			arrows.remove(ent);
		}
	}
	
	public void spawnEffect(Location loc, Effect effect) {
		loc.getWorld().playEffect(loc.add(0, 0.1, 0), effect, 1);
	}

}
