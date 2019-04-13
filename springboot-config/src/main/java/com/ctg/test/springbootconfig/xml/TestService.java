package com.ctg.test.springbootconfig.xml;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2018/9/28 11:48
 */
public class TestService {
    public TestService() {
        System.out.println("这是一个不在springboot扫描范围内的测试类");
    }
    public String test(){
        return "测试xml配置的bean方法test()";
    }
}
