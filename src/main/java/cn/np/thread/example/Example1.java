package cn.np.thread.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author np
 * @date 2018/9/16
 *
 * 顺序执行三个线程输出 T1,T2,T3
 */
public class Example1 {

    public static void main(String[] args) {
        executorsTest();
    }

    /**
     * 第一种方式：使用join()完成
     */
    public static void joinTest(){
        final Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T1");
            }
        });

        final Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("T2");
            }
        });

        final Thread thread3=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T3");
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     * 第二种方式：使用单线程池
     */
    public static void executorsTest(){
        ExecutorService executorService=Executors.newSingleThreadExecutor();

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("T1");
            }
        });

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("T2");
            }
        });

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("T3");
            }
        });

        executorService.shutdown();
    }

}
