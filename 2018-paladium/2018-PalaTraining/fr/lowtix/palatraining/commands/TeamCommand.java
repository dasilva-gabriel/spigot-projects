package fr.lowtix.palatraining.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.palatraining.PalaTraining;
import fr.lowtix.palatraining.guis.TeamGui;
import fr.lowtix.palatraining.utils.GuiManager;

public class TeamCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(args.length == 0 || args[0].equalsIgnoreCase("help")) {
				player.sendMessage("§8§m------------------------------------------");
				player.sendMessage("§e§lPage d'aide pour vos Teams:");
				player.sendMessage("§f §8» §2/§ateam help §7§ovoir cette page");
				player.sendMessage("§f §8» §2/§ateam create §7§ocréer une Team");
				player.sendMessage("§f §8» §2/§ateam menu §7§oouvre le menu de gestion de votre Team");
				player.sendMessage("§f §8» §2/§ateam join §e<pseudo> §7§orejoindre une Team");
				player.sendMessage("§f §8» §2/§ateam invite §e<pseudo> §7§oinviter un joueur dans votre Team");
				player.sendMessage("§f §8» §2/§ateam kick §e<pseudo> §7§oexpulser un joueur de votre Team");
				player.sendMessage("§8§m------------------------------------------");
			} else if(args.length == 1 && args[0].equalsIgnoreCase("create")) {
				PalaTraining.getInstance().getTeamsManager().createTeam(player);
			} else if(args.length == 1 && args[0].equalsIgnoreCase("menu")) {
				GuiManager.openGui(new TeamGui(player));
			} else if(args.length == 2 && args[0].equalsIgnoreCase("join")) {
				
				String username = args[1];
				
				if(Bukkit.getPlayer(username) != null && Bukkit.getPlayer(username).isOnline()) {
					PalaTraining.getInstance().getTeamsManager().joinTeamFromCommand(player, Bukkit.getPlayer(username));
				}
		
			} else if(args.length == 2 && args[0].equalsIgnoreCase("invite")) {
				
				String username = args[1];
				
				if(Bukkit.getPlayer(username) != null && Bukkit.getPlayer(username).isOnline()) {
					PalaTraining.getInstance().getTeamsManager().joinTeamFromCommand(player, Bukkit.getPlayer(username));
				}
		
			} else if(args.length == 2 && args[0].equalsIgnoreCase("kick")) {
				
				String username = args[1];
				
				if(Bukkit.getPlayer(username) != null && Bukkit.getPlayer(username).isOnline()) {
					PalaTraining.getInstance().getTeamsManager().joinTeamFromCommand(player, Bukkit.getPlayer(username));
				}
		
			}
			
		}
		
		return true;
	}

}
