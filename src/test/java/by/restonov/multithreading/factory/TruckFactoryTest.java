package by.restonov.multithreading.factory;

import by.restonov.multithreading.entity.impl.Truck;
import by.restonov.multithreading.factory.impl.TruckFactory;
import by.restonov.multithreading.state.impl.TruckState;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TruckFactoryTest extends Assert {
    TruckFactory factory;
    String[] data;
    Truck expectedTruck;

    @BeforeClass
    public void setUp() {
        factory = new TruckFactory();
        data = new String[]{"1", "hasGoods", "perishable"};
        expectedTruck = new Truck();
        expectedTruck.setId(1);
        expectedTruck.setGoodsAvailability(true);
        expectedTruck.setGoodsArePerishable(true);
        expectedTruck.setCurrentState(TruckState.WAIT);
    }

    @AfterClass
    public void tierDown() {
        factory = null;
        data = null;
        expectedTruck = null;
    }

    @Test
    public void createTest() {
        Truck expected = expectedTruck;
        Truck actual = factory.create(data);
        AssertJUnit.assertEquals(expected, actual);
    }
}
