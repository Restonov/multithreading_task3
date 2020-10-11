package by.restonov.multithreading.factory;

import by.restonov.multithreading.entity.impl.LogisticStationTerminal;
import by.restonov.multithreading.factory.impl.TerminalDequeFactory;
import by.restonov.multithreading.factory.impl.TruckFactory;
import by.restonov.multithreading.state.impl.TerminalState;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TerminalDequeFactoryTest extends Assert {
    TerminalDequeFactory factory;
    Deque<LogisticStationTerminal> expectedTerminals;
    List<Integer> data;

    @BeforeClass
    public void setUp() {
        factory = new TerminalDequeFactory();
        expectedTerminals = new LinkedList<>();
        data = new ArrayList<>();

        expectedTerminals.add(new LogisticStationTerminal(1, TerminalState.FREE));
        expectedTerminals.add(new LogisticStationTerminal(2, TerminalState.FREE));
        data.add(1);
        data.add(2);
    }

    @AfterClass
    public void tierDown() {
        factory = null;
        expectedTerminals = null;
        data = null;
    }

    @Test
    public void createTest() {
        Deque<LogisticStationTerminal> expected = expectedTerminals;
        Deque<LogisticStationTerminal> actual = factory.create(data);
        AssertJUnit.assertEquals(expected, actual);
    }
}
