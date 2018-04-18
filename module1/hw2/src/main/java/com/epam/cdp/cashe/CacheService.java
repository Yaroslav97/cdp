package com.epam.cdp.cashe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class CacheService<K, V> implements Cache<K, V> {

    private static final Logger LOG = LoggerFactory.getLogger(CacheService.class);

    private int maxSize = 100_000;
    private long expireAfterAccess = 5000;
    private AtomicLong evictionCount = new AtomicLong();

    private Map<K, Value<V>> casheMap = new ConcurrentHashMap<>(maxSize);

    private CacheService(CasheServiceBuilder casheServiceBuilder) {
        this.maxSize = casheServiceBuilder.maxSize;
        this.expireAfterAccess = casheServiceBuilder.lastAccessTime;

        clearCacheScheduler(expireAfterAccess);
    }

    @Override
    public Value<V> get(K key) {
        Value<V> value = casheMap.get(key);

        if (casheMap.containsKey(key)) {
            casheMap.put(key, new Value<>(value.getValue(), System.currentTimeMillis()));
        }
        return value;
    }

    @Override
    public void put(K key, V value) {
        if (casheMap.size() >= maxSize && !casheMap.isEmpty()) {
            K entryKey = casheMap.entrySet().stream().min((item1, item2) ->
                    (int) (item1.getValue().getLastUsage() - item2.getValue().getLastUsage())).get().getKey();

            Value<V> removeEntry = casheMap.remove(entryKey);

            if (removeEntry != null) {
                evictionCount.incrementAndGet();
            }
        }

        if (value != null) {
            casheMap.put(key, new Value<>(value, System.currentTimeMillis()));
        } else {
            throw new NullPointerException("Cannot add null to cashe");
        }
    }

    @Override
    public Map asMap() {
        return Collections.unmodifiableMap(casheMap);
    }

    @Override
    public long evictionCount() {
        return evictionCount.get();
    }

    public static class CasheServiceBuilder<K, V> {
        private int maxSize;
        private long lastAccessTime;

        /**
         * Max size
         *
         * @param maxSize the size
         * @return this
         */
        public CasheServiceBuilder<K, V> maxSize(int maxSize) {
            if (maxSize > 100000 || maxSize < 0) {
                throw new IllegalArgumentException("Max size should not be grater than 100 000. Min size should  be grater than 0");
            }
            this.maxSize = maxSize;
            return this;
        }

        /**
         * @param seconds  the seconds
         * @param timeUnit the time unit
         * @return this
         */
        public CasheServiceBuilder<K, V> expireAfterAccess(long seconds, TimeUnit timeUnit) {
            if (seconds <= 0) {
                throw new IllegalArgumentException("Wrong time");
            }

            this.lastAccessTime = TimeUnit.MILLISECONDS.convert(seconds, timeUnit);
            return this;
        }

        public Cache<K, V> build() {
            return new CacheService<>(this);
        }
    }

    /**
     * Auto clear useless items from cash.
     *
     * @param time the time
     */
    private void clearCacheScheduler(long time) {
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutor.schedule(() -> {
            List<K> bucket = casheMap.entrySet().stream().filter(entry -> entry.getValue().getLastUsage() + time
                    < System.currentTimeMillis()).map(Map.Entry::getKey).collect(Collectors.toList());
            bucket.forEach(key -> casheMap.remove(key));
            evictionCount.addAndGet(bucket.size());
            LOG.info("Items to remove - " + bucket);
        }, time, TimeUnit.MILLISECONDS);
    }

}
