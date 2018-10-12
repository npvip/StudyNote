# Spring AOP  

## AOP简介  
AOP是面向切面编程，是一种编程思想（OOP是面向对象编程思想）。利用一种称为"横切"的技术，剖解开封装的对象内部，并将那些影响了多个类的公共行为封装到一个可
重用模块（切面），可以应用于日志功能、验证等。

## 术语  
* 切面  
  横切关注点（跨越应用程序多个模块的功能）被模块化的特殊对象。  
* 通知  
  切面必须要完成的工作，拦截连接点后要执行的代码，分为前置、后置、异常、最终、环绕通知5类。  
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
静态代理实现模式是代理类和被代理类实现同一个接口，且被代理类是代理类的成员属性，以下为实现例子：  
```java
public interface TicketHandler {
    void saleTicket();
}
``` 
```java
public class TrainStation implements TicketHandler{

    @Override
    public void saleTicket() {
        System.out.println("销售火车票-出票来自火车站");
    }
}
```

```java
public class TicketProxy implements TicketHandler{

    private TrainStation trainStation;

    public TicketProxy(){

    }

    public TicketProxy(TrainStation trainStation){
        this.trainStation=trainStation;
    }

    @Override
    public void saleTicket() {
        System.out.println("出票前-代理类");
        if(null!=trainStation){
            trainStation.saleTicket();
        }
        System.out.println("出票后-代理类");
    }
}
```

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
实现步骤：  
1. 引入jar包；  
```
    <!-- spring相关的略 -->
    <!-- AspectJ -->
    <dependency>
      <groupId>aopalliance</groupId>
      <artifactId>aopalliance</artifactId>
      <version>1.0</version>
    </dependency>

    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.8.9</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-expression</artifactId>
      <version>4.3.13.RELEASE</version>
    </dependency>
```
  
2. 编写切面程序，使用注解`@Aspect`在切面类上，在通知方法上`@Before`使用； 
* `@Before`：前置通知  
* `@AfterReturning`：后置通知  
* `@AfterThrowing` ：异常通知  
* `@After`：最终通知  
* `@Around`：环绕通知  
```java
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
```
  
业务代码ArithmeticCalculatorImpl：[ArithmeticCalculatorImpl](https://github.com/npvip/StudyNote/blob/master/src/main/java/cn/np/spring/aop/ArithmeticCalculatorImpl.java)

3. spring配置文件。  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <context:component-scan base-package="cn.np.spring.aop"></context:component-scan>

    <!-- 使AspectJ注解起作用 -->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
</beans>
```  


# 参考  
http://www.gulixueyuan.com/course/46/task/1075/show
