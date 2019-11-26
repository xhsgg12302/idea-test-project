package me.maupassant.springboot.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-26
 * @Desc:
 */
@Aspect
@Service
public class DemoAspect {

    @Around("execution(* me.maupassant.springboot.service.impl.TestAopImpl.test(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        Object result = null;
        System.out.println("判断");
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
