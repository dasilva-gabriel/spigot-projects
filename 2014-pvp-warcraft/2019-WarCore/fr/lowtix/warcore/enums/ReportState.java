package fr.lowtix.warcore.enums;

public enum ReportState {
	
	NO_TRAITEMENT("§cNon traité"),
	TRAITEMENT("§6En traitement"),
	TRAITED("§2Traité");

	private String display;

	private ReportState(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}

}
