package top.hubby.state.practice.mario.v3;

import lombok.NoArgsConstructor;
import top.hubby.state.practice.mario.IMario;
import top.hubby.state.practice.mario.State;

@NoArgsConstructor
public class SuperMario extends IMario {

    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom() {
        // convert state and do logic
        super.context.setCurrentState(MarioStateMachineContext.small);
        super.context.setScore(super.context.getScore() - 100);
    }

    @Override
    public void obtainCape() {
        // super.context.setCurrentState(MarioStateMachineContext.supper);
        super.context.setScore(super.context.getScore() + 200);
    }

    @Override
    public void obtainFireFlower() {
        // super.context.setCurrentState(MarioStateMachineContext.supper);
        super.context.setScore(super.context.getScore() + 300);
    }

    @Override
    public void meetMonster() {
        // do nothing...
    }
}
