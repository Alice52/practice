package top.hubby.state.practice.mario.v0;

import lombok.extern.slf4j.Slf4j;
import top.hubby.state.practice.mario.State;

@Slf4j
public class TestClient {
    public static void main(String[] args) {
        MarioStateMachine mario = new MarioStateMachine();
        mario.obtainMushRoom();
        int score = mario.getScore();
        State state = mario.getCurrentState();
        log.info("mario score: " + score + "; state: " + state);
    }
}
