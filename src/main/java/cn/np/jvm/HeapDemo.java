package cn.np.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author np
 * @date 2018/9/10
 */
public class HeapDemo {

    public static void main(String[] agrs){
        long maxMemory=Runtime.getRuntime().maxMemory();
        long totalMemory=Runtime.getRuntime().totalMemory();

        System.out.println("maxMemory="+maxMemory+"字节,"+(maxMemory/(double)1024/1024)+"MB");
        System.out.println("totalMemory="+totalMemory+"字节,"+(totalMemory/(double)1024/1024)+"MB");

        HeapDemo heapDemo = new HeapDemo();
        heapDemo.testHeapOutMemory();

    }

    /**
     * 测试堆内存溢出
     * JVM参数
     * -Xms16m -Xmx16m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
     *
     */
    public void testHeapOutMemory() {
        List<String> lists = new ArrayList<>();
        int i = 0;
        while (true) {
            lists.add(i + "");
            i++;
        }
    }
}
