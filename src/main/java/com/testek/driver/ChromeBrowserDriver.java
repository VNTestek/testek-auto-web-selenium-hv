package com.testek.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

import static com.testek.consts.FrameConst.ProjectConfig.HEADLESS;

public class ChromeBrowserDriver extends BrowserDriver {
    @Override
    public WebDriver createDriver(boolean... isLoadings) {
        return new ChromeDriver((ChromeOptions) getOptions(isLoadings));
    }

    @Override
    public MutableCapabilities getOptions(boolean... isLoadings) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        //chromeOptions.addArguments("window-size=1940,1050");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setHeadless(Boolean.parseBoolean(HEADLESS));
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        chromeOptions.setCapability("goog:loggingPrefs", logPrefs);
        if (isLoadings.length > 0 && isLoadings[0])
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

        /* Using for skip loading image in background */
        // chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return chromeOptions;
    }
}
