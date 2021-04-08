package fr.cmuagab.sheepwars;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import fr.cmuagab.sheepwars.packet.PacketPlayOutActionBar;
import fr.cmuagab.sheepwars.packet.PacketWorldBorder;
import fr.cmuagab.sheepwars.util.ProxyHashBiMap;
import net.minecraft.server.v1_7_R4.EnumProtocol;
import net.minecraft.server.v1_7_R4.NetworkManager;
import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;
import net.minecraft.util.com.google.common.collect.BiMap;
import net.minecraft.util.io.netty.channel.Channel;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ProtocolInjector {
    private static BiMap<Integer, Class<?>> map;
    private static ProxyHashBiMap<Integer, Class> special;

    public static void inject() {
        for (final Method method : ProtocolInjector.class.getDeclaredMethods()) {
            if (method.getName().equals("addPacket")) {
                method.setAccessible(true);
                try {
                    method.invoke(null, EnumProtocol.PLAY, true, 68, PacketWorldBorder.class);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        ProtocolInjector.overrideI();
        ProtocolInjector.injectCustomPacket(PacketPlayOutChat.class, PacketPlayOutActionBar.class);
        ProtocolInjector.refreshPlayers(true);
    }

    public static void uninject() {
        ProtocolInjector.refreshPlayers(false);
        ProtocolInjector.ejectCustomPacket(PacketPlayOutActionBar.class);
        ProtocolInjector.returnI();
    }

    private static void overrideI() {
        try {
            final Field field = EnumProtocol.class.getDeclaredField("i");
            field.setAccessible(true);
            ProtocolInjector.map = (BiMap<Integer, Class<?>>) field.get(EnumProtocol.PLAY);
            ProtocolInjector.special = new ProxyHashBiMap<Integer, Class>(ProtocolInjector.map);
            ProtocolInjector.set(field, EnumProtocol.PLAY, ProtocolInjector.special);
        } catch (final Exception ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Nope, not allowed.");
        }
    }

    private static void set(final Field f, final Object o, final Object v) throws NoSuchFieldException, IllegalAccessException {
        f.setAccessible(true);
        if ((f.getModifiers() & Modifier.FINAL) != 0) {
            ProtocolInjector.set(Field.class.getDeclaredField("modifiers"), f, f.getModifiers() & ~Modifier.FINAL);
        }
        f.set(o, v);
    }

    @SuppressWarnings("unchecked")
	public static <T extends Packet> void injectCustomPacket(final Class<T> mc, final Class<? extends T> custom) {
        ProtocolInjector.special.inverse().injectSpecial(PacketPlayOutActionBar.class, PacketPlayOutChat.class);
        try {
            final Field field = EnumProtocol.class.getDeclaredField("f");
            field.setAccessible(true);
            ((Map<Class<? extends T>, EnumProtocol>) field.get(null)).put(custom, EnumProtocol.PLAY);
        } catch (final NoSuchFieldException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    public static <T extends Packet> void ejectCustomPacket(final Class<? extends T> custom) {
        ProtocolInjector.special.inverse().ejectSpecial(custom);
        try {
            final Field field = EnumProtocol.class.getDeclaredField("f");
            field.setAccessible(true);
            ((Map<?, ?>) field.get(null)).remove(custom);
        } catch (final NoSuchFieldException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    private static void returnI() {
        try {
            final Field field = EnumProtocol.class.getDeclaredField("i");
            field.setAccessible(true);
            ProtocolInjector.set(field, EnumProtocol.PLAY, ProtocolInjector.map);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
	private static void refreshPlayers(final boolean enable) {
        try {
            final Field channelField = NetworkManager.class.getDeclaredField("m");
            channelField.setAccessible(true);
            for (final Player player : Bukkit.getOnlinePlayers()) {
                final Channel channel = (Channel) channelField.get(((CraftPlayer) player).getHandle().playerConnection.networkManager);
                channel.attr(NetworkManager.f).set(enable ? ProtocolInjector.special : ProtocolInjector.map);
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }
}
