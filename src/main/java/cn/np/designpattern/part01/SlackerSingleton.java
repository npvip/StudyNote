package cn.np.designpattern.part01;

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
