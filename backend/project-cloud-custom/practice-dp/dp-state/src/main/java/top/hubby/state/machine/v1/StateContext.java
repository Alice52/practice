package top.hubby.state.machine.v1;

import com.google.common.collect.HashBasedTable;

public class StateContext {
    private static HashBasedTable<State, Event, State> map = HashBasedTable.create();

    static {
        map.put(State.UN_SUBMITED, Event.SUBMIT, State.LEDER_CHCKED);
        map.put(State.LEDER_CHCKED, Event.PASS, State.HR_CHECKED);
        map.put(State.UN_SUBMITED, Event.REJECT, State.REJECTED);
        map.put(State.HR_CHECKED, Event.PASS, State.FINISHED);
    }

    public static State next(State state, Event event) {
        return map.get(state, event);
    }
}
