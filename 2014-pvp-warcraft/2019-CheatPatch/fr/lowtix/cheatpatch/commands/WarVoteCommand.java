package fr.lowtix.cheatpatch.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.cheatpatch.CheatPatch;

public class WarVoteCommand implements CommandExecutor {
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if((sender.isOp() || !(sender instanceof Player)) && args.length >= 1) {
			
			String cmd = "";
			
			for(int i = 0; i < args.length; i++) {
				cmd += args[i] + " ";
			}
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
			
			CheatPatch.getInstance().getVoteManager().addVote(1);
			CheatPatch.getInstance().getVoteManager().setLast_vote(sender.getName());
		}
		
		return true;
	}

}
