package by.restonov.multithreading.main;

import by.restonov.multithreading.entity.Manager;
import by.restonov.multithreading.entity.impl.LogisticStationTerminal;
import by.restonov.multithreading.entity.impl.Truck;
import by.restonov.multithreading.exception.DataReaderException;
import by.restonov.multithreading.factory.impl.TerminalDequeFactory;
import by.restonov.multithreading.factory.impl.TruckQueueFactory;
import by.restonov.multithreading.parser.DataParser;
import by.restonov.multithreading.reader.DataReader;

import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class App {
    public static void main(String[] args) {
        Manager manager = new Manager();
        TruckQueueFactory truckFactory = new TruckQueueFactory();
        TerminalDequeFactory terminalFactory = new TerminalDequeFactory();
        DataReader reader = new DataReader();
        DataParser parser = new DataParser();

        List<String> data;
        String terminalData = null;
        String truckData = null;
        List<Integer> parsedTerminalData;
        List<String[]> parsedTruckData;

        try {
            data = reader.readData("src/main/resources/source/data.txt");
            terminalData = data.get(DataParser.DATA_ROW_TERMINALS);
            truckData = data.get(DataParser.DATA_ROW_TRUCKS);
        } catch (DataReaderException e) {
            e.printStackTrace();
        }

        parsedTerminalData = parser.parseTerminalData(terminalData);
        parsedTruckData = parser.parseTruckData(truckData);

        Queue<Truck> trucks = truckFactory.create(parsedTruckData);
        Deque<LogisticStationTerminal> terminals = terminalFactory.create(parsedTerminalData);

        manager.initStation(terminals);
        manager.startTrucks(trucks);
    }
}
