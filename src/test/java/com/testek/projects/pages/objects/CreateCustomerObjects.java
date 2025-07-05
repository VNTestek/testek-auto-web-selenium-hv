package com.testek.projects.pages.objects;

import com.testek.consts.FrameConst;
import com.testek.projects.pages.PageManagement;
import com.testek.projects.pages.locator.CustomerLocator;
import com.testek.projects.pages.locator.SupplierLocator;
import com.testek.projects.pages.pages.CreateCustomerPage;
import lombok.Getter;
import org.openqa.selenium.WebElement;

public class CreateCustomerObjects extends BaseObjects {
    @Getter
    public static CreateCustomerObjects instance = new CreateCustomerObjects();

    private final CustomerLocator customerLocator;

    private CreateCustomerObjects() {
        customerLocator = CustomerLocator.getInstance();
    }

    public WebElement findTitle() {
        return findWebElement(customerLocator.getTitle());
    }

    public WebElement findName() {
        return findWebElement(customerLocator.getTxtName());
    }

    public WebElement findPhone() {
        return findWebElement(customerLocator.getTxtPhone());
    }

    public WebElement findEmail() {
        return findWebElement(customerLocator.getTxtEmail());
    }

    public WebElement findContact() {
        return findWebElement(customerLocator.getTxtContact());
    }

    public WebElement findCountry() {
        return findWebElement(customerLocator.getTxtCountry());
    }

    public WebElement findCity() {
        return findWebElement(customerLocator.getTxtCity());
    }

    public WebElement findAddress() {
        return findWebElement(customerLocator.getTxtAddress());
    }

    public WebElement findPostalCode() {
        return findWebElement(customerLocator.getTxtPostalCode());
    }

    public WebElement findBtnAdd() {
        return findWebElement(customerLocator.getBtnAdd());
    }

    public WebElement findTxtCusId() {
        return findWebElement(customerLocator.getTxtCusId());
    }

    public WebElement findTextAreaError() {
        return findWebElement(customerLocator.getTextAreaError());
    }

    public WebElement findMsgAddCusSuccess() {
        return findWebElement(customerLocator.getPopUpAddSupSuccess());
    }

    //Kiểm tra truy cập man them moi khach hang đúng không ?
    public void verifyTitleCreateCustomer() {
        WebElement lblMessage = findTitle();
        assertTrueCondition(lblMessage, lblMessage.isDisplayed(), FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "Access wrong page");
        verifyElementTextEqual(lblMessage, getLanguageValue("CustomerAddNewTitle"));
    }

    public CreateCustomerObjects inputName(String name) {
        this.inputText(findName(), "Customer Name", name);
        return this;
    }

    public CreateCustomerObjects inputPhone(String phone) {
        this.inputText(findPhone(), "Customer Phone", phone);
        return this;
    }

    public CreateCustomerObjects inputEmail (String email) {
        this.inputText(findEmail(), "Customer Email", email);
        return this;
    }

    public CreateCustomerObjects inputContact (String contact) {
        this.inputText(findContact(), "Customer Contact", contact);
        return this;
    }

    public CreateCustomerObjects inputCountry (String country) {
        this.inputText(findCountry(), "Customer Country", country);
        return this;
    }

    public CreateCustomerObjects inputCity (String City) {
        this.inputText(findCity(), "Customer City", City);
        return this;
    }

    public CreateCustomerObjects inputAddress (String address) {
        this.inputText(findAddress(), "Customer Contact", address);
        return this;
    }

    public CreateCustomerObjects inputPostalCode (String postalCode) {
        this.inputText(findPostalCode(), "Customer Contact", postalCode);
        return this;
    }

    public CreateCustomerObjects clickBtnAdd () {
        this.clickTo(findBtnAdd(), "Button Add");
        return this;
    }

    //Verify create supplier successfully
    public void verifySuccessMessageDisplayed() {
        WebElement lblMessage = findMsgAddCusSuccess();
        assertTrueCondition(lblMessage, lblMessage.isDisplayed(), FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The success message is not displayed after adding a customer");
        verifyElementTextEqual(lblMessage, getLanguageValue("CustomerCreateSuccessMessage"));
    }
}
