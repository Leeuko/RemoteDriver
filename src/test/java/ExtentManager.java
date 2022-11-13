import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ExtentManager {
    public static Properties prop = new Properties();

    public static ExtentReports extent;
    public static String filePath = System.getProperty("user.dir") + "/target/ExtentReports.html";

    public ExtentManager() throws IOException {
        prop.load(new FileInputStream("config.properties"));
    }

    public synchronized static ExtentReports getExtent() {
        if (extent == null) {
            extent = new ExtentReports();
            extent.attachReporter(getHtmlReporter());
/*			if (System.getenv("ExtentX").equalsIgnoreCase("true")) {
        extent.attachReporter(getExtentXReporter());
			}*/
            extent.setSystemInfo("os.name", "Windows 10");
            extent.setSystemInfo("java.version", "1.8.0_144");
            extent.setSystemInfo("User", "Liudmila Cosetova");

        }

        return extent;
    }

    private static ExtentHtmlReporter getHtmlReporter() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);

        // make the charts visible on report open
        //  htmlReporter.config().setChartVisibilityOnOpen(true);

        // report title
        //String documentTitle = prop.getProperty("documentTitle", "aventstack - Extent");
        htmlReporter.config().setDocumentTitle("AppiumTestDistribution");
        htmlReporter.config().setReportName("ExtentReports");
        // htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        return htmlReporter;
    }

}
