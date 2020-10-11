package by.restonov.multithreading.factory.impl;

import by.restonov.multithreading.comparator.TruckComparator;
import by.restonov.multithreading.entity.OperationReporter;
import by.restonov.multithreading.entity.impl.Truck;
import by.restonov.multithreading.factory.BaseFactory;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TruckQueueFactory implements BaseFactory<Queue<Truck>, List<String[]>> {

    @Override
    public Queue<Truck> create(List<String[]> parsedTruckData) {
        OperationReporter reporter = OperationReporter.getInstance();
        Queue<Truck> truckQueue = new PriorityQueue<>(new TruckComparator());
        TruckFactory factory = new TruckFactory();
        for (String[] data : parsedTruckData) {
            Truck newTruck = factory.create(data);
            truckQueue.add(newTruck);
        }
        reporter.setTruckQueueSize(truckQueue.size());
        return truckQueue;
    }
}
