package com.simplem.personal_blog.aspect;

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
//定义切面，收集信息进行日志处理
public class LogAspect {

/*    execution ( [modifiers-pattern] 访问权限类型
            ret-type-pattern 返回值类型
            [declaring-type-pattern] 全限定性类名
            name-pattern(param-pattern)方法名(参数名)
           [throws-pattern] 抛出异常类型
      )[]中的内容可以省略，各部分中间用空格隔开*/
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //对Controller层定义切面
    @Pointcut("execution(* com.simplem.personal_blog.controller..*.*(..))")//用pointcut统一管理切面点
    private void log(){ }//别名

    @Before("log()") //前置通知，在目标方法执行前通知
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();    //获取请求的url
        String ip = request.getRemoteAddr();    //获取请求的ip
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
        logger.info("Result 返回结果: " + result);
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
                    "url地址：'" + url + '\'' +
                    ", ip地址：'" + ip + '\'' +
                    ", classMethod方法名：'" + classMethod + '\'' +
                    ", args参数：" + Arrays.toString(args) +
                    '}';
        }

    }
}
