import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PinterestLogin extends BaseTest{
    public WebDriver driver;

    @BeforeMethod
    public void setup() throws InterruptedException {
       /* WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();*/
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("disable-gpu");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(Link.URL);
    }

    @Test
    public void Test1_AI_Submitted_to_Employer_without_Review() throws InterruptedException {
        System.out.println("ThreadName: " + Thread.currentThread().getName() + Thread.currentThread().getStackTrace()[1].getClassName());

        driver.findElement(By.xpath("//*[@id=\"fullpage-wrapper\"]/div[1]/div/div/div[1]/div/div[2]/div[2]/button/div")).click();
        test.get().pass("Login button pushed");
    }

    @AfterMethod

    public void aftermethod()
    {
        this.driver.quit();
    }
}