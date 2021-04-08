package eu.pvpwarcraft.warfight.managers.arenas;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class Arena {

	private String name;
	private String creator;
	private String date;
	private Location pos_start;
	private Location pos_end;
	private Location spawn1;
	private Location spawn2;
	private ArenaType type;
	private boolean inUse;

	public Arena(String name, String creator, String date, Location pos_start, Location pos_end, Location spawn1,
			Location spawn2, ArenaType type) {
		this.name = name;
		this.creator = creator;
		this.date = date;
		this.pos_start = pos_start;
		this.pos_end = pos_end;
		this.spawn1 = spawn1;
		this.spawn2 = spawn2;
		this.type = type;
		this.inUse = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Location getPos_start() {
		return pos_start;
	}

	public void setPos_start(Location pos_start) {
		this.pos_start = pos_start;
	}

	public Location getPos_end() {
		return pos_end;
	}

	public void setPos_end(Location pos_end) {
		this.pos_end = pos_end;
	}

	public Location getSpawn1() {
		return spawn1;
	}

	public void setSpawn1(Location spawn1) {
		this.spawn1 = spawn1;
	}

	public Location getSpawn2() {
		return spawn2;
	}

	public void setSpawn2(Location spawn2) {
		this.spawn2 = spawn2;
	}

	public ArenaType getType() {
		return type;
	}

	public void setType(ArenaType type) {
		this.type = type;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public List<Location> getMapBlocks() {
		return getBlocks(this.pos_start, this.pos_end);
	}

	public List<Location> getBlocks(Location start, Location end) {
		List<Location> locations = new ArrayList<>();

		for (double x = start.getX(); x <= end.getX(); x++) {
			for (double y = start.getY(); y <= end.getY(); y++) {
				for (double z = start.getZ(); z <= end.getZ(); z++) {
					locations.add(new Location(start.getWorld(), x, y, z));
				}
			}
		}

		return locations;
	}

	public boolean isInMap(Location location) {
		return isIn(this.pos_start, this.pos_end, location);
	}

	private boolean isIn(Location start, Location end, Location location) {
		return (((start.getX() <= location.getX()) && (end.getX() >= location.getX()))
				&& ((start.getY() <= location.getY()) && (end.getY() >= location.getY()))
				&& ((start.getZ() <= location.getZ()) && (end.getZ() >= location.getZ())));
	}

	public enum ArenaType {

		HAS_BLOCK, NOT_BLOCK;

	}

}
