package by.restonov.multithreading.validator;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DataValidatorTest extends Assert {
    String correctData;
    String inCorrectData;

    @BeforeClass
    public void setUp() {
        correctData = "1010";
        correctData = "data";
    }

    @AfterClass
    public void tierDown() {
        correctData = null;
        inCorrectData = null;
    }

    @Test
    public void testDataValidatorPositive() {
        boolean condition = DataValidator.validateTerminalData(correctData);
        AssertJUnit.assertTrue(condition);
    }

    @Test
    public void testDataValidatorNegative() {
        boolean condition = DataValidator.validateTerminalData(inCorrectData);
        AssertJUnit.assertFalse(condition);
    }
}
