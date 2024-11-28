package com.glwa.accountservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AopConfig {

    @Before("execution(* com.glwa.accountservice.services.impl.BankAccountServiceImpl.*(..))")
    public void logMethodServiceEntry(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info("In method : "+name+":");
    }
}
