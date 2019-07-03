package com.sono.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
//@Slf4j
@Component
@EnableAspectJAutoProxy
public class LogAspect {
    private Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.sono.service.impl.*.*(..))")
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
        System.out.println(3333);
    }

}
