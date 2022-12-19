package top.hubby.state.practice.mario.v2;

import lombok.Getter;
import top.hubby.state.practice.mario.IMario;
import top.hubby.state.practice.mario.State;

import static top.hubby.state.practice.mario.State.*;

@Getter
public class MarioStateMachine implements IMario {
    private static final State[][] transitionTable = {
        {SUPER, CAPE, FIRE, SMALL},
        {SUPER, CAPE, FIRE, SMALL},
        {CAPE, CAPE, CAPE, SMALL},
        {FIRE, FIRE, FIRE, SMALL}
    };
    private static final int[][] actionTable = {
        {+100, +200, +300, +0},
        {+0, +200, +300, -100},
        {+0, +0, +0, -200},
        {+0, +0, +0, -300}
    };
    private int score;
    private State currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    @Override
    public void obtainMushRoom() {
        executeEvent(Event.GOT_MUSHROOM);
    }

    @Override
    public void obtainCape() {
        executeEvent(Event.GOT_CAPE);
    }

    @Override
    public void obtainFireFlower() {
        executeEvent(Event.GOT_FIRE);
    }

    @Override
    public void meetMonster() {
        executeEvent(Event.MET_MONSTER);
    }

    private void executeEvent(Event event) {
        int stateValue = currentState.getValue();
        int eventValue = event.getValue();
        this.currentState = transitionTable[stateValue][eventValue];
        this.score = actionTable[stateValue][eventValue];
    }
}
