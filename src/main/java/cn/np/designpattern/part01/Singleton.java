package cn.np.designpattern.part01;

/**
 * @author np
 * @date 2018/10/13
 *
 * 单例模式实现
 * 1. 懒汉式
 * 2. 饿汉式
 *
 */
public class Singleton {
    private static Singleton singleton;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
