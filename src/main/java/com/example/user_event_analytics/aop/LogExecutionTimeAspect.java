package com.example.user_event_analytics.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogExecutionTimeAspect {

    @Around("@annotation(com.example.user_event_analytics.annotation.LogExecutionTime)")
    public Object logExecutionTimeAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        log.info("Method {} executed in {} ms", joinPoint.getSignature(), duration);
        log.info("Method name: {}", joinPoint.getSignature().getName());;
        return result;

    }


}
