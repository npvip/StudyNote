# spring接口BeanPostProcessor
## 定义
接口中定义了两个方法：  
```
    // 在Bean执行初始化方法（init-method）之前 执行此方法
    Object postProcessBeforeInitialization(Object var1, String var2) throws BeansException;

    // 在Bean执行初始化方法（init-method）之后 执行此方法
    Object postProcessAfterInitialization(Object var1, String var2) throws BeansException;
```

## 作用
BeanPostProcessor允许在bean初始化回调方法之前和之后进行额外的处理(包括如何构造、属性修改、构造器选择等)，主要特性是它将逐个处理IOC容器中的所有实例，而不是仅仅单个bean实例。  

## 使用BeanPostProcessor
### 创建一个自定义BeanPostProcessor
```
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CustomBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Called postProcessBeforeInitialization() for :" + beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Called postProcessAfterInitialization() for :" + beanName);
        return bean;
    }
}
```

### 调用时机
spring容器在创建一个Bean会依次做如下：  
1. 通过构造函数或工厂方法创建bean实例
2. 设置bean属性的值和bean引用
3. 调用在所有可感知接口中定义的setter方法
4. 将bean实例传递给每个bean后置处理程序的方法postProcessBeforeInitialization()
5. 调用初始化的回调方法
6. 将bean实例传递给每个bean后置处理程序的方法postProcessAfterInitialization()
7. 可以使用bean
8. 当关闭容器时,调用销毁回调方法  

### 注册BeanPostProcessor
声明一个处理器在bean的实例配置文件或使用`BeanDefinition`等,就像注册其他任何bean一样.  

## 实例
```java
public class CustomServiceImpl implements CustomService {

    private String name;
    private String code;

    public CustomServiceImpl() {
        System.out.println("CustomService Construct......");
    }

    public void init() {
        System.out.println("CustomService init");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
```
`spring-beans.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.mycompany.com/schema/myns http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <bean class="com.np.samples.spring.CustomBeanPostProcessor" id="customBeanPostProcessor"></bean>

    <bean class="com.np.samples.spring.CustomServiceImpl" id="customService" init-method="init"></bean>

</beans>
```
`启动`:  
```
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-beans.xml");
```

`输出`:  
```
CustomService Construct......
Called postProcessBeforeInitialization() for :customService
CustomService init
Called postProcessAfterInitialization() for :customService
```

## 参考
https://howtodoinjava.com/spring-core/how-to-create-spring-bean-post-processors/
