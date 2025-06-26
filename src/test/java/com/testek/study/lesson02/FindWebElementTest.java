package com.testek.study.lesson02;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static java.lang.Thread.sleep;

/**
 * Class to find the Web Element and interact with
 *
 * @author: Vincent
 */
@Getter
public class FindWebElementTest {
    // The driver for interacting with the webpage
    private WebDriver webDriver;

    /**
     * Method will be executed before the class starts, will configure the Chrome Driver and initialize
     */
    @BeforeClass
    public void beforeClass() {
        /* WebDriverManager.getInstance(DriverManagerType.CHROME).setup(); */
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--max-window-size");
        chromeOptions.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize(); // Set the window size to maximum
    }

    /**
     * Method executed at the end of each class, will close all running chrome sessions - Debug Mode
     **/
    @AfterClass
    public void afterClass() {
        if (Objects.nonNull(webDriver)) webDriver.quit();
    }


    /**
     * Description: List all the basic methods to find the element
     * 1. Practice manual search on the website
     * 2. Develop methods to check if the element is identified or not?
     */
    @Test(enabled = true, description = "Find the element with basic search methods")
    public void findingElements() {
        /* Access to the website */
        gotoDemoTestWebsite();

        List<WebElement> elementList = webDriver.findElements(By.cssSelector("[class='interactive-section']"));
        List<WebElement> xpathElements = webDriver.findElements(By.xpath("//label[@class='interactive-section']"));

        /* Find the list of elements -> Check the ID of each element in the list */
        xpathElements.forEach(e -> {
            System.out.println(e.getAttribute("id"));
        });

        // region Find by id
        /* Find the element by ID */
        String idXpath = "//h4[@id='db-title-id']";
        String idCSS = "h4[id='db-title-id']";
        String idShortedCSS = "db-title-id";

        /* Apply the search for the object */
        WebElement lblTitleElement = webDriver.findElement(By.xpath(idXpath));
        lblTitleElement = webDriver.findElement(By.cssSelector(idCSS));
        lblTitleElement = webDriver.findElement(By.id(idShortedCSS));
        //endregion

        // region Find by name

        // Find the element by name
        String lblTitleXpath = "//h4[@name='db-title-name']";       // Locator theo XPath
        String lblTitleCSS = "h4[name='db-title-name']";            // Locator theo CSS

        // Apply the search for the object
        lblTitleElement = webDriver.findElement(By.xpath(lblTitleXpath));
        lblTitleElement = webDriver.findElement(By.cssSelector(lblTitleCSS));

        //endregion

        // region Find by class name

        // Find the element by class name
        lblTitleXpath = "//h4[@class='db-title-class']";     // Locator theo XPath
        lblTitleCSS = "h4[class='db-title-class']";          // Locator theo CSS
        String lblTitleShortCSS = ".db-title-class";          // Locator theo CSS
        String lblTitleShortedCSS = "db-title-class";            // Locator theo CSS (short)

        // Apply the search for the object
        lblTitleElement = webDriver.findElement(By.xpath(lblTitleXpath));
        lblTitleElement = webDriver.findElement(By.cssSelector(lblTitleCSS));
        lblTitleElement = webDriver.findElement(By.cssSelector(lblTitleShortCSS));
        lblTitleElement = webDriver.findElement(By.className(lblTitleShortedCSS));

        //endregion

        // region Find by tag name

        // Find the element by tag name
        String tagXPath = "//h4";        // Locator theo XPath
        String tagNameCSS = "h4";        // Locator theo CSS

        // Apply the search for the object
        List<WebElement> lblTitleElements = webDriver.findElements(By.xpath(tagXPath));
        lblTitleElements = webDriver.findElements(By.cssSelector(tagNameCSS));

        //endregion

        // region Switch to link tab

        String linksXPath = "//a[@href='https://discord.gg/jC7ZtmvuDX']";
        webDriver.findElement(By.xpath(linksXPath)).click();

        //endregion

        // region Find by link attribute

        String linkXPath = "//a[@href='https://discord.gg/jC7ZtmvuDX']";
        WebElement linkElement = webDriver.findElement(By.xpath(linkXPath));

        String linkCSS = "a[href='https://discord.gg/jC7ZtmvuDX']";
        linkElement = webDriver.findElement(By.cssSelector(linkCSS));

        String linkShortedCSS = "Testek - Kiểm thử thực chiến";
        linkElement = webDriver.findElement(By.linkText(linkShortedCSS));

        String partialLinkShortedCSS = "Testek";
        linkElement = webDriver.findElement(By.partialLinkText(partialLinkShortedCSS));

        // endregion
    }

    /**
     * Sleep for debugging
     *
     * @param milliseconds : waiting time (unit: milliseconds)
     */
    private void waitForDebug(long milliseconds) {
        try {
            sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Go to Test Website
     */
    private void gotoDemoTestWebsite() {
        String url = "https://testek.vn/lab/auto/web-elements/";
        webDriver.get(url);
    }
}
