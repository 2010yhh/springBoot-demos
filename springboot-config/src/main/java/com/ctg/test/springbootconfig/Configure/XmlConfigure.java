package com.ctg.test.springbootconfig.Configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description:
 * @Configuration可理解为用spring的时候xml里面的<beans>标签
 * @Bean可理解为用spring的时候xml里面的<bean>标签
 * @Author: yanhonghai
 * @Date: 2018/9/28 11:50
 */
@Configuration
@ImportResource(locations= {"classpath:application-bean.xml"})
public class XmlConfigure {
}
