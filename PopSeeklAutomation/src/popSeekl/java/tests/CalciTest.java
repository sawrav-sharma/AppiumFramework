package tests;

import org.testng.annotations.Test;

import java.io.IOException;

public class CalciTest extends BaseTest {

    @Test
    public void testMultiplyFunctionality() throws InterruptedException, IOException {
        objectFactory.getCalci().multiplyTwoDigits();
    }
}
