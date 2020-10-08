package by.restonov.multithreading.entity;

import by.restonov.multithreading.state.BaseState;

public class LogisticStationTerminal {
    private long terminalId;
    private BaseState currentState;

    public LogisticStationTerminal(BaseState state) {
        currentState = state;
    }

    public BaseState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(BaseState state) {
        state.notifyReporter();
        this.currentState = state;
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
