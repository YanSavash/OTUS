package ru.netrax.cache;

public class ElementOfCache<K,V> {
    private final K key;
    private final V value;
    private final long creationalTime;
    private long lastAccessTime;

    public ElementOfCache(K key,V value) {
        this.key = key;
        this.value = value;
        creationalTime = getCurrentTime();
        lastAccessTime = getCurrentTime();
    }

    protected long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public long getCreationalTime() {
        return creationalTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime() {
        this.lastAccessTime = getCurrentTime();
    }
}
