package com.testek.study.lesson09;


import org.apache.logging.log4j.util.Strings;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

/*
 * Purpose: Implement the testing listener
 */
public class TestListener implements ITestListener, ISuiteListener {
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
        // On Start Suite
        // Show suite info to trace in CI/CD or init report
    }

    @Override
    public void onFinish(ISuite iSuite) {
        // On Finish Suite
        // Show info finish to trace in CI/CD
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        // TestListener: START TC
        totalTCs++;
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //TestListener: COMPLETED TC: %s - PASS %s
        passedTCs++;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        // TestListener: COMPLETED TC: %s - FAIL %s
        failedTCs++;
        // Capture failure screen
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        // TestListener: COMPLETED TC: %s - SKIP %s
        skippedTCs++;
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }
}
