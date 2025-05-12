package com.testek.study.lesson07;

import com.testek.study.common.Menu;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

/**
 * Class for Window interaction
 */
@Slf4j
public class ActionsAPITest {
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
     * Access website and interact with frame
     */
    @Test
    public void testAction() {
        // Access to the website
        gotoTestWebsite(Menu.WIDGETS, Menu.TOOL_TIPS);

        // Create a new Actions
        Actions mActions = new Actions(mWebDriver);

        // Hover to elements
        String hoverXPath = "//button[@test-id='tooltip-button-1']";
        WebElement hoverElement = mWebDriver.findElement(By.xpath(hoverXPath));
        mActions.moveToElement(hoverElement).click().build().perform();

        // Verify the hover. The elements had a new attribute "aria-describedby"
        String tooltipXPath = "//div[@test-id='tooltip-1']";
        WebElement hoverEle = mWebDriver.findElement(By.xpath(tooltipXPath));
       String  hoverText = hoverEle.getText();
        log.info("Tooltip text: {}", hoverText);
        waitForDebug(5000);

        // Click via Actions
        String clickXPath = "//div[@test-id='submenu-auto-complete']";
        WebElement btnAutoCompletedEle = mWebDriver.findElement(By.xpath(clickXPath));
        mActions.click(btnAutoCompletedEle).perform();
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
