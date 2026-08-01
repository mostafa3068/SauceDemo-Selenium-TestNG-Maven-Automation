package mostafa.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Provides a singleton ExtentReports instance for the whole test execution.
 */
public final class ExtentManager {

    private static final String REPORTS_DIRECTORY = "reports";
    private static ExtentReports extent;
    private static String reportPath;

    private ExtentManager() {
        throw new IllegalStateException("Utility class");
    }

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            reportPath = REPORTS_DIRECTORY + "/ExtentReport_" + timestamp + ".html";

            File reportDirectory = new File(REPORTS_DIRECTORY);
            if (!reportDirectory.exists()) {
                reportDirectory.mkdirs();
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("SauceDemo Automation Report");
            sparkReporter.config().setDocumentTitle("SauceDemo Test Results");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Tester", "Mostafa Abdelnasser");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("OS Version", System.getProperty("os.version"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }

    public static String getReportPath() {
        return reportPath;
    }

    public static void setBrowserInfo(String browserName) {
        if (extent != null) {
            extent.setSystemInfo("Browser", browserName);
        }
    }
}
