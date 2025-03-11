package com.testek.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import static com.testek.consts.FrameConst.ProjectConfig.HEADLESS;

public class EdgeBrowserDriver extends BrowserDriver {
    @Override
    public WebDriver createDriver(boolean... isLoadings) {
        return new EdgeDriver((EdgeOptions) getOptions(isLoadings));
    }

    @Override
    public MutableCapabilities getOptions(boolean... isLoadings) {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.setAcceptInsecureCerts(true);
        if(HEADLESS) edgeOptions.addArguments("--headless");

        if (isLoadings.length > 0 && isLoadings[0])
            edgeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return edgeOptions;
    }
}
