package com.testek.projects.pages.pages;

import com.testek.consts.FrameConst.FailureHandling;
import com.testek.driver.DriverManager;
import com.testek.projects.common.BasePage;
import com.testek.projects.dataprovider.model.LoginModel;
import com.testek.projects.pages.objects.LoginObjects;
import org.openqa.selenium.support.PageFactory;

import static com.testek.consts.FrameConst.AppConfig;


/**
 * Implement the functions of the login page
 */
public class LoginPage extends BasePage {
    private final LoginObjects loginObjects;

    //Constructor
    public LoginPage() {
        webDriver = DriverManager.getDriver(); // muốn tương tác đc vs phần tử phải khởi tạo webDriver
        PageFactory.initElements(webDriver, this);

        loginObjects = LoginObjects.getInstance();
    }

    //***************** Init WebElement Object *****************/


    /* Go to the login page */
//    public LoginPage goToLoginPage() {
//        goToURL(AppConfig.APP_DOMAIN);
//        return this;
//    }


    //***************** Integration Functions *****************/
    //region Integration Functions
    //1. Kiểm tra có truy cập đúng website không ?
    public void verifyWebsite() {
        loginObjects.verifyAccessSuccess();
    }

    //2. Kiểm tra sau khi login thành công hay không ?
    /* Login  with userName and password */
    public HomePage login(String userName, String password) {
        HomePage homePage = loginObjects.inputUserName(userName)
                                        .inputPassword(password)
                                        .clickLoginButton();

        // Verify the home page after login
        homePage.verifyHomePage();
        return homePage;
    }

    /* Login  with userName and password */
    public HomePage login(LoginModel data) {
        return login(data.getUserName().getValue(), data.getPassword().getValue());
    }

    public void clickToForgotPass() {
        loginObjects.clickForgotPassButton();
    }

    public void clickToRegister() {
        loginObjects.clickRegisterButton();
    }

    //endregion

    //***************** Verify *****************/
    // region Verify


    public void verifyLoginPageDisplay() {
        assertEqualCondition(null, "Testek admin", webDriver.getTitle(),
                FailureHandling.STOP_ON_FAILURE, "Verify the page title");
    }




    //endregion
}
