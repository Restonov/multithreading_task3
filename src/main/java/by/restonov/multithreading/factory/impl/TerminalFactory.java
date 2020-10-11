package by.restonov.multithreading.factory.impl;

import by.restonov.multithreading.entity.impl.LogisticStationTerminal;
import by.restonov.multithreading.factory.BaseFactory;
import by.restonov.multithreading.state.impl.TerminalState;

public class TerminalFactory implements BaseFactory<LogisticStationTerminal, Integer> {

    @Override
    public LogisticStationTerminal create(Integer data) {
        LogisticStationTerminal terminal = new LogisticStationTerminal(TerminalState.FREE);
        terminal.setId(data);
        return terminal;
    }
}
