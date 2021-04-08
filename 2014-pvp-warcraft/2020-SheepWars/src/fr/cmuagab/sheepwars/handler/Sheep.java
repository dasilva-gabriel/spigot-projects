package fr.cmuagab.sheepwars.handler;

import lombok.Getter;
import fr.cmuagab.sheepwars.SheepWarsPlugin;
import fr.cmuagab.sheepwars.entity.CustomSheep;
import fr.cmuagab.sheepwars.sheep.BoardingSheep;
import fr.cmuagab.sheepwars.sheep.DarkSheep;
import fr.cmuagab.sheepwars.sheep.DistorsionSheep;
import fr.cmuagab.sheepwars.sheep.EarthQuakeSheep;
import fr.cmuagab.sheepwars.sheep.ExplosiveSheep;
import fr.cmuagab.sheepwars.sheep.FragmentationSheep;
import fr.cmuagab.sheepwars.sheep.FrozenSheepAction;
import fr.cmuagab.sheepwars.sheep.HealerSheep;
import fr.cmuagab.sheepwars.sheep.IncendiarySheep;
import fr.cmuagab.sheepwars.sheep.IntergalacticSheep;
import fr.cmuagab.sheepwars.sheep.LightningSheep;
import fr.cmuagab.sheepwars.sheep.RemoteSheep;
import fr.cmuagab.sheepwars.sheep.SeekerSheep;
import fr.cmuagab.sheepwars.sheep.SwapSheep;
import fr.cmuagab.sheepwars.util.ItemBuilder;
import fr.cmuagab.sheepwars.util.MathUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings({ "deprecation", "unused" })
public enum Sheep implements Listener {
    // @formatter:off
    BOARDING(ChatColor.WHITE + "Mouton d'abordage", ChatColor.GRAY + "Permet de voyager à dos de\n" + ChatColor.GRAY + "mouton là ou vous le lancez.", new BoardingSheep(), DyeColor.WHITE, -1, false, false, false, 0.25F),
    EXPLOSIVE(ChatColor.RED + "Mouton explosif", ChatColor.GRAY + "Crée une explosion modérée.", new ExplosiveSheep(), DyeColor.RED, 5, true, false, true),
    HEALER(ChatColor.LIGHT_PURPLE + "Mouton soigneur", ChatColor.GRAY + "Soigne les joueurs les plus proches.", new HealerSheep(), DyeColor.PINK, 10, true, true, false),
    INCENDIARY(ChatColor.GOLD + "Mouton incendiaire", ChatColor.GRAY + "Crée une explosion de grande taille\n" + ChatColor.GRAY + "mettant le feu dans la zone.", new IncendiarySheep(), DyeColor.ORANGE, 5, true, false, true, 0.5F),
    SEEKER(ChatColor.GREEN + "Mouton chercheur", ChatColor.GRAY + "Cours vers la cible la plus proche\n" + ChatColor.GRAY + "et explose à son contact.", new SeekerSheep(), DyeColor.LIME, 10, true, false, true, 0.5F),
    DARK(ChatColor.DARK_GRAY + "Mouton ténébreux", ChatColor.GRAY + "Aveugle les ennemis les plus proches.", new DarkSheep(), DyeColor.BLACK, 10, true, false, true),
    FROZEN(ChatColor.AQUA + "Mouton glacé", ChatColor.GRAY + "Ralentit les ennemis et couvre la\n" + ChatColor.GRAY + "zone de neige.", new FrozenSheepAction(), DyeColor.CYAN, 10, true, false, true, 0.5F),
    EARTHQUAKE(ChatColor.GOLD + "Mouton tremblement de terre", ChatColor.GRAY + "Fait trembler la zone et\n" + ChatColor.GRAY + "projete les ennemis\n" + ChatColor.GRAY + "en l'air.", new EarthQuakeSheep(), DyeColor.BROWN, 10, true, false, true),
    DISTORSION(ChatColor.DARK_PURPLE + "Mouton distorsion", ChatColor.GRAY + "Crée de petites distorsions qui attirent\n" + ChatColor.GRAY + "tout ce qui se trouve à proximité !", new DistorsionSheep(), DyeColor.PURPLE, 5, true, false, true),
    FRAGMENTATION(ChatColor.DARK_GRAY + "Mouton à fragmentation", ChatColor.GRAY + "Explose une première fois\n" + ChatColor.GRAY + "et crée des petits moutons\n" + ChatColor.GRAY + "qui explosent à leur tour.", new FragmentationSheep(), DyeColor.GRAY, 5, true, false, true),
    LIGHTNING(ChatColor.YELLOW + "Mouton invocateur de foudre", ChatColor.GRAY + "Crée une tempête et invoque la\n" + ChatColor.GRAY + "foudre dans la zone.", new LightningSheep(), DyeColor.YELLOW, 5, true, false, true, 0.35F),
    REMOTE(ChatColor.DARK_PURPLE + "Mouton téléguidé", ChatColor.GRAY + "Crée un mouton téléguidable\n" + ChatColor.GRAY + "vos contrôles habituels.", new RemoteSheep(), DyeColor.PURPLE, 15, false, false, false, 0.35F),
    INTERGALACTIC(ChatColor.DARK_BLUE + "Mouton intergalactique", ChatColor.GRAY + "Ce mouton vient des confins de\n" + ChatColor.GRAY + "l'espace et invoque une nuée\n" + ChatColor.GRAY + "de météorites... !", new IntergalacticSheep(), DyeColor.BLUE, -1, true, false, false, 0.15F),
    SWAP(ChatColor.DARK_PURPLE + "Mouton swap", ChatColor.GRAY + "Echange votre place avec l'ennemi\n" + ChatColor.GRAY + "le plus proche.", new SwapSheep(), DyeColor.PURPLE, -1, true, false, false, 0.5F);
    // @formatter:on

