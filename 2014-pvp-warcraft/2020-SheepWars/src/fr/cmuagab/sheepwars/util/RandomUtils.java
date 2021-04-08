package fr.cmuagab.sheepwars.util;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.util.Vector;

public final class RandomUtils {

    public static final Random random = new Random(System.nanoTime());

    private RandomUtils() {
        // No instance allowed
    }

    public static Vector getRandomVector() {
        double x, y, z;
        x = RandomUtils.random.nextDouble() * 2 - 1;
        y = RandomUtils.random.nextDouble() * 2 - 1;
        z = RandomUtils.random.nextDouble() * 2 - 1;

        return new Vector(x, y, z).normalize();
    }

    public static Vector getRandomCircleVector() {
        double rnd, x, z;
        rnd = RandomUtils.random.nextDouble() * 2 * Math.PI;
        x = Math.cos(rnd);
        z = Math.sin(rnd);

        return new Vector(x, 0, z);
    }

    public static Material getRandomMaterial(final Material[] materials) {
        return materials[RandomUtils.random.nextInt(materials.length)];
    }

    public static double getRandomAngle() {
        return RandomUtils.random.nextDouble() * 2 * Math.PI;
    }

}
