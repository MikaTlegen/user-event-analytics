package com.example.user_event_analytics.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
@Slf4j
public class TrackEventAspect {

    @Around("@annotation(com.example.user_event_analytics.annotation.TrackEvent)")
    public Object trackEvent(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        Instant timestamp = Instant.now();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication != null ? authentication.getName() : "Anonymous";


        log.info(
                "TrackEvent: method={}, user={}, durationMs={}, timestamp={}",
                joinPoint.getSignature(), // полная сигнатура
                userName,
                duration,
                timestamp
        );

        return result;
    }


}
