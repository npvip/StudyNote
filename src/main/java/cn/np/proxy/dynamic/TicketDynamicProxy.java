package cn.np.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author np
 * @date 2018/9/9
 * 动态代理类
 */
public class TicketDynamicProxy implements InvocationHandler {

    private Object target;

    public TicketDynamicProxy(Object object){
        target=object;
    }

    /**
     * @param proxy 被代理对象
     * @param method 被代理对象方法
     * @param args 被代理对象方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理-执行目标方法前");
        Object result=method.invoke(target);
        System.out.println("动态代理-执行目标方法后");
        return result;
    }
}
