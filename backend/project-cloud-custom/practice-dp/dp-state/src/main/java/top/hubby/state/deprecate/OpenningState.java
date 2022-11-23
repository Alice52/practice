package top.hubby.state.deprecate;

/**
 * @author zack <br>
 * @create 2022-11-23 22:26 <br>
 * @project practice-optimize <br>
 */
public class OpenningState extends LiftState {
    // 开启当然可以关闭了，我就想测试一下电梯门开关功能
    @Override
    public void open() {
        System.out.println("电梯门开启...");
    }

    @Override
    public void close() {
        // 状态修改
        super.context.setLiftState(Context.closeingState);
        // 动作委托为CloseState来执行，也就是委托给了ClosingState子类执行这个动作
        super.context.getLiftState().close();
    }
}
