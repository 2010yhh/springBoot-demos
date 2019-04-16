package com.ctg.test.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * 1)asMap视图的任何方法都不能保证缓存项被原子地加载到缓存中
 * 所以相比于Cache.asMap().putIfAbsent(K,V)，
 * Cache.get(K, Callable<V>) cache.put(key, value)应该总是优先使用
 * 2)guava提供了三种缓存回收的策略：基于容量回收、定时回收和基于引用回收
 */
@Service
public class CommonCacheService {
    public Cache<String, Object> getCache() {
        return cache;
    }

    public void setCache(Cache<String, Object> cache) {
        this.cache = cache;
    }

    private Cache<String,Object> cache = null;
    @PostConstruct
    public void init() {
        cache = CacheBuilder.newBuilder()
                .initialCapacity(100)
                .maximumSize(10000)
                .expireAfterWrite(30,TimeUnit.SECONDS)
                .build();
    }
}
