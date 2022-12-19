package top.hubby.state.practice.mario.v3;

import lombok.Data;
import top.hubby.state.practice.mario.IMario;

@Data
public class MarioStateMachineContext {
    public static final IMario small = new SmallMario();
    public static final IMario supper = new SuperMario();
    private int score;
    private IMario currentState;

    public MarioStateMachineContext() {
        this.score = 0;
        this.currentState = small;
    }

    public void obtainMushRoom() {
        this.currentState.obtainMushRoom();
    }

    public void obtainCape() {
        this.currentState.obtainCape();
    }

    public void obtainFireFlower() {
        this.currentState.obtainFireFlower();
    }

    public void meetMonster() {
        this.currentState.meetMonster();
    }
}
