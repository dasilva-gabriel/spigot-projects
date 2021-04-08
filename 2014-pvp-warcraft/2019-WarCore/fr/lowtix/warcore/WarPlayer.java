package fr.lowtix.warcore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.massivecraft.factions.entity.MPlayer;

import fr.lowtix.warbox.enums.BowTrail;
import fr.lowtix.warbox.enums.Tags;
import fr.lowtix.warcore.enums.ColorTab;
import fr.lowtix.warcore.enums.Levels;
import fr.lowtix.warcore.enums.Ranks;
import fr.lowtix.warcore.enums.SymbolTab;
import fr.lowtix.warcore.managers.FacBoard;
import fr.lowtix.warcore.utils.PlayerUtils;

public class WarPlayer {
	
	private Player player;
	private String sqlName;
	private Ranks rank;
	private Levels level;
	private double points, gemmes;
	private int kills, deaths, streak;
	public long lastChat;
	public MPlayer mplayer;
	public FacBoard board;
	
	public Location lastAFKLoc;
	public int afkPoints;
	public boolean afkMustCommand;
	public String afkMsg;
	public boolean afkToKick;
	
	private PlayerStats playerStats;
	private CustomTab tabCustom;
	private ModPlayer modPlayer;
	
	public WarPlayer(Player player) {
		this.player = player;
		this.rank = Ranks.DEFAULT;
		this.level = Levels.LEVEL_0;
		this.points = 0.0;
		this.gemmes = 0.0;
		this.kills = 0;
		this.deaths = 0;
		this.streak = 0;
		this.sqlName = player.getName();
		this.lastChat = System.currentTimeMillis();
		this.mplayer = MPlayer.get(player);
		
		this.lastAFKLoc = player.getLocation();
		
		this.tabCustom = new CustomTab(this);
		this.playerStats = new PlayerStats(this);
		
		this.setModPlayer(new ModPlayer(this));
		
		this.board = new FacBoard(this);
		
		reloadAfkMsg();
		
		afkMustCommand = false;
		
		WarCore.getInstance().sql.loadUser(this);
		
		WarCore.getInstance().createPlayer(this);
	}
	
