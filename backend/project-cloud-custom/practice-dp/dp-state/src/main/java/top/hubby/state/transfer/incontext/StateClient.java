package top.hubby.state.transfer.incontext;

/**
 * @author zack <br>
 * @create 2022-11-23 23:08 <br>
 * @project practice-optimize <br>
 */
public class StateClient {
    public static void main(String[] args) {

        // 状态的保持与切换者
        JdLogisticsContext context = new JdLogisticsContext();

        // 接单状态
        context.setLogisticsState(new LogisticsStateImpl1());
        context.doAction();

        // 出库状态
        context.setLogisticsState(new LogisticsStateImpl2());
        context.doAction();
    }
}
