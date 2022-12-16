package top.hubby.state.machine.v1;

public enum Event {
    SUBMIT {
        @Override
        void logic() {}
    },
    PASS {
        @Override
        void logic() {}
    },
    REJECT {
        @Override
        void logic() {}
    },
    CANCEL {
        @Override
        void logic() {}
    },
    ;

    abstract void logic();
}
