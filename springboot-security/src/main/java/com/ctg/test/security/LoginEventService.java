package com.ctg.test.security;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * 注意：1）这里的 LoadingCache可以换成数据库或者redis实现
 * 2）实际的项目中，需要给管理员增加解锁账号的功能
 * @Author: yanhonghai
 * @Date: 2019/5/7 14:52
 */
@Service
public class LoginEventService {

    private final int MAX_ATTEMPT = 3;
    private LoadingCache<String,Integer> attemptsCache;
    public LoginEventService() {
        super();
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }

    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(String key) {
        int attempts = 0;
        try {
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean isBlocked(String key) {
        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
    public int getFailCounts(String key) {
        try {
            return attemptsCache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
