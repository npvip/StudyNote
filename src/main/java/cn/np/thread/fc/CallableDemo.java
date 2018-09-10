package cn.np.thread.fc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author np
 * @date 2018/9/10
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        futureGetTest();
    }

    /**
     * future.get()方法有阻塞性
     */
    public static void futureGetTest() throws ExecutionException, InterruptedException {
        MyCallable callable=new MyCallable(18);
        ExecutorService executorService=Executors.newCachedThreadPool();
        Future<String> future=executorService.submit(callable);
        System.out.println("main A "+System.currentTimeMillis());
        System.out.println(future.get());
        System.out.println("main B "+System.currentTimeMillis());
    }


}
