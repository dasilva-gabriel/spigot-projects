package eu.pvpwarcraft.warfight.managers;

import org.bukkit.configuration.file.FileConfiguration;

public enum Permissions {

	COMMANDS_WARARENA("warfight.commands.wararena"),
	COMMANDS_WARFIGHT("warfight.commands.warfight");

	private String line;

	private Permissions(String line) {
		this.line = line;
	}

	public String getPermissions() {
		return this.line;
	}

	public void setPermissions(String line) {
		this.line = line;
	}
	
	public static void init(){
		FileConfiguration config = Configuration.getPermissionsConfig();
		
		for(Permissions perms : values()){
			if(!config.contains(perms.name())){
				config.set(perms.name(), perms.getPermissions());
			}
		}
		
		for(Permissions perms : values()){
			String line = config.getString(perms.name());
			perms.setPermissions(line);
		}
		
		Configuration.savePermissionsConfig();;
	}

}