	public String getSqlName() {
		return sqlName;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Ranks getRank() {
		return rank;
	}

	public void setRank(Ranks rank) {
		this.rank = rank;
	}

	public Levels getLevel() {
		return level;
	}

	public void setLevel(Levels level) {
		this.level = level;
	}

	public double getPoints() {
		this.points = Math.floor((this.points*100)/100);
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}
	
	public void addPoints(double points) {
		this.points += points;
	}
	
	public void removePoints(double points) {
		this.points -= points;
	}
	
	public boolean hasPoints(double points) {
		return this.points >= points;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getStreak() {
		return streak;
	}

	public void setStreak(int streak) {
		this.streak = streak;
	}

	public double getGemmes() {
		return gemmes;
	}

	public void setGemmes(double gemmes) {
		this.gemmes = gemmes;
	}

	public PlayerStats getPlayerStats() {
		return playerStats;
	}

	public void setPlayerStats(PlayerStats playerStats) {
		this.playerStats = playerStats;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}
	
	public CustomTab getTabCustom() {
		return tabCustom;
	}

	public void setTabCustom(CustomTab tabCustom) {
		this.tabCustom = tabCustom;
	}

	public double getCoins() {
		double res = 0.0;
		res = WarCore.getInstance().getEconomy().getBalance(this.player);
		return res;
	}
	
	public void addCoins(double ammount) {
		WarCore.getInstance().getEconomy().depositPlayer(this.player, ammount);
	}
	
	public void removeCoins(double ammount) {
		WarCore.getInstance().getEconomy().withdrawPlayer(this.player, ammount);
	}
	
	public boolean hasCoins(double ammount) {
		return WarCore.getInstance().getEconomy().has(this.player, ammount);
	}
	
	public void save() {
		WarCore.getInstance().sql.saveUser(this);
	}
	
	public void reloadAfkMsg() {
		String result = "";
		
		result += this.player.getName().toLowerCase();
		result += RandomStringUtils.randomAlphanumeric(8);
		
		afkMsg = result;
	}
	
	public void addPointsEffect(double ammount) {
		ammount = Math.floor((ammount*100)/100);
		PlayerUtils.sendActionBar(player, "§a§l+ §f"+ammount+"✴");
		player.playSound(player.getLocation(), Sound.LEVEL_UP, 10.0F, 20.0F);
		addPoints(ammount);
	}
	

	public ModPlayer getModPlayer() {
		return modPlayer;
	}

	public void setModPlayer(ModPlayer modPlayer) {
		this.modPlayer = modPlayer;
	}
	
	public void clear() {
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		
		for(PotionEffect effects : player.getActivePotionEffects()) {
		player.removePotionEffect(effects.getType());
		}
	}


	public class PlayerStats {
		
		private WarPlayer user;
		
		private long lastLevelUp = 0;
		private Tags tag = Tags.NONE;
		private boolean afk = false;
		public String firstConnect = "01/01/2000 10:10"; 
		public int timePlayed = 0;
		
		public boolean joinMessage = false;
		public BowTrail trail = BowTrail.NONE;
		
		private String splitter = "¤";
		
		public PlayerStats(WarPlayer user) {
			this.user = user;
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			this.firstConnect = dateFormat.format(date);
		}

		public WarPlayer getUser() {
			return user;
		}

		public void setUser(WarPlayer user) {
			this.user = user;
		}

		public String getSplitter() {
			return splitter;
		}

		public void setSplitter(String splitter) {
			this.splitter = splitter;
		}

		public long getLastLevelUp() {
			return lastLevelUp;
		}

		public void setLastLevelUp(long lastLevelUp) {
			this.lastLevelUp = lastLevelUp;
		}

		public Tags getTag() {
			return tag;
		}

		public void setTag(Tags tag) {
			this.tag = tag;
		}

		public boolean isAfk() {
			return afk;
		}

		public void setAfk(boolean afk) {
			this.afk = afk;
		}

		public int getSecondsLastRankup() {
			
			int result = 0;
			
			try {
				long elapsed = System.currentTimeMillis() - getLastLevelUp();
				
				long diff = TimeUnit.MILLISECONDS.toSeconds(elapsed);
				
				result = (int) diff;
			} catch (Exception e) {
				result = 0;
			}
			return result;
		}

		public void loadByString(String s) {
			String[] serialized = s.split(splitter);
			System.out.println("TESTING "+s+ " (S:"+serialized.length+") "+serialized[0]);
			if(serialized.length >= 2 && serialized[1] != null) {
				this.lastLevelUp = Long.parseLong(serialized[1]);
			}
			if(serialized.length >= 3 && serialized[2] != null) {
				this.tag = Tags.getTagFromName(serialized[2]);
			}
			if(serialized.length >= 4 && serialized[3] != null) {
				this.user.getTabCustom().setActive(serialized[3].equalsIgnoreCase("1"));
			}
			if(serialized.length >= 5 && serialized[4] != null) {
				this.user.getTabCustom().setColor(ColorTab.getColorFromName(serialized[4]));
			}
			if(serialized.length >= 6 && serialized[5] != null) {
				this.user.getTabCustom().setSymbol(SymbolTab.getSymbolFromName(serialized[5]));
				
				if(this.user.getTabCustom().getSymbol() == null) {
					this.user.getTabCustom().setSymbol(SymbolTab.HEARTH_1);
				}
				
			}
			if(serialized.length >= 7 && serialized[6] != null) {
				setAfk(((serialized[6] == "1" ? true : false)));
			}
			if(serialized.length >= 8 && serialized[7] != null) {
				this.firstConnect = serialized[7];
			}
			if(serialized.length >= 9 && serialized[8] != null) {
				this.timePlayed = Integer.parseInt(serialized[8]);
			}
			if(serialized.length >= 10 && serialized[9] != null) {
				this.joinMessage = serialized[9].equalsIgnoreCase("1");
			}
			if(serialized.length >= 11 && serialized[10] != null) {
				this.trail = BowTrail.getTrail(serialized[10]);
			}
		}
		
		public String getSerial() {
			String serialized = "SERIAL" + splitter;
			serialized += ""+this.lastLevelUp;
			serialized += splitter;
			serialized += this.tag.name();
			serialized += splitter;
			serialized += (this.user.getTabCustom().active == false ? "0" : "1");
			serialized += splitter;
			serialized += this.user.getTabCustom().getColor().name();
			serialized += splitter;
			serialized += this.user.getTabCustom().getSymbol().name();
			serialized += splitter;
			serialized += (isAfk() == false ? "0" : "1");
			serialized += splitter;
			serialized += firstConnect;
			serialized += splitter;
			serialized += timePlayed;
			serialized += splitter;
			serialized += (joinMessage == false ? "0" : "1");
			serialized += splitter;
			serialized += this.trail.name();
			return serialized;
		}
		
	}
	
	public class CustomTab {
		
		private WarPlayer user;
		
		public boolean active = false;
		private ColorTab color = ColorTab.WHITE;
		private SymbolTab symbol = SymbolTab.HEARTH_1;
		
		public CustomTab(WarPlayer user) {
			this.user = user;
		}

		public ColorTab getColor() {
			return color;
		}

		public void setColor(ColorTab color) {
			this.color = color;
		}

		public SymbolTab getSymbol() {
			return symbol;
		}

		public void setSymbol(SymbolTab symbol) {
			this.symbol = symbol;
		}

		public WarPlayer getUser() {
			return user;
		}

		public void setUser(WarPlayer user) {
			this.user = user;
		}

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}
		
		public void reload() {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nametagedit player "+player.getName()+" clear");
				
			
			if(user.getPlayerStats().isAfk()) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nametagedit player "+player.getName()+" suffix §r §8[§4✕§8]");
			}else if (!user.getPlayerStats().isAfk() && user.getRank().isHigher(Ranks.DIAMOND)) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nametagedit player "+player.getName()+" suffix §r §8[§2✔§8]");
			}
			
			if (user.getTabCustom().isActive()){
				String tag = user.getRank().getShortCut() + " " 
			+ user.getTabCustom().getColor().getColor() 
			+ user.getTabCustom().getSymbol().getDisplayName() + " " 
			+ user.getRank().getPrefixColor();
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nametagedit player "+player.getName()+" prefix "+tag);
				int prio = 12 - user.getRank().getPlace();
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nametagedit player "+player.getName()+" priority "+prio);
			} else {
				int prio = 12 - user.getRank().getPlace();
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nametagedit player "+player.getName()+" priority "+prio);
				String tag = user.getRank().getPrefix()+ " " + user.getRank().getPrefixColor();
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nametagedit player "+player.getName()+" prefix "+tag);
			}
			
		}

	}
	
	public class ModPlayer {
		
		private WarPlayer user;
		
		private boolean chatActive = true, mod = false;
		private String track = "";
		
		public boolean followTrack = true;
		
		public String report;
		
		public ItemStack[] armor;
		public ItemStack[] contents;
		
		public ModPlayer(WarPlayer user) {
			this.user = user;
			removeTrack();
		}

		public WarPlayer getUser() {
			return user;
		}

		public void setUser(WarPlayer user) {
			this.user = user;
		}

		public boolean isChatActive() {
			return chatActive;
		}

		public void setChatActive(boolean chatActive) {
			this.chatActive = chatActive;
		}

		public String getTrack() {
			return track;
		}

		public void setTrack(String track) {
			this.track = track;
		}
		
		public void removeTrack() {
			this.track = "**null**";
		}
		
		public boolean isTrack() {
			return !this.track.equalsIgnoreCase("**null**");
		}
		
		public Player getTrackedPlayer() {
			return Bukkit.getPlayer(this.track);
		}
		
		public boolean trackIsOnline() {
			return (Bukkit.getPlayer(this.track) != null && (Bukkit.getPlayer(this.track).isOnline()));
		}

		public boolean isMod() {
			return mod;
		}

		public void setMod(boolean mod) {
			this.mod = mod;
		}

		public boolean isFollowTrack() {
			return followTrack;
		}

		public void setFollowTrack(boolean followTrack) {
			this.followTrack = followTrack;
		}

			

		public String getReport() {
			return report;
		}

		public void setReport(String report) {
			this.report = report;
		}

		public ItemStack[] getArmor() {
			return armor;
		}

		public void setArmor(ItemStack[] armor) {
			this.armor = armor;
		}

		public ItemStack[] getContents() {
			return contents;
		}

		public void setContents(ItemStack[] contents) {
			this.contents = contents;
		}

	}

}
