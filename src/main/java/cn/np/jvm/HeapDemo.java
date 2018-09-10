package cn.np.jvm;

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
    }
}
