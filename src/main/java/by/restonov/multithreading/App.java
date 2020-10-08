package by.restonov.multithreading;

import by.restonov.multithreading.entity.*;
import by.restonov.multithreading.exception.DataReaderException;
import by.restonov.multithreading.factory.TerminalListFactory;
import by.restonov.multithreading.factory.TruckQueueFactory;
import by.restonov.multithreading.parser.DataParser;
import by.restonov.multithreading.reader.DataReader;


import java.util.List;
import java.util.Queue;

public class App {
    public static void main( String[] args ) {

        LogisticStation station = LogisticStation.getInstance();
        TruckQueueInitializer truckQueue = TruckQueueInitializer.getInstance();

        List<String> data;
        List<Integer> parsedTerminalData;
        List<String> parsedTruckData;
        List<LogisticStationTerminal> terminals;
        String terminalData = null;
        String truckData = null;
        Queue<Truck> trucks;
        DataReader reader = new DataReader();
        DataParser parser = new DataParser();

        try {
            data = reader.readData("src/main/resources/source/data.txt");
            terminalData = data.get(DataName.TERMINALS);
            truckData = data.get(DataName.TRUCKS);
        } catch (DataReaderException e) {
            e.printStackTrace();
        }

        parsedTerminalData = parser.parseTerminalData(terminalData);
        parsedTruckData = parser.parseTruckData(truckData);


        terminals = TerminalListFactory.createTerminalList(parsedTerminalData);
        trucks = TruckQueueFactory.createTruckQueue(parsedTruckData);

        station.init(terminals);
        truckQueue.init(trucks);

    }
}
