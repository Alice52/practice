package top.hubby.state.deprecate;

/**
 * @author zack <br>
 * @create 2022-11-23 22:27 <br>
 * @project practice-optimize <br>
 */
public class RunningState extends LiftState {

    // 这是在运行状态下要实现的方法
    @Override
    public void run() {
        System.out.println("电梯正在运行...");
    }

    // 这个事绝对是合理的，光运行不停止还有谁敢做这个电梯？！估计只有上帝了
    @Override
    public void stop() {
        super.context.setLiftState(Context.stoppingState);
        super.context.stop();
    }
}
