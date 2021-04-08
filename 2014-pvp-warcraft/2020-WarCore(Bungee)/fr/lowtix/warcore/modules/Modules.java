package fr.lowtix.warcore.modules;

public class Modules {
	
	private IgnoreModule ignore;
	private MessagingModule messaging;
	private ToggleModule toggle;
	private SocialSpyModule socialspy;
	private ModListModule modlist;
	private ReportModule report;
	private GTPModule gtp;
	
	public Modules() {
		ignore = new IgnoreModule();
		messaging = new MessagingModule();
		toggle = new ToggleModule();
		socialspy = new SocialSpyModule();
		modlist = new ModListModule();
		report = new ReportModule();
		gtp = new GTPModule();
	}

	public IgnoreModule getIgnore() {
		return ignore;
	}

	public MessagingModule getMessaging() {
		return messaging;
	}

	public ToggleModule getToggle() {
		return toggle;
	}
	
	public SocialSpyModule getSocialspy() {
		return socialspy;
	}
	
	public ModListModule getModlist() {
		return modlist;
	}
	
	public ReportModule getReport() {
		return report;
	}
	
	public GTPModule getGtp() {
		return gtp;
	}
}
