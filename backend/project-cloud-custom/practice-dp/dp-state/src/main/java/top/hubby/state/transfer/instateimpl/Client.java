package top.hubby.state.transfer.instateimpl;

/**
 * @author zack <br>
 * @create 2022-11-23 22:34 <br>
 * @project practice-optimize <br>
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setLiftState(new ClosingState());
        context.open();
        context.close();
        context.run();
        context.stop();
    }
}
