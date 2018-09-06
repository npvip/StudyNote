package cn.np;

import cn.np.thread.MyThread;
import org.junit.Test;

/**
 * @author np
 * @date 2018/9/2
 */
public class ThreadTest {

    @Test
    public void testMyThread(){
        MyThread myThread=new MyThread();

        Thread myThread1=new Thread(myThread);

        Thread myThread2=new Thread(myThread);
        myThread1.start();
        myThread2.start();
    }
}
