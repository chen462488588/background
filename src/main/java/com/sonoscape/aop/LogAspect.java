package com.sonoscape.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 日志统一处理
 */
@Aspect
@Slf4j
@Component
@EnableAspectJAutoProxy
public class LogAspect {


    @Pointcut("execution(* com.sonoscape.service.impl.*.*(..))")
    public void printEnterLog() {
    }

//    @Around("printEnterLog()")
//    public void enterAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.warn("enter around " + joinPoint.getSignature().getName());
//        joinPoint.proceed();
//        log.warn("end around  ");
//
//    }

    @Before("printEnterLog()")
    public void before(JoinPoint joinPoint){
        log.error("获取方法名：{}",joinPoint.getSignature().getName());
    }

}
