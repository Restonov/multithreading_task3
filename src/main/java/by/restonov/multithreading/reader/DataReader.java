package by.restonov.multithreading.reader;

import by.restonov.multithreading.exception.DataReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    private static final Logger logger = LogManager.getLogger();

    public List<String> readData(String dataPath) throws DataReaderException {
        List<String> data = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(dataPath))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            logger.error("File reading error", e);
            throw new DataReaderException("File reading error: " + dataPath, e);
        }
        return data;
    }
}

