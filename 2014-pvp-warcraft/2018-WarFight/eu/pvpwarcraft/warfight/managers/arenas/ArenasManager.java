package eu.pvpwarcraft.warfight.managers.arenas;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.warfight.WarFight;
import eu.pvpwarcraft.warfight.managers.arenas.Arena.ArenaType;

public class ArenasManager {

	public static Map<String, Arena> arenas = new HashMap<String, Arena>();

	public static void createArena(String name, Player creator) {
		File f = new File(WarFight.getInstance().getDataFolder() + "/arenas/");
		if (!f.exists()) {
			f.mkdirs();
		}
		f = new File(WarFight.getInstance().getDataFolder() + "/arenas/", name + ".yml");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Object config = YamlConfiguration.loadConfiguration(f);
		((FileConfiguration) config).options().copyDefaults(true);
		((FileConfiguration) config).addDefault("name", name);
		((FileConfiguration) config).addDefault("create_by", creator.getName());
		((FileConfiguration) config).addDefault("create_loc", locationToString(creator.getLocation()));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		((FileConfiguration) config).addDefault("create_date", dateFormat.format(date));
		((FileConfiguration) config).addDefault("type", Arena.ArenaType.HAS_BLOCK.name());
		try {
			((FileConfiguration) config).save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean containsArena(String name) {
		if (arenas.containsKey(name)) {
			return true;
		}
		return false;
	}

	public static Arena getArena(String name) {
		return (Arena) arenas.get(name);
	}
	
	public static List<Arena> getUnusedArenasWithType(ArenaType type) {
		List<Arena> result = new ArrayList<>();
		for (Entry<String, Arena> entry : ArenasManager.arenas.entrySet()) {

			Arena valeur = entry.getValue();
			
			if(!valeur.isInUse() && valeur.getType().name().equalsIgnoreCase(type.name())){
				result.add(valeur);
			}
			
		}
		return result;
	}

	public static List<Arena> getUnusedArenas() {
		List<Arena> result = new ArrayList<>();
		for (Entry<String, Arena> entry : ArenasManager.arenas.entrySet()) {

			Arena valeur = entry.getValue();
			
			if(!valeur.isInUse()){
				result.add(valeur);
			}
			
		}
		return result;
	}

	public static boolean isReady(String arenaName) {
		boolean result = true;
		File f = new File(WarFight.getInstance().getDataFolder() + "/arenas/", arenaName + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		for (ArenasLocations locs : ArenasLocations.values()) {
			if (!config.contains(locs.name())) {
				result = false;
			}
		}
		if (!config.contains("type")) {
			result = false;
		}
		return result;
	}

	public static void setType(String arenaName, Arena.ArenaType type) {
		File f = new File(WarFight.getInstance().getDataFolder() + "/arenas/", arenaName + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		config.set("type", type.name());
	}

	public static void addLocations(String arenaName, Location loc, ArenasLocations loctype) {
		File f = new File(WarFight.getInstance().getDataFolder() + "/arenas/", arenaName + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		config.set(loctype.name(), locationToString(loc));
		try {
			config.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initArenas() {
		arenas.clear();
		File folder = new File(WarFight.getInstance().getDataFolder() + "/arenas/");
		File[] files = folder.listFiles();
		for (final File f : files) {
			if (f.getName().endsWith(".yml")) {
				final FileConfiguration config = YamlConfiguration.loadConfiguration(f);
				String name = config.getString("name");
				String creator = config.getString("create_by");
				String date = config.getString("create_date");
				ArenaType type = Arena.ArenaType.valueOf(config.getString("type"));
				if (isReady(name)) {
					String s_posstart = config.getString(ArenasLocations.POS_START.name());
					String s_posend = config.getString(ArenasLocations.POS_END.name());
					String s_spawn1 = config.getString(ArenasLocations.SPAWN1.name());
					String s_spawn2 = config.getString(ArenasLocations.SPAWN2.name());
					arenas.put(name,
							new Arena(name, creator, date, locationFromString(s_posstart), locationFromString(s_posend),
									locationFromString(s_spawn1), locationFromString(s_spawn2), type));
				} else {
					HashMap<ArenasLocations, Location> states = new HashMap<>();
					for (ArenasLocations locs : ArenasLocations.values()) {
						if (!config.contains(locs.name())) {
							states.put(locs, null);
						} else {
							states.put(locs, locationFromString(config.getString(locs.name())));
						}
					}
					arenas.put(name,
							new Arena(name, creator, date, states.get(ArenasLocations.POS_START),
									states.get(ArenasLocations.POS_END), states.get(ArenasLocations.SPAWN1),
									states.get(ArenasLocations.SPAWN2), type));
				}
			}

		}
	}

	public static boolean containsSavedArena(String name) {
		boolean result = false;
		System.out.println("Trying to check " + name);
		try {
			if (!name.endsWith(".yml")) {
				name = name + ".yml";
			}
			File f = new File(WarFight.getInstance().getDataFolder() + "/arenas/", name);
			if (f.exists()) {
				result = true;
			} else {
				result = false;
			}
			System.out.println("Succefuly check " + name);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on check " + name);
			result = false;
		}
		return result;
	}

	public static void removeArena(String name) {
		System.out.println("Trying to remove " + name);
		try {
			if (!name.endsWith(".yml")) {
				name = name + ".yml";
			}
			File f = new File(WarFight.getInstance().getDataFolder() + "/arenas/", name);
			f.delete();
			System.out.println("Succefuly to remove " + name);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on remove " + name);
		}
	}

	public static String locationToString(Location loc) {
		return loc.getWorld().getName() + "%" + loc.getX() + "%" + loc.getY() + "%" + loc.getZ() + "%" + loc.getYaw()
				+ "%" + loc.getPitch();
	}

	public static Location locationFromString(String s) {
		String[] strings = s.split("%");
		return new Location(Bukkit.getWorld(strings[0]), Double.parseDouble(strings[1]), Double.parseDouble(strings[2]),
				Double.parseDouble(strings[3]), Float.parseFloat(strings[4]), Float.parseFloat(strings[5]));
	}

}
