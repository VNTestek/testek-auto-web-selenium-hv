package com.testek.projects.page.pages;

import com.testek.consts.FrameConst;
import com.testek.driver.DriverManager;
import com.testek.projects.common.BasePage;
import com.testek.projects.page.PageManagement;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.testek.consts.FrameConst.ProjectConfig;

/**
 * Implement the functions of the login page
 * {@link ProjectConfig#APP_URL} is the URL of the login page
 */
public class LoginPage extends BasePage {

    public LoginPage() {
        webDriver = DriverManager.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    //***************** Init WebElement Object *****************/
    //region Init WebElement Object

    @FindBy(id = "normal_login_username")
    WebElement userNameEle;

    @FindBy(id = "normal_login_password")
    WebElement passwordEle;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButtonEle;

    //endregion

    //***************** Init WebElement Object *****************/

    //region Basic Functions

    /* Go to the login page */
    public LoginPage goToLoginPage() {
        goToURL(ProjectConfig.APP_URL);
        return this;
    }

    /* Enter the username */
    public LoginPage enterUserName(String userName) {
        inputTextTo(userNameEle, "UserName", userName);
        return this;
    }

    /* Enter the password */
    public LoginPage enterPassword(String password) {
        inputTextTo(passwordEle, "Password", password);
        return this;
    }

    /* Click the login button */
    public HomePage clickLoginButton() {
        clickElement(loginButtonEle, "Login Button");
        return PageManagement.gotoHomePage();
    }

    //endregion

    //***************** Integration Functions *****************/
    //region Integration Functions

    /* Login  with userName and password */
    public void login(String userName, String password) {
        enterUserName(userName);
        enterPassword(password);
        clickLoginButton();
    }

    //endregion

    //***************** Verify *****************/
    //region Verify

    public LoginPage verifyLoginPageDisplay() {
        webDriver.getTitle();
        assertEqualCondition(null, "Testek admin", webDriver.getTitle(),
                FrameConst.FailureHandling.STOP_ON_FAILURE, "Verify the page title");
        return this;
    }


    //endregion

}
