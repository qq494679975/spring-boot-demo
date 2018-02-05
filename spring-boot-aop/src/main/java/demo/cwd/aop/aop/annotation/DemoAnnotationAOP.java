package demo.cwd.aop.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by chenweida on 2018/1/29.
 */
@Aspect
@Component
@Order(2)//切面顺序
public class DemoAnnotationAOP {
    //Controller层切点路径
    @Pointcut("execution(* demo.cwd.aop.controller..*.*(..))")
    public void controllerAspect() {
    }


    @Around("controllerAspect() && @annotation(demo.cwd.aop.aop.annotation.Demo)")
    public Object checkToken(ProceedingJoinPoint point) throws Throwable {
        Object o = null;
        try {
            System.out.println(" 注解  切面 前  ");
            o = point.proceed();
            System.out.println(" 注解  切面 后  ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

}

