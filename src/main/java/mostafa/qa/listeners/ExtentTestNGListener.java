package mostafa.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import mostafa.qa.core.BaseTest;
import mostafa.qa.utils.ExtentManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestNG listener that writes ExtentReports results and captures screenshots on failures.
 */
public class ExtentTestNGListener implements ITestListener {

    private static final String CI_PROPERTY = "ci.environment";
    private static final ExtentReports EXTENT = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        TEST.set(EXTENT.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TEST.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        TEST.get().log(Status.FAIL, "Test failed: " + result.getThrowable());
        captureScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TEST.get().log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        EXTENT.flush();
        String reportPath = ExtentManager.getReportPath();
        System.out.println("Extent report generated at: " + reportPath);

        if (shouldOpenReport()) {
            openReport(reportPath);
        }
    }

    private void captureScreenshot(ITestResult result) {
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        if (driver == null) {
            return;
        }

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        Path screenshotDirectory = Path.of("reports", "screenshots");
        Path screenshotPath = screenshotDirectory.resolve(result.getMethod().getMethodName() + "_" + timestamp + ".png");

        try {
            Files.createDirectories(screenshotDirectory);
            Files.copy(screenshot.toPath(), screenshotPath);
            TEST.get().addScreenCaptureFromPath(screenshotPath.toString());
        } catch (IOException e) {
            TEST.get().log(Status.INFO, "Screenshot could not be saved: " + e.getMessage());
        }
    }

    private boolean shouldOpenReport() {
        boolean ciPropertyEnabled = Boolean.parseBoolean(System.getProperty(CI_PROPERTY, "false"));
        boolean ciEnvironmentDetected = System.getenv("CI") != null || System.getenv("JENKINS_HOME") != null;
        return !ciPropertyEnabled && !ciEnvironmentDetected && Desktop.isDesktopSupported();
    }

    private void openReport(String reportPath) {
        try {
            File reportFile = new File(reportPath);
            if (reportFile.exists()) {
                Desktop.getDesktop().browse(reportFile.toURI());
            }
        } catch (IOException e) {
            System.out.println("Extent report could not be opened automatically: " + e.getMessage());
        }
    }
}
