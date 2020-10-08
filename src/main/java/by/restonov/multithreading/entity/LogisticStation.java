package by.restonov.multithreading.entity;

import by.restonov.multithreading.exception.CustomException;
import by.restonov.multithreading.state.impl.TerminalState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class LogisticStation {
    private static final Logger logger = LogManager.getLogger();
    private LinkedBlockingQueue<LogisticStationTerminal> freeTerminals;
    private LinkedBlockingQueue<LogisticStationTerminal> busyTerminals;

    private LogisticStation() {
    }

    private static class LogisticStationHolder {
        private static final LogisticStation instance = new LogisticStation();
    }

    public static LogisticStation getInstance() {
        return LogisticStationHolder.instance;
    }

    public void init(List<LogisticStationTerminal> terminals) {
        int size = terminals.size();
        busyTerminals = new LinkedBlockingQueue<>(size);
        freeTerminals = new LinkedBlockingQueue<>(size);
        for (LogisticStationTerminal terminal : terminals) {
            freeTerminals.offer(terminal);
        }
    }

    public LogisticStationTerminal provideTerminal() throws CustomException {
        LogisticStationTerminal terminal = null;
        try {
            terminal =freeTerminals.take();
            busyTerminals.put(terminal);
            terminal.setCurrentState(TerminalState.BUSY);
            logger.info(terminal.getId() + " are busy right now");
        } catch (InterruptedException e) {
            throw new CustomException(e);
        }
        return terminal;
    }

    public void takeTerminal(LogisticStationTerminal terminal) throws CustomException {
        if (busyTerminals.remove(terminal)) {
            try {
                freeTerminals.put(terminal);
                terminal.setCurrentState(TerminalState.FREE);
                logger.info(terminal.getId() + " are free right now");
            } catch (InterruptedException e) {
                throw new CustomException(e);
            }
        } else {
            throw new CustomException("error");
        }

    }

}
