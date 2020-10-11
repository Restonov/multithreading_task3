package by.restonov.multithreading.factory.impl;

import by.restonov.multithreading.entity.impl.LogisticStationTerminal;
import by.restonov.multithreading.factory.BaseFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TerminalDequeFactory implements BaseFactory<Deque<LogisticStationTerminal>, List<Integer>> {

    @Override
    public Deque<LogisticStationTerminal> create(List<Integer> parsedTerminalData) {
        Deque<LogisticStationTerminal> terminalDeque = new LinkedList<>();
        TerminalFactory factory = new TerminalFactory();
        for (Integer terminalId : parsedTerminalData) {
            LogisticStationTerminal terminal = factory.create(terminalId);
            terminalDeque.add(terminal);
        }
        return terminalDeque;
    }
}
