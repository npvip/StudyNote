package cn.np.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PreDestroy;

/**
 * @author np
 * @date 2018/8/30
 * Spring Bean生命周期
 *
 */
public class BeanLifeCycle implements BeanNameAware,BeanFactoryAware,
        InitializingBean,DisposableBean,ApplicationContextAware {

    private String name;
    private int age;

    public BeanLifeCycle(){
        System.out.println("【BeanLifeCycle】调用构造函数");
    }

    public void setName(String name) {
        System.out.println("【BeanLifeCycle】调用setter，为name设置："+name);
        this.name = name;
    }


    public void setAge(int age) {
        System.out.println("【BeanLifeCycle】调用setter，为age设置："+age);
        this.age = age;
    }

    public void init (){
        System.out.println("【BeanLifeCycle】init-method方法init被调用");
    }
    public void close(){
        System.out.println("【BeanLifeCycle】destroy-method方法close被调用");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("【BeanLifeCycle】调用BeanNameAware接口setBenaName方法: "+s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("【BeanLifeCycle】调用BeanFactoryAware接口setBeanFactory方法:"+beanFactory);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【BeanLifeCycle】调用InitializingBean接口afterPropertiesSet方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("【BeanLifeCycle】调用DisposableBean接口destroy方法");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("【BeanLifeCycle】注解销毁方法preDestroy被调用");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("【BeanLifeCycle】调用ApplicationContextAware接口setApplicationContext方法");
    }
}