    public static interface SheepAction {
        public void onSpawn(final Player player, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep);

        public boolean onTicking(final Player player, final long ticks, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep);

        public void onFinish(final Player player, final CustomSheep customSheep, final org.bukkit.entity.Sheep sheep, final boolean death);
    }

    public static org.bukkit.entity.Sheep spawnSheepStatic(final Location location, final Player player) {
        final CustomSheep customSheep = new CustomSheep(((CraftWorld) location.getWorld()).getHandle(), player);
        customSheep.setPosition(location.getX(), location.getY(), location.getZ());
        ((CraftWorld) location.getWorld()).getHandle().addEntity(customSheep);
        return (org.bukkit.entity.Sheep) customSheep.getBukkitEntity();
    }

    public static org.bukkit.entity.Sheep spawnSheep(final Location location, final Player player, final Sheep sheep) {
        final CustomSheep customSheep = new CustomSheep(((CraftWorld) location.getWorld()).getHandle(), player, sheep);
        customSheep.setPosition(location.getX(), location.getY(), location.getZ());
        ((CraftWorld) location.getWorld()).getHandle().addEntity(customSheep);
        return (org.bukkit.entity.Sheep) customSheep.getBukkitEntity();
    }

    public static Sheep giveRandomSheep(final Player player) {
        Sheep sheep = null;
        while (sheep == null) {
            final Sheep temp = Sheep.values()[MathUtils.random.nextInt(Sheep.values().length)];
            if (temp.random >= 1 || MathUtils.randomBoolean(temp.random)) {
                sheep = temp;
            }
        }
        Sheep.giveSheep(player, sheep);
        return sheep;
    }

    public static void giveSheep(final Player player, final Sheep sheep) {
        if (Team.getPlayerTeam(player) != Team.SPEC) {
            for (int i = 0; i < (Kit.getPlayerKit(player) != Kit.MORE_SHEEP || !MathUtils.randomBoolean(0.03F) ? 1 : 2); i++) {
                player.getInventory().addItem(sheep.getIcon());
            }
            player.playSound(player.getLocation(), Sound.SHEEP_IDLE, 1, 1);
        }
    }

    @Getter
    private String name;
    private String description;
    @Getter
    private SheepAction action;
    @Getter
    private DyeColor color;
    @Getter
    private ItemStack icon;
    @Getter
    private int duration;
    @Getter
    private boolean onGround;
    @Getter
    private boolean friendly;
    @Getter
    private boolean drop;
    @Getter
    private float random;

    private Sheep(final String name, final String description, final SheepAction action, final DyeColor color, final int duration, final boolean onGround, final boolean friendly, final boolean drop) {
        this(name, description, action, color, duration, onGround, friendly, drop, 1);
    }

    private Sheep(final String name, final String description, final SheepAction action, final DyeColor color, final int duration, final boolean onGround, final boolean friendly, final boolean drop, final float random) {
        this.name = name;
        this.description = description;
        this.action = action;
        this.color = color;
        this.icon = new ItemBuilder(Material.WOOL, color.getWoolData()).setTitle(name).addLores(description.split("\n")).build();
        this.duration = duration;
        this.onGround = onGround;
        this.friendly = friendly;
        this.drop = drop;
        this.random = random;
        Bukkit.getPluginManager().registerEvents(this, SheepWarsPlugin.i);
    }

    public org.bukkit.entity.Sheep spawnSheep(final Location location, final Player player) {
        return Sheep.spawnSheep(location, player, this);
    }
}
