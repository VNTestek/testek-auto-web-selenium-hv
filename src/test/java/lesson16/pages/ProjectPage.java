package lesson16.pages;


import lesson16.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class ProjectPage extends BasePage {
    Logger logger = Logger.getLogger(ProjectPage.class.getName());
    WebDriver mWebDriver;
    WebDriverWait mWebDriverWait;

    public ProjectPage(WebDriver mWebDriver) {
        super(mWebDriver);
        this.mWebDriver = mWebDriver;
        mWebDriverWait = new WebDriverWait(mWebDriver, Duration.ofSeconds(10));
    }


    /**
     * Sua thong tin project
     *
     * @param data
     */
    public void editProject(int rowIndex, HashMap<String, String> data) {
        WebElement rowEle = getRowContentByIndex(rowIndex);
        WebElement editEle = rowEle.findElement(By.xpath(".//td//a[@title='Edit project']"));
        clickElement(editEle);

        // Edit info
        inputTextTo(By.xpath("//input[@name='title']"), "VAutomation - Demo " + System.currentTimeMillis());

        // Save
        clickElement(By.xpath("//button[@type='submit']"));
    }


    /**
     * Xoa project tai vi tri i
     *
     * @param rowIndex
     */
    public void deleteProject(int rowIndex) {
        WebElement rowEle = getRowContentByIndex(rowIndex);
        WebElement editEle = rowEle.findElement(By.xpath(".//td//a[@title='Delete project']"));
        clickElement(editEle);

        WebElement confirmEle = waitForElementVisible(By.id("confirmDeleteButton"));
        clickElement(confirmEle);

        // Verify
        WebElement message = waitForElementVisible(By.xpath("//div[@class='app-alert-message']"));
    }

    /**
     * Filter project
     * @param filterCol : Column can loc
     * @param filterVal : Gia tri can loc
     */
    public void filterProject(String filterCol, String filterVal) {
        switch (filterCol.toUpperCase()) {
            case "STATUS":
                filterWithStatusCol(filterVal);
                break;
        }

    }

    /**
     * Filter status
     * @param filterVal
     */
    private void filterWithStatusCol(String filterVal) {
        WebElement statusEle = findElement(By.xpath("//button[normalize-space()='Status']"));
        clickElement(statusEle);

        WebElement valueEle = waitForElementVisible(By.xpath(String.format("//li[@data-name='status' and @data-value='%s']", filterVal.toLowerCase())));
        clickElement(valueEle);
    }

    /**
     * Print project
     * @param data
     */
    public void printProject(HashMap<String, String> data) {
        String curWinId = mWebDriver.getWindowHandle();

        WebElement element = waitForElementVisible(By.xpath("//button[@aria-controls='project-table']//span[text()='Print']"));
        clickElement(element);

        mWebDriver.switchTo().window(curWinId);
    }

    /**
     * Download file
     * @param data
     */
    public void exportProject(HashMap<String, String> data) {
        WebElement element = waitForElementVisible(By.xpath("//button[@aria-controls='project-table']//span[text()='Excel']"));
        clickElement(element);
    }

    private WebElement getTableContent() {
        return findElement(By.id("project-table"));
    }

    /**
     * @param index: vi tri dong can lay trong bang: Bat dau tu 1
     * @return
     */
    private WebElement getRowContentByIndex(int index) {
        WebElement tableEle = getTableContent();
        List<WebElement> rows = tableEle.findElements(By.xpath("./tbody/tr"));

        index = Math.max(index, 1); // Validate index. If < 1 -> lay default 1
        index = Math.min(index, rows.size());
        return rows.get(index - 1);
    }

    /**
     * Click to [Add Client button]
     */
    private void clickAddClientButton() {
        String addXPath = "//a[@title='Add client']";
        clickElement(By.xpath(addXPath));
    }

    /**
     * Input text to textbox with placeholder params
     */
    private void inputTextToTextBoxWithPlaceHolder(String placeHolder, String value, String... xpaths) {
        String DYNAMIC_PLACEHOLDER_FORM = "//div[@id='ajaxModal']//*[@placeholder='%s']";
        if (xpaths.length > 0) DYNAMIC_PLACEHOLDER_FORM = xpaths[0];

        By txtByObj = By.xpath(String.format(DYNAMIC_PLACEHOLDER_FORM, placeHolder));
        inputTextTo(txtByObj, value);

    }
}
