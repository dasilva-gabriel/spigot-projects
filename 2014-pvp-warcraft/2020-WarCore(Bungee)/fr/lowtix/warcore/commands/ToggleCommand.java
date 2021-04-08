package fr.lowtix.warcore.commands;

import fr.lowtix.warcore.WarCore;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ToggleCommand extends Command {

	public ToggleCommand(String name, String permission, String[] aliases) {
		super(name, permission, aliases);
	}

	@Override
	public void execute(CommandSender sender, String[] arg) {
		ProxiedPlayer p = (ProxiedPlayer)sender;
		WarCore.getInstance().getModules().getToggle().toggle(p);
	}

}
