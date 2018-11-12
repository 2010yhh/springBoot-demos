package com.ctg.test.annotation;

import java.lang.annotation.*;
/**
* 先定义一个注解类
*/

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)  
@Documented
@Inherited
public @interface MethodTime {
    //自定义属性
}  
