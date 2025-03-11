package com.testek.projects.common;

import com.aventstack.extentreports.Status;
import com.testek.annotations.FrameAnnotation;
import com.testek.annotations.TFSLink;
import com.testek.consts.AuthorType;
import com.testek.driver.DriverManager;
import com.testek.listeners.Retry;
import com.testek.report.ExtentReportManager;
import com.testek.report.ExtentTestManager;
import com.testek.utils.IconUtils;
import com.testek.utils.LogUtils;
import com.testek.utils.configloader.CaptureUtils;
import org.apache.logging.log4j.util.Strings;
import org.testng.*;

import java.util.Map;
import java.util.Objects;

import static com.testek.consts.FrameConst.CategoryType;
import static com.testek.consts.FrameConst.ProjectConfig.BROWSER;
import static com.testek.consts.FrameConst.ReportConst.*;

/*
 * Purpose: Implement the testing listener
 * Datetime:
 */
public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener, IConfigurationListener {
    private final String separateItem = "\n---------------------------------------------------------------";

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : Strings.EMPTY;
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // System.out.println("beforeInvocation ------" + method.getTestMethod().getMethodName() + " -- " + testResult.getTestName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        //System.out.println("afterInvocation ------" + method.getTestMethod().getMethodName() + " -- " + testResult.getTestName());
    }

    @Override
    public void onStart(ISuite iSuite) {
        LogUtils.info(String.format("%s\nTestListener: TESTING FOR TEST SUITE: %s%s", separateItem, iSuite.getName(), separateItem));
        iSuite.setAttribute("WebDriver", DriverManager.getDriver());
        //Starting record video
        if (VIDEO_RECORD) {
            CaptureUtils.startRecord(iSuite.getName());
        }
        ExtentReportManager.initReports(null, null, null, null, false);
    }

    @Override
    public void onFinish(ISuite iSuite) {
        LogUtils.info(String.format("\nTestListener: FINISH TESTING FOR TEST SUITE: %s %s", iSuite.getName(), separateItem));
        ExtentReportManager.flushReports();
        //Stop recording the video
        if (VIDEO_RECORD) {
            CaptureUtils.stopRecord();
        }
    }

    private Map<String, Integer> getResultTestsCount(ISuite suite) {
        int passedCount = 0, failedCount = 0, skippedCount = 0;
        Map<String, ISuiteResult> results = suite.getResults();
        for (ISuiteResult result : results.values()) {
            ITestContext context = result.getTestContext();
            passedCount += context.getPassedTests().getAllResults().size();
        }
        return null;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LogUtils.info(String.format("%s\nTestListener: START TC:  %s", separateItem, getTestName(iTestResult)));

        ExtentTestManager.unload();
        addTestToExtentReport(iTestResult);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogUtils.info(String.format("\nTestListener: COMPLETED TC: %s - PASS %s", getTestName(iTestResult), separateItem));
        updateRetryTestName(iTestResult);

        if (SCREENSHOT_PASSED_STEPS) {
            CaptureUtils.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }

        //ExtentReports log operation for passed tests.
        ExtentReportManager.logMessage(Status.PASS, "Test case: " + getTestName(iTestResult) + " - PASS");
        ExtentReportManager.unloadTest();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LogUtils.info(String.format("\nTestListener: COMPLETED TC: %s - FAIL %s", getTestName(iTestResult), separateItem));
        updateRetryTestName(iTestResult);

        if (SCREENSHOT_FAILED_STEPS) {
            CaptureUtils.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }
        LogUtils.error("FAILED !! Screenshot for test case: " + getTestName(iTestResult));
        if (ExtentTestManager.getExtentTest() == null) {
            addTestToExtentReport(iTestResult);
        }

        //Extent report screenshot file and log
        ExtentReportManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
        ExtentReportManager.logMessage(Status.FAIL, "Test case: " + getTestName(iTestResult) + " - FAIL");
        if (Objects.nonNull(iTestResult.getThrowable())) {
            LogUtils.error(iTestResult.getThrowable());
            ExtentReportManager.logMessage(Status.FAIL, iTestResult.getThrowable());
        }
        ExtentReportManager.unloadTest();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LogUtils.info(String.format("\nTestListener: COMPLETED TC: %s - SKIP %s", getTestName(iTestResult), separateItem));
        updateRetryTestName(iTestResult);

        if (SCREENSHOT_SKIPPED_STEPS) {
            CaptureUtils.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }

        if (ExtentTestManager.getExtentTest() == null) {
            addTestToExtentReport(iTestResult);
        }

        ExtentReportManager.logMessage(Status.SKIP, "Test case: " + getTestName(iTestResult) + " - SKIP");
        ExtentReportManager.unloadTest();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        if (ExtentTestManager.getExtentTest() == null) {
            addTestToExtentReport(iTestResult);
        }
        LogUtils.error("Test failed but it is in defined success ratio " + getTestName(iTestResult));
        ExtentReportManager.logMessage("Test failed but it is in defined success ratio " + getTestName(iTestResult));
        ExtentReportManager.unloadTest();
    }

    public AuthorType[] getAuthorType(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameAnnotation.class) == null) {
            return null;
        }
        return iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameAnnotation.class).author();
    }

    public CategoryType[] getCategoryType(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameAnnotation.class) == null) {
            return null;
        }
        return iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameAnnotation.class).category();
    }

    @Override
    public void onConfigurationSuccess(ITestResult tr) {
        String className = tr.getTestClass().getName();
        ExtentReportManager.logMessage(Status.WARNING, "Configuration: " + getTestName(tr) + " - PASS");
        ExtentReportManager.unloadTest();
        ExtentReportManager.removeTest(tr.getName() + " " + className.substring(className.lastIndexOf(".") + 1));
    }

    @Override
    public void onConfigurationFailure(ITestResult tr) {
        ExtentReportManager.addScreenShot(Status.WARNING, getTestName(tr));
        ExtentReportManager.logMessage(Status.WARNING, "Configuration: " + getTestName(tr) + " - FAIL");
        if (Objects.nonNull(tr.getThrowable())) {
            LogUtils.error(tr.getThrowable());
            ExtentReportManager.logMessage(Status.WARNING, tr.getThrowable());
        }
        ExtentReportManager.unloadTest();
    }

    @Override
    public void onConfigurationSkip(ITestResult tr) {
        ExtentReportManager.logMessage(Status.WARNING, "Configuration: " + getTestName(tr) + " - SKIP");
        ExtentReportManager.unloadTest();
    }

    @Override
    public void beforeConfiguration(ITestResult tr) {
        String className = tr.getTestClass().getName();
        ExtentReportManager.createTest(tr.getName() + " " + className.substring(className.lastIndexOf(".") + 1));
        ExtentReportManager.logMessage(Status.WARNING, "START - Configuration: " + getTestName(tr));
    }

    private void updateRetryTestName(ITestResult iTestResult) {
        String oldName = getTestName(iTestResult);
        if (Objects.nonNull(ExtentTestManager.getExtentTest())) {
            String extendName = ExtentTestManager.getExtentTest().getModel().getName();
            String newName = extendName.replace(oldName, iTestResult.getName());
            ExtentTestManager.getExtentTest().getModel().setName(newName);
        }
    }

    private int increaseTestNum(int current) {
        int retryStatus = Retry.getRetryStatus();
        if (Objects.equals(retryStatus, ITestResult.CREATED) || Objects.equals(retryStatus, ITestResult.SUCCESS))
            return current + 1;
        return current;
    }

    private void addTestToExtentReport(ITestResult iTestResult) {
        String browser = iTestResult.getTestContext().getCurrentXmlTest().getParameter("browser");
        if (Objects.isNull(browser)) browser = BROWSER.toUpperCase();
        else browser = browser.trim().toUpperCase();

        AuthorType[] author = getAuthorType(iTestResult);
        String des = (author.length > 0 ? (author[0] + " - ") : Strings.EMPTY) + getTestDescription(iTestResult);
        iTestResult.setAttribute("author", author.length > 0 ? author[0].toString() : "");


        if (iTestResult.getAttributeNames().contains("invocation")) {
            String dataId = iTestResult.getAttributeNames().contains("dataId") ? iTestResult.getAttribute("dataId").toString() : iTestResult.getName();
            des = String.format("%s </br> ID: %s - Invocation %s", des, dataId, iTestResult.getAttribute("invocation"));
        }

        ExtentReportManager.createTest(iTestResult.getName(), des, browser);
        ExtentReportManager.addAuthors(author);
        String nameTestClass = iTestResult.getTestClass().getName();
        ExtentReportManager.addCategory(nameTestClass.substring(nameTestClass.lastIndexOf(".") + 1));
        ExtentReportManager.addDevices(browser);
        ExtentReportManager.addTFSLink(getTFSLink(iTestResult));
        ExtentReportManager.info(BOLD_START + IconUtils.getOSIcon() + " " + IconUtils.getOSInfo() + BOLD_END);
    }

    public String getTFSLink(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TFSLink.class) == null) {
            return null;
        }
        return iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TFSLink.class).value();
    }
}
