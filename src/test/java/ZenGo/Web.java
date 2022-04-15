package ZenGo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;


public class Web extends Base{

    WebDriver driver;

    @BeforeClass
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void end() {
        driver.quit();
    }

    @Test
    public void verifyTitle() {
        Assert.assertEquals(driver.getTitle(), expTitle, "Test 01: This is not main page title");
        System.out.println("Test 01: Main page title is displayed correctly");
    }

    @Test(priority = 1)
    public void verifyQR() {
        boolean qrCode = driver.findElement(By.xpath("//img[@width='240']")).isDisplayed();
        Assert.assertTrue(qrCode, "Test 02: Main page element is not displayed");
        System.out.println("Test 02: Main page QR code is displayed");
    }

    @Test(priority = 2)
    public void verifyRedirect() {
        driver.findElement(By.xpath("//a[@class='dropdown']")).click();
        driver.findElement(By.xpath("//a[@rel='nofollow noopener']")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains(expUrl), "Test 03: Expected URL is not correct");
        System.out.println("Test 03: Redirected correctly");
//        Assert.assertEquals(driver.getCurrentUrl(), expUrl, "Test 03: Expected URL is not correct");
//        System.out.println("Test 03: Redirected correctly");
    }

    @Test(priority = 3)
    public void verifyLogo() throws InterruptedException{
        WebElement logo = driver.findElement(By.xpath("//*[@id='app']/div/div/img[1]"));
        Thread.sleep(5000);
        boolean t = logo.isDisplayed();
        if (t) {
            System.out.println("Test 04: Logo is displayed successfully");
        } else {
            System.out.println("Test 04: Logo is not displayed");
        }
    }
    @Test(priority = 4)
    public void verifyIcon(){
        WebElement icon = driver.findElement(By.xpath("//*[@id='app']/div/div/img[2]"));
        boolean p = icon.isDisplayed();
        if (p) {
            System.out.println("Test 05: Icon is displayed successfully");
        } else {
            System.out.println("Test 05: Icon is not displayed");
        }
    }
}