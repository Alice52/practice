package top.hubby.state.machine.v2;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public abstract class AbstractStateMachine {

    // 如果此处自己封装<k, k, v, v> 的map 就不需要 Exec 对象
    private HashBasedTable<State, Event, Exec> map = HashBasedTable.create();

    {
        List<MachinePoint> points = init();

        points.forEach(
                x -> {
                    Exec exec = new Exec();
                    exec.setNextState(x.getTo());
                    exec.setHandle(x.getHandle());
                    map.put(x.getFrom(), x.getEvent(), exec);
                });
    }

    abstract List<MachinePoint> init();

    public State getNext(State state, Event event) {
        return map.get(state, event).getNextState();
    }

    public IStateHandle getHandle(State state, Event event) {
        return map.get(state, event).getHandle();
    }

    @Data
    static class Exec {
        private State nextState;
        private IStateHandle handle;
    }
}

class StateMachine extends AbstractStateMachine {

    @Override
    List<MachinePoint> init() {

        MachinePoint build =
                MachinePoint.builder()
                        .from(State.UN_SUBMIT)
                        .event(Event.SUBMIT)
                        .to(State.LEDER_CHCK)
                        .build();

        MachinePoint build2 =
                MachinePoint.builder()
                        .from(State.LEDER_CHCK)
                        .event(Event.PASS)
                        .handle(new LeaderPassHandle())
                        .to(State.HR_CHECK)
                        .build();
        return Lists.newArrayList(build, build2);
    }
}
