package fr.lowtix.cheatpatch.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.cheatpatch.guis.MinerConfirmationGui;
import fr.lowtix.cheatpatch.utils.GuiManager;

public class MinerCommand implements CommandExecutor {
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			if(args.length == 0) {
				
				Player p = (Player) sender;
				
				if(p.hasPermission("warcraft.miner")) {
					GuiManager.openGui(new MinerConfirmationGui(p));
				} else {
					p.sendMessage("§cLes Miners sont en maintenance.");
				}
				
				
				
			}
			
		}
		
		return true;
	}

}
