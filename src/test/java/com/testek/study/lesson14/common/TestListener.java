package com.testek.study.lesson14.common;


import com.aventstack.extentreports.Status;
import com.testek.study.lesson14.report.ExtentReportManager;
import com.testek.study.lesson14.report.ExtentTestManager;
import com.testek.study.lesson14.report.Log;
import com.testek.study.lesson14.pages.LoginPage;
import org.apache.logging.log4j.util.Strings;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;
import java.util.logging.Logger;

/*
 * Purpose: Implement the testing listener
 */
public class TestListener implements ITestListener, ISuiteListener {
    Logger logger = Logger.getLogger(LoginPage.class.getName());
    int totalTCs = 0, passedTCs = 0, skippedTCs = 0, failedTCs = 0;

    /**
     * Get test case name
     */
    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }


    /**
     * Get test case description
     */
    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : Strings.EMPTY;
    }

    @Override
    public void onStart(ISuite iSuite) {
        logger.info("== Vincent : Start TestSuite");
        // On Start Suite
        // Show suite info to trace in CI/CD or init report
        ExtentReportManager.initReports();
    }

    @Override
    public void onFinish(ISuite iSuite) {
        logger.info("== Vincent : Finish TestSuite");
        // Show info finish to trace in CI/CD
        ExtentReportManager.flushReports();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        // TestListener: START TC
        logger.info("== Vincent : START TC "+ getTestName(iTestResult));
        totalTCs++;

        ExtentTestManager.unload();
        addTestToExtentReport(iTestResult);

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //TestListener: COMPLETED TC: %s - PASS %s
        logger.info(String.format("== Vincent : COMPLETED TC %s: PASS",iTestResult.isSuccess()));
        passedTCs++;

        ExtentReportManager.logMessage(Status.PASS, "Test case: " + getTestName(iTestResult) + " - PASS");
        ExtentReportManager.unloadTest();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info(String.format("== Vincent : COMPLETED TC %s: FAILED",iTestResult.isSuccess()));
        failedTCs++;
        // Capture failure screen
        if (ExtentTestManager.getExtentTest() == null) {
            addTestToExtentReport(iTestResult);
        }

        //Extent report screenshot file and log
        ExtentReportManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
        ExtentReportManager.logMessage(Status.FAIL, "Test case: " + getTestName(iTestResult) + " - FAIL");
        if (Objects.nonNull(iTestResult.getThrowable())) {
            Log.error(iTestResult.getThrowable());
            ExtentReportManager.logMessage(Status.FAIL, iTestResult.getThrowable());
        }
        ExtentReportManager.unloadTest();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.info(String.format("== Vincent : COMPLETED TC %s: SKIPPED",iTestResult.isSuccess()));
        skippedTCs++;

        if (ExtentTestManager.getExtentTest() == null) {
            addTestToExtentReport(iTestResult);
        }

        ExtentReportManager.logMessage(Status.SKIP, "Test case: " + getTestName(iTestResult) + " - SKIP");
        ExtentReportManager.unloadTest();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    private void addTestToExtentReport(ITestResult iTestResult) {
        String browser = iTestResult.getTestContext().getCurrentXmlTest().getParameter("browser");
        if (Objects.isNull(browser)) browser = "CHROME";
        else browser = browser.trim().toUpperCase();

        var des = getTestDescription(iTestResult);
        if  (iTestResult.getAttributeNames().contains("invocation"))
            des = des + " - Invocation " + iTestResult.getAttribute("invocation");
        ExtentReportManager.createTest(iTestResult.getName(), des, browser);
        String nameTestClass = iTestResult.getTestClass().getName();
//        ExtentReportManager.addCategory(nameTestClass.substring(nameTestClass.lastIndexOf(".") + 1));
//        ExtentReportManager.addDevices(browser);
        ExtentReportManager.info( getOSIcon() + " " + getOSInfo() );
    }


    public String getOSIcon() {

        String operationSystem = getOSInfo().toLowerCase();
        if (operationSystem.contains("win")) {
            return "<i class='fa fa-windows' ></i>";
        } else if (operationSystem.contains("nix") || operationSystem.contains("nux") || operationSystem.contains("aix")) {
            return "<i class='fa fa-linux' ></i>";
        } else if (operationSystem.contains("mac")) {
            return "<i class='fa fa-apple' ></i>";
        }
        return operationSystem;
    }

    public String getOSInfo() {
        return System.getProperty("os.name").replace(" ", " ");
    }


}
