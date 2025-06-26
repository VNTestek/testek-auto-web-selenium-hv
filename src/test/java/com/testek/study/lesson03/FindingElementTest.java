package com.testek.study.lesson03;

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

public class FindingElementTest {

    // The driver for interacting with the webpage
    private WebDriver webDriver;

    /**
     * This method will be executed before the first test method in the current class is invoked.
     */
    @BeforeClass
    public void beforeClass() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--max-window-size");
        chromeOptions.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize(); // Set the window size to maximum
    }

    /**
     * This method will be executed after all test methods in the current class have been run.
     **/
    @AfterClass
    public void afterClass() {
        if (Objects.nonNull(webDriver)) webDriver.quit();
    }

    /**
     * List the advanced search methods
     * 1. Practice manual search on the website
     * 2. Develop methods to check if the element is identified or not?
     **/
    @Test(description = "Find the element with advanced search methods")
    public void findAdvanceLocatorWithXPath() {
        // Access the website
        gotoTestWebsite();

        // region Basic expression
        String lblTitleXPath = "//h4[@test-id='db-title']";
        WebElement lblTitleElement = webDriver.findElement(By.xpath(lblTitleXPath));

        WebElement currentFormNode = lblTitleElement.findElement(By.xpath("."));
        System.out.printf("Current node: id - %s%n", currentFormNode.getAttribute("test-id"));

        WebElement parentFormNode = lblTitleElement.findElement(By.xpath(".."));
        System.out.printf("Parent node: class - %s%n", parentFormNode.getAttribute("test-id"));
        //endregion


        //region Type of Functions
        lblTitleXPath = "//h4[contains(@name,'db-title')]";
        lblTitleElement = webDriver.findElement(By.xpath(lblTitleXPath));

        String lblTitleCSS = "h4[name*='db-title']";
        lblTitleElement = webDriver.findElement(By.cssSelector(lblTitleCSS));

        String lblTitleStartWithXpath = "//h4[starts-with(@name,'db-')]";
        lblTitleElement = webDriver.findElement(By.xpath(lblTitleStartWithXpath));

        String lblTitleStartWithCSS = "h4[name^='db-']";
        lblTitleElement = webDriver.findElement(By.cssSelector(lblTitleStartWithCSS));

        String lblTitlePositionXPath = "//h4[starts-with(@test-id,'db-')][position()=1]";
        lblTitleElement = webDriver.findElement(By.xpath(lblTitlePositionXPath));

        String lblTitleTextXPath = "//h4[text()='Đào tạo API Testing, Performance Testing, Automation Testing']";
        String lblTitleNormalizeXPath = "//h4[normalize-space()='Đào tạo API Testing, Performance Testing, Automation Testing']";
        lblTitleElement = webDriver.findElement(By.xpath(lblTitleTextXPath));
        lblTitleElement = webDriver.findElement(By.xpath(lblTitleNormalizeXPath));

        // endregion

        // region Find with ancestor & descendant
        String lblTitleAncestorXPath = "//h4[@test-id='db-title']/ancestor::div[@test-id='about-me-grid']";
        lblTitleElement = webDriver.findElement(By.xpath(lblTitleAncestorXPath));

        String lblTitleDescendantXPath = "//div[@test-id='about-me-grid']/descendant::h4[@test-id='db-title']";
        lblTitleElement = webDriver.findElement(By.xpath(lblTitleDescendantXPath));
        //endregion

        // region Find with preceding & following
        String lblTitlePrecedingXPath = "//h4[@test-id ='db-title']/preceding::div[@test-id ='sidebar-root']";
        lblTitleElement = webDriver.findElement(By.xpath(lblTitlePrecedingXPath));

        String lblTitleFollowingXPath = "//h2[@test-id ='about-me-title']/following::h4[@test-id ='db-title']";
        lblTitleElement = webDriver.findElement(By.xpath(lblTitleFollowingXPath));
        //endregion

        // region Sibling Element
        String lblTitleFollowingSiblingXpath = "//h4[@test-id ='db-website']/following-sibling::h4[@test-id ='db-facebook']";
        lblTitleElement = webDriver.findElement(By.xpath(lblTitleFollowingSiblingXpath));

        String lblTitlePrecedingSiblingXpath = "//h4[@test-id ='db-website']/preceding-sibling::h4[@test-id ='db-title']";
        lblTitleElement = webDriver.findElement(By.xpath(lblTitlePrecedingSiblingXpath));
        //endregion

        // region Child - Parent
        String childXPath = "//div[@test-id ='about-me-grid-item']/child::h4[@test-id ='db-website']";
        String parentXPath = "//h4[@test-id ='db-website']/parent::div";
        List<WebElement> childElements = webDriver.findElements(By.xpath(childXPath));
        WebElement parentElement = webDriver.findElement(By.xpath(parentXPath));
        // endregion

        // region Root
        String rootXPath = "//h4[@test-id ='db-website']";
        parentElement = webDriver.findElement(By.xpath(rootXPath)).findElement(By.xpath(".."));
        //endregion
        System.out.println("End!");
        //endregion
    }

    @Test(description = "Find the element with advanced search methods")
    public void findAdvanceLocatorWithCSS() {

    }

    /**
     * Create a xpath expression
     *
     * @param format : Expression format
     * @param values : Values for the expression
     * @return : The xpath
     */
    private String getStringXPath(String format, String... values) {
        return String.format(format, (Object) values);
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

    /**
     * Go to Test Website
     */
    private void gotoTestWebsite() {
       String baseURL = "https://testek.vn/lab/auto/web-elements/";
        webDriver.get(baseURL);
    }
}
