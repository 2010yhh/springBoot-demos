package com.ctg.test.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class SpringbootFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFileApplication.class, args);
    }
    /**
     * 设置上传文件大小，配置文件属性设置无效
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory config = new MultipartConfigFactory();
        config.setMaxFileSize("1100MB");
        config.setMaxRequestSize("1100MB");
        return config.createMultipartConfig();
    }
}
