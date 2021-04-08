package fr.cmuagab.sheepwars.handler;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import fr.cmuagab.sheepwars.entity.CustomSheep;
import fr.cmuagab.sheepwars.entity.EntityMeteor;
import net.minecraft.server.v1_7_R4.BiomeBase;
import net.minecraft.server.v1_7_R4.BiomeMeta;
import net.minecraft.server.v1_7_R4.EntityFireball;
import net.minecraft.server.v1_7_R4.EntityInsentient;
import net.minecraft.server.v1_7_R4.EntitySheep;
import net.minecraft.server.v1_7_R4.EntityTypes;

import org.bukkit.entity.EntityType;

@SuppressWarnings({ "rawtypes", "unchecked" })
public enum Entity {
    METEOR("Fireball", 12, EntityType.FIREBALL, EntityFireball.class, EntityMeteor.class), SHEEP("Sheep", 91, EntityType.SHEEP, EntitySheep.class, CustomSheep.class);

    private String name;
    private int id;
    private EntityType entityType;
    private Class<? extends EntityInsentient> nmsClass;
    private Class<? extends EntityInsentient> customClass;

    private Entity(final String name, final int id, final EntityType entityType, final Class nmsClass, final Class customClass) {
        this.name = name;
        this.id = id;
        this.entityType = entityType;
        this.nmsClass = nmsClass;
        this.customClass = customClass;
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.id;
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public Class<? extends EntityInsentient> getNMSClass() {
        return this.nmsClass;
    }

    public Class<? extends EntityInsentient> getCustomClass() {
        return this.customClass;
    }

    /**
    * Register our entities.
    */
    public static void registerEntities() {
        for (final Entity entity : Entity.values()) {
            Entity.a(entity.getCustomClass(), entity.getName(), entity.getID());
        }

        // BiomeBase#biomes became private.
        BiomeBase[] biomes;
        try {
            biomes = (BiomeBase[]) Entity.getPrivateStatic(BiomeBase.class, "biomes");
        } catch (final Exception exc) {
            // Unable to fetch.
            return;
        }
        for (final BiomeBase biomeBase : biomes) {
            if (biomeBase == null) {
                break;
            }

            // This changed names from J, K, L and M.
            for (final String field : new String[] { "as", "at", "au", "av" }) {
                try {
                    final Field list = BiomeBase.class.getDeclaredField(field);
                    list.setAccessible(true);
                    final List<BiomeMeta> mobList = (List<BiomeMeta>) list.get(biomeBase);

                    // Write in our custom class.
                    for (final BiomeMeta meta : mobList) {
                        for (final Entity entity : Entity.values()) {
                            if (entity.getNMSClass().equals(meta.b)) {
                                meta.b = entity.getCustomClass();
                            }
                        }
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
    * Unregister our entities to prevent memory leaks. Call on disable.
    */
    public static void unregisterEntities() {
        for (final Entity entity : Entity.values()) {
            // Remove our class references.
            try {
                ((Map) Entity.getPrivateStatic(EntityTypes.class, "d")).remove(entity.getCustomClass());
            } catch (final Exception e) {
                e.printStackTrace();
            }

            try {
                ((Map) Entity.getPrivateStatic(EntityTypes.class, "f")).remove(entity.getCustomClass());
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        for (final Entity entity : Entity.values()) {
            try {
                // Unregister each entity by writing the NMS back in place of the custom class.
                Entity.a(entity.getNMSClass(), entity.getName(), entity.getID());
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        // Biomes#biomes was made private so use reflection to get it.
        BiomeBase[] biomes;
        try {
            biomes = (BiomeBase[]) Entity.getPrivateStatic(BiomeBase.class, "biomes");
        } catch (final Exception exc) {
            // Unable to fetch.
            return;
        }
        for (final BiomeBase biomeBase : biomes) {
            if (biomeBase == null) {
                break;
            }

            // The list fields changed names but update the meta regardless.
            for (final String field : new String[] { "as", "at", "au", "av" }) {
                try {
                    final Field list = BiomeBase.class.getDeclaredField(field);
                    list.setAccessible(true);
                    final List<BiomeMeta> mobList = (List<BiomeMeta>) list.get(biomeBase);

                    // Make sure the NMS class is written back over our custom class.
                    for (final BiomeMeta meta : mobList) {
                        for (final Entity entity : Entity.values()) {
                            if (entity.getCustomClass().equals(meta.b)) {
                                meta.b = entity.getNMSClass();
                            }
                        }
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
    * A convenience method.
    * @param clazz The class.
    * @param f The string representation of the private static field.
    * @return The object found
    * @throws Exception if unable to get the object.
    */
    private static Object getPrivateStatic(final Class clazz, final String f) throws Exception {
        final Field field = clazz.getDeclaredField(f);
        field.setAccessible(true);
        return field.get(null);
    }

    /*
    * Since 1.7.2 added a check in their entity registration, simply bypass it and write to the maps ourself.
    */
    private static void a(final Class paramClass, final String paramString, final int paramInt) {
        try {
            ((Map) Entity.getPrivateStatic(EntityTypes.class, "c")).put(paramString, paramClass);
            ((Map) Entity.getPrivateStatic(EntityTypes.class, "d")).put(paramClass, paramString);
            ((Map) Entity.getPrivateStatic(EntityTypes.class, "e")).put(Integer.valueOf(paramInt), paramClass);
            ((Map) Entity.getPrivateStatic(EntityTypes.class, "f")).put(paramClass, Integer.valueOf(paramInt));
            ((Map) Entity.getPrivateStatic(EntityTypes.class, "g")).put(paramString, Integer.valueOf(paramInt));
        } catch (final Exception e) {
            // Unable to register the new class.
            e.printStackTrace();
        }
    }
}
