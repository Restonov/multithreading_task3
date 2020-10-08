package by.restonov.multithreading.state.impl;

import by.restonov.multithreading.entity.OperationReporter;
import by.restonov.multithreading.state.BaseState;

public enum TruckState implements BaseState {

    FULL {
        @Override
        public void notifyReporter() {
            reporter.countLoad();
        }
    },

    EMPTY {
        @Override
        public void notifyReporter() {
            reporter.countUnload();
        }
    };

    OperationReporter reporter = OperationReporter.getInstance();

    public static BaseState defineState(String state) {
        BaseState currentState = null;
        if (state.equals("full")) {
            currentState = TruckState.FULL;
        } else if (state.equals("empty")) {
            currentState = TruckState.EMPTY;
        }
        return currentState;
    }
}
