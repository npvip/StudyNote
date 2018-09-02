package cn.np.thread;

import java.util.List;

/**
 * @author np
 * @date 2018/8/31
 */
public class Mythread extends Thread {


    private List<String> names;

    public Mythread(List<String> nameList){
        names=nameList;
    }


    @Override
    public void run() {


        testMutil();


        /*
        for(int i=0;i<100;i++){
            try {
                Thread.sleep(3000);
                System.out.println("sleep 3s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"-"+i);
        }
        */
    }

    public  void testMutil(){
        if(null!=names && names.size()>0){
            System.out.println(Thread.currentThread().getName()+"names hashcode:"+names.hashCode());

            for(String name:names){
                System.out.println(Thread.currentThread().getName()+"-name:"+name);
            }
        }
    }
}
