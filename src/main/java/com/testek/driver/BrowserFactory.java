package com.testek.driver;

import com.testek.consts.FrameConst;
import com.testek.exceptions.TargetNotValidException;
import com.testek.utils.LogUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.net.URL;
import java.util.EnumMap;

import static com.testek.consts.FrameConst.ProjectConfig.*;

/**
 * Browser Factory, you can create the driver and get the options.
 */
public class BrowserFactory {
    final static  EnumMap<FrameConst.Browser, BrowserDriver> browserDriverMap;
    static {
        browserDriverMap = new EnumMap<>(FrameConst.Browser.class);
        browserDriverMap.put(FrameConst.Browser.CHROME, new ChromeBrowserDriver());
        browserDriverMap.put(FrameConst.Browser.EDGE, new EdgeBrowserDriver());
        browserDriverMap.put(FrameConst.Browser.FIREFOX, new FirefoxBrowserDriver());
        browserDriverMap.put(FrameConst.Browser.SAFARI, new SafariBrowserDriver());
    }

    private BrowserFactory() {}

    /**
     * Create the Selenium Web Driver with the specific browser depending on the target can be LOCAL or REMOTE
     * - LOCAL: The browser will be executed in the local machine with specific browser
     * - REMOTE: The browser will be executed with multiple browsers in the remote machine
     *
     * @param browser : The browser name
     */
    public static void initWebDriver(String browser, boolean... isLoadings) {
        WebDriver webdriver;
        try {
            FrameConst.Browser browserType = FrameConst.Browser.valueOf(browser.toUpperCase());
            FrameConst.Target target = FrameConst.Target.valueOf(TARGET.toUpperCase());

            /* Init the browser driver */
            BrowserDriver browserDriver = browserDriverMap.get(browserType);
            switch (target) {
                case LOCAL:
                    webdriver = browserDriver.createDriver(isLoadings);
                    break;
                case REMOTE:
                    webdriver = initRemoteWebDriver(browserDriver.getOptions(isLoadings));
                    break;
                default:
                    throw new TargetNotValidException(target.toString());
            }
        } catch (Exception e) {
            LogUtils.error("Browser|Target not supported: " + e.getMessage());
            throw new IllegalArgumentException("Browser|Target not supported: " + e.getMessage());
        }

        /* Update the WebDriverManager with the current WebDriver */
        webdriver = ThreadGuard.protect(webdriver);
        DriverManager.setDriver(webdriver);
    }

    /**
     * Create the Selenium RemoteWebDriver for Remote instances
     *
     * @param capability : The browser capabilities
     * @return The Selenium RemoteWebDriver
     */
    private static RemoteWebDriver initRemoteWebDriver(MutableCapabilities capability) {
        RemoteWebDriver remoteWebDriver = null;
        try {
            String remoteURL = String.format("http://%s:%s/wd/hub", REMOTE_URL, REMOTE_PORT);

            remoteWebDriver = new RemoteWebDriver(new URL(remoteURL), capability);
            remoteWebDriver.setFileDetector(new LocalFileDetector());
        } catch (Exception e) {
            LogUtils.error("Remote URL is invalid or Remote Port is not available");
            LogUtils.error(String.format("Browser: %s", capability.getBrowserName()), e);
            throw new IllegalArgumentException("Browser|Target is not available: " + e.getMessage());
        }
        return remoteWebDriver;
    }

}
