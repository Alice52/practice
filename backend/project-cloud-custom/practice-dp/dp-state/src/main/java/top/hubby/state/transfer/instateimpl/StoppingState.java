package top.hubby.state.transfer.instateimpl;

/**
 * @author zack <br>
 * @create 2022-11-23 22:27 <br>
 * @project practice-optimize <br>
 */
public class StoppingState extends LiftState {

    @Override
    public void open() {
        // 状态修改
        super.context.setLiftState(Context.openningState);
        // 动作委托为CloseState来执行，也就是委托给了ClosingState子类执行这个动作
        super.context.getLiftState().open();
    }

    @Override
    public void close() { // 虽然可以关门，但这个动作不归我执行
        // 状态修改
        super.context.setLiftState(Context.closeingState);
        // 动作委托为CloseState来执行，也就是委托给了ClosingState子类执行这个动作
        super.context.getLiftState().close();
    }

    // 停止状态再跑起来，正常的很
    @Override
    public void run() {
        // 状态修改
        super.context.setLiftState(Context.runningState);
        // 动作委托为CloseState来执行，也就是委托给了ClosingState子类执行这个动作
        super.context.getLiftState().run();
    }

    // 停止状态是怎么发生的呢？当然是停止方法执行了
    @Override
    public void stop() {
        System.out.println("电梯停止了...");
    }
}
