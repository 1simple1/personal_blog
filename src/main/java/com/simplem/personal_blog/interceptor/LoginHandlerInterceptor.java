package com.simplem.personal_blog.interceptor;

import com.simplem.personal_blog.model.User;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ClassName: LoginInterceptor
 * Package: com.simplem.personal_blog.interceptor
 * Description：
 * Author: simpleM
 * Date: 2020/10/20 19:11
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功后，应该有用户的session
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            //没有登录，禁止访问，返回登录页面登录
            request.setAttribute("message","要进入后台，请先登录！");
            request.getRequestDispatcher("/admin").forward(request,response);
            return false;//不放行
        }
        return true;//放行
    }
}
