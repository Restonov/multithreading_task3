package by.restonov.multithreading.parser;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DataParserTest extends Assert {
    DataParser parser;
    List<Integer> expectedTerminalData;
    List<Integer> parsedTerminalData;
    List<String[]> expectedTruckData;
    List<String[]> parsedTruckData;
    String unParsedTerminalData;
    String unParsedTruckData;

    @BeforeClass
    public void setUp() {
        parser = new DataParser();
        expectedTerminalData = new ArrayList<>();
        parsedTerminalData = new ArrayList<>();
        expectedTruckData = new ArrayList<>();
        parsedTruckData = new ArrayList<>();

        unParsedTerminalData = "101, 102, 103";
        expectedTerminalData.add(101);
        expectedTerminalData.add(102);
        expectedTerminalData.add(103);

        unParsedTruckData = "(1, hasGoods, perishable)";
        expectedTruckData.add(new String[]{"1", "hasGoods", "perishable"});
    }

    @AfterClass
    public void tierDown() {
        parser = null;
        expectedTerminalData = null;
        parsedTerminalData = null;
        expectedTruckData = null;
        parsedTruckData = null;
        unParsedTerminalData = null;
        unParsedTruckData = null;
    }

    @Test
    public void parseTerminalDataTest() {
        List<Integer> expected = expectedTerminalData;
        List<Integer> actual = parser.parseTerminalData(unParsedTerminalData);
        AssertJUnit.assertEquals(expected, actual);
    }

    @Test
    public void parseTruckDataTest() {
        String[] expected = expectedTruckData.get(0);
        String[] actual = parser.parseTruckData(unParsedTruckData).get(0);
        AssertJUnit.assertArrayEquals(expected, actual);
    }
}
