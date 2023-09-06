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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    /*
    username -  accessibility id Username, email or mobile number
            //android.view.View[@content-desc="Username, email or mobile number"]
password - Password
           //android.view.View[@content-desc="Password"]
login - //android.widget.Button[@content-desc="Log in"]
        //android.view.View[@content-desc="Log in"]
    */

    public void loginIntoInstagram() throws InterruptedException {
            // Wait for a specific condition instead of using Thread.sleep
            WebDriverWait wait = new WebDriverWait(driver, 10);

            // Find elements using more specific locators and reduce redundant code
            WebElement enterUsername = wait.until(ExpectedConditions.elementToBeClickable(By.id("com.instagram.android:id/login_username")));
            enterUsername.sendKeys("test");

            WebElement enterPassword = driver.findElement(By.id("com.instagram.android:id/password"));
            enterPassword.sendKeys("123e33e");

            WebElement clickLogin = driver.findElement(By.id("com.instagram.android:id/next_button"));
            clickLogin.click();

/*
        Thread.sleep(10000);
        WebElement enterUsername = driver.findElementByAccessibilityId("Username, email or mobile number");
        enterUsername.click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//android.view.View[@content-desc='Username, email or mobile number']")).sendKeys("test");
        WebElement enterPassword = driver.findElement(MobileBy.AccessibilityId("Password"));
        enterPassword.click();
        enterPassword.sendKeys("123e33e");
        WebElement clickLogin = driver.findElement(By.xpath("//android.widget.Button[@content-desc='Log in']"));
        clickLogin.click();
*/
    }
}
