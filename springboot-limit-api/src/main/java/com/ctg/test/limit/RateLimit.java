package com.ctg.test.limit;

import java.lang.annotation.*;

/**
 * @Description: TODO
 * @Author: yanhonghai
 * @Date: 2019/4/16 12:38
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE,ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    double limitNum() default 20;  //默认每秒放入桶中的token
}