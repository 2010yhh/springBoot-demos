package com.ctg.test.springboottkmybatis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//注意导入的MapperScan包
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//注意导入的MapperScan包 import tk.mybatis.spring.annotation.MapperScan
@MapperScan(basePackages = "com.ctg.test.springboottkmybatis.mapper")
public class SpringbootTkmybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTkmybatisApplication.class, args);
    }

}
