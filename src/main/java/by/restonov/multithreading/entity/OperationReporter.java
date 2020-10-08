package by.restonov.multithreading.entity;

import by.restonov.multithreading.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class OperationReporter{
    private static final Logger logger = LogManager.getLogger();
    private AtomicInteger truckUnload = new AtomicInteger(0);
    private AtomicInteger truckLoad = new AtomicInteger(0);;
    private AtomicInteger terminalWasBusy = new AtomicInteger(0);;
    private AtomicInteger terminalWasFree = new AtomicInteger(0);;
    private int futureOperationsAmount;

    private OperationReporter() {
    }

    private static class OperationReporterHolder {
        private static final OperationReporter instance = new OperationReporter();
    }

    public static OperationReporter getInstance() {
        return OperationReporter.OperationReporterHolder.instance;
    }

    public void setFutureOperationsAmount(int amount) {
        this.futureOperationsAmount = amount;
    }

    public void countLoad() {
        truckLoad.incrementAndGet();
    }

    public void countUnload() {
        truckUnload.incrementAndGet();
    }

    public void countFree() {
        terminalWasFree.incrementAndGet();
    }

    public void countBusy() {
        terminalWasBusy.incrementAndGet();
    }

    public void report() throws CustomException {
        if ((truckLoad.get() + truckUnload.get()) == futureOperationsAmount) {
            logger.info(toString());
        } else {
            TimeUnit timeUnit = TimeUnit.SECONDS;
            try {
                timeUnit.sleep(1);
                report();
            } catch (InterruptedException e) {
                logger.error("Error while getting report", e);
                throw new CustomException("Error while getting report", e);
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n");
        sb.append("Unloading goods performed = ").append(truckUnload).append(" times");
        sb.append(", Loading goods performed = ").append(truckLoad).append(" times");
        sb.append(", Terminals was busy = ").append(terminalWasBusy).append(" times");
        sb.append(", Terminals was free = ").append(terminalWasFree).append(" times");
        return sb.toString();
    }
}
