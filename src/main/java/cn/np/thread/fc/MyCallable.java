package cn.np.thread.fc;

import java.util.concurrent.Callable;

/**
 * @author np
 * @date 2018/9/10
 */
public class MyCallable implements Callable<String> {
    private int age;

    public MyCallable() {
    }

    public MyCallable(int age) {
        super();
        this.age = age;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return "返回值 age=" +age;
    }
}
