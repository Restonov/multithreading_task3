package by.restonov.multithreading.state.impl;

import by.restonov.multithreading.state.BaseState;

public enum TerminalState implements BaseState {

    BUSY{
        @Override
        public void someAction() {

        }
    },

    FREE {
        @Override
        public void someAction() {

        }
    };
}
