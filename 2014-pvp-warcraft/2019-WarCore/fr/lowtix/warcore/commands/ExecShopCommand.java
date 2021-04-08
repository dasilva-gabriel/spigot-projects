package fr.lowtix.warcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.warbox.enums.Tags;
import fr.lowtix.warcore.WarCore;
import fr.lowtix.warcore.WarPlayer;
import fr.lowtix.warcore.enums.Ranks;
import fr.lowtix.warcore.utils.PlayerUtils;

public class ExecShopCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(!(sender instanceof Player)) {
			
			if(args.length == 2) {
				
				String name = args[0];
				String type = args[1];
				
				if(Bukkit.getPlayer(name) == null || !Bukkit.getPlayer(name).isOnline()) {
					WarCore.getInstance().getLogger().warning("SHOP ERROR (WARCORE):");
					WarCore.getInstance().getLogger().warning("name="+name+" is not connected");
					return true;
				}
				
				Player target = Bukkit.getPlayer(name);
				WarPlayer wPlayer = WarCore.getInstance().getUser(target);
				
				if(type.equalsIgnoreCase("silver")) {
					
					wPlayer.setRank(Ranks.SILVER);
					target.sendMessage("§aRank §8» §7Vous avez été promu au grade de §e"+Ranks.SILVER.getDisplayName()+"§7.");
					target.sendMessage("§bInfo §8» §7Déconnectez puis reconnectez vous pour finialiser vote achat§7.");
					
					PlayerUtils.spawnFirework(target.getLocation(), FireworkEffect.Type.BALL_LARGE, 1, Color.WHITE, Color.WHITE, false, false);
					
					return true;
				} else if(type.equalsIgnoreCase("gold")) {
					
					wPlayer.setRank(Ranks.GOLD);
					target.sendMessage("§aRank §8» §7Vous avez été promu au grade de §e"+Ranks.GOLD.getDisplayName()+"§7.");
					target.sendMessage("§bInfo §8» §7Déconnectez puis reconnectez vous pour finialiser vote achat§7.");
					
					PlayerUtils.spawnFirework(target.getLocation(), FireworkEffect.Type.BALL_LARGE, 1, Color.YELLOW, Color.YELLOW, false, false);
					
					return true;
				} else if(type.equalsIgnoreCase("diamond")) {
					
					wPlayer.setRank(Ranks.DIAMOND);
					target.sendMessage("§aRank §8» §7Vous avez été promu au grade de §e"+Ranks.DIAMOND.getDisplayName()+"§7.");
					target.sendMessage("§bInfo §8» §7Déconnectez puis reconnectez vous pour finialiser vote achat§7.");
					
					PlayerUtils.spawnFirework(target.getLocation(), FireworkEffect.Type.BALL_LARGE, 1, Color.AQUA, Color.AQUA, false, false);
					
					return true;
				} else if(type.startsWith("TAG_")) {
					String tagName = args[1].replaceFirst("TAG_", "");
					Tags tag = Tags.getTagFromName(tagName);
					
					if(tag.equals(Tags.NONE)) {
						WarCore.getInstance().getLogger().warning("SHOP ERROR (WARCORE):");
						WarCore.getInstance().getLogger().warning("name="+name+": OK");
						WarCore.getInstance().getLogger().warning("type="+type+": Tag not found");
						return true;
					}
					
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user "+target.getName()+" add warcore.tags."+tag.name().toLowerCase());
					target.sendMessage("§bInfo §8» §7Vous avez reçu le tag "+tag.getDisplay()+"§7, il est disponible dans §f'§aTags du Chat§f' §7via le §b/menu§7.");
					
				} else if (type.startsWith("gemmes_1")) {
					
					wPlayer.setGemmes(wPlayer.getGemmes() + 1);
					target.sendMessage("§bInfo §8» §7Vous avez reçu §21 gemme§7. Faites §e/menu §7pour utiliser un Booster.");
				} else if (type.startsWith("gemmes_2")) {
					
					wPlayer.setGemmes(wPlayer.getGemmes() + 2);
					target.sendMessage("§bInfo §8» §7Vous avez reçu §22 gemmes§7. Faites §e/menu §7pour utiliser un Booster.");
				} else if (type.startsWith("gemmes_5")) {
					
					wPlayer.setGemmes(wPlayer.getGemmes() + 5);
					target.sendMessage("§bInfo §8» §7Vous avez reçu §25 gemmes§7. Faites §e/menu §7pour utiliser un Booster.");
				}
				
				
				
				
			}
			
		} else {
			sender.sendMessage("§cLa commande est inconnue.");
		}
		return true;
	}

}
