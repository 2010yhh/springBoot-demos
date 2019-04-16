package com.ctg.test.guava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Guava的使用：
 * 对于数据量较小的，而且需要加缓存的接口较少，,建议使用Google提供的guava Cache，它简单易用的同时，性能也好. 而且线程安全。
 * Redis的使用：
 * 对于那些较大数据量的，或者需要加缓存的接口较多的项目，可以去考虑Redis，memcached等等
 */
@SpringBootApplication
public class SpringbootGuavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGuavaApplication.class, args);
    }

}
