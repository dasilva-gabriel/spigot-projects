package fr.lowtix.warcore.managers;

import java.util.ArrayList;
import java.util.List;

import fr.lowtix.warcore.WarCore;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerWrapper {
	
	private String name;
	private String reply;
	private ArrayList<String> ignored;
	private boolean toggle;
	private String socialspy;

	public PlayerWrapper(ProxiedPlayer player) {
		this.name = player.getName();
		this.reply = "";
		this.ignored = new ArrayList<String>();
		this.toggle = true;
		this.socialspy = "none";
		
		if(WarCore.getInstance().getModules().getToggle().getToggled().contains(name.toLowerCase())) {
			this.toggle = false;
		}
		
		WarCore.getInstance().getIgnoreTable().loadIgnores(this);
		
		WarCore.getInstance().addWrapper(this);
	}
	
	public String getName() {
		return name;
	}
	
	public String getReply() {
		return reply;
	}
	
	public List<String> getIgnored() {
		return ignored;
	}
	
	public void setIgnored(ArrayList<String> ignored) {
		this.ignored = ignored;
	}
	
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	public boolean isToggle() {
		return toggle;
	}
	
	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}
	
	public boolean isSocialSpy() {
		return !socialspy.equalsIgnoreCase("none");
	}
	
	public void disableSocialSpy() {
		this.socialspy = "none";
	}
	
	public String getSocialspy() {
		return socialspy;
	}
	
	public void enableSocialspy(String socialspy) {
		this.socialspy = socialspy;
	}
	
}
