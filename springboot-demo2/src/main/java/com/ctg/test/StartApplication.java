package com.ctg.test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ServletComponentScan
//使用@ServletComponentScan 注解后，Servlet、Filter、Listener
// 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
        //运行之后在浏览器中访问：
        // http://localhost:8080/springboot-demo2/test1
        //http://localhost:8080//springboot-demo2/test2
    }
}