package cn.np;

import cn.np.spring.BeanLifeCycle;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author np
 * @date 2018/8/30
 */
public class SpringTest {
    ClassPathXmlApplicationContext context=null;

    @Before
    public void before(){
        System.out.println("Spring ApplicationContext容器开始初始化了......");
        context=new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        System.out.println("Spring ApplicationContext容器初始化完毕了......");
    }

    @Test
    public void beanLifeCycleTest(){

        BeanLifeCycle beanLifeCycle =context.getBean("beanLifeCycle",BeanLifeCycle.class);
        context.registerShutdownHook();
    }
}
