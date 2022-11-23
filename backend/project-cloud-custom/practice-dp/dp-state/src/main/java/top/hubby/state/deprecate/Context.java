package top.hubby.state.deprecate;

/**
 * @author zack <br>
 * @create 2022-11-23 22:26 <br>
 * @project practice-optimize <br>
 */
public class Context {
    // 定义出所有的电梯状态
    public static final OpenningState openningState = new OpenningState(); // 开门状态，这时候电梯只能关闭
    public static final ClosingState closeingState = new ClosingState(); // 关闭状态，这时候电梯可以运行、停止和开门
    public static final RunningState runningState = new RunningState(); //    运行状态，这时候电梯只能停止
    public static final StoppingState stoppingState = new StoppingState(); // 停止状态，这时候电梯可以开门、运行
    // 定义一个当前电梯状态
    private LiftState liftState;

    public LiftState getLiftState() {
        return this.liftState;
    }

    public void setLiftState(LiftState liftState) {
        // 当前环境改变
        this.liftState = liftState;
        // 把当前的环境通知到各个实现类中
        this.liftState.setContext(this);
    }

    public void open() {
        this.liftState.open();
    }

    public void close() {
        this.liftState.close();
    }

    public void run() {
        this.liftState.run();
    }

    public void stop() {
        this.liftState.stop();
    }
}
