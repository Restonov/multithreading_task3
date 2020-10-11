package by.restonov.multithreading.parser;

import by.restonov.multithreading.validator.DataValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataParser {
    public static final int DATA_ROW_TERMINALS = 0;
    public static final int DATA_ROW_TRUCKS = 1;
    private static final String DELIMITER = ",*\\s";
    private static final String TRUCK_DATA_DELIMITER = "\\),*\\s";

    public List<Integer> parseTerminalData(String terminalData) {
        List<Integer> parsedData;
            parsedData = Arrays.stream(terminalData.split(DELIMITER))
                        .filter(DataValidator::validateTerminalData)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
        return parsedData;
    }

    public List<String[]> parseTruckData (String truckData) {
        List<String[]> parsedData = Arrays.stream(truckData.split(TRUCK_DATA_DELIMITER))
                     .map(s -> s.replace("(", ""))
                     .map(s -> s.replace(")", ""))
                     .map(s -> s.split(DELIMITER))
                     .collect(Collectors.toList());
        return parsedData;
    }
}
