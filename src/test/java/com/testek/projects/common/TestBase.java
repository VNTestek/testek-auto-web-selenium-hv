package com.testek.projects.common;

import com.testek.consts.FrameConst;
import com.testek.database.config.DatabaseFactory;
import com.testek.datadriven.BaseModel;
import com.testek.driver.BrowserFactory;
import com.testek.driver.DriverManager;
import com.testek.projects.pages.PageManagement;
import com.testek.projects.pages.pages.LoginPage;
import com.testek.report.ExtentReportManager;
import com.testek.utils.configloader.CaptureUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static com.testek.consts.FrameConst.AppConfig.*;
import static com.testek.consts.FrameConst.ExecuteConfig.EXE_ENV;
import static com.testek.consts.FrameConst.PROJECT_NAME;
import static com.testek.report.ReportConfig.EXECUTED_TESTCASE_NAME;
import static com.testek.report.ReportConfig.VIDEO_RECORD;


@Listeners({TestListener.class})
@Slf4j
public class TestBase {
    public TestBase() {
        PropertiesUtils.getInstance().loadAllProperties();
    }


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        log.info("init beforeSuite");
        // Connect databases
        if (FrameConst.DATABASE_CONNECT_CONFIG) {
            FrameConst.DATABASE_CONNECT_LIST.forEach(databaseInfo ->
                    DatabaseFactory.initDatabaseConnection(databaseInfo.getType(), databaseInfo.getName(), databaseInfo.getUserName(), databaseInfo.getPassword(), databaseInfo.getUrl()));
        }
    }

    @Parameters({"browser", "userName", "password"})
    @BeforeTest(alwaysRun = true)
    public void beforeTest(@Optional("chrome") String browser, @Optional() String userName, @Optional() String password, ITestContext context) {
        BrowserFactory.initWebDriver(browser);

        // Update user credentials if provided
        if (!Objects.isNull(userName)) {
            USER_NAME = userName.trim();
        }
        if (!Objects.isNull(password)) {
            PASSWORD = password.trim();
        }

        String testName = context.getName();
        ExtentReportManager.initReports(testName, APP_VERSION, PROJECT_NAME, EXE_ENV, false);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(ITestContext context) {
        log.info("TestBase: afterTest");
//        ExtentReportManager.flushReports();

        // Clear the driver after test execution
        DriverManager.quitDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestResult tr) {
        if (VIDEO_RECORD) {
            String methodName = tr.getMethod().getMethodName();
            String videoName = String.format("%s_%s_%s", methodName, tr.getAttribute("dataId"), tr.getAttribute("invocation"));
            CaptureUtils.startRecord(videoName);
        }

        addInvocation(tr);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult tr) {
        if (VIDEO_RECORD) {
            CaptureUtils.stopRecord();
        }

        EXECUTED_TESTCASE_NAME = Strings.EMPTY;
    }

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
                    log.error("VException: {}", e.getMessage());
                }
            });
        }
        tr.setAttribute("dataId", dataId.get());

    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        if (Objects.isNull(DriverManager.getDriver())) {
            BrowserFactory.initWebDriver(FrameConst.ExecuteConfig.EXE_BROWSER);
        }

        /* Skip login for LoginTest */
        if (!isLoginTest()) {
            LoginPage loginPage = PageManagement.accessWebPage();
            loginPage.login(USER_NAME, PASSWORD);
        } else DriverManager.getDriver().navigate().refresh();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        log.info("TestBase: afterClass");
        // Clear the driver after class execution
        DriverManager.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        log.info("TestBase: Close Driver ");
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
