package by.restonov.multithreading.comparator;

import by.restonov.multithreading.entity.impl.Truck;
import by.restonov.multithreading.state.impl.TruckState;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TruckComparatorTest extends Assert {
    TruckComparator comparator;
    Truck truck1;
    Truck truck2;

    @BeforeTest
    public void setUp() {
        comparator = new TruckComparator();
        truck1 = new Truck(TruckState.WAIT);
        truck2 = new Truck(TruckState.WAIT);

        truck2.setGoodsArePerishable(true);
        truck1.setGoodsArePerishable(false);
    }

    @AfterClass
    public void tierDown() {
        comparator = null;
        truck1 = null;
        truck2 = null;
    }

    @Test
    public void truckComparatorTest() {
        int expected = 1;
        int actual = comparator.compare(truck1, truck2);
        AssertJUnit.assertEquals(expected, actual);
    }
}
