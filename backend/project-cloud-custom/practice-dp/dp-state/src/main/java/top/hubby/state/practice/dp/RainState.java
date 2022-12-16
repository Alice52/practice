package top.hubby.state.practice.dp;

public class RainState implements IState{

    @Override
    public void handle() {
        System.out.println("今天下雨， 坐公交车上班吧");
    }
}
