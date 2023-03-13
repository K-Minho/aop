package shop.mtcoding.aop.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import shop.mtcoding.aop.model.User;

@Aspect
@Component
public class LoginAdvice {

      @Pointcut("@annotation(shop.mtcoding.aop.LoginUser)")
      public void userLogin() {

      }

      @Around("execution(* shop.mtcoding.aop.controller..*.*(..))")
      public Object loginUserAdvice(ProceedingJoinPoint jp) throws Throwable {
            Object result = jp.proceed();
            Object[] param = new Object[1];
            Object[] args = jp.getArgs();
            for (Object arg : args) {
                  if (arg instanceof User) {
                        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
                                    .getRequestAttributes()).getRequest();
                        HttpSession session = req.getSession();
                        User principal = (User) session.getAttribute("principal");
                        param[0] = principal;
                        result = jp.proceed(param);
                  }

            }
            return result;
      }
}
