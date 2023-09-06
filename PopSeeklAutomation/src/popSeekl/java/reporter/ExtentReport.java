package reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import static tests.BaseTest.driver;

/*
  This class will generate ExtentReport on every run and will delete the older one.
 */
public final class ExtentReport {
    private static ExtentReports extent;
    public static ExtentTest test;
    public static String timeStamp;

    private ExtentReport(){
    }

    public static ExtentReports initReports() throws IOException {
        timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm a").format(new Date());
        ExtentSparkReporter reporter = new ExtentSparkReporter("target//Extent-Reports//report_" + timeStamp + ".html").viewConfigurer().viewOrder()
                .as(new ViewName[]{ViewName.DASHBOARD, ViewName.EXCEPTION, ViewName.TEST, ViewName.CATEGORY, ViewName.LOG, ViewName.AUTHOR})
                .apply();
        final File CONF = new File("ExtentConfig.xml");
        reporter.loadXMLConfig(CONF);
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        reporter.config().setReportName("Appium-HtmlTestReport");
        extent.setSystemInfo("Author", "Regression");
        return extent;
    }

    public static void flushReports() throws IOException {
        extent.flush();
        Desktop.getDesktop().browse(new File("target//Extent-Reports//report_" + timeStamp + ".html").toURI());
    }

    public static void createTest(String testCaseName){
        test = extent.createTest(testCaseName);
        try {
            // Capture screenshot
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Add screenshot to ExtentReport
            test.pass("Step description", MediaEntityBuilder.createScreenCaptureFromBase64String(
                    Base64.getEncoder().encodeToString(screenshotBytes)).build());
        } catch (Exception e) {
            // Add error details to ExtentReport
            test.fail("Step description", MediaEntityBuilder.createScreenCaptureFromBase64String("base64string").build());
            // Handle exception
        }
    }
}