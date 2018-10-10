package cn.np.aop;

import cn.np.spring.aop.ArithmeticCalculator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author np
 * @date 2018/8/30
 */
public class SpringAopTest {
    ClassPathXmlApplicationContext context=null;

    @Before
    public void before(){
        System.out.println("Spring ApplicationContext容器开始初始化了......");
        context=new ClassPathXmlApplicationContext("classpath:spring-aoptest.xml");
        System.out.println("Spring ApplicationContext容器初始化完毕了......");
    }

    @Test
    public void testAopBefore(){
        if (context != null) {
            ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator)context.getBean("arithmeticCalculatorImpl");
            int result1 = arithmeticCalculator.add(3, 6);
            System.out.println("result=" + result1);

            int result2 = arithmeticCalculator.multi(3, 6);
            System.out.println("result=" + result2);
        }
    }

}
