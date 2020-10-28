package com.simplem.personal_blog.controller.admin;

import com.simplem.personal_blog.model.User;
import com.simplem.personal_blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * ClassName: LoginController
 * Package: com.simplem.personal_blog.controller
 * Description：登录控制器
 * Author: simpleM
 * Date: 2020/10/20 17:46
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.login(username,password);
        if(null == user){
            attributes.addFlashAttribute("message","用户名或密码错误！");
            return "redirect:/admin";//重定向回登录
        }else {
            user.setPassword(null);
            session.setAttribute("user",user);
            System.out.println(user);
            return "admin/index";//进入后台
        }
    }

    @GetMapping("/logout")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/admin";
    }
}
