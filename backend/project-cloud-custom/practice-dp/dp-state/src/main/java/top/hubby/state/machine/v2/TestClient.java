package top.hubby.state.machine.v2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestClient {

    public static void main(String[] args) {
        NewStateMachine machine = new NewStateMachine();

        State next = machine.getNext(State.LEDER_CHCK, Event.PASS);
        log.info("State.LEDER_CHCKED and Event.PASS next is: {}", next);
        IStateHandle handle = machine.getHandle(State.LEDER_CHCK, Event.PASS);
        handle.handle();
    }
}
