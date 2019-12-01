package cn.np.designpattern.part01;

/**
 * 饿汉式单例模式
 * @date 2019/12/1
 */
public class HungrySingleton {

    /**
     * 类加载准备阶段会实例化对象
     */
    private static HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return instance;
    }

}
