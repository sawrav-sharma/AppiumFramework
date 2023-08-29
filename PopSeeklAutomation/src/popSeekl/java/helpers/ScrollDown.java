package helpers;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

import static tests.BaseTest.driver;

public class ScrollDown {
    public static void scrollDown() {
        // Get the screen dimensions
        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();

/*
        The startX is set to the center of the screen, while startY is set to 80% of the screen's height,
        indicating where the swipe will start. endY is set to 20% of the screen's height, indicating where
        the swipe will end. This combination effectively simulates a swipe gesture from the top
        (80% down to 20%) of the screen.
*/
        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.2);

        // Perform the swipe gesture
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startX, startY))
                .waitAction()
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

}
