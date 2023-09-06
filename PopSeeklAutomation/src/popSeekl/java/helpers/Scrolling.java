package helpers;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import static tests.BaseTest.driver;
import java.time.Duration;

public class Scrolling {
    public static void scrollDown() {
        Dimension screenSize = driver.manage().window().getSize();
        int startX = screenSize.width / 2;
        int startY = (int) (screenSize.height * 0.8);
        int endY = (int) (screenSize.height * 0.2);

        performSwipe(startX, startY, startX, endY);
    }

    public static void scrollUp() {
        Dimension screenSize = driver.manage().window().getSize();
        int startX = screenSize.width / 2;
        int startY = (int) (screenSize.height * 0.8);
        int endY = (int) (screenSize.height * 0.4);

        performSwipe(startX, startY, startX, endY);
    }

    private static void performSwipe(int startX, int startY, int endX, int endY) {
        TouchAction<?> action = new TouchAction<>(driver);
        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }
}
