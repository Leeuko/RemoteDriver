import java.io.File;
import java.lang.reflect.Method;

import javax.imageio.ImageIO;

import com.aventstack.extentreports.ExtentTest;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class BaseTest extends TestListenerAdapter
{
    public ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();


    public static String capture(WebDriver driver, String screenShotName) throws Exception
    {
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        String dest = System.getProperty("user.dir") + "/ErrorScreenshots/Panel1/" + screenShotName + ".png";
        ImageIO.write(screenshot.getImage(),"PNG",new File(dest));
        return dest;
    }

    public static String capture2(WebDriver driver, String screenShotName) throws Exception
    {
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        String dest = System.getProperty("user.dir") + "/ErrorScreenshots/Panel2/" + screenShotName + ".png";
        ImageIO.write(screenshot.getImage(),"PNG",new File(dest));
        return dest;
    }

    public static String capture3(WebDriver driver, String screenShotName) throws Exception
    {
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        String dest = System.getProperty("user.dir") + "/ErrorScreenshots/Panel3/" + screenShotName + ".png";
        ImageIO.write(screenshot.getImage(),"PNG",new File(dest));
        return dest;
    }

    @BeforeClass
    public synchronized void beforeClass() throws Exception {
        ExtentTest parent = ExtentTestManager.createTest(getClass().getName());
        parentTest.set(parent);
        //throw new Exception("Failed ******* ");
    }

    @BeforeMethod
    public synchronized void beforeMethod(Method method) {
        ExtentTest child = parentTest.get().createNode(method.getName());
        test.set(child);
    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result) {
//		if (result.getStatus() == ITestResult.FAILURE)
//			test.get().log(Status.FAIL,result.getThrowable());
//		else if (result.getStatus() == ITestResult.SKIP)
//			test.get().skip(result.getThrowable());
//		else
//			test.get().pass("Test passed");

        ExtentManager.getExtent().flush();

    }
}
