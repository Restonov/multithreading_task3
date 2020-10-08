package by.restonov.multithreading.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataParser {
    private static final String DELIMITER = "\\,*\\s";
    private static final String TRUCK_DATA_DELIMITER = "\\)\\,*\\s";

    public List<Integer> parseTerminalData(String data) {
        List<Integer> parsedData;
            parsedData = Arrays.stream(data.split(DELIMITER))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
        return parsedData;
    }

    public List<String> parseTruckData(String data) {
        List<String> parsedData;
        parsedData = Arrays.stream(data.split(TRUCK_DATA_DELIMITER))
                    .map(s -> s.replace("(", ""))
                    .map(s -> s.replace(")", ""))
                    .collect(Collectors.toList());
        return parsedData;
    }

    public List<String[]> parseTruckDataForQueue(List<String> parsedTruckData) {
        List<String[]> parsedForQueueTruckData = new ArrayList<>();
        for (String parsedData : parsedTruckData) {
            String[] truckData = parsedData.split(DELIMITER);
            parsedForQueueTruckData.add(truckData);
        }
        return parsedForQueueTruckData;
    }
}
