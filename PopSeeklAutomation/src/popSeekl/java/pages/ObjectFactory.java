package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ObjectFactory {
    private final AppiumDriver<WebElement> driver;
    private Calci calci;

    public ObjectFactory(AppiumDriver<WebElement> driver) {
        this.driver = driver;
    }

    public Calci getCalci() {
        if (calci == null) {
            calci = new Calci(driver);
        }
        return calci;
    }
}