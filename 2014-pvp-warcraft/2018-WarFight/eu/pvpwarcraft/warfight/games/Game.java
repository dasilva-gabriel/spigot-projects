package eu.pvpwarcraft.warfight.games;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.warfight.Param;
import eu.pvpwarcraft.warfight.PlayerWrapper;
import eu.pvpwarcraft.warfight.WarFight;
import eu.pvpwarcraft.warfight.managers.arenas.Arena;
import eu.pvpwarcraft.warfight.managers.kits.Kits;
import eu.pvpwarcraft.warfight.managers.kits.KitsManager;
import eu.pvpwarcraft.warfight.utils.PlayerUtils;

public class Game {
	
	
	private String ID;
	private Arena arena;
	private Kits kit;
	private boolean isRanked;
	private Player first_player;
	private Player second_player;
	private PlayerWrapper first_wrapper;
	private PlayerWrapper second_wrapper;
	private int time;
	private boolean isFinished;
	private boolean isStarted;
	
	public Game(String iD, Arena arena, Kits kit, boolean isRanked, Player first_player, Player second_player) {
		ID = iD;
		this.arena = arena;
		this.kit = kit;
		this.isRanked = isRanked;
		this.first_player = first_player;
		this.second_player = second_player;
		this.first_wrapper = WarFight.getPlayer(first_player);
		this.second_wrapper = WarFight.getPlayer(second_player);
		this.time = (Param.game_time + 7);
		this.isFinished = false;
		this.isStarted = false;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public Kits getKit() {
		return kit;
	}

	public void setKit(Kits kit) {
		this.kit = kit;
	}

	public boolean isRanked() {
		return isRanked;
	}

	public void setRanked(boolean isRanked) {
		this.isRanked = isRanked;
	}

	public Player getFirst_player() {
		return first_player;
	}
	
	public PlayerWrapper getFirst_wrapper() {
		return first_wrapper;
	}
	
	public PlayerWrapper getSecond_wrapper() {
		return second_wrapper;
	}

	public void setFirst_player(Player first_player) {
		this.first_player = first_player;
	}

	public Player getSecond_player() {
		return second_player;
	}

	public void setSecond_player(Player second_player) {
		this.second_player = second_player;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	
	public void loopGame(){
		time --;
		
		this.first_player.setExp((this.time / Param.game_time));
		this.second_player.setExp((this.time / Param.game_time));
		
	}
	
	public void loopStart(){
		if(time > 1200 && !isFinished){
			if(isStarted){
				isStarted = false;
			}
			
			if(time >= (Param.game_time + 5)){
				time --;
				return;
			}
			
			
			int timer = time - 1200;
			int pitcht = (5-timer);
			
			float pitch = (float)(0.2D * pitcht);
            this.first_player.playSound(this.first_player.getLocation().add(0.0D, 1.5D, 0.0D), Sound.SUCCESSFUL_HIT, 2.0F, pitch);
            this.second_player.playSound(this.second_player.getLocation().add(0.0D, 1.5D, 0.0D), Sound.SUCCESSFUL_HIT, 2.0F, pitch);
            
            PlayerUtils.sendTitleToTowPlayers(first_player, second_player, "§e§l"+timer, "§7§oDébut dans...");
			
            PlayerUtils.sendMessageToTowPlayers(this.first_player, this.second_player, "§6WarFight §8» §7Début du match dans §b"+timer + (timer == 1 ? " seconde" : " secondes")+ "§7!");
            
            if(timer == 4 || timer == 2){
            	this.first_player.teleport(this.arena.getSpawn1());
    			this.second_player.teleport(this.arena.getSpawn2());
            }
            
            time --;
            
		}else{
			if(time == 1200){
				this.first_player.teleport(this.arena.getSpawn1());
    			this.second_player.teleport(this.arena.getSpawn2());
    			PlayerUtils.sendTitleToTowPlayers(first_player, second_player, " ", " ");
    			PlayerUtils.playSoundToTowPlayers(first_player, second_player, Sound.FIREWORK_BLAST);
    			
    			PlayerUtils.sendMessageToTowPlayers(first_player, second_player, "§8§m--+------------------------------------------------+--");
    			PlayerUtils.sendMessageToTowPlayers(first_player, second_player, "");
    			PlayerUtils.sendMessageToTowPlayers(first_player, second_player, "  §8» §a§lDébut du match !");
    			PlayerUtils.sendMessageToTowPlayers(first_player, second_player, "");
    			PlayerUtils.sendMessageToTowPlayers(first_player, second_player, "     §8▪ §eKit: §a"+ (isRanked ? "Ranked" : "UnRanked")+" "+this.kit.getName());
    			PlayerUtils.sendMessageToTowPlayers(first_player, second_player, "     §8▪ §eID du match: §7"+this.ID);
    			PlayerUtils.sendMessageToTowPlayers(first_player, second_player, "");
    			PlayerUtils.sendMessageToTowPlayers(first_player, second_player, "§8§m--+------------------------------------------------+--");
    			PlayerUtils.sendMessageToTowPlayers(first_player, second_player, "   §8[§4!§8] §cLa camp est strictement interdite !");
    			PlayerUtils.sendMessageToTowPlayers(first_player, second_player, "§8§m--+------------------------------------------------+--");
    			this.isStarted = true;
    			this.isFinished = false;
			}
		}
	}

	public void start(){
		if(!isStarted && !isFinished){
			this.first_player.teleport(this.arena.getSpawn1());
			this.second_player.teleport(this.arena.getSpawn2());
			
			KitsManager.giveKit(this.kit, this.first_player);
			KitsManager.giveKit(this.kit, this.second_player);
			
			
			
		}
	}

}
