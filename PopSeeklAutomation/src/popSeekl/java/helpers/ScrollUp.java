package helpers;

import static tests.BaseTest.driver;

public class ScrollUp {
    public static void scrollUp() {
        // Get the screen dimensions
        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();
    }
}
