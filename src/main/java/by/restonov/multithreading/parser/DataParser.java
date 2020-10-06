package by.restonov.multithreading.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataParser {
    private static final String DELIMITER = "\\,*\\s";

    public List<Integer> parseData(String data) {
        List<Integer> parsedData;
            parsedData = Arrays.stream(data.split(DELIMITER)).map(Integer::parseInt).collect(Collectors.toList());
        return parsedData;
    }
}
