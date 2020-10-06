package by.restonov.multithreading.entity;

import by.restonov.multithreading.state.BaseState;

public class LogisticStationTerminal {
    private long terminalId;
    private BaseState currentState;
    private int counter = 0;

    public LogisticStationTerminal() {
    }

//    private static class StationTerminalHolder {
//        private static final LogisticStationTerminal instance = new LogisticStationTerminal();
//    }
//
//    public static LogisticStationTerminal getInstance() {
//        return StationTerminalHolder.instance;
//    }

    public void changeState(BaseState state) {
        //TODO check if state could be changed
        this.currentState = state;
    }

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public long getId() {
        return terminalId;
    }

    public void setId(long terminalId) {
        this.terminalId = terminalId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LogisticStationTerminal{");
        sb.append("terminalId=").append(terminalId);
        sb.append('}');
        return sb.toString();
    }
}
