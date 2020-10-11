package by.restonov.multithreading.entity.impl;

import by.restonov.multithreading.entity.BaseEntity;
import by.restonov.multithreading.exception.CustomException;
import by.restonov.multithreading.state.BaseState;
import by.restonov.multithreading.state.impl.TruckState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Deque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticStation implements BaseEntity {
    private static final Logger logger = LogManager.getLogger();
    private static final Lock lock = new ReentrantLock();
    private Semaphore semaphore;
    private Deque<LogisticStationTerminal> terminals;

    private LogisticStation() {
    }

    private static class LogisticStationHolder {
        private static final LogisticStation instance = new LogisticStation();
    }

    public static LogisticStation getInstance() {
        return LogisticStationHolder.instance;
    }

    @Override
    public BaseState getCurrentState() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCurrentState(BaseState state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setId(long terminalId) {
        throw new UnsupportedOperationException();
    }

    public Deque<LogisticStationTerminal> getTerminals() {
        return terminals;
    }

    public void initTerminals(Deque<LogisticStationTerminal> terminals) {
        semaphore = new Semaphore(terminals.size());
        this.terminals = terminals;
    }

    public LogisticStationTerminal allowTruck(Truck truck) throws CustomException {
        try {
            semaphore.acquire();
            truck.setCurrentState(TruckState.MOVE_TO_TERMINAL);
        } catch (InterruptedException e) {
            logger.error("Error while allowing truck to the station", e);
            throw new CustomException("Error while allowing truck to the station", e);
        }
        return provideTerminal();
    }

    public void releaseTruck(Truck truck, LogisticStationTerminal terminal) {
        takeTerminal(terminal);
        truck.setCurrentState(TruckState.LEAVE_STATION);
        semaphore.release();
    }

    private LogisticStationTerminal provideTerminal(){
        lock.lock();
        LogisticStationTerminal terminal = terminals.pollFirst();
        logger.info("Terminal № " + terminal.getId() + " are busy right now");
        lock.unlock();
        return terminal;
    }

    private void takeTerminal(LogisticStationTerminal terminal){
        lock.lock();
        terminals.addLast(terminal);
        logger.info("Terminal № " + terminal.getId() + " are free right now");
        lock.unlock();
    }

}
