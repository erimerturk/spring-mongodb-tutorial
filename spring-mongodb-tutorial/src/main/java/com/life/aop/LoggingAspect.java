package com.life.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect
{
    
    @AfterReturning(
            pointcut = "execution(public * com.life.repository..*(..))",
            returning= "result")
         public void deneme(JoinPoint joinPoint, Object result) {
       
          System.out.println("logAfterReturning() is running!");
          System.out.println("hijacked : " + joinPoint.getSignature().getName());
          System.out.println("Method returned value is : " + result);
          System.out.println("******");
       
         }

}
