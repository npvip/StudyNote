package cn.np.proxy.staticproxy;

/**
 * @author np
 * @date 2018/9/9
 *
 * 静态代理测试
 */
public class Client {

    public static void main(String[] args){
        //直接调用
        TrainStation trainStation=new TrainStation();
        trainStation.saleTicket();
        //静态代理调用
        TicketProxy ticketProxy=new TicketProxy(trainStation);
        ticketProxy.saleTicket();
    }
}
