package fr.cmuagab.sheepwars.handler;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public enum Kit {
    // @formatter:off
    MORE_HEALTH(ChatColor.GOLD + "Plus de vie", ChatColor.GRAY + "Augmente les points de vie\n" + ChatColor.GRAY + "\n" + ChatColor.GRAY + "Augmente la santé de " + ChatColor.AQUA + "1 " + ChatColor.RED + "♥", Material.APPLE, 5),
    BETTER_BOW(ChatColor.GOLD + "Arc amélioré", ChatColor.GRAY + "Améliore votre arc\n" + ChatColor.GRAY + "\n" + ChatColor.AQUA + "10%" + ChatColor.GRAY + " de chance de recul\n" + ChatColor.GRAY + "" + ChatColor.AQUA + "5% " + ChatColor.GRAY + "de chance de critique", Material.BOW, 10),
    BETTER_SWORD(ChatColor.GOLD + "Epée améliorée", ChatColor.GRAY + "Améliore votre épée\n" + ChatColor.GRAY + "\n" + ChatColor.GRAY + "" + ChatColor.AQUA + "Epée en bois\n" + ChatColor.AQUA + "5% " + ChatColor.GRAY + "de chance de critique", Material.STONE_SWORD, 10),
    MORE_SHEEP(ChatColor.GOLD + "Plus de Moutons", ChatColor.GRAY + "Augmente les chances d'obtenir\n" + ChatColor.GRAY + "un mouton supplémentaire\n" + ChatColor.GRAY + "\n" + ChatColor.AQUA + "3% "+ChatColor.GRAY + "de chance d'obtenir un mouton", Material.WOOL, 12),
    BUILDER(ChatColor.GOLD + "Constructeur", ChatColor.GRAY + "De quoi construire et détruire\n" + ChatColor.GRAY + "le Clic-droit active vos TNT !\n" + ChatColor.GRAY + "\n" + ChatColor.AQUA + "1 " + ChatColor.GRAY + "bloc de TNT\n" + ChatColor.GRAY + "" + ChatColor.AQUA + "5 " + ChatColor.GRAY + "blocs de sable rouge", Material.BRICK, 20),
    MOBILITY(ChatColor.GOLD + "Mobilité", ChatColor.GRAY + "Améliore votre mobilité\n" + ChatColor.GRAY + "\n" + ChatColor.GRAY + "Rapidité I\n" + ChatColor.GRAY + "Chute armortie I", Material.LEATHER_BOOTS, 15),
    ARMORED_SHEEP(ChatColor.GOLD + "Moutons Blindés", ChatColor.GRAY + "Vos moutons sont beaucoup\n" + ChatColor.GRAY + "plus résistants aux dégâts\n" + ChatColor.GRAY + "\n" + ChatColor.AQUA + "120% " + ChatColor.GRAY + "des points de vie", Material.WOOL, (short) 15, 20);
    // @formatter:on

    private static Map<Player, Kit> playerKits = new HashMap<>();

    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private Material material;
    @Getter
    private short durability;
    @Getter
    private int wins;

    public static Kit getPlayerKit(final Player player) {
        return Kit.playerKits.get(player);
    }

    public static void setPlayerKit(final Player player, final Kit kit) {
        if (kit == null) {
            Kit.playerKits.remove(player);
        } else {
            Kit.playerKits.put(player, kit);
        }
    }

    private Kit(final String name, final String description, final Material material, final int wins) {
        this(name, description, material, (short) 0, wins);
    }

    private Kit(final String name, final String description, final Material material, final short durability, final int wins) {
        this.name = name;
        this.description = description;
        this.material = material;
        this.durability = durability;
        this.wins = wins;
    }
}
