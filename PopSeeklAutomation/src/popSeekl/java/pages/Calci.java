package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reporter.ExtentLogger;
import tests.BaseTest;

import java.io.File;
import java.io.IOException;

public class Calci extends BaseTest {
    public Calci(AppiumDriver<WebElement> driver){
        PageFactory.initElements(driver,this);
    }

    public void multiplyTwoDigits() throws InterruptedException, IOException {
        System.out.println("Test script started....");
        ExtentLogger.info("Desired capabilities - " + driver);
        Thread.sleep(5000);
        MobileElement clickResult = (MobileElement) driver.findElement(By.id("com.oneplus.calculator:id/result"));
        clickResult.click();
        MobileElement clickDigit_9 = (MobileElement) driver.findElement(By.id("com.oneplus.calculator:id/digit_9"));
        clickDigit_9.click();
        WebElement clickMultiplyBtn = driver.findElement(By.id("com.oneplus.calculator:id/op_mul"));
        clickMultiplyBtn.click();
        WebElement clickDigit_8 = driver.findElement(By.id("com.oneplus.calculator:id/digit_8"));
        clickDigit_8.click();
        WebElement clickEqualsToBtn = driver.findElement(MobileBy.AccessibilityId("equals"));
        clickEqualsToBtn.click();
        WebElement fetchResult = driver.findElement(By.id("com.oneplus.calculator:id/result"));
        int result = Integer.parseInt(fetchResult.getText());
        Assert.assertEquals(result, 72);

        // Take a screenshot
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Defining the destination path
        String destinationPath = "target//Screenshots//screenshot.png";
        FileUtils.copyFile(screenshotFile, new File(destinationPath));
        ExtentLogger.info("Result - " + result);
    }
}
