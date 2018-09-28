# java注解学习

## 语法
1.  定义  
注解通过关键词*@interface*进行定义  
```java
public @interface MyAnnotation {
    
}
```

2. 元注解  
>元注解是可以注解到注解上的注解, 是一种基本的注解.  
元标签包含: @Retention, @Documented, @Target, @Inherited, @Repeatable  

* @Retention  
保留期，说明注解的存活时间。取值包含以下  
- RetentionPolicy.SOURCE: 注解只在源码阶段保留，在编译器进行编译时会被忽略  
- RetentionPolicy.CLASS: 注解被保留到编译进行时，不会被加载到JVM中  
- RetentionPolicy.RUNTIME: 注解会被保留到程序运行时  

* @Documented  
将注解的元素包含到javadoc中  

* @Target  
指定注解运用的地方  
- ElementType.FIELD: 可以给属性进行注解  
- ElementType.METHOD: 可以给方法进行注解  
- ElementType.TYPE: 可以给一个类型进行注解，比如类、接口、枚举  
- ElementType.PACKAGE: 可以给一个包进行注解  
- ElementType.PARAMETER: 可以给一个方法内的参数进行注解  
- ElementType.LOCAL_VARIABLE: 可以给局部变量进行注解  
- ElementType.ANNOTATION_TYPE: 可以给一个注解进行注解  
- ElementType.CONSTRUCTOR: 可以给构造方法进行注解 

* @Inherited  
继承，父类被@Inherited注解过的注解进行注解，如果子类没有被任何注解应用的话，子类将会继承父类的注解。  

* @Repeatable  
是java8的一个新特性，可以被重复注解。  

3. java内置的注解  
* @Deprecated: 过时的方法、类、成员变量，编译器会警告提醒。  
* @Override: 提示子类重写父类的方法。
* @SuppressWarnings: 阻止警告的意思。  
* @FunctionalInterface: 函数式接口注解，java8引进的新特性。  
* @SafeVarargs: 参数安全类型检查。      
    
4. 注解的属性  
注解只有成员变量，没有方法。注解的成员变量即为注解的属性。下面代码定义了注解MyAnnotation用于id和msg属性，可以使用default设置属性默认值：  
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RunTIME)
public @interface MyAnnotation {
    int id() default 0;
    String msg() default "Hello";
}
```  
5. 注解的提取  
[使用反射取提取](https://github.com/npvip/StudyNote/blob/master/src/main/java/cn/np/annotation/TestAnnotation.java)
 




参考：  
* https://blog.csdn.net/briblue/article/details/73824058