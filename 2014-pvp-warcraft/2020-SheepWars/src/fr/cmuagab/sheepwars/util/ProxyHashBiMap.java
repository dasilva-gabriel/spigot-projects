package fr.cmuagab.sheepwars.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.util.com.google.common.collect.BiMap;

/**
* @author DarkSeraphim
*/
public class ProxyHashBiMap<K, V> implements BiMap<K, V> {

    private final BiMap<K, V> delegate;

    private final ProxyHashBiMap<V, K> inverse;

    // This will proxy
    protected Map<K, K> proxy = new HashMap<K, K>(1);

    public ProxyHashBiMap(final BiMap<K, V> map) {
        this.delegate = map;
        this.inverse = new Inverse(this, map.inverse(), map);
    }

    private ProxyHashBiMap(final BiMap<K, V> map, final BiMap<V, K> inversed) {
        this.delegate = map;
        this.inverse = null;
    }

    @Override
    public int size() {
        return this.delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return this.delegate.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return this.delegate.containsValue(value);
    }

    @Override
    public V get(Object key) {
        Object okey = null;
        if ((okey = this.proxy.get(key)) != null) {
            key = okey;
        }
        return this.delegate.get(key);
    }

    @Override
    public V put(@Nullable final K k, @Nullable final V v) {
        return this.delegate.put(k, v);
    }

    @Override
    public V remove(final Object key) {
        return this.delegate.remove(key);
    }

    @Override
    public V forcePut(@Nullable final K k, @Nullable final V v) {
        return this.delegate.forcePut(k, v);
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> map) {
        this.delegate.clear();
    }

    @Override
    public void clear() {
        this.delegate.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.delegate.keySet();
    }

    @Override
    public Set<V> values() {
        return this.delegate.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.delegate.entrySet();
    }

    @Override
    public ProxyHashBiMap<V, K> inverse() {
        return this.inverse;
    }

    public void injectSpecial(final K key, final K okey) {
        this.proxy.put(key, okey);
    }

    public void ejectSpecial(final K key) {
        this.proxy.remove(key);
    }

    private class Inverse<V, K> extends ProxyHashBiMap<V, K> {
        private final ProxyHashBiMap original;

        private Inverse(final ProxyHashBiMap original, final BiMap<V, K> forward, final BiMap<K, V> backward) {
            super(forward, backward);
            this.original = original;
        }

        /**
        * Prevent creation of more Maps, just return the original
        * @return the original BiMap
        */
        @Override
        public ProxyHashBiMap<K, V> inverse() {
            return this.original;
        }
    }
}
