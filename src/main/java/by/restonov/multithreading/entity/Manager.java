package by.restonov.multithreading.entity;

import by.restonov.multithreading.entity.impl.LogisticStation;
import by.restonov.multithreading.entity.impl.LogisticStationTerminal;
import by.restonov.multithreading.entity.impl.Truck;

import java.util.Deque;
import java.util.Queue;

public class Manager {

    public void initStation(Deque<LogisticStationTerminal> terminalDeque) {
        LogisticStation station = LogisticStation.getInstance();
        station.initTerminals(terminalDeque);
    }

    public void startTrucks(Queue<Truck> truckQueue) {
        while (truckQueue.isEmpty() == false) {
            truckQueue.poll().start();
        }
    }
}
