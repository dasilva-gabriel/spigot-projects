package fr.cmuagab.sheepwars.entity;

import fr.cmuagab.sheepwars.util.ParticleEffect;
import net.minecraft.server.v1_7_R4.EntityFireball;
import net.minecraft.server.v1_7_R4.MovingObjectPosition;
import net.minecraft.server.v1_7_R4.World;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class EntityMeteor extends EntityFireball {
    private final float speedModifier = 1.15F;
    private final float impactPower = 4.0F;
    private boolean explosion;

    public EntityMeteor(final World world) {
        super(world);
    }

    public EntityMeteor(final World world, final Player shooter) {
        super(world);
        this.shooter = ((CraftPlayer) shooter).getHandle();
    }

    @Override
    public void h() {
        if (this.inWater) {
            this.world.createExplosion(this.shooter, this.locX, this.locY, this.locZ, this.impactPower, true, true);
            this.die();
        } else {
            final Location location = this.getBukkitEntity().getLocation();
            if (this.explosion) {
                location.getWorld().playEffect(location, Effect.EXPLOSION_HUGE, 1);
            }
            ParticleEffect.FLAME.display(location, 0.2F, 0.5F, 0.2F, 0, 10);
            ParticleEffect.SMOKE.display(location, 0.2F, 0.5F, 0.2F, 0, 10);
            this.explosion = !this.explosion;
            this.motX *= this.speedModifier;
            this.motY *= this.speedModifier;
            this.motZ *= this.speedModifier;
            super.h();
        }
    }

    @Override
    public void a(final MovingObjectPosition movingobjectposition) {
        this.world.createExplosion(this.shooter, this.locX, this.locY, this.locZ, this.impactPower, true, true);
        this.die();
    }
}
