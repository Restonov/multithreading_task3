package by.restonov.multithreading.state.impl;

import by.restonov.multithreading.entity.OperationReporter;
import by.restonov.multithreading.state.BaseState;

public enum TruckState implements BaseState {

    WORK {
        @Override
        public void sendReport() {
            reporter.addWorkPerformed();
        }
    },

    MOVE_TO_TERMINAL {
        @Override
        public void sendReport() {
            reporter.addAllowedTrucks();
        }
    },

    WAIT {
        @Override
        public void sendReport() {
            reporter.addWaitingTrucks();
        }
    },

    LEAVE_STATION {
        @Override
        public void sendReport() {
            reporter.addGoneTrucks();
        }
    };

    OperationReporter reporter = OperationReporter.getInstance();

}
