package eu.pvpwarcraft.meetup.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.pvpwarcraft.meetup.handler.Step;
import eu.pvpwarcraft.meetup.utils.guis.GuiManager;
import eu.pvpwarcraft.meetup.utils.guis.RetryGui;

public class RetryCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(Step.isStep(Step.PRE_GAME)){
				GuiManager.openGui(new RetryGui(player));	
				return true;
			}else{
				player.sendMessage("§6WarFight §8» §cErreur, vous ne pouvez pas faire cela: La phase de PvP est active ou n'a pas encore été active.");
				return true;
			}
		}
		return true;
	}

}
