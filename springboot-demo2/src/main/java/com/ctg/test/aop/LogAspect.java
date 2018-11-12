package com.ctg.test.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/** 
 * @Description: 日志切面
 * aop的作用：切面就是横切面，代表的是一个普遍存在的共有功能
 * 横切关注点：经常发生在核心关注点的多处，而各处都基本相似。比如权限认证、日志、方法耗时
 * 、事务处理
 */

@Aspect  //声明切面
@Component  
public class LogAspect {  
	protected Logger log = LoggerFactory.getLogger(LogAspect.class);
	//定义横切点，标记方法
    @Pointcut("execution(* com.ctg.test.controller..*.*(..))")
    public void webLog(){}  
    //前置通知，切点之前执行
    @Before("webLog()")  
    public void deBefore(JoinPoint joinPoint) throws Throwable {  
        // 接收到请求，记录请求内容  
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
        HttpServletRequest request = attributes.getRequest();  
        // 记录下请求内容  
        log.info("URL : " + request.getRequestURL().toString());  
        log.info("HTTP_METHOD : " + request.getMethod());  
        log.info("IP : " + request.getRemoteAddr());  
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());  
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));  
  
    }  
    //切点执行成功之后执行
    @AfterReturning(returning = "ret", pointcut = "webLog()")  
    public void doAfterReturning(Object ret) throws Throwable {  
        // 处理完请求，返回内容  
    	log.info("方法的返回值 : " + ret);  
    }  
  
    //后置异常通知，切点抛出异常后执行
    @AfterThrowing("webLog()")  
    public void throwss(JoinPoint jp){  
    	log.info("方法异常时执行.....");  
    }  
  
    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行；切点执行之后执行
    @After("webLog()")  
    public void after(JoinPoint jp){  
    	log.info("方法最后执行.....");  
    }  
  
    //环绕通知,环绕增强，相当于MethodInterceptor，目标方法的调用由环绕通知决定
//    @Around("webLog()")
//    public Object arround(ProceedingJoinPoint pjp) {
//    	log.info("方法环绕start.....");
//        try {
//            Object o =  pjp.proceed();
//            log.info("方法环绕proceed，结果是 :" + o);
//            return o;
//        } catch (Throwable e) {
//            //e.printStackTrace();
//            log.error("方法环绕proceed，异常结果是 :" + e.getMessage());
//            return null;
//        }
//    }
}  