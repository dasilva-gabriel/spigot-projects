package eu.pvpwarcraft.warfight.managers.guis;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import eu.pvpwarcraft.warfight.managers.arenas.Arena;
import eu.pvpwarcraft.warfight.managers.arenas.Arena.ArenaType;
import eu.pvpwarcraft.warfight.managers.arenas.ArenasLocations;
import eu.pvpwarcraft.warfight.managers.arenas.ArenasManager;
import eu.pvpwarcraft.warfight.utils.ItemBuilder;

public class ConfigArenaGui extends Gui {
	
	private Arena arena;
	private String lore_loc[] = { "  ", " §8• §7Légende:", "   §8■ §ePosition non définie: §7§oGris", "   §8■ §ePosition définie: §a§oVert" };
	private String lore_type_1[] = { " §8• §7Arènes avec blocks: §8[§2✔ §aSélectionné§8]", " §8• §7Arènes sans blocks: §8[§f§8]"};
	private String lore_type_2[] = { " §8• §7Arènes avec blocks: §8[§f§8]", " §8• §7Arènes sans blocks: §8[§2✔ §aSélectionné§8]"};

	public ConfigArenaGui(Player player, Arena arena) {
		super("§8■ §cAdministration §8■", 3, player);
		this.arena = arena;
	}

	@Override
	public void drawScreen() {
		
		for(int i = 0; i < 9; i++){
			setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDamage(0).setName(" ").build());
		}
		
		setItem(2, new ItemBuilder(Material.PAPER).setName("§8» §7Nom de l'arène: §e"+arena.getName()).build());
		setItem(4, new ItemBuilder(Material.EYE_OF_ENDER).setName("§8» §7Se téléporter à l'arène §e"+arena.getName()).build());
		setItem(6, new ItemBuilder(Material.BOOK_AND_QUILL).setName("§8» §7Créer par §e"+arena.getCreator()+" §8(§7Le: §b"+arena.getDate()+"§8)").build());
		
		setItem(9, new ItemBuilder(Material.IRON_BOOTS).setName("§7Positions à définir §3➡").setLore(lore_loc).build());
		
		if(arena.getPos_start() == null){
			setItem(11, new ItemBuilder(Material.INK_SACK).setDamage(8).setName("§8» §7Placer la position §cPosition Haute").build());
		}else{
			setItem(11, new ItemBuilder(Material.INK_SACK).setDamage(10).setName("§8» §7Replacer la position §aPosition Haute").build());
		}
		
		if(arena.getPos_end() == null){
			setItem(12, new ItemBuilder(Material.INK_SACK).setDamage(8).setName("§8» §7Placer la position §cPosition Basse").build());
		}else{
			setItem(12, new ItemBuilder(Material.INK_SACK).setDamage(10).setName("§8» §7Replacer la position §aPosition Basse").build());
		}
		
		if(arena.getSpawn1() == null){
			setItem(13, new ItemBuilder(Material.INK_SACK).setDamage(8).setName("§8» §7Placer la position §cSpawn 1").build());
		}else{
			setItem(13, new ItemBuilder(Material.INK_SACK).setDamage(10).setName("§8» §7Replacer la position §aSpawn 1").build());
		}
		
		if(arena.getSpawn2() == null){
			setItem(14, new ItemBuilder(Material.INK_SACK).setDamage(8).setName("§8» §7Placer la position §cSpawn 2").build());
		}else{
			setItem(14, new ItemBuilder(Material.INK_SACK).setDamage(10).setName("§8» §7Replacer la position §aSpawn 2").build());
		}
		
		if(arena.getType().equals(ArenaType.HAS_BLOCK)){
			setItem(19, new ItemBuilder(Material.COBBLESTONE).setName("§8» §7Type de l'arène:").setLore(lore_type_1).build());
		}else{
			setItem(19, new ItemBuilder(Material.BARRIER).setName("§8» §7Type de l'arène:").setLore(lore_type_2).build());
		}

	}

	@Override
	public void onClick(int position, ItemStack item, ClickType click) {

		Player player = getPlayer();

		if (item.getType() == Material.STAINED_GLASS_PANE) {
			return;
		}
		
		if (position == 4) {
			player.teleport(arena.getSpawn1());
			player.sendMessage("§6Settings §8» §7Téléportation à l'arène §b"+arena.getName()+"§7!");
			GuiManager.closePlayer(player);
			return;
		}

		if (position == 19) {
			
			if(arena.getType().equals(ArenaType.HAS_BLOCK)){
				ArenasManager.setType(arena.getName(), ArenaType.NOT_BLOCK);
				arena.setType(ArenaType.NOT_BLOCK);
				player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 10.0F);
			}else{
				ArenasManager.setType(arena.getName(), ArenaType.HAS_BLOCK);
				arena.setType(ArenaType.HAS_BLOCK);
				player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 10.0F);
			}

			GuiManager.closePlayer(player);
			GuiManager.openGui(new ConfigArenaGui(player, arena));
			
		}
		
		Location loc = player.getLocation();
		
		if (position == 11) {

			ArenasManager.addLocations(arena.getName(), loc, ArenasLocations.POS_START);
			
			player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 10.0F);
			player.sendMessage("§6Settings §8» §7Vous avez placé la position §aPosition Haute de l'arène "+arena.getName()+"§7!");
			
			arena.setPos_start(loc);
			
			GuiManager.closePlayer(player);
			GuiManager.openGui(new ConfigArenaGui(player, arena));
			
			return;
		}
		if (position == 12) {

			ArenasManager.addLocations(arena.getName(), loc, ArenasLocations.POS_END);
						
			player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 10.0F);
			player.sendMessage("§6Settings §8» §7Vous avez placé la position §aPosition Basse de l'arène "+arena.getName()+"§7!");
			
			arena.setPos_end(loc);
			
			GuiManager.closePlayer(player);
			GuiManager.openGui(new ConfigArenaGui(player, arena));
			
			return;
		}
		if (position == 13) {

			ArenasManager.addLocations(arena.getName(), loc, ArenasLocations.SPAWN1);
						
			player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 10.0F);
			player.sendMessage("§6Settings §8» §7Vous avez placé la position §aSpawn 1 de l'arène "+arena.getName()+"§7!");

			arena.setSpawn1(loc);

			GuiManager.closePlayer(player);
			GuiManager.openGui(new ConfigArenaGui(player, arena));
			
			return;
		}
		if (position == 14) {

			ArenasManager.addLocations(arena.getName(), player.getLocation(), ArenasLocations.SPAWN2);			
			player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 10.0F);
			player.sendMessage("§6Settings §8» §7Vous avez placé la position §aSpawn 2 de l'arène "+arena.getName()+"§7!");

			arena.setSpawn2(loc);
				
			GuiManager.closePlayer(player);
			GuiManager.openGui(new ConfigArenaGui(player, arena));
			
			return;
		}

	}

}
