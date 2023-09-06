package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PopSeeklWebLogin {
    private WebDriver driver;
    private final String userName = "indiana12";

    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://popseekl-a27k.onrender.com/login");
    }

    public void assertTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assert actualTitle.equals(expectedTitle) : "Title assertion failed";
        System.out.println("Title assertion passed!");
    }

    public void logIntoApplication() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("button.CookieConsent_continueBtn__I9CRs > Span")).click();
        driver.findElement(By.name("email")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.cssSelector("button.LoginForm_loginClass__zc4r7.Button_primaryButton__1r13o")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("button.Button_inlineTextButtonRoot__1Uh2a > *")).click();
        driver.findElement(By.cssSelector("div.TopbarDesktop_toggleBtn__3vysr > span")).click();
        String actualTitle = driver.findElement(By.cssSelector("a.TopbarDesktop_avatarSection__3JZnR > h2")).getText();
        assert actualTitle.equals(userName) : "Username assertion failed";
        System.out.println("Username assertion passed! Actual user is: " + actualTitle);
    }

    public void quitBrowser() {
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        PopSeeklWebLogin popSeeklLogin = new PopSeeklWebLogin();
        popSeeklLogin.setupBrowser();
        popSeeklLogin.assertTitle("Log in | Popseekl");
        popSeeklLogin.logIntoApplication();
        popSeeklLogin.quitBrowser();
    }
}
