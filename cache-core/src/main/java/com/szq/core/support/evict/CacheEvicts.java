package com.szq.core.support.evict;

import com.szq.cache.api.ICacheEvict;

/**
 * 丢弃策略
 */
public final class CacheEvicts {

    private CacheEvicts(){}

    /**
     * 无策略
     *
     * @param <K> key
     * @param <V> value
     * @return 结果
     * @since 0.0.2
     */
    public static <K, V> ICacheEvict<K, V> none() {
        return new CacheEvictNone<>();
    }

    /**
     * 先进先出
     *
     * @param <K> key
     * @param <V> value
     * @return 结果
     */
    public static <K, V> ICacheEvict<K, V> fifo() {
        return new CacheEvictFIFO<>();
    }

}