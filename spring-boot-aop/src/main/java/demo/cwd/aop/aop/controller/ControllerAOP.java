package demo.cwd.aop.aop.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by chenweida on 2018/1/29.
 */
@Component// 首先初始化切面类
@Aspect// 声明为切面类，底层使用动态代理实现AOP
@Order(3)
public class ControllerAOP {

    /**
     * 只切有 @RestController的类
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void cutController() {

    }

    @Around("cutController()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {
        Object o = null;
        try {
            System.out.println(" 只切RestController  切面 前  ");
            o = point.proceed();
            System.out.println(" 只切RestController  切面 后  ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}
