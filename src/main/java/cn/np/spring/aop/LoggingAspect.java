package cn.np.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author np
 * @date 2018/10/10
 *
 * 日志切面
 *
 */
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(public int cn.np.spring.aop.ArithmeticCalculatorImpl.*(int, int))")
    public void beforeMethod(JoinPoint joinPoint) {
        // 目标方法
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("The method " + methodName +" args:" + Arrays.toString(args));
    }
}
