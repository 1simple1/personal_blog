package com.simplem.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * classMethod: LogAspect
 * Package: com.simplem.blog.aspect
 * Description：
 * Author: simpleM
 * Date: 2020/10/08 14:42
 */
@Aspect
@Component
//定义切面，进行日志信息处理
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //对Controller层定义切面
    @Pointcut("execution(* com.simplem.personal_blog.controller.*.*(..))")
    public void log(){ }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //获取调用的类名和方法名
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." +joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        logger.info("RequestLog : "+requestLog);
    }

//    @After("log()")
//    public void doAfter(){
//        logger.info("--------after---------");
//    }

    //在控制台输出日志信息
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result : " + result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
