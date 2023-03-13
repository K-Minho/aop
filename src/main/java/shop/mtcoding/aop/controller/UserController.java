package shop.mtcoding.aop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.aop.handler.aop.LoginUser;
import shop.mtcoding.aop.model.User;

@RequiredArgsConstructor // final 의존성 주입코드
@RestController
public class UserController {

      private final HttpSession session;

      @GetMapping("/login")
      public String login() {
            User user = new User(1, "ssar", "1234", "asdf@asdf");
            session.setAttribute("principal", user);
            return "login!";
      }

      @GetMapping("/user/1")
      public String user() {
            return "ok";
      }

      @GetMapping("/auth/1")
      public String auth(@LoginUser User principal) {
            System.out.println("insert!");
            System.out.println(principal.getUsername());
            return "ok";
      }
}
