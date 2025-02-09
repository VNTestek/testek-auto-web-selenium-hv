package com.testek.projects.page.pages;

import com.testek.projects.common.BasePage;
import com.testek.study.lesson12.common.DriverManager;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.testek.consts.FrameConst.ProjectConfig;

/**
 * Implement the functions of the login page
 * {@link ProjectConfig#APP_URL} is the URL of the login page
 */
public class FormPage extends BasePage {
    public FormPage() {
        super();
        PageFactory.initElements(webDriver, this);
    }

    //***************** Init WebElement Object *****************/
    //region Init WebElement Object

    @FindBy(xpath = "//input[@id='username']")
    WebElement userNameEle;

    //endregion

    //***************** Init WebElement Object *****************/

    //region Basic Functions

    /* Enter the username */
    public FormPage enterUserName(String userName) {
        inputTextTo(userNameEle, "UserName", userName);
        return this;
    }

    //endregion

    //***************** Integration Functions *****************/
    //region Integration Functions
    public FormPage fillForm(String userName) {
        enterUserName(userName);
        return this;
    }

    //endregion

    //***************** Verify *****************/
    //region Verify

    public FormPage verifyFormPage() {

        return this;
    }

    //endregion

}
