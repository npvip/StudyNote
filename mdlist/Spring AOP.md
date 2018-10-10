# Spring AOP  

## 简介 

## 术语  
* 切面  
  横切关注点（跨越应用程序多个模块的功能）被模块化的特殊对象。  
* 通知  
  切面必须要完成的工作。  
* 目标  
  被通知的对象。  
* 代理  
  向目标对象应用通知之后创建的对象。  
* 连接点  
  程序执行的某个特定位置：如类某个方法调用前、调用后、方法抛出异常后等。  
* 切点  
  通过切点定位到特定的连接点。  

## 为什么要使用Spring AOP？  
使用面向切面的编程（AOP）主要可以解决以下问题：  
1. 代码混乱：随着业务的增多，非业务需求（日志、验证等）代码混在业务代码中，使得业务方法急剧膨胀。  
2. 代码分散：以日志需求为例，只是为了满足这一需求，就要在多个模块中多次重复的使用类似的日志代码，或日志需求发生变化，需要修改所有的模块。  
 

## 代理模式  

### 静态代理  

### 动态代理 
JDK动态代理模式只能代理接口不能代理类，若要代理类可以使用CGLIB。  
以下例子通过JDK动态代理实现AOP，在执行方法前后可以做一些操作：  
```java
public interface ArithmeticCalculator {

    int add(int x, int y);

    int sub(int x, int y);

    int multi(int x, int y);

    int div(int x, int y);
}
``` 
```java
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {

    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public int sub(int x, int y) {
        return x - y;
    }

    @Override
    public int multi(int x, int y) {
        return x * y;
    }

    @Override
    public int div(int x, int y) {
        if (y == 0) {
            return 0;
        }
        return x / y;
    }
}
```
```java
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
                // 执行方法前
                System.out.println("The method " + method.getName() + " args:" + Arrays.toString(args));
                // 执行方法
                Object result = method.invoke(target, args);
                return result;
            }
        };

        ArithmeticCalculator proxy  = (ArithmeticCalculator) Proxy.newProxyInstance(loader,interfaces,h);
        System.out.println(proxy.add(2, 4));
    }
}
```


## Spring AOP实现  

### AspectJ  
Java社区里最完整最流行的AOP框架。

# 参考  
http://www.gulixueyuan.com/course/46/task/1075/show
