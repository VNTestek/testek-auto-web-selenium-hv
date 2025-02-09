package com.testek.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static com.testek.consts.FrameConst.ProjectConfig.HEADLESS;

public class FirefoxBrowserDriver extends BrowserDriver {
    @Override
    public WebDriver createDriver(boolean... isLoadings) {
        return new FirefoxDriver((FirefoxOptions) getOptions(isLoadings));
        // TODO: 19/02/2022 : Vincent: Need to catch the error when PC does not have the firefox browser
    }

    @Override
    public MutableCapabilities getOptions(boolean... isLoadings) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.setAcceptInsecureCerts(true);
        firefoxOptions.setHeadless(Boolean.valueOf(HEADLESS));

        if (isLoadings.length > 0 && isLoadings[0])
            firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return firefoxOptions;
    }
}
