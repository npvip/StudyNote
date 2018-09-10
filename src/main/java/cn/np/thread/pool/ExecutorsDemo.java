package cn.np.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author np
 * @date 2018/9/10
 *
 * Executors是Executor的工厂类
 * ThreadPoolExecutor是Executor的实现类
 * 由于在创建使用ThreadPoolExecutor创建线程池的实例时需要传入多个参数，
 * 为了方便，一般选用Executors工厂类进行创建，其底层就是实例化ThreadPoolExecutor
 *
 */
public class ExecutorsDemo {

    public static void main(String[] args){

    //    boundThreadPoolTest();
        singleThreadPoolTest();


    }

    /**
     * 无界线程池
     */
    public static void noBoundThreadPoolTest(){
        ExecutorService executorService=Executors.newCachedThreadPool();

        for (int i=0;i<5;i++){
            executorService.submit(new MyThreadA((""+i+1)));
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("------");

        for (int i=0;i<5;i++){
            executorService.submit(new MyThreadA((""+i+1)));
        }
    }

    /**
     * 有界线程池
     */
    public static void boundThreadPoolTest(){
        //入参设置1可以看成是单一线程池
        ExecutorService executorService=Executors.newFixedThreadPool(2);
        for (int i=0;i<5;i++){
            executorService.submit(new MyThreadA((""+i+1)));
        }

    }

    /**
     * 单一线程池
     */
    public static void singleThreadPoolTest(){
        ExecutorService executorService=Executors.newSingleThreadExecutor();
        for (int i=0;i<5;i++){
            executorService.submit(new MyThreadA((""+i+1)));
        }
    }

}
