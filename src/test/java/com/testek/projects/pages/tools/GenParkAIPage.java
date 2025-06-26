package com.testek.projects.pages.tools;

import com.testek.driver.DriverManager;
import com.testek.projects.common.BasePage;
import com.testek.utils.configloader.CaptureUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GenParkAIPage extends BasePage {

    public GenParkAIPage() {
        webDriver = DriverManager.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    //***************** Init WebElement Object *****************/
    //region Init WebElement Object

    @FindBy(xpath = "//button[contains(@class,'invite_page_content_button')]")
    WebElement inviteButtonEle;

    @FindBy(id = "loginWithEmailWrapper")
    WebElement loginWithEmailEle;

    @FindBy(id = "createAccount")
    WebElement createAccountEle;

    @FindBy(id = "email")
    WebElement emailEle;

    @FindBy(id = "emailVerificationControl_but_send_code")
    WebElement sendCodeEle;

    @FindBy(id = "emailVerificationCode")
    WebElement emailVerificationCodeEle;

    @FindBy(id = "emailVerificationControl_but_verify_code")
    WebElement verifyCodeEle;

    @FindBy(id = "newPassword")
    WebElement newPasswordEle;


    @FindBy(id = "reenterPassword")
    WebElement reenterPasswordEle;

    @FindBy(xpath = "//button[@id='continue']")
    WebElement continueEle;

    @FindBy(xpath = "//input[@placeholder='Enter phone number']")
    WebElement phoneNumberEle;

    @FindBy(xpath = "//button[text()='Get verification code']")
    WebElement getVerificationCodeEle;

    @FindBy(id = "verification_code")
    WebElement verificationCodeEle;

    @FindBy(xpath = "//button[text()='Claim Membership Benefits']")
    WebElement claimMembershipBenefitsEle;

    public GenParkAIPage clickInviteButton() {
        clickTo(inviteButtonEle);
        return this;
    }

    public GenParkAIPage clickLoginWithEmail() {
        clickTo(loginWithEmailEle);
        return this;
    }

    public GenParkAIPage clickCreateAccount() {
        clickTo(createAccountEle);
        return this;
    }

    public GenParkAIPage enterEmail(String email) {
        this.inputText(emailEle, "Email", email);
        return this;
    }


    public GenParkAIPage clickSendCode() {
        clickTo(sendCodeEle);
        return this;
    }

    public GenParkAIPage enterVerificationCode(String verificationCode) {
        this.inputText(emailVerificationCodeEle, "Verification Code", verificationCode);
        return this;
    }


    public GenParkAIPage clickVerifyCode() {
        clickTo(verifyCodeEle);
        return this;
    }

    public GenParkAIPage enterNewPassword(String newPassword) {
        this.inputText(newPasswordEle, "New Password", newPassword);
        return this;
    }

    public GenParkAIPage enterReenterPassword(String reenterPassword) {
        this.inputText(reenterPasswordEle, "Reenter Password", reenterPassword);
        return this;
    }

    public GenParkAIPage clickContinue() {
        clickTo(continueEle);
        return this;
    }

    public GenParkAIPage enterPhoneNumber(String phoneNumber) {
        this.inputText(phoneNumberEle, "Phone Number", phoneNumber);
        return this;
    }

    public GenParkAIPage clickGetVerificationCode() {
        clickTo(getVerificationCodeEle);
        return this;
    }

    public GenParkAIPage enterPhoneVerificationCode(String verificationCode) {
        this.inputText(verificationCodeEle, "Verification Code", verificationCode);
        return this;
    }

    public GenParkAIPage clickClaimMembershipBenefits() {
        clickTo(claimMembershipBenefitsEle);
        return this;
    }


    public void captureScreen(String phone, String email, String mailPrefix) {
        CaptureUtils.captureScreenshot(DriverManager.getDriver(), mailPrefix + "_" + phone + "_" + email);
    }

    public void captureScreen(String email, String mailPrefix) {
        CaptureUtils.captureScreenshot(DriverManager.getDriver(), mailPrefix + "_" + email);
    }

    @FindBy(id="MicrosoftAccountExchange")
    WebElement microsoftAccountExchangeEle;
    @FindBy(xpath = "//input[@name='loginfmt']")
    WebElement loginOutEle;

    @FindBy(id="idSIButton9")
    WebElement nextEle;

    @FindBy(xpath = "//input[@name='passwd']")
    WebElement passwordEle;

    @FindBy(xpath = "//button[@aria-label='Skip for now']")
    WebElement skipForNowEle;

    @FindBy(id = "acceptButton")
    WebElement acceptButtonEle;

    @FindBy(xpath = "//input[@name='ucaccept']")
    WebElement ucacceptEle;

    public GenParkAIPage clickMicrosoftAccountExchange() {
        clickTo(microsoftAccountExchangeEle);
        return this;
    }

    public GenParkAIPage enterLoginOut(String loginOut) {
        this.inputText(loginOutEle, "LoginOut", loginOut);
        return this;
    }

    public GenParkAIPage clickNext() {
        clickTo(nextEle);
        return this;
    }

    public GenParkAIPage enterPassword(String password) {
        this.inputText(passwordEle, "Password", password);
        return this;
    }

    public GenParkAIPage clickSkipForNow() {
        clickTo(skipForNowEle);
        return this;
    }

    public GenParkAIPage clickAcceptButton() {
        clickTo(acceptButtonEle);
        return this;
    }

    public GenParkAIPage clickUcaccept() {
        clickTo(ucacceptEle);
        return this;
    }

    @FindBy(xpath = "//span[contains(@class,'vti__flag')]")
    WebElement flagEle;

    @FindBy(xpath = "//ul[@role='listbox']/input")
    WebElement searchCountryEle;

    @FindBy(xpath = "//li[@role='option']//strong[text()='Colombia']")
    WebElement colombiaEle;

    public GenParkAIPage clickFlag() {
        clickTo(flagEle);
        return this;
    }

    public GenParkAIPage enterSearchCountry(String country) {
        this.inputText(searchCountryEle, "Search Country", country);
        return this;
    }

    public GenParkAIPage clickColombia() {
        clickTo(colombiaEle);
        return this;
    }


}
