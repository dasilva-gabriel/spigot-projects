package fr.lowtix.warbox.enums;

import org.bukkit.Effect;

public enum BowTrail {
	
	NONE("Aucun", null),
	CLOUD("Nuage", Effect.CLOUD),
	COLOUR("Nuage de couleurs", Effect.COLOURED_DUST),
	CRIT("Critique", Effect.CRIT),
	EXPLOSION("Explosion", Effect.EXPLOSION),
	FIREWORKS("Artifice", Effect.FIREWORKS_SPARK),
	FLAME("Flammes", Effect.FLAME),
	HAPPY("Joie", Effect.HAPPY_VILLAGER),
	HEARTH("Coeurs", Effect.HEART),
	SPELL("Spell", Effect.INSTANT_SPELL),
	SMOKE("Fumée", Effect.LARGE_SMOKE),
	MAGIC("Magie", Effect.MAGIC_CRIT),
	LAVA("Lave", Effect.LAVA_POP),
	NOTE("Note", Effect.NOTE),
	THUNDER("Mécontent", Effect.VILLAGER_THUNDERCLOUD),
	WITCH("Sorcière", Effect.WITCH_MAGIC);
	
	private String displayName;
	private Effect effect;
	
	private BowTrail(String displayName, Effect effect) {
		this.displayName = displayName;
		this.effect = effect;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Effect getEffect() {
		return effect;
	}
	
	public static BowTrail getTrail(String name) {
		BowTrail result = NONE;
		for(BowTrail trails : BowTrail.values()) {
			if(name.equalsIgnoreCase(trails.name())) {
				result = trails;
			}
		}
		return result;
	}
	
	public static BowTrail getNext(BowTrail trail) {
		BowTrail result = NONE;
		boolean exec = false;
		for(BowTrail trails : values()) {
			
			if(exec) {
				exec = false;
				result = trails;
				break;
			}
			
			if(trails.equals(trail)) {
				exec = true;
			}
		}
		
		return result;
	}
	
}
