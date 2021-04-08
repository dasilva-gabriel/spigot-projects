package fr.lowtix.palatraining.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import fr.lowtix.palaarenasmanager.PalaArenasManager;
import fr.lowtix.palaarenasmanager.enums.SchemType;
import fr.lowtix.palaarenasmanager.managers.Arena;
import fr.lowtix.palatraining.PalaTraining;

public class GameArenasManager {

	private HashMap<Arena, Boolean> activeArenas = new HashMap<Arena, Boolean>();
	private ArrayList<String> arenaCreationIDs = new ArrayList<String>();
	
	// FALSE: Not in use
	// TRUE: In use
	private LinkedHashMap<Location, Boolean> locs = new LinkedHashMap<Location, Boolean>();
	
	public GameArenasManager() {
		PalaTraining.getInstance().getLogger().info("Start of loading...");
		
		try {
			
			generateLocations(Bukkit.getWorld("world"), 125, 500, 500, 1000, 8000, -5000, 5000);
			
			for(int i = 0; i <= 6; i++) {
				createArena(false);
			}
			
			for(int i = 0; i <= 4; i++) {
				createArena(true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			PalaTraining.getInstance().getLogger().warning("An error occurred while loading the plugin !");
		}
	}
	
	public Arena getArenaForGame(boolean team) {
		
		Arena arena = null;
		for(Arena arenas : activeArenas.keySet()) {
			Boolean ac = activeArenas.get(arenas);
			if(ac) {
				if(team && arenas.getType().equals(SchemType.BIG)) {
					arena = arenas;
					break;
				} else if(!team && arenas.getType().equals(SchemType.SMALL)) {
					arena = arenas;
					break;
				}
				
			}
		}
		
		if(arena != null) {
			activeArenas.put(arena, false);
		}
		
		return arena;
		
	}
	
	public void readyArenaPostGame(Arena arena) {
		if(!activeArenas.get(arena)) {
			if((System.currentTimeMillis() - arena.getCreatedDate().getTime()) > (1800 *1000)) {
				activeArenas.remove(arena);
				PalaArenasManager.getInstance().getArenasManager().deleteArena(arena);
				
				if(locs.containsKey(arena.getPastePosition()) && locs.get(arena.getPastePosition())) {
					locs.put(arena.getPastePosition(), false);
				}
				
			} else {
				activeArenas.put(arena, true);
			}
			
		}
	}
	
	public void readyArena(Arena arena) {
		arenaCreationIDs.remove(arena.getId());
		activeArenas.put(arena, true);
	}
	
	public ArrayList<Arena> getReadyArenas(boolean team) {
		ArrayList<Arena> arenasResult = new ArrayList<Arena>();
		for(Arena arenas : activeArenas.keySet()) {
			Boolean ac = activeArenas.get(arenas);
			if(ac) {
				if(!team) {
					if(arenas.getType().equals(SchemType.SMALL)) {
						arenasResult.add(arenas);
					}
				} else {
					arenasResult.add(arenas);
				}
			}
		}
		
		return arenasResult;
		
	}
	
	public void createArena(boolean teamType) {
		Location loc = getLocation();
		
		Arena arena = PalaArenasManager.getInstance().getArenasManager().createArena(loc, (teamType == true ? SchemType.BIG : SchemType.SMALL));
		arenaCreationIDs.add(arena.getId());
	}
	
	public ArrayList<String> getArenaCreationIDs() {
		return arenaCreationIDs;
	}
	
	/*
	 * Gestion des locations
	 */
	
	public Location getLocation() {
		for(Location locations : locs.keySet()) {
			if(!locs.get(locations)) {
				locs.put(locations, true);
				return locations;
			}
		}
		return null;
	}
	
	// world, 125, 500, 500, 1000, 8000, -5000, 5000
	public void generateLocations(World world, int y, int xDist, int zDist, int minX, int maxX, int minZ, int maxZ) {
		
		for(int x = minX; x <= maxX; x+=xDist) {
			
			for(int z = minZ; z <= maxZ; z+=zDist) {
				
				locs.put(new Location(world, x, y, z), false);
			}
			
		}
		
	}
	
}
