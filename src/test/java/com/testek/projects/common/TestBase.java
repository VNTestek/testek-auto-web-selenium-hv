package com.testek.projects.common;

import com.testek.consts.FrameConst;
import com.testek.datadriven.BaseModel;
import com.testek.driver.BrowserFactory;
import com.testek.driver.DriverManager;
import com.testek.projects.page.PageManagement;
import com.testek.projects.page.pages.LoginPage;
import com.testek.report.ExtentReportManager;
import com.testek.utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static com.testek.consts.FrameConst.ProjectConfig.*;


@Listeners({TestListener.class})
public class TestBase {
    public TestBase() {
        PropertiesUtils.getInstance().loadAllProperties();
    }

    @Parameters({"browser"})
    @BeforeSuite
    public void beforeSuite(@Optional("chrome") String browser) {
        LogUtils.info("TestBase: beforeSuite");
        new File(FrameConst.PROJECT_PATH + "ExportData").deleteOnExit();
        BrowserFactory.initWebDriver(browser);
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest(ITestContext context) {
        String testName = context.getName();
        ExtentReportManager.initReports(testName, APPLICATION_VERSION, APPLICATION_NAME, APPLICATION_ENV, false);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(ITestContext context) {

    }

    @BeforeMethod(alwaysRun = true)
    public void addInvocation(ITestResult tr) {
        tr.setAttribute("invocation", tr.getMethod().getParameterInvocationCount());
        AtomicReference<String> dataId = new AtomicReference<>(tr.getTestName() != null ? tr.getTestName() : tr.getMethod().getConstructorOrMethod().getName());
        if (tr.getParameters().length > 0) {
            Arrays.stream(tr.getParameters()).forEach(o -> {
                try {
                    BaseModel model = (BaseModel) o;
                    String temp = model.getTestId().getValue();
                    if (!temp.isEmpty()) dataId.set(temp);
                } catch (Exception e) {
                    LogUtils.error("VException: " + e.getMessage());
                }
            });
        }
        tr.setAttribute("dataId", dataId.get());

    }

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        if (Objects.isNull(DriverManager.getDriver())) {
            BrowserFactory.initWebDriver(browser);
        }

        /* Skip login for LoginTest */
        if (!isLoginTest()) {
            LoginPage loginPage = PageManagement.accessWebPage();
//            loginPage.login("user_com_role", "aA12345678@");
            loginPage.login("admin_com_role", "aA12345678@");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void closeDriver() {
        LogUtils.info("TestBase: Close Driver ");
        DriverManager.quitDriver();
    }

    private boolean isLoginTest() {
        String testClass = this.getClass().getName();
        String[] matches = new String[]{"Login"};
        for (String m : matches) {
            if (testClass.contains(m)) {
                return true;
            }
        }
        return false;
    }

}
