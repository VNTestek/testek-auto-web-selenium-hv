package lession16.pages;

import lession16.common.BasePage;
import lession16.pages.ClientPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class HomePage extends BasePage {
    Logger logger = Logger.getLogger(HomePage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;
    public HomePage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }

    /**
     * Access Client Page
     * @return
     */
    public ClientPage gotoClientsPage() {
        logger.info("Click Client Element");
        String clientXPath ="//a[@href='https://rise.fairsketch.com/clients']";
        //WebElement clientEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(clientXPath)));
        clickElement(By.xpath(clientXPath));

        return new ClientPage(mWebDriver);
    }

    /**
     * Access Project Page
     * @return
     */
    public ProjectPage gotoProjectPage() {
        logger.info("Click  Element");
        String clientXPath ="//a[@href='https://rise.fairsketch.com/projects/all_projects']";
        //WebElement clientEle = mWebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(clientXPath)));
        clickElement(By.xpath(clientXPath));

        return new ProjectPage(mWebDriver);
    }
}
