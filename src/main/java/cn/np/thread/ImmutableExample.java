package cn.np.thread;

/**
 * @author np
 * @date 2019/11/24
 */
public class ImmutableExample {


    public static void main(String[] args) {
        String flag = "yes";
        DemoThread demoThread1 = new DemoThread(flag);
        DemoThread demoThread2 = new DemoThread(flag);

        Thread thread1 = new Thread(demoThread1);
        Thread thread2 = new Thread(demoThread2);

        thread1.start();
        thread2.start();

    }
}

class DemoThread implements Runnable {

    private String flag;

    public DemoThread (String flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag.equals("yes")) {
            System.out.println("yes! do something....");
            flag = "no";
        } else {
            System.out.println("no! do nothing....");
        }
    }
}

class Data {
    private String flag;

    private void doSomething() {

    }

}
