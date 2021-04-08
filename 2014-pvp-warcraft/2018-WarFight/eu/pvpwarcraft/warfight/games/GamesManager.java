package eu.pvpwarcraft.warfight.games;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.warfight.PlayerWrapper;
import eu.pvpwarcraft.warfight.PlayerWrapper.PlayerStates;
import eu.pvpwarcraft.warfight.WarFight;
import eu.pvpwarcraft.warfight.managers.arenas.Arena;
import eu.pvpwarcraft.warfight.managers.arenas.Arena.ArenaType;
import eu.pvpwarcraft.warfight.managers.arenas.ArenasManager;
import eu.pvpwarcraft.warfight.managers.kits.Kits;
import eu.pvpwarcraft.warfight.managers.lobby.LobbyManager;
import eu.pvpwarcraft.warfight.utils.PlayerUtils;

public class GamesManager {

	public static Map<String, Game> games_1vs1 = new HashMap<>();

	public static Arena randomArena(Kits kit) {
		Arena arena = null;
		if (kit.equals(Kits.BUILDUHC)) {
			List<Arena> arenas = ArenasManager.getUnusedArenasWithType(ArenaType.HAS_BLOCK);
			if (!arenas.isEmpty()) {
				Random rand = new Random();
				int randn = rand.nextInt(arenas.size());
				arena = arenas.get(randn);
			}
		} else {
			List<Arena> arenas = ArenasManager.getUnusedArenasWithType(ArenaType.NOT_BLOCK);
			if (!arenas.isEmpty()) {
				Random rand = new Random();
				int randn = rand.nextInt(arenas.size());
				arena = arenas.get(randn);
			}
		}
		return arena;
	}

	public static void actionOnGames() {
		for (Entry<String, Game> games : games_1vs1.entrySet()) {

			Game game = games.getValue();

			if (!game.isStarted() && !game.isFinished()) {
				game.loopStart();
			}else if(game.isStarted() && !game.isFinished()){
				game.loopGame();
			}

		}
	}

	public static void start1v1Game(Player first, Player second, Kits kit, boolean isRanked) {
		Arena arena = randomArena(kit);

		PlayerWrapper pw_first = WarFight.getPlayer(first);
		PlayerWrapper pw_second = WarFight.getPlayer(second);

		if (arena == null) {
			LobbyManager.goToLobby(first, false, true);
			LobbyManager.goToLobby(second, false, true);

			PlayerUtils.sendMessageToTowPlayers(first, second,
					"§8§m--+------------------------------------------------+--");
			PlayerUtils.sendMessageToTowPlayers(first, second, "");
			PlayerUtils.sendMessageToTowPlayers(first, second, "  §8» §c§lArènes saturées: §eNos arènes sont pleines.");
			PlayerUtils.sendMessageToTowPlayers(first, second, "");
			PlayerUtils.sendMessageToTowPlayers(first, second, "     §8▪ §eInfo: §7Veuillez retenter dans un instant.");
			PlayerUtils.sendMessageToTowPlayers(first, second,
					"     §8▪ §eAstuce: §7Vous pouver essaier un autre type de jeu.");
			PlayerUtils.sendMessageToTowPlayers(first, second, "");
			PlayerUtils.sendMessageToTowPlayers(first, second,
					"   §8[§3?§8] §bSi le problème persiste contactez un Administrateur.");
			PlayerUtils.sendMessageToTowPlayers(first, second, "");
			PlayerUtils.sendMessageToTowPlayers(first, second,
					"§8§m--+------------------------------------------------+--");
			PlayerUtils.playSoundToTowPlayers(first, second, Sound.VILLAGER_NO);
			return;
		} else {

			PlayerUtils.sendMessageToTowPlayers(first, second, "§6WarFight §8» §7Création de l'arène...");

			try {
				Random random1 = new Random();
				int numb1 = 1 + random1.nextInt(8);
				Random random2 = new Random();
				int numb2 = 1 + random2.nextInt(8);
				Random random3 = new Random();
				int numb3 = 1 + random3.nextInt(8);

				String ra_name = RandomStringUtils.randomAlphabetic(4);
				ra_name += "-" + numb1;
				ra_name += "" + numb2;
				ra_name += "" + numb3;

				PlayerUtils.sendMessageToTowPlayers(first, second, "§6WarFight §8» §aL'arène à été créée.");
				PlayerUtils.sendMessageToTowPlayers(first, second,
						"§6WarFight §8» §7Début du match §b#" + ra_name + "§7.");

				Game game = new Game("#" + ra_name, arena, kit, isRanked, first, second);
				pw_first.setState(PlayerStates.IN_GAME);
				pw_first.setGame(game);
				pw_second.setState(PlayerStates.IN_GAME);
				pw_second.setGame(game);

				games_1vs1.put(game.getID(), game);
				game.start();
				PlayerUtils.sendMessageToTowPlayers(first, second, "§6WarFight §8» §aTéléportation...");
			} catch (Exception e) {
				String error_code = "#" + RandomStringUtils.randomAlphabetic(5) + ":GAME_START";
				WarFight.getInstance().getLogger()
						.warning("START >>> " + error_code + " >>> Erreur lors de la création de l'arène avec "
								+ first.getName() + " et " + second.getName() + ".");
				e.printStackTrace();
				WarFight.getInstance().getLogger()
						.warning("END >>> " + error_code + " >>> Erreur lors de la création de l'arène avec "
								+ first.getName() + " et " + second.getName() + ".");
				PlayerUtils.sendMessageToTowPlayers(first, second,
						"§6WarFight §8» §7Une erreur est survenue lors de la création de l'arène.");
				PlayerUtils.sendMessageToTowPlayers(first, second,
						"§4Warn §8» §cVeuillez conatcter un Administrateur avec le code erreur. §8(§f" + error_code
								+ "§8)");
			}
		}
	}

}
