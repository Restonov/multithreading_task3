package by.restonov.multithreading.entity.impl;

import by.restonov.multithreading.entity.BaseEntity;
import by.restonov.multithreading.exception.CustomException;
import by.restonov.multithreading.state.BaseState;
import by.restonov.multithreading.state.impl.TerminalState;
import by.restonov.multithreading.state.impl.TruckState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class LogisticStationTerminal implements BaseEntity {
    private static final Logger logger = LogManager.getLogger();
    private long terminalId;
    private BaseState currentState;

    public LogisticStationTerminal() {
    }

    public LogisticStationTerminal(BaseState state) {
        currentState = state;
    }

    public LogisticStationTerminal(long terminalId, BaseState currentState) {
        this.terminalId = terminalId;
        this.currentState = currentState;
    }

    public BaseState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(BaseState state) {
        state.sendReport();
        this.currentState = state;
    }

    public long getId() {
        return terminalId;
    }

    public void setId(long terminalId) {
        this.terminalId = terminalId;
    }

    public void defineWork(Truck truck, boolean goodsAvailability) {
        if (goodsAvailability == true) {
            try {
                performUnload(truck);
            } catch (CustomException e) {
                e.printStackTrace();
            }
        } else {
            try {
                performLoad(truck);
            } catch (CustomException e) {
                e.printStackTrace();
            }
        }
    }

    private void performLoad(Truck truck) throws CustomException {
        setCurrentState(TerminalState.BUSY);
        truck.setCurrentState(TruckState.WORK);

        //simulating goods loading
        TimeUnit timeUnit = TimeUnit.SECONDS;
        try {
            timeUnit.sleep(2);
        } catch (InterruptedException e) {
            logger.error("Error while performing truck load", e);
            throw new CustomException("Error while performing truckload", e);
        }

        logger.info("Truck № " + truck.getId() + " loaded at terminal № " + terminalId);
        setCurrentState(TerminalState.FREE);
    }

    private void performUnload(Truck truck) throws CustomException {
        setCurrentState(TerminalState.BUSY);
        truck.setCurrentState(TruckState.WORK);

        //simulating goods unloading
        TimeUnit timeUnit = TimeUnit.SECONDS;
        try {
            timeUnit.sleep(1);
        } catch (InterruptedException e) {
            logger.error("Error while performing truck unload", e);
            throw new CustomException("Error while performing truckload", e);
        }

        logger.info("Truck № " + truck.getId() + " unloaded at terminal № " + terminalId);
        setCurrentState(TerminalState.FREE);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LogisticStationTerminal{");
        sb.append("terminalId=").append(terminalId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogisticStationTerminal terminal = (LogisticStationTerminal) o;

        if (terminalId != terminal.terminalId) return false;
        return currentState != null ? currentState.equals(terminal.currentState) : terminal.currentState == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (terminalId ^ (terminalId >>> 32));
        result = 31 * result + (currentState != null ? currentState.hashCode() : 0);
        return result;
    }
}
