package com.sonoscape.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


/**
 * 异常统一处理
 */
@Aspect
@Component
@EnableAspectJAutoProxy
@Slf4j
public class ExceptionAspect {

    @Pointcut("execution(* com.sonoscape.service.impl.*.*(..))")
    public void gloableExceptionHandler() {
    }

    /**
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "gloableExceptionHandler()", throwing = "e")
    public void doException(JoinPoint joinPoint, Throwable e) {
        if (e != null) {
            log.error("出现异常，内容：{},{}", e.getCause(), e.getMessage());
        }
    }
}
