package lession14.common;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class DriverManager {
    private static final ThreadLocal<WebDriver> mWebDriver = new ThreadLocal<>();

    public static void setWebDriver(WebDriver webDriver){
        DriverManager.mWebDriver.set(webDriver);
    }

    public static WebDriver getWebDriver(){
        return  mWebDriver.get();
    }

    public  static void quit(){
        if(Objects.nonNull(getWebDriver())){
            getWebDriver().quit();
            mWebDriver.remove();
        }
    }
}
