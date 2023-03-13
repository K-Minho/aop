package shop.mtcoding.aop.handler.interceptor;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.catalina.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.aop.handler.Interceptor;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

      private final HttpSession session;

      @Override
      public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new Interceptor()).addPathPatterns("/auth/**");
      }

      @Override
      public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
            resolvers.add(new HandlerMethodArgumentResolver() {

                  @Override
                  public boolean supportsParameter(MethodParameter parameter) {
                        boolean check1 = (parameter.getParameterAnnotation(Session.class) != null);
                        boolean check2 = User.class.equals(parameter.getParameterType());
                        return false;
                  }

                  @Override
                  @Nullable
                  public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
                              NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory)
                              throws Exception {
                        return session.getAttribute("principal");
                  }

            });
      }
}
