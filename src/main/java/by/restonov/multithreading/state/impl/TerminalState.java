package by.restonov.multithreading.state.impl;

import by.restonov.multithreading.entity.OperationReporter;
import by.restonov.multithreading.state.BaseState;

public enum TerminalState implements BaseState {

    BUSY{
        @Override
        public void notifyReporter() {
            reporter.countBusy();
        }
    },

    FREE {
        @Override
        public void notifyReporter() {
            reporter.countFree();
        }
    };

    OperationReporter reporter = OperationReporter.getInstance();
}
