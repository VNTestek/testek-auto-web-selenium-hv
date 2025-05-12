package com.testek.study.lesson07.exercise;

import com.testek.study.common.Menu;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Objects;

import static java.lang.Thread.sleep;

@Getter
@Setter
public class SolutionTest {
    // The driver for interacting with the webpage
    private WebDriver webDriver;

    /**
     * Method will be executed before each class, configure Chrome Driver and initialize
     */
    @BeforeClass
    public void beforeClass() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--max-window-size");
        chromeOptions.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(chromeOptions);
    }

    /**
     * Method will be executed after each class, will close all running chrome sessions - Debug Mode
     **/
    @AfterClass
    public void afterClass() {
        if (Objects.nonNull(webDriver)) webDriver.quit();
    }

    /**
     * URL: <a href="https://testek.vn/lab/auto/web-elements">...</a>
     * Access to Elements navigation
     */
    @Test(description = "Element Interaction")
    public void testInteraction() {
        gotoTestWebsite();

        // Define the locator format then apply to elements
    }

    /**
     * Go to Test Website
     */
    private void gotoTestWebsite() {
        String url = "https://testek.vn/lab/auto/web-elements/";
        webDriver.get(url);

        String XPATH_MENU_FORMAT = "//div[@test-id='%s']";

        // Access: Form > [Student Form]
        WebElement mnuElementEle = webDriver.findElement(By.xpath(String.format(XPATH_MENU_FORMAT, Menu.FORM.getName())));
        mnuElementEle.click();

        WebElement mnuEle = webDriver.findElement(By.xpath(String.format(XPATH_MENU_FORMAT, Menu.STUDENT_FORM.getName())));
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
