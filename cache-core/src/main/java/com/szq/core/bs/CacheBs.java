package com.szq.core.bs;

import com.szq.cache.api.ICache;
import com.szq.cache.api.ICacheEvict;
import com.szq.core.core.Cache;
import com.szq.core.core.CacheContext;
import com.szq.core.support.evict.CacheEvicts;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存引导类
 */
public final class CacheBs<K,V> {
    private CacheBs(){}
    /**
     * 创建对象实例
     * @param <K> key
     * @param <V> value
     * @return this
     */
    public static <K,V> CacheBs<K,V> newInstance(){
        return new CacheBs<>();
    }
    /**
     * map 实现
     */
    private Map<K,V> map = new HashMap<>();
    /**
     * 大小限制
     */
    private int size = Integer.MAX_VALUE;
    /**
     * 驱除策略
     */
    private ICacheEvict<K,V> evict = CacheEvicts.fifo();
    /**
     * map 实现
     * @param map map
     * @return this
     * @since 0.0.2
     */
    public CacheBs<K, V> map(Map<K, V> map) {

        this.map = map;
        return this;
    }

    /**
     * 设置 size 信息
     * @param size size
     * @return this
     * @since 0.0.2
     */
    public CacheBs<K, V> size(int size) {

        this.size = size;
        return this;
    }

    /**
     * 设置驱除策略
     * @param evict 驱除策略
     * @return this
     * @since 0.0.2
     */
    public CacheBs<K, V> evict(ICacheEvict<K, V> evict) {
        this.evict = evict;
        return this;
    }

    /**
     * 构建缓存信息
     * @return 缓存信息
     * @since 0.0.2
     */
    public ICache<K,V> build() {
        CacheContext<K,V> context = new CacheContext<>();
        context.cacheEvict(evict);
        context.map(map);
        context.size(size);

        return new Cache<>(context);
    }
}
