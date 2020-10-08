package by.restonov.multithreading.factory;

import by.restonov.multithreading.comparator.TruckComparator;
import by.restonov.multithreading.entity.OperationReporter;
import by.restonov.multithreading.entity.Truck;
import by.restonov.multithreading.parser.DataParser;
import by.restonov.multithreading.state.BaseState;
import by.restonov.multithreading.state.impl.TruckState;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TruckQueueFactory {
    private static final int ID = 0;
    private static final int STATE = 1;
    private static final int PERISHABLE = 2;

    public static Queue<Truck> createTruckQueue(List<String> parsedForQueueTruckData) {
        Queue<Truck> truckQueue = new PriorityQueue<>(new TruckComparator());
        DataParser parser = new DataParser();
        List<String[]> truckData;
        OperationReporter reporter = OperationReporter.getInstance();
        reporter.setFutureOperationsAmount(parsedForQueueTruckData.size());

        truckData = parser.parseTruckDataForQueue(parsedForQueueTruckData);
        for (String[] truck : truckData) {
            long truckId = Long.parseLong(truck[ID]);
            BaseState truckState = TruckState.defineState(truck[STATE]);

            Truck newTruck = new Truck(truckState);
            newTruck.setId(truckId);
            newTruck.definePerishable(truck[PERISHABLE]);
            truckQueue.add(newTruck);
        }
        return truckQueue;
    }
}
