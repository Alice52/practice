package top.hubby.state.transfer.instateimpl;

/**
 * @author zack <br>
 * @create 2022-11-23 22:25 <br>
 * @project practice-optimize <br>
 */
public abstract class LiftState {
    // 定义一个环境角色，也就是封装状态的变化引起的功能变化
    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    // 电梯开门动作
    public void open() {}
    // 电梯关门动作
    public void close() {}
    // 电梯运行动作
    public void run() {}
    // 电梯停止动作
    public void stop() {}
}
