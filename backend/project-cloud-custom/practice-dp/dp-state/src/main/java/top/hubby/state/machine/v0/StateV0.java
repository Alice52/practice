package top.hubby.state.machine.v0;

@Deprecated
public enum StateV0 {
    FINISH {
        @Override
        StateV0 next() {
            return this;
        }
    },
    UN_SUBMIT {
        @Override
        StateV0 next() {
            return LEADER_CHECK;
        }
    },
    LEADER_CHECK {
        @Override
        StateV0 next() {
            return HR_CHECK;
        }
    },
    HR_CHECK {
        @Override
        StateV0 next() {
            return FINISH;
        }
    },
    ;

    abstract StateV0 next();
}
