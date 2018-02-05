package demo.cwd.aop.aop;

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
@Order(1) //数字越小优先级越高
public class DemoSimpleAOP {
    //Controller层切点路径  拦截包下面的全部方法
    @Pointcut("execution(* demo.cwd.aop.controller..*.*(..))")
    public void controllerAspect() {
    }


    @Around("controllerAspect() ")
    public Object checkToken(ProceedingJoinPoint point) throws Throwable {

        Object o = null;
        try {
            System.out.println(" 简单  切面 前  ");
            //入参
            for (Object arg : point.getArgs()) {
                System.out.println(arg);
            }
            o = point.proceed();
            //返回值
            System.out.println(" 简单  切面 后  :" + o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

}

