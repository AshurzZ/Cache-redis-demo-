package com.szq.core.support.evict;

import com.szq.cache.api.ICacheEvict;
import com.szq.cache.api.ICacheEvictContext;

/**
 * 丢弃策略
 */
public class CacheEvictNone<K,V> implements ICacheEvict<K,V> {

    @Override
    public void evict(ICacheEvictContext<K, V> context) {

    }

}