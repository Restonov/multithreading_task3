package by.restonov.multithreading.entity;

import by.restonov.multithreading.exception.CustomException;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogisticStation {
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

    public void innit(List<LogisticStationTerminal> terminals) {
        int size = terminals.size();
        busyTerminals = new LinkedBlockingQueue<>(size);
        freeTerminals = new LinkedBlockingQueue<>(size);
        for (LogisticStationTerminal terminal : terminals) {
            freeTerminals.offer(terminal);
        }
        System.out.println(terminals);
        System.out.println(freeTerminals);
    }

    public LogisticStationTerminal provideTerminal() throws CustomException {
        LogisticStationTerminal terminal = null;
        try {
            terminal =freeTerminals.take();
            busyTerminals.put(terminal);
            System.out.println(terminal.getId() + " занят");
            terminal.increment();
        } catch (InterruptedException e) {
            throw new CustomException(e);
        }
        return terminal;
    }

    public void takeTerminal(LogisticStationTerminal terminal) throws CustomException {
        if (busyTerminals.remove(terminal)) {
            try {
                freeTerminals.put(terminal);
            } catch (InterruptedException e) {
                throw new CustomException(e);
            }
        } else {
            throw new CustomException("error");
        }

    }

}
