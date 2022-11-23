package top.hubby.state;

/**
 * @author zack <br/>
 * @create 2022-11-23 23:07 <br/>
 * @project practice-optimize <br/>
 */
public class LogisticsStateImpl1  implements LogisticsState {
    @Override
    public void doAction(JdLogisticsContext context) {
        System.out.println("商家已经接单，正在处理中...");
    }
}
