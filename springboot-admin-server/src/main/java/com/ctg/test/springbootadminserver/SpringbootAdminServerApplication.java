package com.ctg.test.springbootadminserver;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**监控界面：http://localhost:8080/admin-web
 * admin-server通过采集actuator端点数据，显示在spring -boot-admin-ui上，已知的端点几乎都有进行采集，
 * 通过spring-boot-admin可以动态切换日志级别、导出日志、导出heapdump、监控各项指标 等等
 */
@SpringBootApplication
@EnableAdminServer
public class SpringbootAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAdminServerApplication.class, args);
    }
}
