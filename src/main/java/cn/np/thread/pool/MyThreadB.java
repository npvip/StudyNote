package cn.np.thread.pool;

/**
 * @author np
 * @date 2018/9/10
 */
public class MyThreadB implements Runnable {
    private String name;

    public MyThreadB() {
    }

    public MyThreadB(String name) {
        super();
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("["+Thread.currentThread().getName()+"]-begin-"+System.currentTimeMillis()+"【"+name+"】");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("["+Thread.currentThread().getName()+"]-end-"+System.currentTimeMillis()+"【"+name+"】");
    }
}
