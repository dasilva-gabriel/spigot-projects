package eu.pvpwarcraft.warfight.managers.queues;

import eu.pvpwarcraft.warfight.managers.kits.Kits;

public enum Queues {
	
	RANKED_BUILDUHC(0, "Ranked BuildUHC 1v1", Kits.BUILDUHC, true),
	UNRANKED_BUILDUHC(1, "UnRanked BuildUHC 1v1", Kits.BUILDUHC, false),
	RANKED_ARCHER(2, "Ranked Archer 1v1", Kits.ARCHER, true),
	UNRANKED_ARCHER(3, "UnRanked Archer 1v1", Kits.ARCHER, false),
	RANKED_NODEBUFF(4, "Ranked NoDebuff 1v1", Kits.NODEBUFF, true),
	UNRANKED_NODEBUFF(5, "UnRanked NoDebuff 1v1", Kits.NODEBUFF, false),
	RANKED_DEBUFF(6, "Ranked Debuff 1v1", Kits.DEBUFF, true),
	UNRANKED_DEBUFF(7, "UnRanked Debuff 1v1", Kits.DEBUFF, false),
	RANKED_GAPPLE(8, "Ranked GApple 1v1", Kits.GAPPLE, true),
	UNRANKED_GAPPLE(9, "UnRanked GApple 1v1", Kits.GAPPLE, false),
	RANKED_COMBO(10, "Ranked GApple 1v1", Kits.COMBO, true),
	UNRANKED_COMBO(11, "UnRanked GApple 1v1", Kits.COMBO, false),
	RANKED_AXEPVP(8, "Ranked AxePvP 1v1", Kits.AXEPVP, true),
	UNRANKED_AXEPVP(9, "UnRanked AxePvP 1v1", Kits.AXEPVP, false);
	
	private int slot;
	private String name;
	private Kits kit;
	private boolean isRanked;
	
	private Queues(int slot, String name, Kits kit, boolean isRanked) {
		this.slot = slot;
		this.name = name;
		this.kit = kit;
		this.isRanked = isRanked;
	}

	public int getSlot() {
		return slot;
	}

	public String getName() {
		return name;
	}

	public Kits getKit() {
		return kit;
	}

	public boolean isRanked() {
		return isRanked;
	}
	
	
	
	

}
