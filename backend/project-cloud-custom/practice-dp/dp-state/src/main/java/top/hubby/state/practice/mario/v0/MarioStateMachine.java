package top.hubby.state.practice.mario.v0;

import lombok.Getter;
import top.hubby.state.practice.mario.IMario;
import top.hubby.state.practice.mario.State;

@Getter
public class MarioStateMachine implements IMario {
    private int score;
    private State currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    @Override
    public void obtainMushRoom() {
        // todo
    }

    @Override
    public void obtainCape() {
        // todo
    }

    @Override
    public void obtainFireFlower() {
        // todo
    }

    @Override
    public void meetMonster() {
        // todo
    }
}
