package by.restonov.multithreading.factory;

import by.restonov.multithreading.comparator.TruckComparator;
import by.restonov.multithreading.entity.impl.Truck;
import by.restonov.multithreading.factory.impl.TruckQueueFactory;
import by.restonov.multithreading.state.impl.TruckState;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TruckQueueFactoryTest extends Assert {
    TruckQueueFactory factory;
    Queue<Truck> expectedTrucks;
    List<String[]> data;

    @BeforeClass
    public void setUp() {
        factory = new TruckQueueFactory();
        expectedTrucks = new PriorityQueue<>(new TruckComparator());
        data = new ArrayList<>();

        expectedTrucks.add(new Truck(2, true, false, TruckState.WAIT));
        expectedTrucks.add(new Truck(1, true, true, TruckState.WAIT));
        data.add(new String[]{"1", "hasGoods", "perishable"});
        data.add(new String[]{"2", "hasGoods", "null"});
    }

    @AfterClass
    public void tierDown() {
        factory = null;
        expectedTrucks = null;
        data = null;
    }

    @Test
    public void createTest() {
        Truck expected = expectedTrucks.poll();
        Truck actual = factory.create(data).poll();
        AssertJUnit.assertEquals(expected, actual);
    }
}
