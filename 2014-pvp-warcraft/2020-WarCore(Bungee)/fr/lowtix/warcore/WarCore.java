package fr.lowtix.warcore;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import fr.lowtix.warcore.commands.GTPCommand;
import fr.lowtix.warcore.commands.IgnoreCommand;
import fr.lowtix.warcore.commands.MessageCommand;
import fr.lowtix.warcore.commands.ModListCommand;
import fr.lowtix.warcore.commands.ReplyCommand;
import fr.lowtix.warcore.commands.ReportCommand;
import fr.lowtix.warcore.commands.SocialSpyCommand;
import fr.lowtix.warcore.commands.ToggleCommand;
import fr.lowtix.warcore.listeners.InOutListener;
import fr.lowtix.warcore.managers.PlayerWrapper;
import fr.lowtix.warcore.modules.Modules;
import fr.lowtix.warcore.sql.IgnoreTable;
import fr.lowtix.warcore.sql.ToggleTable;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class WarCore extends Plugin {

	private static WarCore instance;

	private static HashMap<String, PlayerWrapper> wrappers = new HashMap<String, PlayerWrapper>();

	private File sqlFile;
	private Configuration sqlConfig;
	
	private IgnoreTable ignoreTable;
	private ToggleTable toggleTable;
	
	private Modules modules;

	@Override
	public void onEnable() {
		instance = this;

		initSQLConfig();
		
		ignoreTable = new IgnoreTable().createTable();
		toggleTable = new ToggleTable().createTable();
		
		modules = new Modules();
		
		initListeners();
	}

	private void initListeners() {
		ProxyServer.getInstance().getPluginManager().registerListener(this, new InOutListener());
		
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new MessageCommand("msg", "", new String[] {"w", "m", "t", "pm", "emsg", "epm", "tell", "etell", "whisper", "ewhisper"}));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new ReplyCommand("r", "", new String[] {"er", "reply", "ereply"}));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new GTPCommand("gtp", "", new String[] {"gteleport"}));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new IgnoreCommand("ignore", "", new String[] {"eignore", "unignore", "eunignore", "delignore", "edelignore", "remignore", "eremignore", "rmignore", "ermignore"}));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new ModListCommand("modlist", "", new String[] {"listmod"}));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new ReportCommand("report", "", new String[] {"ereport"}));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new SocialSpyCommand("socialspy", "", new String[] {"esocialspy"}));
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new ToggleCommand("msgtoggle", "", new String[] {"togglemsg"}));
	}

	private void initSQLConfig() {
		if (!getDataFolder().exists()) getDataFolder().mkdirs(); 
		this.sqlFile = new File(getDataFolder(), "/sql.yml");
		if (!this.sqlFile.exists()) {
			try {
				this.sqlFile.createNewFile();
				try {
					this.sqlConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.sqlFile);
					this.sqlConfig.set("host", "127.0.0.1:3306");
					this.sqlConfig.set("base", "warcore");
					this.sqlConfig.set("username", "root");
					this.sqlConfig.set("password", "icitumetlemdp");
					ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.sqlConfig, this.sqlFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		try {
			this.sqlConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.sqlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("CHECK: " + getSQLConfig().getString("username")+ " " + getSQLConfig().getString("host"));
	}

	public static WarCore getInstance() {
		return instance;
	}
	
	public IgnoreTable getIgnoreTable() {
		return ignoreTable;
	}
	
	public ToggleTable getToggleTable() {
		return toggleTable;
	}
	
	public Modules getModules() {
		return modules;
	}

	public Configuration getSQLConfig() {
		return sqlConfig;
	}

	public File getSqlFile() {
		return sqlFile;
	}

	public void sendMessage(ProxiedPlayer player, String message, ChatColor color, boolean bold, boolean italic,
			boolean strike) {
		BaseComponent com = new TextComponent(message);

		if (bold)
			com.setBold(true);
		if (italic)
			com.setItalic(true);
		if (strike)
			com.setStrikethrough(true);

		com.setColor(color);
		player.sendMessage(com);
	}

	public void sendMessage(ProxiedPlayer player, String message, ChatColor color) {
		sendMessage(player, message, color, false, false, false);
	}

	public HashMap<String, PlayerWrapper> getWrappers() {
		return wrappers;
	}

	public boolean containsWrapper(String name) {
		return getWrappers().containsKey(name);
	}

	public void addWrapper(PlayerWrapper wrapper) {
		if (!containsWrapper(wrapper.getName())) {
			wrappers.put(wrapper.getName(), wrapper);
		}
	}

	public PlayerWrapper getWrapper(ProxiedPlayer player) {
		if (containsWrapper(player.getName())) {
			return wrappers.get(player.getName());
		}
		return new PlayerWrapper(player);
	}
	
	public void removeWrapper(PlayerWrapper wrap) {
		if(containsWrapper(wrap.getName())) {
			wrappers.remove(wrap.getName());
		}
	}

}
