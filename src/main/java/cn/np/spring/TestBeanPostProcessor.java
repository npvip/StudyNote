package cn.np.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author np
 * @date 2018/9/1
 */
public class TestBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("【后置处理器-TestBeanPostProcessor】执行方法<postProcessBeforeInitialization>:beanName="+s);
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("【后置处理器-TestBeanPostProcessor】执行方法<postProcessAfterInitialization>:beanName="+s);
        return o;
    }
}
