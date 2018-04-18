package com.epam.cdp.cashe;

import java.util.Map;

public interface Cache<K, V> {

    /**
     * Get entry from cache by key.
     * @param key the key
     * @return  Value
     */
    Value<V> get(K key);

    /**
     * Put entry to cache.
     * @param key the key
     * @param value the value
     */
    void put(K key, V value);

    /**
     * Get cache as map.
     * @return map
     */
    Map asMap();

    /**
     * Number of cache evictions
     * @return number
     */
    long evictionCount();

}
