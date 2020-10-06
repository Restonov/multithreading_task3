package by.restonov.multithreading.entity;

import by.restonov.multithreading.exception.CustomException;
import by.restonov.multithreading.state.BaseState;
import by.restonov.multithreading.state.impl.TruckState;

public class Truck extends Thread{
    private long truckId;
    private boolean isPerishable = false;
    private BaseState currentState;

    public Truck() {
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
                performLoad(terminal);
           } else {
                performUnload(terminal);
           }
           try {
                station.takeTerminal(terminal);
           } catch (CustomException e) {
                e.printStackTrace();
           }
    }

    public void setCurrentState(BaseState state) {
        //TODO check if state could be changed
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

    @Override
    public long getId() {
        return truckId;
    }

    public void setId(long id) {
        this.truckId = id;
    }

    public void performLoad(LogisticStationTerminal terminal) {
        System.out.println(truckId + " загружен" + " в терминале " + terminal.getId());
    }

    public void performUnload(LogisticStationTerminal terminal) {
        System.out.println(truckId + " разгружен"  + " в терминале " + terminal.getId());
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
