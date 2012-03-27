package com.life.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class FeedAspect
{
    
    @AfterReturning(
            pointcut = "execution(public * com.life.service..*create(..))",
            returning= "result")
         public void feedOlustur(JoinPoint joinPoint, Object result) {
//       Feedable o = (Feedable) joinPoint.getArgs()[0];
//       Feed f = o.getFeed();
//        System.out.println(joinPoint.getKind() + "  -- " + joinPoint.getClass() + "  -- " + joinPoint.getTarget() + "  -- " + joinPoint.getThis());
          System.out.println("logAfterReturning() is running!");
          System.out.println("hijacked : " + joinPoint.getSignature().getName());
          System.out.println("Method returned value is : " + result);
          System.out.println("******");
       
         }

}
