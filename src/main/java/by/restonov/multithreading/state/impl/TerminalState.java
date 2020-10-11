package by.restonov.multithreading.state.impl;

import by.restonov.multithreading.entity.OperationReporter;
import by.restonov.multithreading.state.BaseState;

public enum TerminalState implements BaseState {

    BUSY{
        @Override
        public void sendReport() {
            reporter.countBusy();
        }
    },

    FREE {
        @Override
        public void sendReport() {
            reporter.countFree();
        }
    };

    OperationReporter reporter = OperationReporter.getInstance();
}
