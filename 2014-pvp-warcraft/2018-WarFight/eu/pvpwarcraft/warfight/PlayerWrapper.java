package eu.pvpwarcraft.warfight;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.warfight.games.Game;
import eu.pvpwarcraft.warfight.managers.board.BoardType;
import eu.pvpwarcraft.warfight.managers.board.ScoreboardManager;
import eu.pvpwarcraft.warfight.managers.queues.Queues;

public class PlayerWrapper {

	private String name;
	private Long connexion;
	private PlayerStates state;
	private Queues queue;
	private Game game;
	private ScoreboardManager board;
	private double ender_cooldown;

	public PlayerWrapper(Player player) {
		this.name = player.getName();
		this.connexion = System.currentTimeMillis();
		this.state = PlayerStates.LOBBY;
		this.queue = null;
		this.board = new ScoreboardManager(player);
		this.board.loadBoardToPlayer(BoardType.LOBBY);
		this.game = null;
		this.ender_cooldown = 0;
		WarFight.addPlayer(this);
	}

	public ScoreboardManager getBoard() {
		return this.board;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(name);
	}

	public String getName() {
		return name;
	}

	public Long getConnexion() {
		return connexion;
	}

	public void setConnexion(Long connexion) {
		this.connexion = connexion;
	}

	public PlayerStates getState() {
		return state;
	}

	public void setState(PlayerStates state) {
		this.state = state;
	}

	public Queues getQueue() {
		return queue;
	}

	public void setQueue(Queues queue) {
		this.queue = queue;
	}

	public String getPearlBar(){
		int pearl = (int) (this.ender_cooldown*4);
		int cooldown = (Param.ender_cooldown*4);
		String bar = "";
		bar += "§b";
        for(int i = 1; i <= cooldown-pearl; i++){
            bar += "▪";
        }
		bar += "§7";
        for(int i = 1; i <= pearl; i++){
            bar += "▪";
        }
        return "§8["+bar+"§8]";
	}
	
	public double getEnder_cooldown() {
		return ender_cooldown;
	}

	public void setEnder_cooldown(double ender_cooldown) {
		this.ender_cooldown = ender_cooldown;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}



	public enum PlayerStates {

		LOBBY, IN_QUEUE, IN_GAME, IN_PARTY;
		
		public static boolean isState(PlayerWrapper pw, PlayerStates state){
			PlayerStates current = pw.getState();
			if(current.name().equalsIgnoreCase(state.name())){
				return true;
			}
			return false;
		}

	}

}
