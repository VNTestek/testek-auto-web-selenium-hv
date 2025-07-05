package com.testek.projects.pages.objects;

import com.testek.consts.FrameConst;
import com.testek.projects.pages.locator.SupplierLocator;
import lombok.Getter;
import org.openqa.selenium.WebElement;

public class CreateSupplierObjects extends BaseObjects {
    @Getter
    public static CreateSupplierObjects instance = new CreateSupplierObjects();

    private final SupplierLocator supplierLocator;

    private CreateSupplierObjects() {
        supplierLocator = SupplierLocator.getInstance();
    }

    public WebElement findTitle() {
        return findWebElement(supplierLocator.getTitle());
    }

    public WebElement findSupName() {
        return findWebElement(supplierLocator.getTxtSupName());
    }

    public WebElement findSupPhone() {
        return findWebElement(supplierLocator.getTxtSupPhone());
    }

    public WebElement findSupContact() {
        return findWebElement(supplierLocator.getTxtSupContact());
    }

    public WebElement findSupCountry() {
        return findWebElement(supplierLocator.getTxtSupCountry());
    }

    public WebElement findSupCity() {
        return findWebElement(supplierLocator.getTxtSupCity());
    }

    public WebElement findSupAddress() {
        return findWebElement(supplierLocator.getTxtSupAddress());
    }

    public WebElement findSupPostalCode() {
        return findWebElement(supplierLocator.getTxtSupPostalCode());
    }

    public WebElement findBtnAddMore() {
        return findWebElement(supplierLocator.getBtnAddMore());
    }

    public WebElement findTxtSupId() {
        return findWebElement(supplierLocator.getTxtSupId());
    }

    public WebElement findTextAreaError() {
        return findWebElement(supplierLocator.getTextAreaError());
    }

    public WebElement findMsgAddSupSuccess() {
        return findWebElement(supplierLocator.getPopUpAddSupSuccess());
    }

    //Kiểm tra truy cập Nhà cung cấp đúng không ?
    public void verifyTitleCreateSupplier() {
        WebElement lblMessage = findTitle();
        assertTrueCondition(lblMessage, lblMessage.isDisplayed(), FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "Access wrong page");
        verifyElementTextEqual(lblMessage, getLanguageValue("SupplierAddNewTitle"));
    }

    public CreateSupplierObjects inputSupName(String supName) {
        this.inputText(findSupName(), "Supplier Name", supName);
        return this;
    }

    public CreateSupplierObjects inputSupPhone(String supPhone) {
        this.inputText(findSupPhone(), "Supplier Phone", supPhone);
        return this;
    }

    public CreateSupplierObjects inputSupContact (String supConact) {
        this.inputText(findSupContact(), "Supplier Contact", supConact);
        return this;
    }

    public CreateSupplierObjects inputSupCountry (String supCountry) {
        this.inputText(findSupCountry(), "Supplier Country", supCountry);
        return this;
    }

    public CreateSupplierObjects inputSupCity (String supCity) {
        this.inputText(findSupCity(), "Supplier City", supCity);
        return this;
    }

    public CreateSupplierObjects inputSupAddress (String supAddress) {
        this.inputText(findSupAddress(), "Supplier Address", supAddress);
        return this;
    }

    public CreateSupplierObjects inputSupPostalCode (String supPostalCode) {
        this.inputText(findSupPostalCode(), "Supplier Postal Code", supPostalCode);
        return this;
    }

    public CreateSupplierObjects clickBtnAddMore () {
        this.clickTo(findBtnAddMore(), "Button Add More");
        return this;
    }

    public CreateSupplierObjects clearSupName() {
        this.clearText(findSupName());
        return this;
    }

    public CreateSupplierObjects clearSupPhone() {
        this.clearText(findSupPhone());
        return this;
    }

    public CreateSupplierObjects clearSupContact () {
        this.clearText(findSupContact());
        return this;
    }

    public CreateSupplierObjects clearSupCountry () {
        this.clearText(findSupCountry());
        return this;
    }

    public CreateSupplierObjects clearSupCity () {
        this.clearText(findSupCity());
        return this;
    }

    public CreateSupplierObjects clearSupAddress () {
        this.clearText(findSupAddress());
        return this;
    }

    public CreateSupplierObjects clearSupPostalCode () {
        this.clearText(findSupPostalCode());
        return this;
    }

    //Verify create supplier successfully
    public void verifySuccessMessageDisplayed() {
        WebElement lblMessage = findMsgAddSupSuccess();
        assertTrueCondition(lblMessage, lblMessage.isDisplayed(), FrameConst.FailureHandling.CONTINUE_ON_FAILURE, "The success message is not displayed after adding a supplier");
        verifyElementTextEqual(lblMessage, getLanguageValue("SupplierCreateSuccessMessage"));
    }
}
