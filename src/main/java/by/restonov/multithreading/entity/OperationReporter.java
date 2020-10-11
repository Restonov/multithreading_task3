package by.restonov.multithreading.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class OperationReporter{
    private static final Logger logger = LogManager.getLogger();
    private AtomicInteger truckQueueSize = new AtomicInteger(0);
    private AtomicInteger waitingTrucks = new AtomicInteger(0);
    private AtomicInteger allowedTrucks = new AtomicInteger(0);
    private AtomicInteger workPerformed = new AtomicInteger(0);
    private AtomicInteger goneTrucks = new AtomicInteger(0);
    private AtomicInteger terminalWasBusy = new AtomicInteger(0);
    private AtomicInteger terminalWasFree = new AtomicInteger(0);

    private OperationReporter() {
    }

    private static class OperationReporterHolder {
        private static final OperationReporter instance = new OperationReporter();
    }

    public static OperationReporter getInstance() {
        return OperationReporter.OperationReporterHolder.instance;
    }

    public void setTruckQueueSize(int truckQueueSize) {
        this.truckQueueSize.addAndGet(truckQueueSize);
    }

    public void addWaitingTrucks() {
        waitingTrucks.incrementAndGet();
    }

    public void addAllowedTrucks() {
        allowedTrucks.incrementAndGet();
    }

    public void addWorkPerformed() {
        workPerformed.incrementAndGet();
    }

    public void addGoneTrucks() {
        goneTrucks.incrementAndGet();
        report();
    }

    public void countFree() {
        terminalWasFree.incrementAndGet();
    }

    public void countBusy() {
        terminalWasBusy.incrementAndGet();
    }

    private void report() {
        if (truckQueueSize.get() == goneTrucks.get())
        logger.info(toString());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n");
        sb.append("Truck queue = ").append(waitingTrucks).append(" trucks, ")
        .append("Station has ").append(terminalWasFree).append(" terminals, ")
        .append(allowedTrucks).append(" trucks allowed to the Station, ")
        .append("work was performed ").append(workPerformed).append(" times, ")
        .append("and finally ").append(goneTrucks).append(" trucks leave the Station");
        return sb.toString();
    }
}
