package com.testek.study.lesson07;

import com.testek.study.common.Menu;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

/**
 * Class for Window interaction
 */
@Slf4j
public class FrameInteractionTest {
    private WebDriver mWebDriver;

    @BeforeMethod
    public void beforeTestMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
        mWebDriver = new ChromeDriver(chromeOptions);
    }

    @AfterMethod
    public void afterMethod() {
        if (mWebDriver != null) {
            mWebDriver.quit();
            mWebDriver = null;
        }
    }


    /**
     * Truy cap website and interact with frame
     */
    @Test
    public void testFrameNavigation() {
        gotoTestWebsite(Menu.ALERT_FRAME_AND_WINDOWS, Menu.FRAME);

        // Find the frame and verify
        String frameXPath = "//iframe[@id='large-frame']";
        String frameID = "large-frame";
        WebElement frameEle = mWebDriver.findElement(By.id(frameID));

        // Access to this frame
        mWebDriver.switchTo().frame(frameEle);

        WebElement childFrameEle = mWebDriver.findElement(By.tagName("h1"));
        String actChildFrameText = childFrameEle.getText();
        log.info("Current text: {}", actChildFrameText);

        //region Frame verification
        String expHeadingText = "This is a Large Frame";
        if(actChildFrameText.equals(expHeadingText)){
            log.info("The frame text:  {}", actChildFrameText);
        } else {
            log.info("The frame text is not same: actual: {}, expected: {}", actChildFrameText, expHeadingText);
        }
        //endregion

        // Back to page contents
        mWebDriver.switchTo().defaultContent();
        log.info("Back to the main page");
        waitForDebug(5000);
    }

    /**
     * Go to Test Website
     */
    private void gotoTestWebsite(Menu parent, Menu subMenu) {
        String url = "https://testek.vn/lab/auto/web-elements/";
        mWebDriver.get(url);

        String XPATH_MENU_FORMAT = "//div[@test-id='%s']";

        // Access: Form > [Student Form]
        WebElement mnuElementEle = mWebDriver.findElement(By.xpath(String.format(XPATH_MENU_FORMAT, parent.getName())));
        mnuElementEle.click();

        WebElement mnuEle = mWebDriver.findElement(By.xpath(String.format(XPATH_MENU_FORMAT, subMenu.getName())));
        mnuEle.click();

        waitForDebug(5000);
    }

    /**
     * Sleep for debugging
     *
     * @param milliseconds : number of milliseconds
     */
    private void waitForDebug(long milliseconds) {
        try {
            sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
