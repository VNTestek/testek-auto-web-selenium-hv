package lesson15.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.JsonFormatter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import lesson15.common.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.util.Objects;


public final class ExtentReportManager {

    private static ExtentReports extentReports;

    public static void initReports() {
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();

            String reportPath = System.getProperty("user.dir") + File.separator + "ExtentReports";
            File file = new File(reportPath);
            if(!file.exists()) {file.mkdirs();}
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath + File.separator + "ExtentReport.html");
            JsonFormatter json = new JsonFormatter(reportPath + File.separator + "ExtentReport.json");
            extentReports.attachReporter(json, spark);
            //spark.config().setOfflineMode(true);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Automation Test - version 1.0");
            spark.config().setReportName("Automation Test - version 1.0");
            spark.config().setEncoding("UTF-8");
            spark.viewConfigurer().viewOrder().as(new ViewName[]{
                    ViewName.DASHBOARD,
                    ViewName.TEST,
                    ViewName.CATEGORY,
                    ViewName.DEVICE,
                    ViewName.EXCEPTION,
                    ViewName.AUTHOR,
                    ViewName.LOG
            }).apply();
            extentReports.setSystemInfo("Framework Name", "Selenium  + TestNG + ExtentReport ");
            extentReports.setSystemInfo("Author", "Author");
            extentReports.setSystemInfo("Testing Version", "1.0");
        }
    }

    public static void flushReports() {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
        lesson15.report.ExtentTestManager.unload();
    }

    public static void createTest(String testCaseName) {
        lesson15.report.ExtentTestManager.setExtentTest(extentReports.createTest(testCaseName, null));
    }

    public static void createTest(String testCaseName, String description, String browser) {
        // Apply for Chrome browser. Refer to https://www.w3schools.com/icons
        String ICON_BROWSER_CHROME = "<i class=\"fa fa-chrome\" aria-hidden=\"true\"></i>";
        String testName = ICON_BROWSER_CHROME + " : " + testCaseName + String.format("<br/> <p style='font-size: 0.75em'>%s</p>", description);
        lesson15.report.ExtentTestManager.setExtentTest(extentReports.createTest(testName, null));
    }

    public static void unloadTest() {
        if (Objects.nonNull(lesson15.report.ExtentTestManager.getExtentTest()))
            lesson15.report.ExtentTestManager.unload();
    }

    public static void removeTest(String testCaseName) {
        unloadTest();
        extentReports.removeTest(testCaseName);
    }

    /**
     * Add the screenshot.
     */
    public static void addScreenShot(String message) {
        addScreenShot(Status.INFO, message);
    }

    /**
     * Add the screenshot.
     */
    public static void addScreenShot(Status status, String message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null) {
            try {
                if(DriverManager.getWebDriver()!=null) {
                    String base64Image = "data:image/png;base64," + ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.BASE64);
                    lesson15.report.ExtentTestManager.getExtentTest().log(status, message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
                } else
                    lesson15.report.ExtentTestManager.getExtentTest().log(status, message);
            } catch (Exception exception) {
                lesson15.report.ExtentTestManager.getExtentTest().log(status, message);
                lesson15.report.ExtentTestManager.getExtentTest().log(status, exception);
            }
        }
    }



    public static void addTFSLink(String tfsLink) {
        if (Objects.nonNull(tfsLink) && !tfsLink.isEmpty()) {
            String[] tmp = tfsLink.split(",");
            for (String link : tmp)
                lesson15.report.ExtentTestManager.getExtentTest().info(MarkupHelper.createLabel("This TC has been FAILED, see details at <a href=\"" + "TFS_LINK" + link + "\">TFS Link</a>", ExtentColor.AMBER));
        }
    }

    synchronized public static void addCategory(String cateName) {
        lesson15.report.ExtentTestManager.getExtentTest().assignCategory(cateName);
    }


    synchronized public static void addDevices(String device) {
        lesson15.report.ExtentTestManager.getExtentTest().assignDevice(device.trim().toUpperCase());
    }

    public static void logMessage(String message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null) lesson15.report.ExtentTestManager.getExtentTest().log(Status.INFO, message);
    }

    public static void logMessage(Status status, String message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null) lesson15.report.ExtentTestManager.getExtentTest().log(status, message);
    }

    public static void logMessage(Status status, Object message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null)
            lesson15.report.ExtentTestManager.getExtentTest().log(status, (Throwable) message);
    }

    public static void pass(String message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null) lesson15.report.ExtentTestManager.getExtentTest().pass(message);
    }

    public static void pass(Markup message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null) lesson15.report.ExtentTestManager.getExtentTest().pass(message);
    }

    public static void fail(String message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null) lesson15.report.ExtentTestManager.getExtentTest().fail(message);
    }

    public static void fail(Object message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null) lesson15.report.ExtentTestManager.getExtentTest().fail((String) message);
    }

    public static void fail(Markup message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null) lesson15.report.ExtentTestManager.getExtentTest().fail(message);
    }

    public static void skip(String message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null) lesson15.report.ExtentTestManager.getExtentTest().skip(message);
    }

    public static void skip(Markup message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null) lesson15.report.ExtentTestManager.getExtentTest().skip(message);
    }

    public static void info(Markup message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null) lesson15.report.ExtentTestManager.getExtentTest().info(message);
    }

    public static void info(String message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null) lesson15.report.ExtentTestManager.getExtentTest().info(message);
    }

    public static void warning(String message) {
        if (lesson15.report.ExtentTestManager.getExtentTest() != null) ExtentTestManager.getExtentTest().log(Status.WARNING, message);
    }
}
