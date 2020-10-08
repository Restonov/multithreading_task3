package by.restonov.multithreading.entity;

import by.restonov.multithreading.exception.CustomException;

import java.util.Queue;

public class TruckQueueInitializer {

    private TruckQueueInitializer() {
    }

    private static class TruckQueueInitializerHolder {
        private static final TruckQueueInitializer instance = new TruckQueueInitializer();
    }

    public static TruckQueueInitializer getInstance() {
        return TruckQueueInitializer.TruckQueueInitializerHolder.instance;
    }

    public void init(Queue<Truck> trucks) {
        OperationReporter reporter = OperationReporter.getInstance();

        while (!trucks.isEmpty()) {
            Truck truck = trucks.poll();
            truck.start();
        }

        try {
            reporter.report();
        } catch (
                CustomException e) {
            e.printStackTrace();
        }
    }

}
