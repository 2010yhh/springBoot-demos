package com.ctg.test.springbootstartertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot会自动进行类的自动配置:
 * 1)SpringBoot 在启动时会去依赖的starter包中寻找 resources/META-INF/spring.factories 文件，然后根据文件中配置的Jar包去扫描项目所依赖的Jar包，这类似于 Java 的 SPI 机制。
 * 2)根据 spring.factories配置加载AutoConfigure类。
 * 3)根据 @Conditional等注解的条件，进行自动配置并将Bean注入Spring Context 上下文当中。
 */
@SpringBootApplication
public class SpringbootStarterTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStarterTestApplication.class, args);
    }

}
