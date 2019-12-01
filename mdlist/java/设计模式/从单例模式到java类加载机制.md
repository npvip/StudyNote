

## 单例模式
在《设计模式之禅》一书中给出的定义是：确保某一个类只有一个实例，而且自行实例化并向整个系统提供整个实例。单例模式是一种很常见的设计模式，在JDK源码以及
各流行的java框架中被大量使用，如JDK中Runtime类、Mybatis中的VFS类、log4j中的MDC类等。单例模式常见的两种实现方法即为大家所熟知的懒汉式和饿汉式。

## 单例模式实现

### 饿汉式
饿汉式为类加载时或类初始化时就创建实例对象（static final 与 static），即在调用getInstance(假设获取为获取单例实例的方法)方法时，类实例已经存在，
不用再使用new进行创建。

```java
/**
 * 饿汉式单例模式
 * @date 2019/12/1
 */
public class HungrySingleton {

    /**
     * 类加载初始化阶段会实例化对象
     */
    private static HungrySingleton instance = new HungrySingleton();
    
     /**
         * 类加载准备阶段会实例化对象
         */
      //  private static final HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return instance;
    }

}
```
jdk中的Runtime类以及log4j sdk中的MDC等均采用饿汉式实现单例模式。使用饿汉式实现单例模式的优点是，不存在线程安全问题；存在的缺点是在类加载时会使用
更多的资源去初始化实例，在很多情况下是不需过多考虑这个问题的。

### 懒汉式
懒汉式与饿汉式的区别：实例化对象的时机不同，懒汉式是在第一次需要实例时进行实例化。  
下面给出懒汉式实现单例模式且线程安全的例子：  

```java
/**
 * 懒汉式 单例模式
 * @date 2019/12/1
 */
public class SlackerSingleton {

    private static volatile SlackerSingleton instance;

    public static SlackerSingleton getInstance () {
        if (instance == null) { // 1
            synchronized (SlackerSingleton.class) { // 2
                if (instance == null) {  // 3
                    instance = new SlackerSingleton(); // 4
                }
            }
        }

        return instance; // 5
    }
}
```

实现步骤说明：  
* 注释1的`if`判断主要作用是提升性能，在`instance`被实例化后，后面通过`getInstance()`获取时就可以直接返回
* 注释2是在`instance`为`null`的情况下在可能多线程的情况下只有一个实例的同步控制
* 注释3此步的作用是当前一个线程执行完注释4之后，释放锁后在执行注释5返回前，另一个线程恰好执行到注释2处获得锁，如果没有注释3的判断直接使用new实例化
对象就会出现重复实例化的问题，不能保证单例。
* `volatile`在此处的作用是防止指令重排，在注释4处new实例化对象不是一个原子操作，具体原因了解类加载机制

此实现方式对比直接在getInstance方法上加synchronized性能会高（意味着每次调用getInstance都是需要同步执行）。
而在实际的应用中使用此方法的情况较少。

### 内部静态类实现