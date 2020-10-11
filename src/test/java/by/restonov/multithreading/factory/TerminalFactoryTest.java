package by.restonov.multithreading.factory;

import by.restonov.multithreading.entity.impl.LogisticStationTerminal;
import by.restonov.multithreading.factory.impl.TerminalFactory;
import by.restonov.multithreading.state.impl.TerminalState;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TerminalFactoryTest extends Assert {
    TerminalFactory factory;
    Integer data;
    LogisticStationTerminal expectedTerminal;

    @BeforeClass
    public void setUp() {
        factory = new TerminalFactory();
        data = 101;
        expectedTerminal = new LogisticStationTerminal(TerminalState.FREE);
        expectedTerminal.setId(101);
    }

    @AfterClass
    public void tierDown() {
        factory = null;
        data = null;
        expectedTerminal = null;
    }

    @Test
    public void createTest() {
        LogisticStationTerminal expected = expectedTerminal;
        LogisticStationTerminal actual = factory.create(data);
        AssertJUnit.assertEquals(expected, actual);
    }
}
