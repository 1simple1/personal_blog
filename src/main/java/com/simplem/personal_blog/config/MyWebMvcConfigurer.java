package com.simplem.personal_blog.config;


import com.simplem.personal_blog.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: MyMvcConfig
 * Package: com.simplem.personal_blog.config
 * Description：
 * Author: simpleM
 * Date: 2020/10/20 22:05
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    //自定义的视图控制器，
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("admin/login");
    }

    //自定义的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/admin/**")       //admin/**路径下的请求全部要通过LoginInterceptor过滤
                .excludePathPatterns("/admin")      //admin和admin/login不用过滤
                .excludePathPatterns("/admin/login");
    }
}
