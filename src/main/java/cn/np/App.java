package cn.np;

import cn.np.thread.Mythread;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App app=new App();
        app.shareVariableTest();

    }

//    public int runThreadTest(){
//        Mythread mythread=new Mythread();
//        mythread.start();
//
//        return 10;
//    }


    public void shareVariableTest(){
        List<String> nameList1=new ArrayList<>();
        for(int i=0;i<10000;i++){
            nameList1.add("firstname-"+i);
        }

        Mythread mythread1=new Mythread(nameList1);
        mythread1.start();


        List<String> nameList2=new ArrayList<>();
        for(int i=0;i<100;i++){
            nameList2.add("secondname-"+i);
        }
        Mythread mythread2=new Mythread(nameList2);
        mythread2.start();

    }
}
