package cn.np.proxy.dynamic;

import cn.np.spring.aop.ArithmeticCalculator;
import cn.np.spring.aop.ArithmeticCalculatorImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author np
 * @date 2018/10/10
 */
public class LoggingAspect {
    public static void main(String[] args) {

        ArithmeticCalculator target = new ArithmeticCalculatorImpl();
        // 类加载器
        ClassLoader loader = target.getClass().getClassLoader();
        // 要加载的接口
        Class[] interfaces = new Class[]{ArithmeticCalculator.class};
        // 执行方法
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("The method " + method.getName() + " args:" + Arrays.toString(args));
                Object result = method.invoke(target, args);
                return result;
            }
        };

        ArithmeticCalculator proxy  = (ArithmeticCalculator) Proxy.newProxyInstance(loader,interfaces,h);
        System.out.println(proxy.add(2, 4));
    }
}
