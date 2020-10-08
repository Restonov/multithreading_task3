package by.restonov.multithreading.entity;

import by.restonov.multithreading.exception.CustomException;
import by.restonov.multithreading.state.BaseState;
import by.restonov.multithreading.state.impl.TruckState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Truck extends Thread{
    private static final Logger logger = LogManager.getLogger();
    private long truckId;
    private boolean isPerishable = false;
    private BaseState currentState;

    public Truck(BaseState state) {
        currentState = state;
    }

    @Override
    public void run() {
        LogisticStation station = LogisticStation.getInstance();
        LogisticStationTerminal terminal = null;
        try {
            terminal = station.provideTerminal();
        } catch (CustomException e) {
            e.printStackTrace();
        }
           if (currentState == TruckState.EMPTY) {
               try {
                   performLoad(terminal);
               } catch (CustomException e) {
                   e.printStackTrace();
               }
           } else if (currentState == TruckState.FULL) {
               try {
                   performUnload(terminal);
               } catch (CustomException e) {
                   e.printStackTrace();
               }
           }
           try {
                station.takeTerminal(terminal);
           } catch (CustomException e) {
                e.printStackTrace();
           }
    }

    public void setCurrentState(BaseState state) {
        state.notifyReporter();
        this.currentState = state;
    }

    public BaseState getCurrentState() {
        return currentState;
    }

    public void setPerishable(boolean isPerishable) {
        this.isPerishable = isPerishable;
    }

    public boolean getPerishable() {
        return this.isPerishable;
    }

    public void definePerishable(String isPerishable) {
        if (isPerishable.equals("perishable")) {
            setPerishable(true);
        }
    }

    @Override
    public long getId() {
        return truckId;
    }

    public void setId(long id) {
        this.truckId = id;
    }

    public void performLoad(LogisticStationTerminal terminal) throws CustomException {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        try {
            timeUnit.sleep(1);
        } catch (InterruptedException e) {
            logger.error("Error while permorming truck load", e);
            throw new CustomException("Error while permorming truckload", e);
        }
        setCurrentState(TruckState.FULL);
        logger.info(truckId + " loaded at terminal " + terminal.getId());
    }

    public void performUnload(LogisticStationTerminal terminal) throws CustomException {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        try {
            timeUnit.sleep(1);
        } catch (InterruptedException e) {
            logger.error("Error while permorming truck unload", e);
            throw new CustomException("Error while permorming truckload", e);
        }
        setCurrentState(TruckState.EMPTY);
        logger.info(truckId + " unloaded at terminal " + terminal.getId());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Truck{");
        sb.append("truckId=").append(truckId);
        sb.append(", isPerishable=").append(isPerishable);
        sb.append(", currentState=").append(currentState);
        sb.append('}');
        return sb.toString();
    }
}
