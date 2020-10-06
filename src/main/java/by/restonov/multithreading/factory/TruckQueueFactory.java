package by.restonov.multithreading.factory;

import by.restonov.multithreading.comparator.TruckComparator;
import by.restonov.multithreading.entity.Truck;
import by.restonov.multithreading.state.impl.TruckState;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TruckQueueFactory {

    public static PriorityQueue<Truck> createTruckQueue(List<Integer> parsedData) {
        PriorityQueue<Truck> truckQueue = new PriorityQueue<>(new TruckComparator());
        for (Integer truckId : parsedData) {
            Truck truck = new Truck();
            truck.setId(truckId);
            truck.setCurrentState(TruckState.FULL);
            if (truckId > 10){
                truck.setPerishable(true);
            }
            truckQueue.add(truck);
        }
        return truckQueue;
    }
}
