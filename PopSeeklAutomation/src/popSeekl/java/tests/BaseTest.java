package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.ObjectFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseTest {

    public static AppiumDriver<WebElement> driver;
    public static String serverUrl;
    public static String platformName;
    public static String deviceName;
    protected static ObjectFactory objectFactory;

    @BeforeClass
    public void setup() throws IOException {
        objectFactory = new ObjectFactory(driver);
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(input);
        }

        platformName = prop.getProperty("platformName");
        deviceName = prop.getProperty("deviceName");
        serverUrl = prop.getProperty("localHostServerUrl");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        switch (platformName.toLowerCase()) {
            case "android":
                setDesiredCapabilitiesForAndroid(desiredCapabilities);
                break;
            case "ios":
                setDesiredCapabilitiesForIOS(desiredCapabilities);
                break;
            default:
                throw new IllegalArgumentException("Invalid platform specified in config.properties");
        }
    }

    protected void setDesiredCapabilitiesForAndroid(DesiredCapabilities desiredCapabilities) throws MalformedURLException {
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "2f5f0d02");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
//        desiredCapabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\user\\IdeaProjects\\PopSeeklAutomation\\src\\main\\app\\app-arm64-v8a-release (2).apk");
        desiredCapabilities.setCapability("appPackage", "com.oneplus.calculator");
        desiredCapabilities.setCapability("appActivity", "com.oneplus.calculator.Calculator");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        driver = new AndroidDriver<>(new URL(serverUrl), desiredCapabilities);
        System.out.println("Application started....");
    }

    protected void setDesiredCapabilitiesForIOS(DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("bundleId", "your.app.bundleId");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
//        desiredCapabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\user\\IdeaProjects\\PopSeeklAutomation\\src\\main\\app\\app-arm64-v8a-release (2).apk");
        desiredCapabilities.setCapability("appPackage", "com.popseekl");
        desiredCapabilities.setCapability("appActivity", "com.popseekl.MainActivity");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        System.out.println("Application started....");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
