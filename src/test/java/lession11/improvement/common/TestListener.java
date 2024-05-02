package lession11.improvement.common;


import lession11.pages.LoginPage;
import org.apache.logging.log4j.util.Strings;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

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
    }

    @Override
    public void onFinish(ISuite iSuite) {
        logger.info("== Vincent : Finish TestSuite");
        // Show info finish to trace in CI/CD
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        // TestListener: START TC
        logger.info("== Vincent : START TC "+ getTestName(iTestResult));
        totalTCs++;
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //TestListener: COMPLETED TC: %s - PASS %s
        logger.info(String.format("== Vincent : COMPLETED TC %s: PASS",iTestResult.isSuccess()));
        passedTCs++;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info(String.format("== Vincent : COMPLETED TC %s: FAILED",iTestResult.isSuccess()));
        failedTCs++;
        // Capture failure screen
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.info(String.format("== Vincent : COMPLETED TC %s: SKIPPED",iTestResult.isSuccess()));
        skippedTCs++;
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }
}
