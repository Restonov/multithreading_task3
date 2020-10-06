package by.restonov.multithreading.state.impl;

import by.restonov.multithreading.state.BaseState;

public enum TruckState implements BaseState {

    FULL {
        @Override
        public void someAction() {

        }
    },

    EMPTY {
        @Override
        public void someAction() {

        }
    };
}
