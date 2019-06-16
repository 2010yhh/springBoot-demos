package com.ctg.test;

import ch.qos.logback.classic.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/** 
 * @Description: mybatits,echache,redis
 * @author: 2010yhh
 * @date: 2018年3月12日 上午12:20:56 
 */
//@RestController
//@SpringBootApplication
//第1种，导入xml bean配置文件
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
//@ComponentScan("com.ctg.test")//使包路径下带有注解的类可以使用@Autowired自动注入
//@ImportResource(locations={"classpath:application-bean.xml"})
//public class StartApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(StartApplication.class, args);
//  ////http://localhost:8080/application/test1
//    }
//}
//第2种,扫描多个包
@SpringBootApplication
@MapperScan("com.ctg.test.mapper")//配置mybatis包扫描
@EnableCaching// 启用缓存注解
public class StartApplication {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StartApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
        logger.info(".............application start");
    }
}
