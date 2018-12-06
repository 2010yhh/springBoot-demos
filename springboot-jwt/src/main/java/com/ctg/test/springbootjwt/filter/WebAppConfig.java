package com.ctg.test.springbootjwt.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/** 
 * @Description: 注册拦截器,类似于在xml中配置bean 
 */    
//@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
	//增加拦截器
    @Override
     public void addInterceptors(InterceptorRegistry registry){
         registry.addInterceptor(new JwtInterceptor())    //指定拦截器类
                 .addPathPatterns("/**");        //指定该类拦截的url
     }
} 