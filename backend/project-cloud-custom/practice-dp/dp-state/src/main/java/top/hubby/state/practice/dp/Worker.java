package top.hubby.state.practice.dp;

import lombok.Data;
import top.hubby.state.practice.State;

@Data
public class Worker {
    private String name;
    private State state;

    private IState iState;

    public void oneDay() {
        System.out.println("9: 起床");
        iState.handle();
        System.out.println("21: 下班");
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.setName("张三");
        worker.setIState(new RainState());
        worker.oneDay();
    }
}
