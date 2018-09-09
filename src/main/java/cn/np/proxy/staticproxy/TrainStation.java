package cn.np.proxy.staticproxy;

/**
 * @author np
 * @date 2018/9/9
 */
public class TrainStation implements TicketHandler{

    @Override
    public void saleTicket() {
        System.out.println("销售火车票-出票来自火车站");
    }
}
