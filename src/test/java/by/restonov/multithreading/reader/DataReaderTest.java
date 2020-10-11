package by.restonov.multithreading.reader;

import by.restonov.multithreading.exception.DataReaderException;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DataReaderTest extends Assert {
    DataReader reader;
    List<String> correctData;
    List<String> dataCorrectPath;
    List<String> dataIncorrectPath;

    @BeforeClass
    public void setUp() {
        reader = new DataReader();
        correctData = new ArrayList<>();
        dataCorrectPath = new ArrayList<>();
        dataIncorrectPath = new ArrayList<>();

        correctData.add("101");
        correctData.add("102");
        correctData.add("103");
    }

    @AfterClass
    public void tierDown() {
        reader = null;
        correctData = null;
        dataCorrectPath = null;
        dataIncorrectPath = null;
    }

    @Test
    public void dataReaderPositiveTest() {
        try {
            dataCorrectPath = reader.readData("src/main/resources/source/testData.txt");
        } catch (DataReaderException e) {
            e.printStackTrace();
        }
        List<String> expected = correctData;
        List<String> actual = dataCorrectPath;
        AssertJUnit.assertEquals(expected, actual);
    }

    @Test
    public void dataReaderNegativeTest() {
        try {
            dataIncorrectPath = reader.readData("incorrect_path");
        } catch (DataReaderException e) {
            e.printStackTrace();
        }

        List<String> expected = correctData;
        List<String> actual = dataIncorrectPath;
        AssertJUnit.assertNotSame(expected, actual);
    }
}
