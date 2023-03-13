package shop.mtcoding.aop.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAdvice {

      // 경로 축약
      @Pointcut("@annotation(shop.mtcoding.aop.handler.aop.Hello)")
      public void hello() {
      }

      @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
      public void getMapping() {
      }

      @Before("hello()")
      public void helloAdvice() {
            System.out.println("hello!");

      }

      @After("getMapping()")
      public void getAdvice() {
            System.out.println("get!");

      }

      @Around("hello()")
      public Object aroundAdvice(ProceedingJoinPoint jp) throws Throwable {
            Object[] args = jp.getArgs();
            System.out.println("around! args size : " + args.length);

            for (Object arg : args) {
                  if (arg instanceof String) {
                        String name = (String) arg;
                        System.out.println("Hello " + name + "!");
                  }
            }
            return jp.proceed();
      }
}
