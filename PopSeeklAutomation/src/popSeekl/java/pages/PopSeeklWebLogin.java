package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PopSeeklWebLogin {
    public static WebDriver driver;
    public String userName = "indiana12";

    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://popseekl-a27k.onrender.com/login");
    }

    public void assertTitle() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Log in | Popseekl";
        assert actualTitle.equals(expectedTitle) : "Title assertion failed";
        System.out.println("Title assertion passed!");
    }

    public void logIntoApplication() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@class='CookieConsent_continueBtn__I9CRs']/Span")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(userName);
//        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("12345678");
//        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@class=" +
                "'LoginForm_loginClass__zc4r7 Button_primaryButton__1r13o']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[contains(@class, 'Button_inlineTextButtonRoot__1Uh2a')]//*[@class='EmailVerificationForm_modalIcon__2Nrxt']")).click();
//        Thread.sleep(10000);
        driver.findElement(By.xpath("(//div[@class='TopbarDesktop_toggleBtn__3vysr']/span)[1]")).click();
        String actualTitle = driver.findElement(By.xpath("//a[@class='TopbarDesktop_avatarSection__3JZnR']/h2")).getText();
        String expectedTitle = userName;
        assert actualTitle.equals(expectedTitle) : "Username assertion failed";
        System.out.println("Username assertion passed! Actual user is :- " + actualTitle);
    }

    public void quitBrowser() {
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        PopSeeklWebLogin popSeeklLogin = new PopSeeklWebLogin();
        popSeeklLogin.setupBrowser();
        popSeeklLogin.assertTitle();
        popSeeklLogin.logIntoApplication();
        popSeeklLogin.quitBrowser();
    }
}
