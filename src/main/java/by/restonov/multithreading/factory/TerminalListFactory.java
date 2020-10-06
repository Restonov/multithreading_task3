package by.restonov.multithreading.factory;

import by.restonov.multithreading.entity.LogisticStationTerminal;

import java.util.ArrayList;
import java.util.List;

public class TerminalListFactory {

    public static List<LogisticStationTerminal> createTerminalList(List<Integer> parsedData) {
        List<LogisticStationTerminal> terminalList = new ArrayList<>();
        for (Integer terminalId : parsedData) {
            LogisticStationTerminal terminal = new LogisticStationTerminal();
            terminal.setId(terminalId);
            terminalList.add(terminal);
        }
        return terminalList;
    }
}
