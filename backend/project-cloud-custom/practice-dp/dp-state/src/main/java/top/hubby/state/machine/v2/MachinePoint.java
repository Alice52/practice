package top.hubby.state.machine.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

enum State {
    REJECT,
    FINISH,
    UN_SUBMIT,
    LEDER_CHCK,
    HR_CHECK,
    ;
}

enum Event {
    SUBMIT,
    PASS,
    REJECT,
    CANCEL;
}

interface IStateHandle {
    void handle();
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachinePoint {
    private State from;
    private State to;
    private Event event;
    private IStateHandle handle;
}

@Slf4j
class LeaderPassHandle implements IStateHandle {

    @Override
    public void handle() {
        log.info("leader pass handle logic");
    }
}
