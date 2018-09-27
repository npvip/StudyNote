package cn.np.thread;


/**
 * @author np
 * @date 2018/8/31
 */
public class MyThread implements Runnable {


    private  float money=123.4f;



    @Override
    public  void run() {

        subMoney();


    }


    private synchronized float subMoney(){
        System.out.println(Thread.currentThread().getName()+"-before sub money="+money);
        this.money=money-10;
        System.out.println(Thread.currentThread().getName()+"-after sub money="+money);
        return money;
    }
}
