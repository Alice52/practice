package top.hubby.state.transfer.incontext;

/**
 * @author zack <br>
 * @create 2022-11-23 23:07 <br>
 * @project practice-optimize <br>
 */
public class LogisticsStateImpl2 implements LogisticsState {
    @Override
    public void doAction(JdLogisticsContext context) {
        System.out.println("商品已经出库...");
    }
}
