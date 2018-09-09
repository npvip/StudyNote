package cn.np.proxy.dynamic;


import java.util.Date;

/**
 * @author np
 * @date 2018/9/9
 */
public class TrainStation implements TicketHandler,LogHandler{

    @Override
    public void saleTicket() {
        System.out.println("销售火车票-出票来自火车站");
    }

    @Override
    public void writeLog() {
        System.out.println("记录日志，当前时间是:"+new Date().getTime());
    }
}
