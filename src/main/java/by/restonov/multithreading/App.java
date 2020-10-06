package by.restonov.multithreading;

import by.restonov.multithreading.entity.DataName;
import by.restonov.multithreading.entity.LogisticStation;
import by.restonov.multithreading.entity.LogisticStationTerminal;
import by.restonov.multithreading.entity.Truck;
import by.restonov.multithreading.exception.DataReaderException;
import by.restonov.multithreading.factory.TerminalListFactory;
import by.restonov.multithreading.factory.TruckQueueFactory;
import by.restonov.multithreading.parser.DataParser;
import by.restonov.multithreading.reader.DataReader;


import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main( String[] args ) {

        LogisticStation station = LogisticStation.getInstance();

        List<String> data;
        List<Integer> parsedTerminalData = null;
        List<Integer> parsedTruckData = null;
        List<LogisticStationTerminal> terminals;
        String terminalData = null;
        String truckData = null;
        PriorityQueue<Truck> trucks;
        DataReader reader = new DataReader();
        DataParser parser = new DataParser();

        try {
           data = reader.readData("src/main/resources/source/data.txt");
           terminalData = data.get(DataName.TERMINALS);
           truckData = data.get(DataName.TRUCKS);
        } catch (DataReaderException e) {
            e.printStackTrace();
        }

        parsedTerminalData = parser.parseData(terminalData);
        parsedTruckData = parser.parseData(truckData);

        terminals = TerminalListFactory.createTerminalList(parsedTerminalData);
        trucks = TruckQueueFactory.createTruckQueue(parsedTruckData);

        station.innit(terminals);
        long before = System.currentTimeMillis();



        for (int i = 0; i < 10; i++) {
            Truck truck = trucks.poll();
                truck.start();
        }


        long after = System.currentTimeMillis();

        System.out.println(after - before);

//        TimeUnit timeUnit = TimeUnit.SECONDS;
//        try {
//            timeUnit.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }
}
