package eu.pvpwarcraft.dayz.users;

public enum InGameRanks {
	
	RECRUE(1, "Recrue"),
	APPRENTIE_I(2, "Apprentie I"),
	APPRENTIE_II(3, "Apprentie II"),
	SOLDAT_I(4, "Soldat I"),
	SOLDAT_II(5, "Soldat II"),
	SOLDAT_III(6, "Soldat III"),
	SOLDAT_IV(7, "Soldat IV"),
	CAPORAL_I(8, "Caporal I"),
	CAPORAL_II(9, "Caporal II"),
	SERGENT_I(10, "Sergent I"),
	SERGENT_II(11, "Sergent II"),
	SERGENT_III(12, "Sergent III"),
	ADJUDANT_I(13, "Adjudant I"),
	ADJUDANT_II(14, "Adjudant II"),
	MAJOR_I(15, "Major I"),
	MAJOR_II(16, "Major II"),
	LIEUTENANT_I(17, "Lieutenant I"),
	LIEUTENANT_II(18, "Lieutenant II"),
	LIEUTENANT_III(19, "Lieutenant III"),
	COMMANDANT(20, "Commandant"),
	COLONEL(21, "Colonel"),
	GENERAL_I(22, "Général I"),
	GENERAL_II(23, "Général II"),
	MARCHAL(24, "Maréchal");
	
	private int position;
	private String name;
	
	private InGameRanks(int position, String name) {
		this.name = name;
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public int getPosition() {
		return position;
	}
	
	public InGameRanks upRank() {
		InGameRanks result = InGameRanks.RECRUE;
		if(getPosition() < 24){
			for(InGameRanks ranks : InGameRanks.values()){
				if((getPosition() + 1) == ranks.getPosition()){
					result = ranks;
					break;
				}
			}
		}
		return result;
	}
	
	public InGameRanks downRank() {
		InGameRanks result = InGameRanks.RECRUE;
		if(getPosition() > 1){
			for(InGameRanks ranks : InGameRanks.values()){
				if((getPosition() - 1) == ranks.getPosition()){
					result = ranks;
					break;
				}
			}
		}
		return result;
	}

}
