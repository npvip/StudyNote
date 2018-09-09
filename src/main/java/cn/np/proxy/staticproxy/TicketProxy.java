package cn.np.proxy.staticproxy;

/**
 * @author np
 * @date 2018/9/9
 * 静态代理类
 */
public class TicketProxy implements TicketHandler{

    private TrainStation trainStation;

    public TicketProxy(){

    }

    public TicketProxy(TrainStation trainStation){
        this.trainStation=trainStation;
    }

    @Override
    public void saleTicket() {
        System.out.println("出票前-代理类");
        if(null!=trainStation){
            trainStation.saleTicket();
        }
        System.out.println("出票后-代理类");
    }
}
