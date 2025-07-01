package com.testek.study.lesson13.exercise.pages.pages;

import com.testek.consts.FrameConst;
import com.testek.driver.DriverManager;
import com.testek.projects.common.BasePage;
import com.testek.projects.dataprovider.model.LoginModel;
import com.testek.projects.pages.pages.HomePage;
import com.testek.study.lesson13.exercise.pages.objects.LoginObjects;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    private final LoginObjects loginObjects;

    public LoginPage(){
        webDriver = DriverManager.getDriver();
        PageFactory.initElements(webDriver, this);

        loginObjects = LoginObjects.getInstance();
    }
    public LoginPage goToLoginPage() {
        goToURL(FrameConst.AppConfig.APP_DOMAIN);
        return this;
    }


    //***************** Integration Functions *****************/
    //region Integration Functions

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

    //endregion

    //***************** Verify *****************/
    // region Verify


    public void verifyLoginPageDisplay() {
        assertEqualCondition(null, "Testek admin", webDriver.getTitle(),
                FrameConst.FailureHandling.STOP_ON_FAILURE, "Verify the page title");
    }


}
