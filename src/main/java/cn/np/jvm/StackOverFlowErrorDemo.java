package cn.np.jvm;

/**
 * @author np
 * @date 2018/9/10
 *
 * 实验StackOverFlowError
 */
public class StackOverFlowErrorDemo {

    public void func(){
        func();
    }

    public static void main(String[] agrs){
        StackOverFlowErrorDemo stackOverFlowErrorDemo=new StackOverFlowErrorDemo();
        stackOverFlowErrorDemo.func();
    }
}
