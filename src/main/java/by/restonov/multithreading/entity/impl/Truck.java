package by.restonov.multithreading.entity.impl;

import by.restonov.multithreading.entity.BaseEntity;
import by.restonov.multithreading.exception.CustomException;
import by.restonov.multithreading.state.BaseState;

public class Truck extends Thread implements BaseEntity {
    private long truckId;
    private boolean goodsAvailability = false;
    private boolean isGoodsArePerishable = false;
    private BaseState currentState;

    public Truck() {
    }

    public Truck(BaseState state) {
        currentState = state;
    }

    public Truck(long truckId, boolean goodsAvailability, boolean isGoodsArePerishable, BaseState currentState) {
        this.truckId = truckId;
        this.goodsAvailability = goodsAvailability;
        this.isGoodsArePerishable = isGoodsArePerishable;
        this.currentState = currentState;
    }

    public boolean getGoodsAvailability() {
        return goodsAvailability;
    }

    public void setGoodsAvailability(boolean goodsAvailability) {
        this.goodsAvailability = goodsAvailability;
    }

    public void setCurrentState(BaseState state) {
        state.sendReport();
        this.currentState = state;
    }

    public BaseState getCurrentState() {
        return currentState;
    }

    public void setGoodsArePerishable(boolean isPerishable) {
        this.isGoodsArePerishable = isPerishable;
    }

    public boolean getGoodsArePerishable() {
        return this.isGoodsArePerishable;
    }

    @Override
    public long getId() {
        return truckId;
    }

    public void setId(long id) {
        this.truckId = id;
    }

    @Override
    public void run() {
        LogisticStation station = LogisticStation.getInstance();
        LogisticStationTerminal terminal = null;

        try {
            terminal = station.allowTruck(this);
            terminal.defineWork(this, goodsAvailability);
        } catch (CustomException e) {
            e.printStackTrace();
        } finally {
            station.releaseTruck(this, terminal);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Truck{");
        sb.append("truckId=").append(truckId);
        sb.append(", goodsAvailability=").append(goodsAvailability);
        sb.append(", isGoodsArePerishable=").append(isGoodsArePerishable);
        sb.append(", currentState=").append(currentState);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Truck truck = (Truck) o;

        if (truckId != truck.truckId) return false;
        if (goodsAvailability != truck.goodsAvailability) return false;
        if (isGoodsArePerishable != truck.isGoodsArePerishable) return false;
        return currentState != null ? currentState.equals(truck.currentState) : truck.currentState == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (truckId ^ (truckId >>> 32));
        result = 31 * result + (goodsAvailability ? 1 : 0);
        result = 31 * result + (isGoodsArePerishable ? 1 : 0);
        result = 31 * result + (currentState != null ? currentState.hashCode() : 0);
        return result;
    }
}
