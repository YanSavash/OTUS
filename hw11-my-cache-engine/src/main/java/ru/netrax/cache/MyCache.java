package ru.netrax.cache;

import java.lang.ref.SoftReference;
import java.util.*;
import java.util.function.Function;

public class MyCache<K, V> implements Cache<K, V> {
    private static final int TIME_THRESHOLD_MS = 5;

    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final boolean isEternal;

    private final Map<K, SoftReference<ElementOfCache<K, V>>> elements = new LinkedHashMap<>();
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;

    public MyCache(int maxElements, long lifeTimeMs, long idleTimeMs, boolean isEternal) {
        this.maxElements = maxElements;
        this.lifeTimeMs = lifeTimeMs > 0 ? lifeTimeMs : 0;
        this.idleTimeMs = idleTimeMs > 0 ? idleTimeMs : 0;
        this.isEternal = lifeTimeMs == 0 && idleTimeMs == 0 || isEternal;
    }

    @Override
    public void put(K key, V value) {
        if (elements.size() == 1)
            deleteUselessElement();
        elements.put(key, new SoftReference<>(new ElementOfCache<>(key, value)));

        if (!isEternal) {
            if (lifeTimeMs != 0) {
                TimerTask lifeTimerTask = getTimerTask(key, lifeElement -> lifeElement.getCreationalTime() + lifeTimeMs);
                timer.schedule(lifeTimerTask, lifeTimeMs);
            }
            if (idleTimeMs != 0) {
                TimerTask idleTimerTask = getTimerTask(key, idleElement -> idleElement.getLastAccessTime() + idleTimeMs);
                timer.schedule(idleTimerTask, idleTimeMs, idleTimeMs);
            }
        }
    }

    private void deleteUselessElement() {
        ElementOfCache<K, V> deleteElement = elements.values().stream()
                .min(Comparator.comparingLong(p -> {
                    ElementOfCache elementOfCache = p.get();
                    return elementOfCache != null ? elementOfCache.getLastAccessTime() : 0;
                }))
                .orElse(elements.get(elements.keySet().iterator().next()))
                .get();
        elements.remove(deleteElement != null ? deleteElement.getKey() : 0);
    }

    public void dispose() {
        timer.cancel();
    }

    private TimerTask getTimerTask(final K key, Function<ElementOfCache<K, V>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                ElementOfCache<K, V> element = elements.get(key).get();
                if (element == null || isT1BeforeT2(timeFunction.apply(element), System.currentTimeMillis())) {
                    elements.remove(key);
                    this.cancel();
                }
            }
        };
    }

    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }

    @Override
    public void remove(K key) {
        elements.remove(key);
    }

    @Override
    public V get(K key) {
        if (elements.containsKey(key) && elements.get(key) != null) {
            ElementOfCache<K, V> elem = elements.get(key).get();
            if (elem != null && elem.getValue() != null) {
                hit++;
                elem.setLastAccessTime();
                System.out.println("hits: " + hit);
                return elem.getValue();
            } else {
                miss++;
                System.out.println("miss: " + miss);
                return null;
            }
        }
        miss++;
        System.out.println("miss: " + miss);
        return null;
    }
}
