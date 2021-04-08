package fr.lowtix.warbox.enums;

public enum TagsType {
	
	BOX("Trouvez ce tag dans la box"), LIMITED("Edition limitée"), PRIVATE("Edition privée");
	
	private String display;

	private TagsType(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}
	
}
