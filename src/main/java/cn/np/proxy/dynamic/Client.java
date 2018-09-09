package cn.np.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author np
 * @date 2018/9/9
 */
public class Client {

    public static void main(String[] agrs){
        TrainStation trainStation=new TrainStation();
        Class<?> clz=trainStation.getClass();
        Class<?>[] interfaces=clz.getInterfaces();

        InvocationHandler handler=new TicketDynamicProxy(trainStation);

        TicketHandler ticketHandler=(TicketHandler) Proxy.newProxyInstance(clz.getClassLoader(),interfaces,handler);
        ticketHandler.saleTicket();

        LogHandler logHandler=(LogHandler) Proxy.newProxyInstance(clz.getClassLoader(),interfaces,handler);
        logHandler.writeLog();

    }
}
