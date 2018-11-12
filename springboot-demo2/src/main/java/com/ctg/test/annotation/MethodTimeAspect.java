package com.ctg.test.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** 
 * @Description: 自定义注解可以避免代码的冗余，无侵害
 */
@Aspect
@Component
public class MethodTimeAspect {

    private static Logger logger = LoggerFactory.getLogger(MethodTimeAspect.class);

    @Pointcut("@annotation(com.ctg.test.annotation.MethodTime)")
    public void methodTimePointcut() {

    }

    @Around("methodTimePointcut()")
    public Object interceptor(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        logger.info(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName() + " cost: " + (System.currentTimeMillis() - startTime) + "ms");

        return result;
    }

}
