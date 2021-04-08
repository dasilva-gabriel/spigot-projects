package fr.cmuagab.sheepwars.entity;

import java.lang.reflect.Field;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.entity.Player;

import fr.cmuagab.sheepwars.handler.Sheep;
import fr.cmuagab.sheepwars.util.ParticleEffect;
import fr.cmuagab.sheepwars.util.WorldUtils;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntitySheep;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;

public class CustomSheep extends EntitySheep {
	
    private Sheep sheep;
    private Player player;
    private World world;
    private boolean explosion = true;
    private boolean ground;
    private long defaultTicks;
    private long ticks;
    private boolean drop;

    public CustomSheep(final World world) {
        super(world);
    }

    public CustomSheep(final World world, final Player player) {
        super(world);
        this.player = player;
        this.world = ((CraftWorld) player.getWorld()).getHandle();
    }

    public CustomSheep(final World world, final Player player, final Sheep sheep) {
        this(world, player);
        this.getNavigation().a(true);
        this.a(0.9F, 1.3F);
        // Settings
        this.sheep = sheep;
        this.ticks = sheep.getDuration() == -1 ? Long.MAX_VALUE : sheep.getDuration() * 20;
        this.defaultTicks = this.ticks;
        this.ground = !sheep.isOnGround();
        this.drop = sheep.isDrop();
        this.setColor(sheep.getColor().ordinal());
        this.setCustomName(sheep.getName());
        if (sheep != null) {
            sheep.getAction().onSpawn(player, this, this.getBukkitSheep());
        }
        // Custom Sheeps
        if (sheep == Sheep.INTERGALACTIC || sheep == Sheep.LIGHTNING) {
            this.fireProof = true;
        } else if (sheep == Sheep.SEEKER) {
            try {
                final Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
                bField.setAccessible(true);
                final Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
                cField.setAccessible(true);
                bField.set(this.goalSelector, new UnsafeList<PathfinderGoalSelector>());
                bField.set(this.targetSelector, new UnsafeList<PathfinderGoalSelector>());
                cField.set(this.goalSelector, new UnsafeList<PathfinderGoalSelector>());
                cField.set(this.targetSelector, new UnsafeList<PathfinderGoalSelector>());
            } catch (final Exception e) {
                e.printStackTrace();
            }
            this.getNavigation().b(true);
            this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, false));
            this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8));
            this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
            this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
            this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 0, true));
            this.getAttributeInstance(GenericAttributes.d).setValue(0.2300000041723251D * 1.8D); // Speed modifier
        }
    }

    @Override
    public void g(final double d0, final double d1, final double d2) {}

    @Override
    public void e(float sideMot, float forMot) {
        if (this.sheep != null && this.ground) {
            // Custom Sheeps
            if (this.sheep == Sheep.REMOTE) {
                if (this.passenger == null || !(this.passenger instanceof EntityHuman) || !this.getCustomName().equals(Sheep.REMOTE.getName())) {
                    super.e(sideMot, forMot);
                    this.W = 0.5F; // Make sure the entity can walk over half slabs, instead of jumping
                    return;
                }
                this.lastYaw = this.yaw = this.passenger.yaw;
                this.pitch = this.passenger.pitch * 0.5F;
                // Set the entity's pitch, yaw, head rotation etc.
                this.b(this.yaw, this.pitch); //[url]https://github.com/Bukkit/mc-dev/blob/master/net/minecraft/server/Entity.java#L163-L166[/url]
                this.aO = this.aM = this.yaw;
                this.W = 1.0F; // The custom entity will now automatically climb up 1 high blocks
                sideMot = ((EntityLiving) this.passenger).bd * 0.5F;
                forMot = ((EntityLiving) this.passenger).be;
                if (forMot <= 0.0F) {
                    forMot *= 0.25F; // Make backwards slower
                }
                sideMot *= 0.75F; // Also make sideways slower
                final float speed = 0.35F; // 0.2 is the default entity speed. I made it slightly faster so that riding is better than walking
                this.i(speed); // Apply the speed
            }
        }
        super.e(sideMot, forMot); // Apply the motion to the entity
    }

    @Override
    public void bL() {
        try {
            if (this.sheep != null) {
                final Location location = this.getBukkitEntity().getLocation();
                if (this.ticks % 2 == 0) {
                    ParticleEffect.FIREWORKS_SPARK.display(location, 0, 0, 0, 0, 1);
                }
                if (!this.ground) {
                    this.ground = this.sheep.isFriendly() || this.onGround || this.inWater;
                } else {
                    if (this.sheep.isFriendly() || this.ticks <= this.defaultTicks - 20) {
                        if (this.ticks == 0 || this.sheep.getAction().onTicking(this.player, this.ticks, this, this.getBukkitSheep()) || !this.isAlive()) {
                            boolean death = true;
                            if (this.isAlive()) {
                                this.die();
                                death = false;
                            }
                            this.sheep.getAction().onFinish(this.player, this, this.getBukkitSheep(), death);
                            return;
                        }
                    }
                    this.ticks--;
                }
                // Custom Sheeps
                if (this.sheep == Sheep.INTERGALACTIC) {
                    if (this.explosion) {
                        location.getWorld().playEffect(location, Effect.EXPLOSION_LARGE, 1);
                    }
                    ParticleEffect.FLAME.display(location, 0.2F, 0.5F, 0.2F, 0, 10);
                    ParticleEffect.SMOKE.display(location, 0.2F, 0.5F, 0.2F, 0, 10);
                    this.explosion = !this.explosion;
                }
            }
        } catch (final Exception exception) {} finally {
            super.bL();
        }
    }

    @Override
    public void dropDeathLoot(final boolean flag, final int i) {
        if (this.drop) {
            this.world.getWorld().dropItem(this.getBukkitEntity().getLocation(), this.sheep.getIcon());
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public org.bukkit.entity.Sheep getBukkitSheep() {
        return (org.bukkit.entity.Sheep) this.getBukkitEntity();
    }

    public void explode(final float power) {
        this.explode(power, true, false);
    }

    public void explode(final float power, final boolean fire) {
        this.explode(power, true, fire);
    }

    public void explode(final float power, final boolean breakBlocks, final boolean fire) {
        this.drop = false;
        this.die();
        WorldUtils.createExplosion(this.player, this.world, this.locX, this.locY, this.locZ, power, breakBlocks, fire);
    }
}
